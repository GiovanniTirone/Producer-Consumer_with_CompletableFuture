import java.nio.Buffer;
import java.nio.IntBuffer;
import java.util.Arrays;

public class Consumer {


    public void readData (int currentPos, int buffer[]) throws InterruptedException {
        System.out.println("-------------------------------------------");
        System.out.println("Buffer: " + Arrays.toString(buffer));
        Thread.sleep((int)Math.floor(Math.random()*10000));
        System.out.println("The data is " + buffer[currentPos]);
        buffer[currentPos] = -1;
        System.out.println("Buffer: " + Arrays.toString(buffer));
        System.out.println("-------------------------------------------");

    }
}
