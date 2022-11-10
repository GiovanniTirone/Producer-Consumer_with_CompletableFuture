package person.actions;
import buffer.Buffer;

public abstract class RunActionOnBuffer implements Runnable {

    Buffer buffer;

    public RunActionOnBuffer(Buffer buffer) {
        this.buffer = buffer;
    }

    public abstract void action (Buffer buffer) throws Exception;

    @Override
    public void run() {
        action(buffer);
    }
}
