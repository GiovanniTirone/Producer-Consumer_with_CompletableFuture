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
        Consumer consumer = new Consumer(false,2000);


        CompletableFuture commonFuture = CompletableFuture.runAsync(()-> System.out.println("-------------------Start common future-----------------"));


        Thread producerThread = new Thread(() -> {
            while (true) {
                commonFuture.thenRun(producer.readyToAct())
                        .thenRun(producer.wait(empty))
                        .thenRun(producer.wait(mutex))
                        .thenRun(producer.actOnBuffer(buffer))
                        .thenRun(producer.signal(mutex))
                        .thenRun(producer.signal(full));
            }
        });


        Thread consumerThread = new Thread(()->{
            while(true){
                commonFuture.thenRun(consumer.readyToAct())
                        .thenRun(consumer.wait(full))
                        .thenRun(consumer.wait(mutex))
                        .thenRun(consumer.actOnBuffer(buffer))  //non toglie il dato dal buffer peche???
                        .thenRun(consumer.signal(mutex))
                        .thenRun(consumer.signal(empty));
            }
        });



        producerThread.start();
        consumerThread.start();




        /*   THIS DOESN'T WORK

            while (true) {
                commonFuture.thenRun(consumer.readyToAct())
                        .thenRun(consumer.wait(full))
                        .thenRun(consumer.wait(mutex))
                        .thenRun(consumer.actOnBuffer(buffer))  //non toglie il dato dal buffer peche???
                        .thenRun(consumer.signal(mutex));


                commonFuture.thenRun(producer.readyToAct())
                        .thenRun(producer.wait(empty))
                        .thenRun(producer.wait(mutex))
                        .thenRun(producer.actOnBuffer(buffer))
                        .thenRun(producer.signal(mutex))
                        .thenRun(producer.signal(full));
            }

        */
    }



}