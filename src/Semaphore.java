import java.util.concurrent.CompletableFuture;

public class Semaphore {
    final private int maxInts;

    private int currentPos;

    public CompletableFuture<Void> wait ;

    public CompletableFuture <Void> signal;

    public Semaphore(int maxInts,int initialPos){
        this.maxInts = maxInts;
        this.currentPos = initialPos;

        this.wait = CompletableFuture.runAsync(() -> {
            if(currentPos >0) currentPos -=1;
        });

        this.signal = CompletableFuture.runAsync(() -> {
            if(currentPos<maxInts) currentPos +=1;
        });

    }

    public int getMaxInts() {
        return maxInts;
    }

    public int getCurrentPos() {
        return currentPos;
    }

    public void setCurrentPos(int currentPos) {
        this.currentPos = currentPos;
    }

    public CompletableFuture<Void> getWait() {
        return wait;
    }

    public void setWait(CompletableFuture<Void> wait) {
        this.wait = wait;
    }

    public CompletableFuture<Void> getSignal() {
        return signal;
    }

    public void setSignal(CompletableFuture<Void> signal) {
        this.signal = signal;
    }
}
