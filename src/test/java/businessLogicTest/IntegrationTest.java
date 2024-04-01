package businessLogicTest;

import exception.PolynomialException;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.function.Executable;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class IntegrationTest extends TestTemplate{

    // if the textField is empty
    @Test
    public void integrationTest1(){
        expectedString = null;
        polynomial1 = "";
        Assertions.assertThrows(NullPointerException.class, new Executable() {
            @Override
            public void execute() throws Throwable {
                expectedString = Derivative.derivative(polynomial2);
            }
        });
    }

    // invalid input
    @Test
    public void integrationTest2(){
        expectedString = null;
        polynomial1 = "2y+6";
        Assertions.assertThrows(NullPointerException.class, new Executable() {
            @Override
            public void execute() throws Throwable {
                expectedString = Derivative.derivative(polynomial2);
            }
        });
    }

    //testcase for normal polynomial
    @Test
    public void integrationTest3() throws PolynomialException {
        expectedString = "x^3+x^2+6x+C";
        polynomial1 = "3x^2+2x+6";
        assertEquals(expectedString, resultString = businessLogicTest.Integration.integration(polynomial1));
    }

    @Test
    public void integrationTest4() throws PolynomialException {
        expectedString = "0.5x^2+x+C";
        polynomial1 = "x+1";
        assertEquals(expectedString, resultString = businessLogicTest.Integration.integration(polynomial1));
    }

}
