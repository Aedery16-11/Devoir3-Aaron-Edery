package sio.devoir3sio1b.Model;

public class Ticket
{
    private int idTicket;
    private String nomTicket;
    private String dateTicket;
    private String etatTicket;

    public Ticket(int idTicket, String nomTicket, String dateTicket, String etatTicket) {
        this.idTicket = idTicket;
        this.nomTicket = nomTicket;
        this.dateTicket = dateTicket;
        this.etatTicket = etatTicket;
    }

    public int getIdTicket() {
        return idTicket;
    }

    public String getNomTicket() {
        return nomTicket;
    }

    public String getDateTicket() {
        return dateTicket;
    }

    public String getEtatTicket() {
        return etatTicket;
    }
}
