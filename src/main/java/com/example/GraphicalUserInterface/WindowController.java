package com.example.GraphicalUserInterface;

import exception.PolynomialException;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;
import java.util.HashMap;
import java.util.Map;

import static businessLogicTest.AddSub.addSub;
import static businessLogicTest.Derivative.derivative;
import static businessLogicTest.Division.division;
import static businessLogicTest.Integration.integration;
import static businessLogicTest.Multiplication.multiplication;
import static dataModels.Polynomial.convertPolynomial;
import static dataModels.Polynomial.extractMonomials;

// class for the interface control
public class WindowController {

    @FXML private ImageView closeBtn;
    @FXML private ImageView clear;

    @FXML private TextField polynomial1;
    @FXML private TextField polynomial2;
    @FXML private TextField result;

    @FXML private ImageView plus;
    @FXML private ImageView minus;
    @FXML private ImageView multiply;
    @FXML private ImageView division;
    @FXML private ImageView integral;
    @FXML private ImageView derivative;

    public WindowController() {
        // Default constructor needed for FXMLLoader
    }

    public void init(Stage stage){

        // close button to close the application
        closeBtn.setOnMouseClicked(mouseEvent -> {
            closeBtn.getScene().getWindow();
            stage.close();
        });

        // refresh button to clear all the text
        clear.setOnMouseClicked(mouseEvent -> {
            polynomial1.clear();
            polynomial2.clear();
            result.clear();
            result.setText("R:");
        });

        // all the methods for the operations with their exceptions

        Runnable performAddition = () -> {
            try {
                performOperation("+");
            } catch (PolynomialException e) {
                throw new RuntimeException(e);
            }
        };
        Runnable performSubtraction = () -> {
            try {
                performOperation("-");
            } catch (PolynomialException e) {
                throw new RuntimeException(e);
            }
        };

        Runnable performMultiplication = () -> {
            try {
                performMultiplicationOperation();
            } catch (PolynomialException e) {
                throw new RuntimeException(e);
            }
        };

        Runnable performDivision = () -> {
            try {
                performDivisionOperation();
            } catch (PolynomialException e) {
                throw new RuntimeException(e);
            }
        };

        Runnable performIntegral = () -> {
            try {
                performIntegralOperation();
            } catch (PolynomialException e) {
                throw new RuntimeException(e);
            }
        };

        Runnable performDerivative = () -> {
            try {
                performDerivativeOperation();
            } catch (PolynomialException e) {
                throw new RuntimeException(e);
            }
        };

        // Set mouse click event handlers using method references
        plus.setOnMouseClicked(mouseEvent -> performAddition.run());
        minus.setOnMouseClicked(mouseEvent -> performSubtraction.run());
        multiply.setOnMouseClicked(mouseEvent -> performMultiplication.run());
        division.setOnMouseClicked(mouseEvent -> performDivision.run());
        integral.setOnMouseClicked(mouseEvent -> performIntegral.run());
        derivative.setOnMouseClicked(mouseEvent -> performDerivative.run());

    }

    private void performOperation(String operation) throws PolynomialException {
        Map<Integer, Integer> monomials1 = new HashMap<>();
        extractMonomials(polynomial1.getText(), monomials1);
        Map<Integer, Integer> monomials2 = new HashMap<>();
        extractMonomials(polynomial2.getText(), monomials2);
        try {
            Map<Integer, Double> result1 = addSub(monomials1, monomials2, operation);
            result.setText(convertPolynomial(result1));
        } catch (PolynomialException e){
            showErrorAlert("ERROR: Cannot have both inputs null!", e.getMessage());
        } catch (NumberFormatException e) {
            showErrorAlert("Arithmetic error occurred!", e.getMessage());
        }
    }

    private void performMultiplicationOperation() throws PolynomialException {
        Map<Integer, Integer> monomials1 = new HashMap<>();
        extractMonomials(polynomial1.getText(), monomials1);
        Map<Integer, Integer> monomials2 = new HashMap<>();
        extractMonomials(polynomial2.getText(), monomials2);
        try {
            Map<Integer, Double> result1 = multiplication(monomials1, monomials2);
            result.setText(convertPolynomial(result1));
        } catch (PolynomialException e) {
            showErrorAlert("Multiplication error occurred!", e.getMessage());
        } catch (NumberFormatException e) {
            showErrorAlert("Arithmetic error occurred!", e.getMessage());
        }
    }

    private void performDivisionOperation() throws PolynomialException {
        try {
            result.setText(division(polynomial1.getText(), polynomial2.getText()));
        } catch (PolynomialException e) {
            showErrorAlert("Division error occurred!", e.getMessage());
        } catch (NumberFormatException e) {
            showErrorAlert("Arithmetic error occurred!", e.getMessage());
        }
    }

    private void performIntegralOperation() throws PolynomialException {
        try {
            result.setText("∫=" + integration(polynomial1.getText()));
        } catch (PolynomialException e) {
            showErrorAlert("Integration error occurred!", e.getMessage());
        }
    }

    private void performDerivativeOperation() throws PolynomialException {
        try {
            result.setText("d/dx=" + derivative(polynomial2.getText()));
        } catch (PolynomialException e) {
            showErrorAlert("Derivative error occurred!", e.getMessage());
        }
    }

    // method to display an error message in the interface
    private void showErrorAlert(String header, String message) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("Error");
        alert.setHeaderText(header);
        alert.setContentText(message);
        alert.showAndWait();
    }
}
