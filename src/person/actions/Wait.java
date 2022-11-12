package person.actions;
import semaphores.Semaphore;

public class Wait implements Runnable{

    Semaphore semaphore;

    public Wait (Semaphore semaphore){
        this.semaphore = semaphore;
    }

    @Override
    public void run() {
        System.out.println("------------RUN WAIT--------------");
        while(semaphore.getCurrentInt()<=0) {
            try {
                Thread.sleep(1000);
                System.out.println(".....waiting semaphore....");
                System.out.println("info semaphore: " + semaphore);
            } catch (InterruptedException ex) {
                throw new RuntimeException(ex);
            }
        }
        try {
            semaphore.decreaseCurrentInt();
            System.out.println(semaphore);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    };
}

