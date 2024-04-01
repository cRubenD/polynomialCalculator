package businessLogicTest;

import exception.PolynomialException;

import java.util.HashMap;
import java.util.Map;

public class Multiplication extends PolynomialException{

    public Multiplication(String message) {
        super(message);
    }

    public static Map<Integer, Double> multiplication(Map<Integer, Integer> monomials1, Map<Integer, Integer> monomials2) throws PolynomialException {

        if (monomials1.isEmpty() || monomials2.isEmpty()) {
            throw new PolynomialException("Solution: insert both inputs!");
        } else {
            Map<Integer, Double> result = new HashMap<>();

            // for multiplication, we iterate to every entrySet of the 2 polynomials
            for (Map.Entry<Integer, Integer> entry : monomials1.entrySet()) {
                int degree1 = entry.getKey();
                int coefficient1 = entry.getValue();
                for (Map.Entry<Integer, Integer> entry2 : monomials2.entrySet()) {
                    int degree2 = entry2.getKey();
                    int coefficient2 = entry2.getValue();
                    // if we multiply by 0, the monomials is null
                    if((coefficient1*coefficient2) == 0){
                        continue;
                    }
                    // monomials with the same degree are combined
                    if (result.containsKey(degree1 + degree2)) {
                        result.put(degree1 + degree2, result.get(degree1 + degree2) + coefficient1 * coefficient2);
                    } else {
                        result.put(degree1 + degree2, (double) (coefficient1 * coefficient2));
                    }
                }
            }
            return result;
        }
    }
}
