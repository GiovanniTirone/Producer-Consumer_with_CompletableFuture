package person;
import buffer.Buffer;
import person.actions.RunActionOnBuffer;
import java.util.HashMap;

public class Consumer extends Person{


    public Consumer(boolean randomWaiting, int waitingStepTime) {
        super(randomWaiting, waitingStepTime);
    }

    public Consumer(boolean randomWaiting, int waitingStepTime, Buffer buffer) {
        super(randomWaiting, waitingStepTime);
        this.runActionsOnBuffersMap = new HashMap<>();
        runActionsOnBuffersMap.put(buffer, new RunActionOnBuffer(buffer) {
            @Override
            public void action(Buffer buffer) throws Exception {
                readDataFromBuffer(buffer);
            }
        });
    }

    private void readDataFromBuffer (Buffer buffer) throws Exception {
        System.out.println("-------------------------------------------");
        System.out.println("buffer.Buffer: " + buffer);
        int readData = buffer.removeLast();
        System.out.println("The read data is " + readData);
        System.out.println("buffer.Buffer: " + buffer);
        System.out.println("-------------------------------------------");
    }


}
