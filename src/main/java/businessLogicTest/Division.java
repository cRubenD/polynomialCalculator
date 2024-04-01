package businessLogicTest;

import exception.PolynomialException;

import java.util.*;

import static businessLogicTest.AddSub.addSub;
import static dataModels.Polynomial.convertPolynomial;
import static dataModels.Polynomial.extractMonomials;

public class Division extends PolynomialException{

    public Division(String message) {
        super(message);
    }

    public static String division(String polynomial1, String polynomial2) throws PolynomialException {

        Map<Integer, Integer> monomials1 = new HashMap<>();
        extractMonomials(polynomial1, monomials1);
        Map<Integer, Integer> monomials2 = new HashMap<>();
        extractMonomials(polynomial2, monomials2);

        if (monomials1.isEmpty() || monomials2.isEmpty() || polynomial2.equals("0")) {
            throw new PolynomialException("Solution: insert both inputs! Notice: you can't divide by zero");
        } else {

            // sort the polynomials so it's easier to operate
            Map<Integer, Integer> polynomialSorted1 = sortByDegree(monomials1);
            Map<Integer, Integer> polynomialSorted2 = sortByDegree(monomials2);

            Map<Integer, Double> result = new HashMap<>();
            Map<Integer, Integer> remainder = new HashMap<>();

            // we can divide as long as the degree of the dividend is greater or equal to the degree of the divisor
            while (maxDegree(polynomialSorted1) >= maxDegree(polynomialSorted2)) {
                // calculate the term of the result
                int degree = maxDegree(polynomialSorted1) - maxDegree(polynomialSorted2);
                int coefficient = polynomialSorted1.get(maxDegree(polynomialSorted1)) /
                        polynomialSorted2.get(maxDegree(polynomialSorted2));

                result.put(degree, (double) coefficient);

                // we subtract in order to continue the division
                for (Map.Entry<Integer, Integer> entry : polynomialSorted2.entrySet()) {
                    int degree1 = entry.getKey() + degree;
                    int coefficient1 = entry.getValue() * coefficient;
                    remainder.put(degree1, coefficient1);
                }

                Map<Integer, Double> aux = addSub(polynomialSorted1, remainder, "-");
                polynomialSorted1.clear();
                remainder.clear();

                for (Map.Entry<Integer, Double> entry : aux.entrySet()) {
                    double coeff = entry.getValue();
                    polynomialSorted1.put(entry.getKey(), (int) coeff);
                }

                // if the division can not continue, print the result and the remainer
                if (maxDegree(aux) < maxDegree(polynomialSorted2) && !convertPolynomial(aux).equals("0")) {
                    Map<Integer, Double> aux1 = new HashMap<>();
                    for (Map.Entry<Integer, Integer> entry : polynomialSorted2.entrySet()) {
                        aux1.put(entry.getKey(), Double.valueOf(entry.getValue()));
                    }
                    return convertPolynomial(result) + ", r: " + convertPolynomial(aux) + "/" + convertPolynomial(aux1);
                }
            }
            return convertPolynomial(result);
        }
    }

    // sort the polynomials by degree in descending order
    private static Map<Integer, Integer> sortByDegree(Map<Integer, Integer> map) {
        List<Map.Entry<Integer, Integer>> sortedEntries = new ArrayList<>(map.entrySet());
        sortedEntries.sort((entry1, entry2) -> Integer.compare(entry2.getKey(), entry1.getKey()));

        Map<Integer, Integer> sortedMap = new HashMap<>();
        for (Map.Entry<Integer, Integer> entry : sortedEntries) {
            sortedMap.put(entry.getKey(), entry.getValue());
        }

        return sortedMap;
    }

    // method to get the greatest degree in a polynomial
    private static int maxDegree(Map<Integer, ?> polynomial) {
        int maxDegree = -1;
        for (int degree : polynomial.keySet()) {
            if (degree > maxDegree) {
                maxDegree = degree;
            }
        }
        return maxDegree;
    }


}