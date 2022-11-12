package tests.consumerActions;
import person.Consumer;
import semaphores.Semaphore;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.atomic.AtomicInteger;

public class Test02_WaitMutexConsumer {

    public static void main(String[] args) {

        Consumer consumer = new Consumer(false,5000);
        AtomicInteger i = new AtomicInteger();
        CompletableFuture startConsumerFuture = CompletableFuture.runAsync(()-> System.out.println("----------Start producer future--------"));

        Semaphore full = new Semaphore("full",5,5);
        Semaphore mutex = new Semaphore("mutex",1,1);

        while(i.get() <10){
            startConsumerFuture
                    .thenRun(consumer.readyToActRunnable)
                    .thenRun(consumer.wait(full))
                    .thenRun(consumer.wait(mutex))
                    .thenRun(()->{
                        System.out.println("go on the step: " + i.getAndIncrement());
                    })
                    .thenRun(consumer.signal(mutex));
        }
    }
}
