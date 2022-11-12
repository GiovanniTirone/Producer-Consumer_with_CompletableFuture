package tests.producerActions;

import buffer.Buffer;
import person.Producer;
import semaphores.Semaphore;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.atomic.AtomicInteger;

public class Test03_ActOnBufferProducer {

    public static void main(String[] args) {
        Producer producer = new Producer(false,5000);
        AtomicInteger i = new AtomicInteger();
        CompletableFuture startProducerFuture = CompletableFuture.runAsync(()-> System.out.println("----------Start producer future--------"));

        int maxCapacity = 5;

        Semaphore empty = new Semaphore("empty",maxCapacity,maxCapacity );
        Semaphore mutex = new Semaphore("mutex",1,1);

        Buffer buffer = new Buffer(maxCapacity);

        while(i.get() <10){
            startProducerFuture
                    .thenRun(producer.readyToActRunnable)
                    .thenRun(producer.wait(empty))
                    .thenRun(producer.wait(mutex))
                    .thenRun(producer.actOnBuffer(buffer))
                    .thenRun(()->{
                        System.out.println("go on the step: " + i.incrementAndGet());
                    })
                    .thenRun(producer.signal(mutex));

        }
    }

}
