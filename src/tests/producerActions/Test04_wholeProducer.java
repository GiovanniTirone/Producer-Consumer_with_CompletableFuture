package tests.producerActions;

import buffer.Buffer;
import person.Producer;
import semaphores.Semaphore;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.atomic.AtomicInteger;

public class Test04_wholeProducer {

    public static void main(String[] args) {
        Producer producer = new Producer(false,5000);
        CompletableFuture startProducerFuture = CompletableFuture.runAsync(()-> System.out.println("----------Start producer future--------"));

        int maxCapacity = 5;

        Semaphore empty = new Semaphore("empty",maxCapacity,maxCapacity );
        Semaphore mutex = new Semaphore("mutex",1,1);

        Buffer buffer = new Buffer(maxCapacity);

        startProducerFuture.thenRunAsync(()->{
            try {
                System.out.println("---------------START ASYNC-------------------");
                Thread.sleep(40000);
                System.out.println("---------------REMOVE FROM BUFFER--------------");
                buffer.removeLast();
                empty.increaseCurrentInt();
            } catch (Exception e) {
                e.printStackTrace();
            }
        });


        while(true){
            startProducerFuture
                    .thenRun(producer.readyToActRunnable)
                    .thenRun(producer.wait(empty))
                    .thenRun(producer.wait(mutex))
                    .thenRun(producer.actOnBuffer(buffer))
                    .thenRun(producer.signal(mutex));
        }
    }

}
