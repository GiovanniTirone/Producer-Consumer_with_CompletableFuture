package buffer;

import java.util.ArrayList;

public class Buffer extends ArrayList<Integer> {

    int maxCapacity;

    public Buffer (int maxCapacity) {
        this.maxCapacity = maxCapacity;
    }

    public void add (int i) throws Exception {
        if(this.size()<maxCapacity) this.add(i);
        else throw new Exception("Cannot add element in a full buffer.");
    }

    public int removeLast () throws Exception {
        if(this.size()>0) return this.remove(this.size());
        else throw new Exception("Cannot remove elements from an empty buffer.");
    }

    public boolean checkFull () {
        if(this.size() == maxCapacity) return true;
        else return false;
    }

    public boolean checkEmpty () {
        if(this.size() == 0) return true;
        else return false;
    }



}
