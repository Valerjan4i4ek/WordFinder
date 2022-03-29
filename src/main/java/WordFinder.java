import java.io.File;
import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.Map;

public interface WordFinder extends Remote {
    String checkAuthorization(String login, String password) throws RemoteException;
    String checkDirectory(File directory) throws RemoteException;
    Map<String, Integer> findTheWord(File directory, String keyword) throws RemoteException;
}
