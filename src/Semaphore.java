import java.util.concurrent.CompletableFuture;
import java.util.function.Function;



public class Semaphore {

    final private int maxInts;

    private int currentInt;

    private Runnable wait;

    private Runnable signal;

    public Semaphore(int maxInts, int initialPos){
        this.maxInts = maxInts;
        this.currentInt = initialPos;
        this.wait = () -> {
            try {
                decreaseCurrentInt();
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        };
        this.signal = () -> increaseCurrentInt();
    }

    public int getMaxInts() {
        return maxInts;
    }

    public int getCurrentInt() {
        return currentInt;
    }

    public void setCurrentInt(int currentInt) {
        this.currentInt = currentInt;
    }

    private void decreaseCurrentInt () throws InterruptedException {
        System.out.println("decrease current int");
        while(currentInt<=0) Thread.sleep(2000);
        if (currentInt > 0) currentInt -= 1;
    };

    private void increaseCurrentInt () {
        System.out.println("increase current int");
        if(currentInt < maxInts-1) currentInt +=1;
    };

    public Runnable getWait() {
        return wait;
    }

    public void setWait(Runnable wait) {
        this.wait = wait;
    }

    public Runnable getSignal() {
        return signal;
    }

    public void setSignal(Runnable signal) {
        this.signal = signal;
    }
}
