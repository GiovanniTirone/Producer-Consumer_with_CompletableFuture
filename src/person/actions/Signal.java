package person.actions;
import semaphores.Semaphore;

public class Signal implements Runnable{

    Semaphore semaphore;

    public Signal ( Semaphore semaphore){
        this.semaphore = semaphore;
    }

    @Override
    public void run() {
        System.out.println("-------------RUN SIGNAL--------------");
        try {
            semaphore.increaseCurrentInt();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
