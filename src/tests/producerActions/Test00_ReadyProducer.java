package tests.producerActions;
import person.Producer;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.atomic.AtomicInteger;

public class Test00_ReadyProducer {
    public static void main(String[] args) {
        Producer producer = new Producer(false,5000);
        AtomicInteger i = new AtomicInteger();
        CompletableFuture startProducerFuture = CompletableFuture.runAsync(()-> System.out.println("----------Start producer future--------"));

        while(i.get() <10){
            /*CompletableFuture producerIsReady = CompletableFuture.runAsync(producer.readyToActRunnable);
            CompletableFuture producerFuture = CompletableFuture.completedFuture(producerIsReady);*/
            startProducerFuture
                    .thenRun(producer.readyToActRunnable)
                    .thenRun(()->{
                        System.out.println("go on the step: " + i.getAndIncrement());
                    });

            /*producerFuture.(()->{
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
            }).thenRun(()->{
                System.out.println("next step: " + i.getAndIncrement());
            });
             */
        }
    }
}
