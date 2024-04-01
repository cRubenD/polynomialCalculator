package dataModels;

import exception.PolynomialException;

import javafx.scene.control.Alert;
import javafx.scene.control.TextField;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Polynomial {

    public Polynomial(TextField polynomial1, TextField polynomial2) {
    }

    // method to parse the polynomial in monomials
    public static void extractMonomials(String polynomial, Map<Integer, Integer> monomials) {
        try {
            exceptionCheck(polynomial);
        } catch (PolynomialException e){
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error");
            alert.setHeaderText("Invalid Inputs");
            alert.setContentText("Calculator only accepts x for variable and digits");
            alert.showAndWait();
        }

        int degree;
        int coefficient = 0;
        // special case if TextField is "x"
        if (polynomial.equals("x")) {
            coefficient = 1;
            degree = 1;
            monomials.put(degree, coefficient);
            return;
        }

        // the pattern for coefficient and degree
        Pattern pattern = Pattern.compile("([-+]?\\b\\d*)?[xX](?:\\^(-?\\d+))?|([+-]?\\b\\d+\\b)");
        Matcher matcher = pattern.matcher(polynomial);

        while (matcher.find()) {

            if(matcher.group().equals("x")){
                coefficient = 1;
                degree = 1;
                monomials.put(degree, coefficient);
                continue;
            }

            // all the special cases for degree
            if (matcher.group(2) == null && matcher.group().contains("x")) {
                degree = 1;
            } else if (matcher.group(2) == null) {
                degree = 0;
            } else
                degree = Integer.parseInt(matcher.group(2));
            if (degree > 1 && matcher.group(1).isEmpty()) {
                coefficient = 1;
                monomials.put(degree, coefficient);
                continue;
            }

            // all the special cases for the coefficient
            if ((matcher.group(1) != null && matcher.group().equals("+"))) {
                coefficient = 1;
            } else if (matcher.group(1) != null && matcher.group().equals("-")) {
                coefficient = -1;
            } else if (matcher.group(1) != null) {
                coefficient = Integer.parseInt(matcher.group(1));
            } else if (matcher.group(1) == null) {
                coefficient = Integer.parseInt(matcher.group());
            }

            monomials.put(degree, coefficient);
        }
    }

    // method to convert a map with degrees and coefficients to a polynomial string
    public static String convertPolynomial(Map<Integer, Double> monomials) {

        StringBuilder polynomial = new StringBuilder();
        // sort the map to print the polynomial from descending degree
        List<Map.Entry<Integer, Double>> polynomialSorted = new ArrayList<>(monomials.entrySet());
        polynomialSorted.sort((i1, i2) -> Integer.compare(i2.getKey(), i1.getKey()));
        int i = 0;

        for (Map.Entry<Integer, Double> entry : polynomialSorted) {

            int degree = entry.getKey();
            double coefficient = entry.getValue();
            if (coefficient == 0)
                continue;

            // special cases for coefficients and degrees
            polynomial.append(coefficient > 0 ? (i == 0) ? "" : "+" : "-");
            if (coefficient != 1 && coefficient != -1 || degree == 0) {
                if(coefficient - (int)coefficient == 0) {
                    polynomial.append((int) Math.abs(coefficient));
                } else {
                    polynomial.append(Math.abs(coefficient));
                }
            }
            polynomial.append(degree == 1 ? "x" : (degree > 1) ? "x^" + degree : "");
            i++;
        }

        // if the map is empty we need to print "0"
        if (polynomial.isEmpty()){
            return "0";
        }
        return polynomial.toString();
    }

    // exception for all the characters that are not digits, '^', '+', '-' or 'x'
    private static void exceptionCheck(String polynomial) throws PolynomialException {
        String validPattern = "^[0-9\\+\\-\\^xX\\s]*$";
        if(!polynomial.matches(validPattern)){
            throw new PolynomialException("Invalid Polynomial");
        }
    }
}
