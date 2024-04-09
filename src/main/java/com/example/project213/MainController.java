package com.example.project213;


import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.input.KeyEvent;

public class MainController {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private RadioButton baRButton;

    @FXML
    private Label degreeErrorLable;

    @FXML
    private RadioButton dsRButton;

    @FXML
    private TextField emailField;

    @FXML
    private TextField engscoreField;

    @FXML
    private Button formExplorerStartButton;

    @FXML
    private RadioButton gmRButton;

    @FXML
    private TextField lastnameField;

    @FXML
    private TextField mathscoreField;

    @FXML
    private RadioButton msRButton;

    @FXML
    private TextField nameField;

    @FXML
    private TextField phoneField;

    @FXML
    private TextArea reportTextArea;

    @FXML
    private Label scoreErrorLable;

    @FXML
    private RadioButton seRButton;

    @FXML
    private Button submitButton;

    @FXML
    private TextField ukrscoreField;

    private boolean DoInputsCorrect = false;
    private int radioCheck = 0;

    @FXML
    void initialize() {

        submitButton.setOnAction(event -> {
            checkEmpty(nameField);
            checkEmpty(lastnameField);
            checkEmpty(phoneField);
            checkEmpty(emailField);
            checkEmpty(mathscoreField);
            checkEmpty(ukrscoreField);
            checkEmpty(engscoreField);
            checkScore(mathscoreField);
            checkScore(ukrscoreField);
            checkScore(engscoreField);
            radioCheck = checkRadioButton(dsRButton) + checkRadioButton(seRButton) + checkRadioButton(msRButton) + checkRadioButton(baRButton) + checkRadioButton(gmRButton);
            if(radioCheck == 0){
                degreeErrorLable.setVisible(true);
            }
            else if (DoInputsCorrect == true){
                degreeErrorLable.setVisible(false);
                ArrayList<String> degrees = new ArrayList<>();
                if(checkRadioButton(dsRButton) == 1){degrees.add("DS");}
                if(checkRadioButton(seRButton) == 1){degrees.add("SE");}
                if(checkRadioButton(msRButton) == 1){degrees.add("MS");}
                if(checkRadioButton(baRButton) == 1){degrees.add("BA");}
                if(checkRadioButton(gmRButton) == 1){degrees.add("GM");}

                reportTextArea.setDisable(false);
                reportTextArea.setText(nameField.getText() + " " + lastnameField.getText() +
                        "\n" + phoneField.getText() +
                        "\n" + emailField.getText() +
                        "\nMath        - " + mathscoreField.getText() +
                        "\nUkrainian - " + ukrscoreField.getText() +
                        "\nEnglish     - " + engscoreField.getText() +
                        "\nAverage   - " + ((Integer.valueOf(mathscoreField.getText()) + Integer.valueOf(ukrscoreField.getText()) + Integer.valueOf(engscoreField.getText())) / 3) +
                        "\nDegree(s) - " + degrees
                );
                try (BufferedWriter writer = new BufferedWriter(new FileWriter(nameField.getText() + " " + lastnameField.getText()))) {
                    writer.write(reportTextArea.getText());
                } catch (IOException e) {
                }
            }
        });

        //apply num filter
        applyNumericFilter(mathscoreField);
        applyNumericFilter(ukrscoreField);
        applyNumericFilter(engscoreField);
        applyNumericFilter(phoneField);

    }

    //num filter method
    private void applyNumericFilter(TextField textField) {
        textField.addEventFilter(KeyEvent.KEY_TYPED, event -> {
            if (!event.getCharacter().matches("[0-9]")) {
                event.consume();
            }
        });
    }

    private void checkEmpty(TextField textField) {
        if(textField.getText().equals("")){
            textField.setStyle("-fx-border-color: ffcc20; -fx-border-width: 3; -fx-border-radius: 20; -fx-background-radius: 20; -fx-focus-color: transperent;");
            DoInputsCorrect = false;
        }
        else{
            textField.setStyle("-fx-border-color: d8d8d8; -fx-border-width: 3; -fx-border-radius: 20; -fx-background-radius: 20; -fx-focus-color: transperent;");
            DoInputsCorrect = true;
        }
    }
    private void checkScore(TextField textField) {
        if(Integer.valueOf(textField.getText()) < 100 || Integer.valueOf(textField.getText()) > 200){
            textField.setStyle("-fx-border-color:  D01822; -fx-border-width: 3; -fx-border-radius: 20; -fx-background-radius: 20; -fx-focus-color: transperent;");
            scoreErrorLable.setVisible(true);
            DoInputsCorrect = false;
        }
        else{
            textField.setStyle("-fx-border-color: d8d8d8; -fx-border-width: 3; -fx-border-radius: 20; -fx-background-radius: 20; -fx-focus-color: transperent;");
            scoreErrorLable.setVisible(false);
            DoInputsCorrect = true;
        }
    }
    private int checkRadioButton(RadioButton radioButton) {
        if(!radioButton.isSelected()){
            return 0;
        }
        else {
            return 1;
        }
    }

}
