package tests.producerActions;
import person.Producer;
import semaphores.Semaphore;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.atomic.AtomicInteger;

public class Test01_WaitEmptyProducer {
    public static void main(String[] args) {
        Producer producer = new Producer(false,5000);
        AtomicInteger i = new AtomicInteger();
        CompletableFuture startProducerFuture = CompletableFuture.runAsync(()-> System.out.println("----------Start producer future--------"));

        Semaphore empty = new Semaphore("empty",5,5 );

        while(i.get() <10){
            startProducerFuture
                    .thenRun(producer.readyToActRunnable)
                    .thenRun(producer.wait(empty))
                    .thenRun(()->{
                        System.out.println("go on the step: " + i.incrementAndGet());
                    });

        }
    }
}
