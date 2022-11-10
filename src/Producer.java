import java.nio.Buffer;
import java.nio.IntBuffer;
import java.util.Arrays;
import java.util.concurrent.CompletableFuture;
import java.util.function.Supplier;

public class Producer {


    private int generateData () {
        return (int)Math.floor(Math.random()*1000);
    }

    private void addDataToBuffer (int currentPos,int buffer[]) throws InterruptedException {
        System.out.println("-------------------------------------------");
        System.out.println("Buffer: " + Arrays.toString(buffer));
        System.out.println("Generate data");
        buffer[currentPos]=generateData();
        System.out.println("Buffer: " + Arrays.toString(buffer));
        System.out.println("-------------------------------------------");
    }


    public Runnable addDataToBufferRun (int currentPos,int buffer[]){
        return () -> {
            try {
                addDataToBuffer(currentPos,buffer);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        };
    }





}
