package businessLogicTest;

import exception.PolynomialException;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.function.Executable;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class AddSubTest extends TestTemplate{

    // null inputs expect an exception
    @Test
    public void addTest1(){
        Assertions.assertThrows(PolynomialException.class, new Executable() {
            @Override
            public void execute() throws Throwable {
                result = businessLogicTest.AddSub.addSub(monomials1, monomials2, "+");
            }
        });
    }

    // test for basic addition of both polynomials
    @Test
    public void addTest2() throws PolynomialException {
        // x^2+3x+5 + 2x-3 = x^2+5x+2
        monomials1.put(2, 1);
        monomials1.put(1, 3);
        monomials1.put(0, 5);
        monomials2.put(1, 2);
        monomials2.put(0, -3);
        expected.put(2, 1.0);
        expected.put(1, 5.0);
        expected.put(0, 2.0);

        assertEquals(expected, result = businessLogicTest.AddSub.addSub(monomials1, monomials2, "+"));
    }

    // test case for only one polynomial
    @Test
    public void addTest3() throws PolynomialException {
        // x^2+3 + null = x^2+3
        monomials1.put(2, 1);
        monomials1.put(0, 3);
        expected.put(2, 1.0);
        expected.put(0, 3.0);

        assertEquals(expected, result = businessLogicTest.AddSub.addSub(monomials1, monomials2, "+"));
    }

    // we do the same testcases for the subtraction operation
    @Test
    public void subTest1(){
        Assertions.assertThrows(PolynomialException.class, new Executable() {
            @Override
            public void execute() throws Throwable {
                result = businessLogicTest.AddSub.addSub(monomials1, monomials2, "-");
            }
        });
    }

    @Test
    public void subTest2() throws PolynomialException {
        // x^2+3x+5 - 2x-3 = x^2+x+8
        monomials1.put(2, 1);
        monomials1.put(1, 3);
        monomials1.put(0, 5);
        monomials2.put(1, 2);
        monomials2.put(0, -3);
        expected.put(2, 1.0);
        expected.put(1, 1.0);
        expected.put(0, 8.0);

        assertEquals(expected, result = businessLogicTest.AddSub.addSub(monomials1, monomials2, "-"));
    }

    @Test
    public void subTest3() throws PolynomialException {
        // x^2+3 - null = x^2+3
        monomials1.put(2, 1);
        monomials1.put(0, 3);
        expected.put(2, 1.0);
        expected.put(0, 3.0);

        assertEquals(expected, result = businessLogicTest.AddSub.addSub(monomials1, monomials2, "-"));
    }

    // test if null - polynomial = -(polynomial)
    @Test
    public void subTest4() throws PolynomialException {
        // null - (x^2+3)  = -(x^2+3)
        monomials2.put(2, 1);
        monomials2.put(0, 3);
        expected.put(2, -1.0);
        expected.put(0, -3.0);

        assertEquals(expected, result = businessLogicTest.AddSub.addSub(monomials1, monomials2, "-"));
    }

}
