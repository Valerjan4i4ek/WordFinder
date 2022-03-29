import java.io.File;
import java.rmi.RemoteException;
import java.util.*;

public class RemoteWordFinderServer implements WordFinder{
    MySQLClass sql = new MySQLClass();
    List<User> listUsers;
    List<Documents> listDocuments;
    int countAuthorization;
    int countDocuments;

    @Override
    public String checkAuthorization(String login, String password) throws RemoteException {
        String s = sql.checkUser(login, password);
        if(s != null && !s.isEmpty()){
            if(s.equals("NEW REGISTRATION")){
                System.out.println(s);
                incrementAuthorization();
                sql.addAuthorization(new User(countAuthorization, login, password));
                return s;
            }
            else{
                System.out.println(s);
                return s;
            }
        }
        else {
            incrementAuthorization();
            sql.addAuthorization(new User(countAuthorization, login, password));
            System.out.println("new registration");
            return "new registration";
        }
    }

    @Override
    public String checkDirectory(File directory) throws RemoteException {
        File[] files = directory.listFiles();

        if(files != null){
            for(File file : files){
                if (!file.isDirectory()){
                    incrementDocuments();
                    Date date = new Date();
                    sql.addDocuments(new Documents(countDocuments, file.getName(), date));
                }
            }
        }
        else{
            return "ololo ni4ogo nema";
        }
        return "";
    }

    @Override
    public Map<String, Integer> findTheWord(File directory, String keyword) throws RemoteException {
        Counter counter = new Counter(directory, keyword);
//        counter.finder();
        Map<String, Integer> map = counter.finder();

        return map;
    }

    public void incrementAuthorization(){
        listUsers = sql.checkAuthorization2();
        if(listUsers != null && !listUsers.isEmpty()){
            countAuthorization = listUsers.get(listUsers.size()-1).getId();
            countAuthorization++;
        }
        else{
            countAuthorization++;
        }
    }

    public void incrementDocuments(){
        listDocuments = sql.checkDocuments();
        if(listDocuments != null && !listDocuments.isEmpty()){
            countDocuments = listDocuments.get(listDocuments.size()-1).getId();
            countDocuments++;
        }
        else{
            countDocuments++;
        }
    }
}
