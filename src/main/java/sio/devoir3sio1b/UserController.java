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
import sio.devoir3sio1b.Tools.ConnexionBDD;

import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;

public class UserController
{
    User user;
    @javafx.fxml.FXML
    private TableColumn tcNomTicket;
    @javafx.fxml.FXML
    private TableColumn tcDateTicket;
    @javafx.fxml.FXML
    private TableColumn tcNumeroTicket;
    @javafx.fxml.FXML
    private TableColumn tcEtatTicket;
    @javafx.fxml.FXML
    private TableView tvTickets;
    @javafx.fxml.FXML
    private ComboBox cboEtats;
    @javafx.fxml.FXML
    private Button btnModifier;
    ConnexionBDD connexionBDD;
    ServiceTicket serviceTicket;
    int idUser;

    // La méthode initDatas remplace la méthode "initialize"
    public void initDatas(User unUSer) throws SQLException, ClassNotFoundException {
        // Cette ligne ne doit pas être modifiée
        // On récupère ici l'utilisateur connecté
        user = unUSer;

        // A complèter à partir d'ici
        connexionBDD = new ConnexionBDD();
        serviceTicket = new ServiceTicket();
        idUser = unUSer.getIdUser();
        tcNomTicket.setCellValueFactory(new PropertyValueFactory<>("nomTicket"));
        tcDateTicket.setCellValueFactory(new PropertyValueFactory<>("dateTicket"));
        tcEtatTicket.setCellValueFactory(new PropertyValueFactory<>("etatTicket"));
        tcNumeroTicket.setCellValueFactory(new PropertyValueFactory<>("idTicket"));
        tvTickets.setItems(FXCollections.observableList(serviceTicket.getAllTicketsByUser(idUser)));
    }


    @javafx.fxml.FXML
    public void btnModifierClicked(Event event) throws SQLException {

    }
}
