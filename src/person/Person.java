package person;
import buffer.Buffer;
import lombok.Data;
import person.actions.ReadyToAct;
import person.actions.ActOnBufferRunnable;
import person.actions.Signal;
import person.actions.Wait;
import semaphores.Semaphore;

@Data
public abstract class Person {

    public ReadyToAct readyToActRunnable;

    public Person (boolean randomWaiting, int waitingStepTime) {
        this.readyToActRunnable = new ReadyToAct(randomWaiting,waitingStepTime);
    }

    public Runnable readyToAct () {
        System.out.println(this.getClass().getName().substring(7).toUpperCase() + " is ready to act");
        return readyToActRunnable;
    }

    public Runnable wait (Semaphore semaphore)  {
        System.out.println(this.getClass().getName().substring(7).toUpperCase() + " wait the semaphore " + semaphore);
        return new Wait(semaphore);
    }

    public Runnable signal (Semaphore semaphore){
        System.out.println(this.getClass().getName().substring(7).toUpperCase() + " signal the semaphore " + semaphore);
        return new Signal(semaphore);
    }

    public Runnable actOnBuffer (Buffer buffer) {
        System.out.println(this.getClass().getName().substring(7).toUpperCase() + " acts on buffer " + buffer);
        return new ActOnBufferRunnable(buffer) {
            @Override
            public void action(Buffer buffer) {
                actionOnBuffer(buffer);
            }
        };
    }

    abstract void actionOnBuffer (Buffer buffer) ;


}
