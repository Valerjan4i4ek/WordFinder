import java.util.Date;

public class UserLogin {
    private int id;
    private String login;
    private Date lastLogin;

    public UserLogin(int id, String login, Date lastLogin) {
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

    public Date getLastLogin() {
        return lastLogin;
    }

    public void setLastLogin(Date lastLogin) {
        this.lastLogin = lastLogin;
    }
}
