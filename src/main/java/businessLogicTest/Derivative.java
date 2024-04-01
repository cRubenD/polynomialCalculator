package businessLogicTest;

import exception.PolynomialException;

import java.util.HashMap;
import java.util.Map;

import static dataModels.Polynomial.convertPolynomial;
import static dataModels.Polynomial.extractMonomials;

public class Derivative extends PolynomialException{

    public Derivative(String message) {
        super(message);
    }

    public static String derivative(String polynomial) throws PolynomialException {

        if (polynomial.isEmpty()) {
            throw new PolynomialException("TextField 2 cannot be empty!");
        } else {

            Map<Integer, Integer> monomials1 = new HashMap<>();
            extractMonomials(polynomial, monomials1);

            Map<Integer, Double> derivativeMonomials = new HashMap<>();

            // normal derivation, multiply the degree with the coefficient and subtract the degree by 1
            for (Map.Entry<Integer, Integer> entry : monomials1.entrySet()) {
                int degree = entry.getKey() - 1;
                double coefficient = (double) entry.getValue() * (degree + 1);
                if (degree < 0) {
                    continue;
                }
                derivativeMonomials.put(degree, coefficient);
            }

            return convertPolynomial(derivativeMonomials);
        }
    }
}
