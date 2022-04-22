import java.sql.Timestamp;
import java.util.Date;

public class UserLogin {
    private int id;
    private String login;
    private Timestamp lastLogin;

    public UserLogin(int id, String login, Timestamp lastLogin) {
        this.id = id;
        this.login = login;
        this.lastLogin = lastLogin;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public Timestamp getLastLogin() {
        return lastLogin;
    }

    public void setLastLogin(Timestamp lastLogin) {
        this.lastLogin = lastLogin;
    }
}
