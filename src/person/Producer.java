package person;

import buffer.Buffer;
import person.actions.RunActionOnBuffer;

import java.util.HashMap;

public class Producer extends Person {


    public Producer(boolean randomWaiting, int waitingStepTime) {
        super(randomWaiting, waitingStepTime);
    }

    public Producer(boolean randomWaiting, int waitingStepTime, Buffer buffer) {
        super(randomWaiting, waitingStepTime);
        this.runActionsOnBuffersMap = new HashMap<>();
        runActionsOnBuffersMap.put(buffer, new RunActionOnBuffer(buffer) {
            @Override
            public void action(Buffer buffer) throws Exception {
                addDataToBuffer(generateData(),buffer);
            }
        });
    }

    private int generateData () {
        return (int)Math.floor(Math.random()*1000);
    }

    private void addDataToBuffer (int data, Buffer buffer) throws Exception {
        System.out.println("-------------------------------------------");
        System.out.println("buffer.Buffer: " + buffer);
        System.out.println("Generate data");
        buffer.add(data);
        System.out.println("buffer.Buffer: " + buffer);
        System.out.println("-------------------------------------------");
    }




}
