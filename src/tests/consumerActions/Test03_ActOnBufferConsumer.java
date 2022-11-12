package tests.consumerActions;

import buffer.Buffer;
import person.Consumer;
import semaphores.Semaphore;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.atomic.AtomicInteger;

public class Test03_ActOnBufferConsumer {

    public static void main(String[] args) {

        Consumer consumer = new Consumer(false,5000);
        AtomicInteger i = new AtomicInteger();
        CompletableFuture startConsumerFuture = CompletableFuture.runAsync(()-> System.out.println("----------Start producer future--------"));

        Semaphore full = new Semaphore("full",5,5);
        Semaphore mutex = new Semaphore("mutex",1,1);

        Buffer buffer = new Buffer(5);
        buffer.add(1);
        buffer.add(2);
        buffer.add(3);
        buffer.add(4);
        buffer.add(5);

        while(i.get() <10){
            startConsumerFuture
                    .thenRun(consumer.readyToActRunnable)
                    .thenRun(consumer.wait(full))
                    .thenRun(consumer.wait(mutex))
                    .thenRun(consumer.actOnBuffer(buffer))
                    .thenRun(consumer.signal(mutex));
        }
    }
}
