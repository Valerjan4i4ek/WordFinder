import java.lang.reflect.InvocationTargetException;
import java.sql.*;
import java.sql.Date;
import java.util.*;

public class MySQLClass {

    public MySQLClass(){
        baseCreate();
        tableAuthorizationCreate();
        tableDocumentsCreate();
        tableRequestsCreate();
        tableUserLoginCreate();
    }

    public Connection getConnection(String dbName) throws SQLException, ClassNotFoundException, NoSuchMethodException, IllegalAccessException, InvocationTargetException, InstantiationException {
        String url = "jdbc:mysql://localhost/" + ((dbName != null)? (dbName) : (""));
        String username = "root";
        String password = "1234";
        Class.forName("com.mysql.cj.jdbc.Driver").getDeclaredConstructor().newInstance();

        return DriverManager.getConnection(url, username, password);
    }

    public void baseCreate(){
        try{
            Connection conn = null;
            Statement st = null;

            try{
                conn = getConnection(null);
                st = conn.createStatement();
                st.executeUpdate("CREATE DATABASE IF NOT EXISTS WordFinder");
            }
            finally {
                try{
                    if(conn != null){
                        conn.close();
                    }
                } catch (Exception e){
                    e.printStackTrace();
                }
                try{
                    if(st != null){
                        st.close();
                    }
                } catch (Exception e){
                    e.printStackTrace();
                }
            }
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
    }

    public void tableAuthorizationCreate(){
        try{
            Connection conn = null;
            Statement st = null;

            try{
                conn = getConnection("WordFinder");
                st = conn.createStatement();
                st.executeUpdate("CREATE TABLE IF NOT EXISTS WordFinder.authorization " +
                        "(id INT NOT NULL, login VARCHAR(20) NOT NULL, password VARCHAR(20) NOT NULL)");
            }
            finally {
                try{
                    if(conn != null){
                        conn.close();
                    }
                } catch (Exception e){
                    e.printStackTrace();
                }
                try{
                    if(st != null){
                        st.close();
                    }
                } catch (Exception e){
                    e.printStackTrace();
                }
            }
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
    }

    public void tableDocumentsCreate(){
        try{
            Connection conn = null;
            Statement st = null;

            try{
                conn = getConnection("WordFinder");
                st = conn.createStatement();
                st.executeUpdate("CREATE TABLE IF NOT EXISTS WordFinder.documents " +
                        "(id INT NOT NULL, name VARCHAR(20) NOT NULL, date DATE NOT NULL)");
            }finally {
                try{
                    if(conn != null){
                        conn.close();
                    }
                } catch (Exception e){
                    e.printStackTrace();
                }
                try{
                    if(st != null){
                        st.close();
                    }
                } catch (Exception e){
                    e.printStackTrace();
                }
            }
        } catch (Exception e)
        {
            e.printStackTrace();
        }
    }

    public void tableUserLoginCreate(){
        try{
            Connection conn = null;
            Statement st = null;

            try{
                conn = getConnection("WordFinder");
                st = conn.createStatement();
                st.executeUpdate("CREATE TABLE IF NOT EXISTS WordFinder.userLogin " +
                        "(id INT NOT NULL, login VARCHAR(20) NOT NULL, lastLogin TIMESTAMP NOT NULL)");
            } finally {
                try{
                    if(conn != null){
                        conn.close();
                    }
                } catch (Exception e){
                    e.printStackTrace();
                }
                try{
                    if(st != null){
                        st.close();
                    }
                } catch (Exception e){
                    e.printStackTrace();
                }
            }
        } catch (Exception e)
        {
            e.printStackTrace();
        }
    }

    public void tableRequestsCreate(){
        try{
            Connection conn = null;
            Statement st = null;

            try{
                conn = getConnection("WordFinder");
                st = conn.createStatement();
                st.executeUpdate("CREATE TABLE IF NOT EXISTS WordFinder.requests " +
                        "(id INT NOT NULL, request VARCHAR(20) NOT NULL, documentId INT NOT NULL, userId INT NOT NULL)");
            } finally {
                try{
                    if(conn != null){
                        conn.close();
                    }
                } catch (Exception e){
                    e.printStackTrace();
                }
                try{
                    if(st != null){
                        st.close();
                    }
                } catch (Exception e){
                    e.printStackTrace();
                }
            }
        } catch (Exception e)
        {
            e.printStackTrace();
        }
    }

    public void addAuthorization(User user){
        try{
            Connection conn = null;
            PreparedStatement ps = null;

            try{
                conn = getConnection("WordFinder");
                ps = conn.prepareStatement("INSERT INTO authorization (id, login, password) VALUES (?, ?, ?)");
                ps.setInt(1, user.getId());
                ps.setString(2, user.getUserName());
                ps.setString(3, user.getUserPassword());
                ps.executeUpdate();
            } finally {
                try{
                    if(conn != null){
                        conn.close();
                    }
                } catch (Exception e){
                    e.printStackTrace();
                }
                try{
                    if(ps != null){
                        ps.close();
                    }
                } catch (Exception e){
                    e.printStackTrace();
                }
            }
        } catch (Exception e){
            e.printStackTrace();
        }
    }

    public void addRequests(CustomerRequest request){
        try{
            Connection conn = null;
            PreparedStatement ps = null;

            try{
                conn = getConnection("WordFinder");
                ps = conn.prepareStatement("INSERT INTO requests (id, request, documentId, userId) VALUES (?, ?, ?, ?)");
                ps.setInt(1, request.getId());
                ps.setString(2, request.getRequest());
                ps.setInt(3, request.getDocumentId());
                ps.setInt(4, request.getUserId());
                ps.executeUpdate();
            } finally {
                try{
                    if(conn != null){
                        conn.close();
                    }
                } catch (Exception e){
                    e.printStackTrace();
                }
                try{
                    if(ps != null){
                        ps.close();
                    }
                } catch (Exception e){
                    e.printStackTrace();
                }
            }
        } catch (Exception e){
            e.printStackTrace();
        }
    }

    public void addUserLogin(UserLogin userLogin){
        try{
            Connection conn = null;
            PreparedStatement ps = null;

            try{
                conn = getConnection("WordFinder");
                ps = conn.prepareStatement("INSERT INTO userLogin (id, login, lastLogin) VALUES (?, ?, ?)");
                ps.setInt(1, userLogin.getId());
                ps.setString(2, userLogin.getLogin());
                ps.setTimestamp(3, userLogin.getLastLogin());
                ps.executeUpdate();
            } finally {
                try{
                    if(conn != null){
                        conn.close();
                    }
                } catch (Exception e){
                    e.printStackTrace();
                }
                try{
                    if(ps != null){
                        ps.close();
                    }
                } catch (Exception e){
                    e.printStackTrace();
                }
            }
        } catch (Exception e){
            e.printStackTrace();
        }
    }

    public void addDocuments(Documents documents){
        try{
            Connection conn = null;
            PreparedStatement ps = null;

            try{
                conn = getConnection("WordFinder");
                ps = conn.prepareStatement("INSERT INTO documents (id, name, date) VALUES (?, ?, ?)");
                ps.setInt(1, documents.getId());
                ps.setString(2, documents.getName());
                ps.setDate(3, new java.sql.Date(documents.getDate().getTime()));
                ps.executeUpdate();
            }finally {
                try{
                    if(conn != null){
                        conn.close();
                    }
                } catch (Exception e){
                    e.printStackTrace();
                }
                try{
                    if(ps != null){
                        ps.close();
                    }
                } catch (Exception e){
                    e.printStackTrace();
                }
            }
        } catch (Exception e){
            e.printStackTrace();
        }
    }

    public List<String> checkFileName(){
        List<String> list = new ArrayList<>();
        try{
            Connection conn = null;
            PreparedStatement ps = null;
            ResultSet rs = null;

            try{
                conn = getConnection("WordFinder");
                String query = "SELECT * FROM documents";
                ps = conn.prepareStatement(query);
                rs = ps.executeQuery();

                while (rs.next()){
                    try{
                        String name = rs.getString("name");
                        list.add(name);
                    } catch (Exception e){
                        e.printStackTrace();
                    }
                }
            } finally {
                try {
                    if (conn != null) {
                        conn.close();
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
                try {
                    if (ps != null) {
                        ps.close();
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
                try {
                    if (rs != null) {
                        rs.close();
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        } catch (Exception e){
            e.printStackTrace();
        }
        return list;
    }

    public List<Integer> checkUserLogin(){
        List<Integer> list = new LinkedList<>();

        try{
            Connection conn = null;
            PreparedStatement ps = null;
            ResultSet rs = null;

            try{
                conn = getConnection("WordFinder");
                String query = "SELECT id FROM userLogin";
                ps = conn.prepareStatement(query);
                rs = ps.executeQuery();

                while (rs.next()){
                    int id = rs.getInt("id");
                    list.add(id);
                }
            } finally {
                try{
                    if(conn != null){
                        conn.close();
                    }
                } catch (Exception e){
                    e.printStackTrace();
                }
                try{
                    if(ps != null){
                        ps.close();
                    }
                } catch (Exception e){
                    e.printStackTrace();
                }
                try{
                    if(rs != null){
                        rs.close();
                    }
                } catch (Exception e){
                    e.printStackTrace();
                }
            }
        } catch (Exception e){
            e.printStackTrace();
        }
        return list;
    }

    public List<UserLogin> checkUserLogin(String log){
        List<UserLogin> list = new LinkedList<>();

        try{
            Connection conn = null;
            PreparedStatement ps = null;
            ResultSet rs = null;

            try{
                conn = getConnection("WordFinder");
                String query = "SELECT * FROM userLogin WHERE login = ?";
                ps = conn.prepareStatement(query);
                ps.setString(1, log);
                rs = ps.executeQuery();

                while (rs.next()){
                    try{
                        int id = rs.getInt("id");
                        String login = rs.getString("login");
                        Timestamp lastLogin = rs.getTimestamp("lastLogin");
                        UserLogin userLogin = new UserLogin(id, login, lastLogin);
                        list.add(userLogin);
                    } catch (Exception e){
                        e.printStackTrace();
                    }
                }
            } finally {
                try{
                    if(conn != null){
                        conn.close();
                    }
                } catch (Exception e){
                    e.printStackTrace();
                }
                try{
                    if(ps != null){
                        ps.close();
                    }
                } catch (Exception e){
                    e.printStackTrace();
                }
                try{
                    if(rs != null){
                        rs.close();
                    }
                } catch (Exception e){
                    e.printStackTrace();
                }
            }
        } catch (Exception e){
            e.printStackTrace();
        }

        return list;
    }

    public String checkUser(String login, String password) {
        try {
            Connection conn = null;
            PreparedStatement ps = null;
            ResultSet rs = null;

            try {
                conn = getConnection("WordFinder");
                String query = "SELECT * FROM authorization WHERE login = ?";
                ps = conn.prepareStatement(query);
                ps.setString(1, login);
                rs = ps.executeQuery();

                if (rs.next()) {
                    if(password.equals(rs.getString("password"))){
                        return  "AUTHORIZATION IS OK";
                    }
                    else{
                        return  "INCORRECT PASSWORD";
                    }
                }
                else{
                    return "NEW REGISTRATION";
                }
            } finally {
                try {
                    if (conn != null) {
                        conn.close();
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
                try {
                    if (ps != null) {
                        ps.close();
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
                try {
                    if (rs != null) {
                        rs.close();
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }catch (Exception e){
            e.printStackTrace();
        }
        return "";
    }

    List<Integer> checkRequest(){
        List<Integer> list = new LinkedList<>();

        try{
            Connection conn = null;
            PreparedStatement ps = null;
            ResultSet rs = null;

            try{
                conn = getConnection("WordFinder");
                String query = "SELECT id FROM requests";
                ps = conn.prepareStatement(query);
                rs = ps.executeQuery();

                while (rs.next()){
                    int id = rs.getInt("id");
                    list.add(id);
                }
            } finally {
                try{
                    if(conn != null){
                        conn.close();
                    }
                } catch (Exception e){
                    e.printStackTrace();
                }
                try{
                    if(ps != null){
                        ps.close();
                    }
                } catch (Exception e){
                    e.printStackTrace();
                }
                try{
                    if(rs != null){
                        rs.close();
                    }
                } catch (Exception e){
                    e.printStackTrace();
                }
            }
        } catch (Exception e){
            e.printStackTrace();
        }

        return list;
    }

    public List<Integer> checkDocumentsId(){
        List<Integer> list = new LinkedList<>();

        try{
            Connection conn = null;
            PreparedStatement ps = null;
            ResultSet rs = null;

            try{
                conn = getConnection("WordFinder");
                String query = "SELECT id FROM documents";
                ps = conn.prepareStatement(query);
                rs = ps.executeQuery();

                while (rs.next()){
                    int id = rs.getInt("id");
                    list.add(id);
                }
            } finally {
                try{
                    if(conn != null){
                        conn.close();
                    }
                } catch (Exception e){
                    e.printStackTrace();
                }
                try{
                    if(ps != null){
                        ps.close();
                    }
                } catch (Exception e){
                    e.printStackTrace();
                }
                try{
                    if(rs != null){
                        rs.close();
                    }
                } catch (Exception e){
                    e.printStackTrace();
                }
            }
        } catch (Exception e){
            e.printStackTrace();
        }

        return list;
    }

    public List<Documents> checkDocuments(){
        List<Documents> list = new LinkedList<>();

        try{
            Connection conn = null;
            PreparedStatement ps = null;
            ResultSet rs = null;

            try{
                conn = getConnection("WordFinder");
                String query = "SELECT * FROM documents";
                ps = conn.prepareStatement(query);
                rs = ps.executeQuery();

                while (rs.next()){
                    try{
                        int id = rs.getInt("id");
                        String name = rs.getString("name");
                        Date date = rs.getDate("date");
                        Documents documents = new Documents(id, name, date);
                        list.add(documents);
                    } catch (Exception e){
                        e.printStackTrace();
                    }
                }
            } finally {
                try{
                    if(conn != null){
                        conn.close();
                    }
                } catch (Exception e){
                    e.printStackTrace();
                }
                try{
                    if(ps != null){
                        ps.close();
                    }
                } catch (Exception e){
                    e.printStackTrace();
                }
                try{
                    if(rs != null){
                        rs.close();
                    }
                } catch (Exception e){
                    e.printStackTrace();
                }
            }
        } catch (Exception e){
            e.printStackTrace();
        }

        return list;
    }

    public List<Integer> checkUserId(){
        List<Integer> list = new LinkedList<>();

        try{
            Connection conn = null;
            PreparedStatement ps = null;
            ResultSet rs = null;

            try{
                conn = getConnection("WordFinder");
                String query = "SELECT id FROM authorization";
                ps = conn.prepareStatement(query);
                rs = ps.executeQuery();
            } finally {
                try{
                    if(conn != null){
                        conn.close();
                    }
                } catch (Exception e){
                    e.printStackTrace();
                }
                try{
                    if(ps != null){
                        ps.close();
                    }
                } catch (Exception e){
                    e.printStackTrace();
                }
                try{
                    if(rs != null){
                        rs.close();
                    }
                } catch (Exception e){
                    e.printStackTrace();
                }
            }
        } catch (Exception e){
            e.printStackTrace();
        }

        return list;
    }

    public List<User> checkAuthorization(){
        List<User> list = new LinkedList<>();

        try{
            Connection conn = null;
            PreparedStatement ps = null;
            ResultSet rs = null;

            try{
                conn = getConnection("WordFinder");
                String query = "SELECT * FROM authorization";
                ps = conn.prepareStatement(query);
                rs = ps.executeQuery();

                while (rs.next()){
                    try{
                        int id = rs.getInt("id");
                        String login = rs.getString("login");
                        String password = rs.getString("password");
                        User user = new User(id, login, password);
                        list.add(user);
                    } catch (Exception e){
                        e.printStackTrace();
                    }
                }
            } finally {
                try{
                    if(conn != null){
                        conn.close();
                    }
                } catch (Exception e){
                    e.printStackTrace();
                }
                try{
                    if(ps != null){
                        ps.close();
                    }
                } catch (Exception e){
                    e.printStackTrace();
                }
                try{
                    if(rs != null){
                        rs.close();
                    }
                } catch (Exception e){
                    e.printStackTrace();
                }
            }
        } catch (Exception e){
            e.printStackTrace();
        }

        return list;
    }
}
