package tests.consumerActions;

import buffer.Buffer;
import person.Consumer;
import semaphores.Semaphore;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.atomic.AtomicInteger;

public class Test04_wholeConsumer {

    public static void main(String[] args) {

        Consumer consumer = new Consumer(false,5000);
        CompletableFuture startConsumerFuture = CompletableFuture.runAsync(()-> System.out.println("----------Start producer future--------"));

        Semaphore full = new Semaphore("full",5,5);
        Semaphore mutex = new Semaphore("mutex",1,1);

        Buffer buffer = new Buffer(5);
        buffer.add(1);
        buffer.add(2);
        buffer.add(3);
        buffer.add(4);
        buffer.add(5);

        startConsumerFuture.thenRunAsync(()->{
            try {
                System.out.println("---------------START ASYNC-------------------");
                Thread.sleep(40000);
                System.out.println("---------------REMOVE FROM BUFFER--------------");
                buffer.add(6);
                full.increaseCurrentInt();
            } catch (Exception e) {
                e.printStackTrace();
            }
        });


        while(true){
            startConsumerFuture
                    .thenRun(consumer.readyToActRunnable)
                    .thenRun(consumer.wait(full))
                    .thenRun(consumer.wait(mutex))
                    .thenRun(consumer.actOnBuffer(buffer))
                    .thenRun(consumer.signal(mutex));
        }
    }
}
