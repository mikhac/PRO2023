package second.junit;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertTrue;

public class AdvanceMathTest {

    AdvanceMath math;

    @Before
    public void setUp() {
        System.out.println("Run setUp");
        math = new AdvanceMath();
    }

    @Test
    public void additionTest() {
        int a = math.addition(1, 4);
        Assert.assertEquals(5, a);
    }

    @Test
    public void additionTestString() {
        int a = math.addition("1", 4);
        Assert.assertEquals(5, a);
    }

    @Test(expected = Exception.class)
    public void additionTestString2() {
        int a = math.addition("a1", 4);
    }

    @Test
    public void testAddition() {
    }

    @Test
    public void testTestAddition() {
    }

    @Test(expected = Exception.class)
    public void testMultiply() throws Exception {
        int result = math.multiply(1000000000, 1000000000);
        Assert.assertEquals(20, math.multiply(4, 5));
        Assert.assertEquals(0, math.multiply(0, 5));
    }
}