package sio.devoir3sio1b.Services;

import sio.devoir3sio1b.Model.Ticket;
import sio.devoir3sio1b.Tools.ConnexionBDD;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class ServiceEtat {
    private Connection cnx;
    private PreparedStatement ps;
    private ResultSet rs;

    public ServiceEtat() {
        cnx = ConnexionBDD.getCnx();
    }


    // A vous de jouer
    public ArrayList<String> getAllEtat() throws SQLException {
        ArrayList<String> etat = new ArrayList<>();
        ps = cnx.prepareStatement("SELECT DISTINCT etats.nomEtat\n" +
                "FROM etats\n" +
                "INNER JOIN tickets on etats.idEtat = tickets.numEtat");
        rs = ps.executeQuery();
        while (rs.next()) {
            etat.add(rs.getString(1));
        }
        return etat;


    }

    public int getIdEtat(String nomEtat) throws SQLException {
        int numEtat = 0;
        ps = cnx.prepareStatement("SELECT idEtat\n" +
                "FROM etats\n" +
                "WHERE nomEtat = ?");
        ps.setString(1, nomEtat);
        rs = ps.executeQuery();
        rs.next();
        numEtat = rs.getInt(1);
        ps.close();
        rs.close();
        return numEtat;

    }
}