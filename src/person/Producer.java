package person;
import buffer.Buffer;

public class Producer extends Person {


    public Producer(boolean randomWaiting, int waitingStepTime) {
        super(randomWaiting, waitingStepTime);
    }

    @Override
    void actionOnBuffer(Buffer buffer)  {
        System.out.println("-------------------------------------------");
        System.out.println("buffer.Buffer: " + buffer);
        System.out.println("Generate data");
        buffer.addInt(generateData());
        System.out.println("buffer.Buffer: " + buffer);
        System.out.println("-------------------------------------------");
    }

    private int generateData () {
        return (int)Math.floor(Math.random()*1000);
    }



}
