package tests;

import buffer.Buffer;
import person.Consumer;
import person.Producer;
import semaphores.Semaphore;
import java.util.concurrent.CompletableFuture;

public class TestProducerConsumer {
    public static void main(String[] args) throws InterruptedException {
        int maxInts = 5;
        Buffer buffer = new Buffer(maxInts);

        Semaphore empty = new Semaphore("empty",maxInts, maxInts);
        Semaphore full = new Semaphore("full",maxInts, 0);
        Semaphore mutex = new Semaphore("mutex",1, 1);
        Producer producer = new Producer(false,1000);
        Consumer consumer = new Consumer(false,50000);

        CompletableFuture startProducerFuture = CompletableFuture.runAsync(()-> System.out.println("----------Start producer future--------"));
        CompletableFuture startConsumerFuture = CompletableFuture.runAsync(()-> System.out.println("----------Start consumer future--------"));
        CompletableFuture commonFuture = CompletableFuture.runAsync(()-> System.out.println("-------------------Start common future-----------------"));


            while (true) {
                commonFuture.thenRunAsync(consumer.readyToActRunnable)
                        .thenRun(consumer.wait(full))
                        .thenRun(consumer.wait(mutex))
                        .thenRun(consumer.actOnBuffer(buffer))  //non toglie il dato dal buffer peche???
                        .thenRun(consumer.signal(mutex));


                commonFuture.thenRun(producer.readyToActRunnable)
                        .thenRun(producer.wait(empty))
                        .thenRun(producer.wait(mutex))
                        .thenRun(producer.actOnBuffer(buffer))
                        .thenRun(producer.signal(mutex))
                        .thenRun(producer.signal(full));
            }


    }



}