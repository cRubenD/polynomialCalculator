package businessLogicTest;

import exception.PolynomialException;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.function.Executable;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class MultiplicationTest extends TestTemplate{

    // null inputs expect an exception
    @Test
    public void mutliplicationTest1(){
        Assertions.assertThrows(PolynomialException.class, new Executable() {
            @Override
            public void execute() throws Throwable {
                result = businessLogicTest.Multiplication.multiplication(monomials1, monomials2);
            }
        });
    }

    // only one input
    @Test
    public void mutliplicationTest2(){
        monomials1.put(1, 2);
        monomials1.put(2, 1);
        Assertions.assertThrows(PolynomialException.class, new Executable() {
            @Override
            public void execute() throws Throwable {
                result = businessLogicTest.Multiplication.multiplication(monomials1, monomials2);
            }
        });
    }

    // multiply by 0
    @Test
    public void multiplicationTest3() throws PolynomialException {
        monomials1.put(1, 2);
        monomials1.put(2, 3);
        monomials2.put(0, 0);
        assertEquals(expected, result = businessLogicTest.Multiplication.multiplication(monomials1, monomials2));
    }

    // normal multiplication
    @Test
    public void multiplicationTest4() throws PolynomialException {
        monomials1.put(0, 2);
        monomials1.put(1, 3);
        monomials2.put(0, 1);
        monomials2.put(1, 2);
        expected.put(2, 6.0);
        expected.put(1, 7.0);
        expected.put(0, 2.0);
        assertEquals(expected, result = businessLogicTest.Multiplication.multiplication(monomials1, monomials2));
    }
}
