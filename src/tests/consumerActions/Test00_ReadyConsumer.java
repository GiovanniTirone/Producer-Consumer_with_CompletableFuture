package tests.consumerActions;

import person.Consumer;
import person.Producer;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.atomic.AtomicInteger;

public class Test00_ReadyConsumer {
    public static void main(String[] args) {
        Consumer consumer = new Consumer(false,5000);
        AtomicInteger i = new AtomicInteger();
        CompletableFuture startConsumerFuture = CompletableFuture.runAsync(()-> System.out.println("----------Start producer future--------"));

        while(i.get() <10){
            startConsumerFuture
                    .thenRun(consumer.readyToActRunnable)
                    .thenRun(()->{
                        System.out.println("go on the step: " + i.getAndIncrement());
                    });

        }
    }
}
