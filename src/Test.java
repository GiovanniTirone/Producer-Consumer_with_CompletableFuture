import buffer.Buffer;
import person.Consumer;
import person.Producer;
import semaphores.Semaphore;

import java.util.concurrent.CompletableFuture;

public class Test {
    public static void main(String[] args) throws InterruptedException {
        int maxInts = 5;
        Buffer buffer = new Buffer(maxInts);

        CompletableFuture cf = new CompletableFuture();

        Semaphore empty = new Semaphore(maxInts, maxInts);
        Semaphore full = new Semaphore(maxInts, 0);
        Semaphore mutex = new Semaphore(1, 1);
        Producer producer = new Producer(false,2000,buffer);
        Consumer consumer = new Consumer(false,1000,buffer);


        while (true) {

            cf.runAsync(producer.getReadyToAct())
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