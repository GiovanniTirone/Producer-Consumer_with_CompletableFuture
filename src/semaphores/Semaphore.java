package semaphores;


public class Semaphore {

    private String name;
    final private int maxInts;

    private int currentInt;



    public Semaphore(String name, int maxInts, int initialPos){
        this.name = name;
        this.maxInts = maxInts;
        this.currentInt = initialPos;
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

    public void decreaseCurrentInt () throws Exception {
        System.out.println("decrease current int");
        if(currentInt <= 0 ) throw new Exception("The current int must be >0");
        currentInt--;
    };

    public void increaseCurrentInt () throws Exception {
        System.out.println("increase current int");
        if(currentInt >= maxInts) throw new Exception("The current int must be < maxInts");
        if(currentInt < maxInts) currentInt +=1;
    };

    @Override
    public String toString() {
        return " Semaphore: " + name +
                " - maxInts: " + maxInts +
                " - currentInt=" + currentInt ;
    }
}
