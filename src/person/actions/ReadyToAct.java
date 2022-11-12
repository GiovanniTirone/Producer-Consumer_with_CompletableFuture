package person.actions;
public class ReadyToAct implements Runnable {

    boolean randomWaiting;

    int stepTime;

    public ReadyToAct (boolean randomWaiting, int stepTime) {
        this.randomWaiting = randomWaiting;
        this.stepTime = stepTime;
    }


    @Override
    public void run() {
        System.out.println("------------RUN READY TO ACT--------------");
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
    }
}
