package second.junit;

public class AdvanceMath {

    public int addition(int a, int b) {
        return a + b;
    }

    public int addition(String a, int b) {
        int i = Integer.parseInt(a);
        return i + b;
    }

    public int multiply(int a, int b) throws Exception {
        long la = a;
        long lb = b;

        if (la * lb > Integer.MAX_VALUE) {
            throw new Exception("value over the limit");
        }

        return a * b;
    }
}
