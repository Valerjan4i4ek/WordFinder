import java.io.File;
import java.rmi.RemoteException;
import java.util.*;

public class RemoteWordFinderServer implements WordFinder{
    MySQLClass sql = new MySQLClass();
    List<User> listUsers;
    List<Documents> listDocuments;
    List<CustomerRequest> listRequest;
    List<UserLogin> listUserLogin;
    int countAuthorization;
    int countDocuments;
    int countRequest;
    int countUserLogin;

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
        List<String> list = sql.checkFileName();
        File[] files = directory.listFiles();

        if(files != null){
            for(File file : files){
                if (!file.isDirectory()){
                    incrementDocuments();
                    Date date = new Date();
                    if(!list.contains(file.getName())){
                        sql.addDocuments(new Documents(countDocuments, file.getName(), date));
                    }
                }
            }
        }
        else{
            return "ololo ni4ogo nema";
        }
        return "";
    }

    @Override
    public List<String> changes(File directory, String login) throws RemoteException {
//        List<Documents> list = sql.checkDocuments();
//        File[] files = directory.listFiles();
//        List<String> listNames = new ArrayList<>();
//
//        if(files != null){
//            for(File file : files){
//                if(!file.isDirectory()){
//                    for (int i = 0; i < list.size(); i++) {
//                        if(file.getName().equals(list.get(i).getName())){
//                            if(file.lastModified() > list.get(i).getDate().getTime()){
//                                listNames.add(file.getName());
//                            }
//                        }
//                    }
//                }
//            }
//        }
//        return listNames;

        List<UserLogin> listLogins = sql.checkUserLogin();
        File[] files = directory.listFiles();
        List<String> listNames = new ArrayList<>();

        if(files != null){
            for(File file : files){
                if(!file.isDirectory()){
                    for (int i = listLogins.size()-1; i > 0; i--) {
                        if(listLogins.get(i).getLogin().equals(login)){
                            if(file.lastModified() > listLogins.get(i).getLastLogin().getTime()){
                                listNames.add(file.getName());
                            }
                        }

                    }
                }
            }
        }
        return listNames;
    }

    @Override
    public String lasLogin(String login, Date lastLogin) throws RemoteException {
          incrementUserLogin();
          sql.addUserLogin(new UserLogin(countUserLogin, login, lastLogin));
          return "";
    }

    @Override
    public Map<String, Integer> findTheWord(File directory, String keyword) throws RemoteException {
        Counter counter = new Counter(directory, keyword);
        Map<String, Integer> map = counter.finder();

        return map;
    }

    @Override
    public String addRequest(String request, String documentName, String userName) throws RemoteException {
        List<Documents> documentsList = sql.checkDocuments();
        List<User> userList = sql.checkAuthorization2();

        if(documentsList != null && !documentsList.isEmpty() && userList != null && !userList.isEmpty()){
            for (int i = 0; i < documentsList.size(); i++) {
                for (int j = 0; j < userList.size(); j++) {
                    if(documentsList.get(i).getName().equals(documentName) && userList.get(j).getUserName().equals(userName)){
                        incrementRequest();
                        sql.addRequests(new CustomerRequest(countRequest, request, documentsList.get(i).getId(), userList.get(j).getId()));
                    }
                }
            }
        }
        return "";
    }

    public void incrementRequest(){
        listRequest = sql.checkRequest();
        if(listRequest != null && !listRequest.isEmpty()){
            countRequest = listRequest.get(listRequest.size()-1).getId();
            countRequest++;
        }
        else{
            countRequest++;
        }
    }

    public void incrementUserLogin(){
        listUserLogin = sql.checkUserLogin();
        if(listUserLogin != null && !listUserLogin.isEmpty()){
            countUserLogin = listUserLogin.get(listUserLogin.size()-1).getId();
            countUserLogin++;
        }
        else{
            countUserLogin++;
        }
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
