import java.util.ArrayList;
import java.util.List;

public class Main {
    public static void main(String[] args) {

        List<Integer> numbers = new ArrayList<>();
        for (int i = 1; i < 10000; i++ ){
            numbers.add(i);
        }

        int chunkSize = numbers.size() / 4;

        List<List<Integer>> chunks = new ArrayList<>();
        for (int i = 0; i < 4; i++){
            int start = i * chunkSize;
            int end = (i + 1) * chunkSize;

            if (i == 3){
                end = numbers.size();
            }
            List<Integer> subList = numbers.subList(start, end);
            chunks.add(subList);
        }

        List<Integer> oddNumbers = new ArrayList<>();
        List<Integer> evenNumbers = new ArrayList<>();
        List<Thread> threads = new ArrayList<>();

        for (int i = 0; i < 4; i++){
            List<Integer> chunk = chunks.get(i);
            Thread thread = new Thread(new NumberProcessor(chunk, evenNumbers, oddNumbers));
            threads.add(thread);
            thread.start();

        }

        try {
            for (Thread thread : threads){
                thread.join();
            }
        } catch (InterruptedException e){
            System.out.println(e.getMessage());
        }

        System.out.println("Çift Sayılar: " + evenNumbers);
        System.out.println("Tek Sayılar: " + oddNumbers);


    }
}