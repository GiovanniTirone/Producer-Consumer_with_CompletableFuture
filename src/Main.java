import java.nio.Buffer;
import java.nio.IntBuffer;

public class Main {
    public static void main(String[] args) {
        int maxInts = 10;
        int buffer [] = new int[10];
        Semaphore empty = new Semaphore(maxInts,maxInts);
        Semaphore full = new Semaphore(maxInts,0);
        Semaphore mutex = new Semaphore(1,1);
        Producer producer = new Producer();
        Consumer consumer = new Consumer();

        while(true) {
            empty.getWait().thenCompose((s) -> mutex.getWait().thenApply((t) -> {
                try {
                    producer.addDataToBuffer(full.getCurrentPos(), buffer);
                } catch (Exception e) {
                    e.printStackTrace();
                }
                return null;
            }).thenCompose((x) -> mutex.getSignal().thenCompose((y) -> {
                full.getSignal();
                return null;
            })));


            full.getWait().thenCompose((s) -> mutex.getWait().thenApply((t) -> {
                try {
                    consumer.readData(full.getCurrentPos(), buffer);
                } catch (Exception e) {
                    e.printStackTrace();
                }
                return null;
            }).thenCompose((x) -> mutex.getSignal().thenCompose((y) -> {
                empty.getSignal();
                return null;
            })));
        }
    }
}