package tests;
import buffer.Buffer;

public class TestBuffer {
    public static void main(String[] args) {
        Buffer buffer = new Buffer(3);
        buffer.addInt(1);
        buffer.addInt(1);
        buffer.addInt(1);
        buffer.addInt(1);
        System.out.println(buffer);
        System.out.println(buffer.removeLast());
        buffer.removeLast();
        buffer.removeLast();
        buffer.removeLast();
        System.out.println(buffer);
    }



}
