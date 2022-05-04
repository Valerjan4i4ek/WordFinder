import java.io.File;
import java.rmi.Remote;
import java.rmi.RemoteException;
import java.sql.Timestamp;
import java.util.Date;
import java.util.List;
import java.util.Map;

public interface WordFinder extends Remote {
    String checkAuthorization(String login, String password) throws RemoteException;
    String checkDirectory() throws RemoteException;
    Map<String, Integer> findTheWord(String keyword) throws RemoteException;
    String addRequest(String request, String documentName, String userName) throws RemoteException;
    List<String> changes(String login) throws RemoteException;
    String lastLogin(String login, Timestamp lastLogin) throws RemoteException;
}
