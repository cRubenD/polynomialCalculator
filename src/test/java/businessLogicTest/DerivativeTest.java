package businessLogicTest;

import exception.PolynomialException;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.function.Executable;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class DerivativeTest extends TestTemplate{

    // if the textField is empty
    @Test
    public void derivativeTest1(){
        expectedString = null;
        polynomial2 = "";
        Assertions.assertThrows(PolynomialException.class, new Executable() {
            @Override
            public void execute() throws Throwable {
                expectedString = Derivative.derivative(polynomial2);
            }
        });
    }

    // invalid input
    @Test
    public void derivativeTest2(){
        expectedString = null;
        polynomial2 = "xy+7";
        Assertions.assertThrows(ExceptionInInitializerError.class, new Executable() {
            @Override
            public void execute() throws Throwable {
                expectedString = Derivative.derivative(polynomial2);
            }
        });
    }

    // testcase for a normal polynomial
    @Test
    public void derivativeTest3() throws PolynomialException {
        expectedString = "6x+5";
        polynomial2 = "3x^2+5x-7";
        assertEquals(expectedString, resultString = businessLogicTest.Derivative.derivative(polynomial2));
    }

    // testcase for a simple constant
    @Test
    public void derivativeTest4() throws PolynomialException {
        expectedString = "0";
        polynomial2 = "5";
        assertEquals(expectedString, resultString = businessLogicTest.Derivative.derivative(polynomial2));
    }
}
