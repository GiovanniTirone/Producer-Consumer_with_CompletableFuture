package person.actions;
import java.util.function.Function;

public class ReadyToAct implements Runnable {

    boolean randomWaiting;

    int stepTime;

    public ReadyToAct (boolean randomWaiting, int stepTime) {
        this.randomWaiting = randomWaiting;
        this.stepTime = stepTime;
    }

    @Override
    public Object apply(Object o) {
        if(randomWaiting) {
            try {
                Thread.sleep((int)Math.floor(Math.random())*10000);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
        else{
            try {
                Thread.sleep(stepTime);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
        return null;
    }

    @Override
    public Function andThen(Function after) {
        return Function.super.andThen(after);
    }

    @Override
    public Function compose(Function before) {
        return Function.super.compose(before);
    }
}
