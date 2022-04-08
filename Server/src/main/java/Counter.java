import java.io.File;
import java.io.IOException;
import java.util.*;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;
import java.util.concurrent.FutureTask;

public class Counter {
    private File directory;
    private String keyword;
    private int count;
    private Map<String, Integer> map = new HashMap<>();

    public Counter(File directory, String keyword) {
        this.directory = directory;
        this.keyword = keyword;
    }

    public Map<String, Integer> finder() {
        count = 0;
        File[] files = directory.listFiles();
        List<Counter> results = new ArrayList<>();

        if(files != null){
            for(File file : files){
                if(file.isDirectory()){
                    Counter counter = new Counter(file, keyword);
                    results.add(counter);
                }
                else{
                    int a = search(file);
                    count += a;
                }

            }
        }
        writeToConsole();
        return map;
    }

    // Метод определения количества вхождений ключевых слов в каждый файл
    public int search(File file) {
        try {
            try (Scanner in = new Scanner(file)) {
                int keywordInFileCount = 0;
                while (in.hasNextLine()) {
                    String line = in.nextLine();
                    for(int i = 0; i < line.length(); i++) {
                        if(line.indexOf(keyword, i) == i) {
                            keywordInFileCount++;
                        }
                    }
                }
//                System.out.println(file.getName() + " совпадений в файле " + keywordInFileCount);
                if(keywordInFileCount > 0){
                    map.put(file.getName(), keywordInFileCount);
                }

                return keywordInFileCount;
            }
        } catch (IOException e) {
            return 0;
        }
    }

    public void writeToConsole(){
        map.entrySet().stream()
                .sorted(Map.Entry.<String, Integer>comparingByValue().reversed());
        for(Map.Entry<String, Integer> entry : map.entrySet()){
            System.out.println(entry.getKey() + " совпадений в файле " + entry.getValue());
        }
    }
}


