/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Lecture11;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

/**
 * @author aashgar
 */
public class MainApp extends Application {
    private Label labelTitle, labelError;
    private TextField textFieldLoginName;
    private PasswordField passwordField;
    private Button buttonSubmit, buttonCancel;
    private Stage stage;

    @Override
    public void start(Stage primaryStage) throws Exception {
        labelTitle = new Label("Login Information");
        textFieldLoginName = new TextField();
        textFieldLoginName.setPromptText("Login Name");
        passwordField = new PasswordField();
        passwordField.setPromptText("Password");
        labelError = new Label("Initial text");
        labelError.setId("label-error");
        VBox vBox1 = new VBox(10, labelTitle, textFieldLoginName, passwordField, labelError);
        vBox1.setAlignment(Pos.CENTER);
        vBox1.setStyle("-fx-border-color: red");

        buttonSubmit = new Button("Submit");
        buttonCancel = new Button("Cancel");
        buttonSubmit.setOnAction(new MyEventHandler());
        buttonCancel.setOnAction(new MyEventHandler());
        HBox hBox1 = new HBox();
        hBox1.getChildren().addAll(buttonSubmit, buttonCancel);
        hBox1.setSpacing(20);
        hBox1.setAlignment(Pos.CENTER);
        hBox1.setStyle("-fx-border-color: lime");

        VBox vBox2 = new VBox(10, vBox1, hBox1);
        vBox2.setAlignment(Pos.CENTER);
        vBox2.setStyle("-fx-border-color: navy");
        vBox2.setPadding(new Insets(10, 20, 10, 20));
        vBox2.getStyleClass().add("vbox");

        FlowPane flowPane = new FlowPane(vBox2);
        flowPane.setAlignment(Pos.CENTER);

        Scene scene = new Scene(flowPane, 400, 300);
//        scene.getStylesheets().add("file:src/Ch2Part2Apps2/styles.css");
        scene.getStylesheets().add(getClass().getResource("styles.css").toExternalForm());
        primaryStage.setScene(scene);
        primaryStage.setTitle("Login Screen");
        primaryStage.show();
        stage = primaryStage;
    }

    public static void main(String[] args) {
        launch(args);
    }

    private class MyEventHandler implements EventHandler<ActionEvent> {

        @Override
        public void handle(ActionEvent event) {
            if (event.getSource() == buttonSubmit) {
                File loginFile;
                try {
                    loginFile = new File("./src/Lecture11/password.txt");
                    Scanner readerFile = new Scanner(loginFile);
                    boolean notLogin = true;
                    while (readerFile.hasNextLine()) {
                        if (textFieldLoginName.getText().equals(readerFile.next()) &&
                                passwordField.getText().equals(readerFile.next())

                        ) {
                            labelError.setText("Valid User");
                            readerFile.close();
                            stage.close();
                            new MainScreen().show();
                        }
                    }
                    if (notLogin) labelError.setText("Invalid Data");
                } catch (Exception e) {
                    System.out.println(e.getMessage());
                }


//                if(textFieldLoginName.getText().equals("a") &&
//                        passwordField.getText().equals("1")){
//                    labelError.setText("Valid User");
//                    stage.close();
//                    new MainScreen().show();
//
//                }
//                else
//                    labelError.setText("Invalid Data");
            } else if (event.getSource() == buttonCancel) {
                textFieldLoginName.setText("");
                passwordField.setText("");
            }
        }

    }
}
