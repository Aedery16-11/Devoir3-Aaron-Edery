package sio.devoir3sio1b;

import javafx.collections.FXCollections;
import javafx.event.Event;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import sio.devoir3sio1b.Model.Ticket;
import sio.devoir3sio1b.Model.User;
import sio.devoir3sio1b.Services.ServiceEtat;
import sio.devoir3sio1b.Services.ServiceTicket;
import sio.devoir3sio1b.Services.ServiceUser;
import sio.devoir3sio1b.Tools.ConnexionBDD;

import java.net.URL;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.ResourceBundle;

public class AdminController implements Initializable
{
    @javafx.fxml.FXML
    private TableColumn tcNomTicket;
    @javafx.fxml.FXML
    private TableColumn tcDateTicket;
    @javafx.fxml.FXML
    private TableView<User> tvUsers;
    @javafx.fxml.FXML
    private TableColumn tcNomUser;
    @javafx.fxml.FXML
    private TableColumn tcNumeroTicket;
    @javafx.fxml.FXML
    private TableColumn tcEtatTicket;
    @javafx.fxml.FXML
    private TableColumn tcNumeroUser;
    @javafx.fxml.FXML
    private TableView<Ticket> tvTickets;
    @javafx.fxml.FXML
    private TextField txtNomTicket;
    @javafx.fxml.FXML
    private Button btnInserer;
    @javafx.fxml.FXML
    private ComboBox cboEtats;
    ServiceUser serviceUser;
    ConnexionBDD connexionBDD;
    ServiceEtat serviceEtat;
    int idUser;
    ServiceTicket ticket;
    String nomEtat;
    int numEtat;

    @javafx.fxml.FXML
    public void tvUsersClicked(Event event) throws SQLException {
        idUser = tvUsers.getSelectionModel().getSelectedItem().getIdUser();
        tvTickets.setItems(FXCollections.observableList(ticket.getAllTicketsByUser(idUser)));
    }

    @Override
    public void initialize(URL location, ResourceBundle resources)
    {
        try {
            connexionBDD = new ConnexionBDD();
            ticket = new ServiceTicket();

            serviceEtat = new ServiceEtat();
            serviceUser = new ServiceUser();
            tcNomUser.setCellValueFactory(new PropertyValueFactory<>("nomUser"));
            tcNumeroUser.setCellValueFactory(new PropertyValueFactory<>("idUser"));
            tvUsers.setItems(FXCollections.observableList(serviceUser.getAllUsers()));

            tcNomTicket.setCellValueFactory(new PropertyValueFactory<>("nomTicket"));
            tcDateTicket.setCellValueFactory(new PropertyValueFactory<>("dateTicket"));
            tcEtatTicket.setCellValueFactory(new PropertyValueFactory<>("etatTicket"));
            tcNumeroTicket.setCellValueFactory(new PropertyValueFactory<>("idTicket"));

            cboEtats.setItems(FXCollections.observableList(serviceEtat.getAllEtat()));

        } catch (SQLException e) {
            throw new RuntimeException(e);
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    @javafx.fxml.FXML
    public void btnInsererClicked(Event event) throws SQLException {
       nomEtat = tvTickets.getSelectionModel().getSelectedItem().getEtatTicket();
       numEtat = serviceEtat.getIdEtat(nomEtat);
        if (tvUsers.getSelectionModel().getSelectedItem() == null)
        {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setHeaderText("Choix de l'utilisateur");
            alert.setContentText("Sélectionner un utilsateur");
            alert.showAndWait();
        }
        else if (txtNomTicket.getText() == "" || txtNomTicket.getText() ==null)
        {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setHeaderText("Erreur de saisie");
            alert.setContentText("Saisir un nom de tcicket");
            alert.showAndWait();
        }
        else if (cboEtats.getSelectionModel().getSelectedItem() == null)
        {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setHeaderText("Choix de l'état");
            alert.setContentText("Sélectionner un état");
            alert.showAndWait();
        }
        else
        {
           ticket.insererNouveauTicket(txtNomTicket.getText(), tvUsers.getSelectionModel().getSelectedItem().getIdUser(), numEtat );
        }
    }
}
