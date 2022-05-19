import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.sql.Timestamp;
import java.time.Instant;
import java.util.Date;
import java.util.List;
import java.util.Map;

public class Client {

    public static final String UNIQUE_BINDING_NAME = "server.WordFinder";
    static BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
    private static User user;
//    private static String directory = "Server/documents";
    static Registry registry;
    static WordFinder wordFinder;

    static {
        try {
            registry = LocateRegistry.getRegistry("127.0.0.1", 2732);
            wordFinder = (WordFinder) registry.lookup(UNIQUE_BINDING_NAME);
        } catch (RemoteException e) {
            e.printStackTrace();
        } catch (NotBoundException e) {
            e.printStackTrace();
        }
    }

//    public static WordFinder connection() throws IOException, NotBoundException, RemoteException{
//
//        return wordFinder;
//    }

    public Client() throws  IOException, NotBoundException{
    }

    public static void main(String[] args) throws IOException, NotBoundException, RemoteException {
        checkDirectory();
        authorization();
    }

    public static void authorization() throws IOException, NotBoundException, RemoteException{

        System.out.println("Enter you login and password");
        String login = reader.readLine();
        String password = reader.readLine();
        user = new User(login, password);
        String result = wordFinder.checkAuthorization(login, password);
        System.out.println(result);
        if(result.equals("INCORRECT PASSWORD")){
            authorization();
        }
        else{
            wordFinder.lastLogin(login, new Timestamp(System.currentTimeMillis()));
            changes();
            System.out.println();
            findTheWord();
            findTheWordAgain();
        }
    }
    public static void checkDirectory() throws IOException, NotBoundException, RemoteException{
        wordFinder.checkDirectory();
    }

    public static void changes() throws IOException, NotBoundException, RemoteException{
        List<String> list = wordFinder.changes(user.getUserName());
        if(list != null && !list.isEmpty()){
            for (int i = 0; i < list.size(); i++) {
                System.out.println(list.get(i) + " был изменен");
            }
        }
        else if(list.size() == 0){
            System.out.println(" ");
        }
        else{
            System.out.println();
            System.out.println("изменений в файлах нет");
            System.out.println();
        }
    }

    public static void findTheWord() throws IOException, NotBoundException, RemoteException{
        System.out.println("Введите слово:");
        String keyword = reader.readLine();

        Map<String, Integer> map = wordFinder.findTheWord(keyword);
        if(map != null && !map.isEmpty()){
            for(Map.Entry<String, Integer> entry : map.entrySet()){
                System.out.println(entry.getKey() + " совпадений в файле " + entry.getValue());
                wordFinder.addRequest(keyword, entry.getKey(), user.getUserName());
            }
        }
    }

    public static void findTheWordAgain() throws IOException, NotBoundException, RemoteException{
        System.out.println("Вы желаете выполнить повторный поиск? Y or N");
        String s = reader.readLine();

        if(s.equals("Y")){
            findTheWord();
            findTheWordAgain();
        }
        else if(s.equals("N")){
            System.exit(1);
        }
    }
}
