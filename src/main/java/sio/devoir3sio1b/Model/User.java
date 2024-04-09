package sio.devoir3sio1b.Model;

public class User
{
    private int idUser;
    private String nomUser;
    private String statutUser;

    public User(int idUser, String nomUser, String statutUser) {
        this.idUser = idUser;
        this.nomUser = nomUser;
        this.statutUser = statutUser;
    }

    public int getIdUser() {
        return idUser;
    }

    public String getNomUser() {
        return nomUser;
    }

    public String getStatutUser() {
        return statutUser;
    }
}
