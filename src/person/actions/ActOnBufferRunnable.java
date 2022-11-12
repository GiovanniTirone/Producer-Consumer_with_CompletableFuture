package person.actions;
import buffer.Buffer;

public abstract class ActOnBufferRunnable implements Runnable {

    Buffer buffer;

    public ActOnBufferRunnable(Buffer buffer) {
        this.buffer = buffer;
    }

    public abstract void action (Buffer buffer) throws Exception;

    @Override
    public void run() {
        System.out.println("------------RUN ACT ON BUFFER--------------");
        try {
            action(buffer);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
