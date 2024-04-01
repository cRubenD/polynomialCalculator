package businessLogicTest;

import exception.PolynomialException;
import java.util.HashMap;
import java.util.Map;

import static dataModels.Polynomial.convertPolynomial;
import static dataModels.Polynomial.extractMonomials;

public class Integration extends PolynomialException{

    public Integration(String message) {
        super(message);
    }

    public static String integration(String polynomial) throws PolynomialException {

        if (polynomial.isEmpty()) {
            throw new PolynomialException("TextField 1 cannot be empty!");
        } else {

            Map<Integer, Integer> monomials1 = new HashMap<>();
            extractMonomials(polynomial, monomials1);

            Map<Integer, Double> integratedMonomials = new HashMap<>();

            // normal integration, we add one to the current degree and divide the monomial by it
            for (Map.Entry<Integer, Integer> entry : monomials1.entrySet()) {
                int degree = entry.getKey() + 1;
                double coefficient = (double) entry.getValue() / degree;
                integratedMonomials.put(degree, coefficient);
            }

            // also add 'C' for constants
            return convertPolynomial(integratedMonomials) + "+C";
        }
    }
}
