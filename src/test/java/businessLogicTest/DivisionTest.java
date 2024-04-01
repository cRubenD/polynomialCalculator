package businessLogicTest;

import exception.PolynomialException;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.function.Executable;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class DivisionTest extends TestTemplate{

    // null inputs
    @Test
    public void divisionTest1(){
        expectedString = "";
        polynomial1 = "";
        polynomial2 = "";
        Assertions.assertThrows(PolynomialException.class, new Executable() {
            @Override
            public void execute() throws Throwable {
                expectedString = Division.division(polynomial1, polynomial2);
            }
        });
    }

    // only one input
    @Test
    public void divisionTest2(){
        expectedString = "";
        polynomial1 = "x+6";
        polynomial2 = "";
        Assertions.assertThrows(PolynomialException.class, new Executable() {
            @Override
            public void execute() throws Throwable {
                expectedString = Division.division(polynomial1, polynomial2);
            }
        });
    }

    // division by 0
    @Test
    public void divisionTest3(){
        expectedString = "";
        polynomial1 = "x+5";
        polynomial2 = "0";
        Assertions.assertThrows(PolynomialException.class, new Executable() {
            @Override
            public void execute() throws Throwable {
                expectedString = Division.division(polynomial1, polynomial2);
            }
        });
    }

    // invalid input
    @Test
    public void divisionTest4(){
        expectedString = "";
        polynomial1 = "x+5";
        polynomial2 = "y-3";
        Assertions.assertThrows(NoClassDefFoundError.class, new Executable() {
            @Override
            public void execute() throws Throwable {
                expectedString = Division.division(polynomial1, polynomial2);
            }
        });
    }

    // 0 divided by
    @Test
    public void divisionTest5() throws PolynomialException {
        expectedString = "0";
        polynomial1 = "0";
        polynomial2 = "x+5";
        assertEquals(expectedString, resultString = businessLogicTest.Division.division(polynomial1, polynomial2));
    }

    // division without remainder
    @Test
    public void divisionTest6() throws PolynomialException {
        expectedString = "x+3";
        polynomial1 = "x^2+5x+6";
        polynomial2 = "x+2";
        assertEquals(expectedString, resultString = businessLogicTest.Division.division(polynomial1, polynomial2));
    }

    // division with remainder
    @Test
    public void divisionTest7() throws PolynomialException {
        expectedString = "2x^2+12x+18, r: 46/x-2";
        polynomial1 = "2x^3+8x^2-6x+10";
        polynomial2 = "x-2";
        assertEquals(expectedString, resultString = businessLogicTest.Division.division(polynomial1, polynomial2));
    }

    // divident with greater degree
    @Test
    public void divisionTest8() throws PolynomialException {
        expectedString = "0";
        polynomial1 = "2x^3+10";
        polynomial2 = "x^4-6";
        assertEquals(expectedString, resultString = businessLogicTest.Division.division(polynomial1, polynomial2));
    }
}
