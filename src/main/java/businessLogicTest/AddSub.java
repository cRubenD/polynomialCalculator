package businessLogicTest;

import exception.PolynomialException;

import java.util.HashMap;
import java.util.Map;

public class AddSub extends PolynomialException {
    public AddSub(String message) {
        super(message);
    }

    public static Map<Integer, Double> addSub(Map<Integer, Integer> monomials1, Map<Integer, Integer> monomials2, String operation) throws PolynomialException, NumberFormatException {

        if (monomials1.isEmpty() && monomials2.isEmpty()) {
            throw new PolynomialException("Solution: insert at least one input!");
        } else {

            for (Map.Entry<Integer, Integer> entry : monomials2.entrySet()) {
                // if we have the same degree, we add or subtract the coefficients
                if (monomials1.containsKey(entry.getKey())) {
                    int newCoefficient;
                    // depending on the operator we choose the operation
                    if (operation.equals("+")) {
                        newCoefficient = monomials1.get(entry.getKey()) + entry.getValue();
                    } else {
                        newCoefficient = monomials1.get(entry.getKey()) - entry.getValue();
                    }
                    // if the coefficient is 0, the monomial does not exist, so we delete it
                    if (newCoefficient != 0) {
                        monomials1.put(entry.getKey(), newCoefficient);
                    } else monomials1.remove(entry.getKey());
                } else if (operation.equals("+")) {
                    monomials1.put(entry.getKey(), entry.getValue());
                } else {
                    monomials1.put(entry.getKey(), -entry.getValue());
                }
            }

            Map<Integer, Double> result = new HashMap<>();
            for (Map.Entry<Integer, Integer> entry : monomials1.entrySet()) {
                result.put(entry.getKey(), (double) entry.getValue());
            }

            return result;
        }
    }
}
