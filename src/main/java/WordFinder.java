import java.io.File;
import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.Date;
import java.util.List;
import java.util.Map;

public interface WordFinder extends Remote {
    String checkAuthorization(String login, String password) throws RemoteException;
    String checkDirectory(File directory) throws RemoteException;
    Map<String, Integer> findTheWord(File directory, String keyword) throws RemoteException;
    String addRequest(String request, String documentName, String userName) throws RemoteException;
    List<String> changes(File directory, String login) throws RemoteException;
    String lasLogin(String login, Date lastLogin) throws RemoteException;
}
