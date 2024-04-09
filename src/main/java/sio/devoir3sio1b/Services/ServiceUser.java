package sio.devoir3sio1b.Services;

import sio.devoir3sio1b.Model.Ticket;
import sio.devoir3sio1b.Model.User;
import sio.devoir3sio1b.Tools.ConnexionBDD;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class ServiceUser
{
    private Connection cnx;
    private PreparedStatement ps;
    private ResultSet rs;

    public ServiceUser()
    {
        cnx = ConnexionBDD.getCnx();
    }

    // Cette méthode est opérationnelle : à ne pas modifier !
    // Elle permet de vérifier si un utilisateur existe avec le login et le mot de passe
    // passé en paramètre
    // La méthode retourne null si on ne trouve pas l'utilisateur
    // Si on le trouve on retourne l'utilisateur
    public User verifierIdentifiants(String login, String mdp) throws SQLException {
        User user = null;
        ps = cnx.prepareStatement("select idUser,nomUser, statutUser from users where loginUser = ? and pwdUser = ?");
        ps.setString(1,login);
        ps.setString(2,mdp);
        rs = ps.executeQuery();
        if(rs.next())
        {
            user = new User(Integer.parseInt(rs.getString(1)),rs.getString(2),rs.getString(3));
        }
        ps.close();
        rs.close();
        return user;
    }


    // A vous de jouer
    public ArrayList<User> getAllUsers() throws SQLException {
        ArrayList<User> lesUsers = new ArrayList<>();
        ps = cnx.prepareStatement("select users.idUser, users.nomUser, users.statutUser from users");
        rs = ps.executeQuery();
        while (rs.next())
        {
            User user = new User(rs.getInt(1), rs.getString(2), rs.getString(3));
            lesUsers.add(user);
        }
        ps.close();
        rs.close();
        return lesUsers;

    }


}
