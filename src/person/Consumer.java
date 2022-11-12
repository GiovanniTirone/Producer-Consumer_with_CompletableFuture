package person;
import buffer.Buffer;

public class Consumer extends Person{


    public Consumer(boolean randomWaiting, int waitingStepTime) {
        super(randomWaiting, waitingStepTime);
    }

    @Override
    void actionOnBuffer(Buffer buffer)  {
        System.out.println("-------------------------------------------");
        System.out.println("buffer.Buffer: " + buffer);
        int readData = buffer.removeLast();
        System.out.println("The read data is " + readData);
        System.out.println("buffer.Buffer: " + buffer);
        System.out.println("-------------------------------------------");
    }


}
