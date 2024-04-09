package sio.devoir3sio1b.Services;

import sio.devoir3sio1b.Model.Ticket;
import sio.devoir3sio1b.Tools.ConnexionBDD;
import java.sql.Connection;
import java.sql.*;
import java.time.LocalDate;
import java.util.ArrayList;

public class ServiceTicket
{
    private Connection cnx;
    private PreparedStatement ps;
    private ResultSet rs;

    public ServiceTicket()
    {
        cnx = ConnexionBDD.getCnx();
    }

    // A vous de jouer
//    public ArrayList<Ticket> getAllTickets() throws SQLException {
//        ArrayList<Ticket> lesTickets = new ArrayList<>();
//        ps = cnx.prepareStatement("SELECT idTicket, nomTicket, dateTicket, etats.nomEtat\n" +
//                "FROM tickets\n" +
//                "INNER JOIN etats ON tickets.numEtat = etats.idEtat\n");
//        rs = ps.executeQuery();
//        while (rs.next())
//        {
//            Ticket ticket = new Ticket(rs.getInt(1), rs.getString(2), rs.getString(3), rs.getString(4));
//            lesTickets.add(ticket);
//        }
//        ps.close();
//        rs.close();
//        return lesTickets;
//    }

    public ArrayList<Ticket> getAllTicketsByUser(int idUser) throws SQLException {
        ArrayList<Ticket> lesTickets = new ArrayList<>();
        ps = cnx.prepareStatement("SELECT idTicket, nomTicket, dateTicket, etats.nomEtat\n" +
                "FROM tickets\n" +
                "INNER JOIN etats ON tickets.numEtat = etats.idEtat\n" +
                "INNER JOIN users ON tickets.numUser = users.idUser\n" +
                "WHERE users.idUser = ?");
        ps.setInt(1, idUser);
        rs = ps.executeQuery();
        while (rs.next())
        {
            Ticket ticket = new Ticket(rs.getInt(1), rs.getString(2), rs.getString(3), rs.getString(4));
            lesTickets.add(ticket);
        }
        ps.close();
        rs.close();
        return lesTickets;
    }


    // Cette méthode est opérationnelle : à ne pas modifier !
    public void insererNouveauTicket(String nomTicket, int numUser, int numEtat) throws SQLException {
        ps = cnx.prepareStatement("insert into tickets values(null,?,?,?,?)");
        ps.setString(1,nomTicket);
        ps.setDate(2, Date.valueOf(LocalDate.now()));
        ps.setInt(3,numUser);
        ps.setInt(4,numEtat);
        ps.executeUpdate();
        ps.close();
    }
}
