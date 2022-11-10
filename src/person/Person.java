package person;

import buffer.Buffer;
import lombok.Data;
import person.actions.ReadyToAct;
import person.actions.RunActionOnBuffer;
import semaphores.Semaphore;
import java.util.Map;
import java.util.function.Function;

@Data
public class Person {

    protected Function <Semaphore,Void> wait;

    protected Function <Semaphore,Void> signal;

    protected ReadyToAct readyToAct;

    protected Map<Buffer, RunActionOnBuffer> runActionsOnBuffersMap;


    public Person (boolean randomWaiting, int waitingStepTime) {

        this.wait = (Semaphore s) -> {
            try {
                while(s.getCurrentInt()<=0) Thread.sleep(1000);
                s.increaseCurrentInt();
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
            return null;
        };

        this.signal = (Semaphore s) -> {
            try {
                s.increaseCurrentInt();
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
            return null;
        };

        this.readyToAct = new ReadyToAct(randomWaiting,waitingStepTime);

    }





}
