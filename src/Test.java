import java.util.concurrent.CompletableFuture;

public class Test {
    public static void main(String[] args) throws InterruptedException {
        int maxInts = 10;
        int buffer[] = new int[10];

        CompletableFuture cf = new CompletableFuture();

        Semaphore empty = new Semaphore(maxInts, maxInts);
        Semaphore full = new Semaphore(maxInts, 0);
        Semaphore mutex = new Semaphore(1, 1);
        Producer producer = new Producer();
        Consumer consumer = new Consumer();


        while (true) {

            cf.runAsync(empty.getWait())
                    .thenRunAsync(mutex.getWait())
                    .thenRun(producer.addDataToBufferRun(full.getCurrentInt(),buffer))
                    .thenRun(mutex.getSignal())
                    .thenRun(full.getSignal());


            cf.runAsync(full.getWait())
                    .thenRunAsync(mutex.getWait())
                    .thenRun(consumer.readDataFromBufferRun(full.getCurrentInt(),buffer))
                    .thenRun(mutex.getSignal())
                    .thenRun(empty.getSignal());

        }

    }



}