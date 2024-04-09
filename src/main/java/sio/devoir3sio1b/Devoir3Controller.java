package sio.devoir3sio1b;

import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import sio.devoir3sio1b.Model.User;
import sio.devoir3sio1b.Services.ServiceUser;
import sio.devoir3sio1b.Tools.ConnexionBDD;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;

public class Devoir3Controller implements Initializable
{
    @FXML
    private TextField txtLogin;
    @FXML
    private Button btnConnexion;
    @FXML
    private Label txtErreur;
    @FXML
    private PasswordField txtMotDePasse;
    ServiceUser serviceUser;
    String login;
    String mdp;
    User user;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        try {
            ConnexionBDD cnx = new ConnexionBDD();
            login = txtLogin.getText();
            mdp = txtMotDePasse.getText();
            serviceUser = new ServiceUser();
            //user = new User(1, "Gilbert", "admin");
            serviceUser.verifierIdentifiants(login, mdp);
            user = new User(user.getIdUser(), user.getNomUser(), user.getStatutUser());

        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @FXML
    public void btnConnexionClicked(Event event) throws SQLException, IOException, ClassNotFoundException {



        // A vous de compléter ici avec les erreurs éventuelles de saisie
        // A vous de vérifier si l'utilisateur existe avec le login et le mot de passe

        if (txtLogin.getText().isEmpty())
        {
            txtErreur.setText("Saisir votre login");
        }

//        if (login == "" || login == null)
//        {
//            txtErreur.setText("Saisir votre login");
//        }
//        else if (mdp == "" || mdp == null)
//        {
//            txtErreur.setText("Saisir votre mot de passe");
//        }



        else if(user.getStatutUser().equals("admin"))
        {
                FXMLLoader fxmlLoader = new FXMLLoader(Devoir3Application.class.getResource("admin-view.fxml"));
                Scene scene = new Scene(fxmlLoader.load());
                Stage stage = new Stage();
                stage.setTitle("Administrateur");
                stage.setScene(scene);
                stage.show();
        }
        else
        {
                FXMLLoader fxmlLoader = new FXMLLoader(Devoir3Application.class.getResource("user-view.fxml"));
                Scene scene = new Scene(fxmlLoader.load());
                UserController userController = fxmlLoader.getController();
                userController.initDatas(user);
                Stage stage = new Stage();
                stage.setTitle("Utilisateur");
                stage.setScene(scene);
                stage.show();
        }
//        if (Integer.parseInt(login) != Integer.parseInt(String.valueOf(serviceUser.verifierIdentifiants(login, mdp))) || Integer.parseInt(mdp) != Integer.parseInt(String.valueOf(serviceUser.verifierIdentifiants(login, mdp))));
//        {
//            txtErreur.setText("Identifiants incorrects");
//        }


    }
}