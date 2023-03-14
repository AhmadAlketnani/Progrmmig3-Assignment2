/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Lecture9;

import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.util.ArrayList;

/**
 * @author aashgar
 */
public class MainApp3 extends Application {
    private ListView<String> listViewScr, listViewDes;
    private TextField textFieldItem;
    private CheckBox checkBoxMultSel;
    private RadioButton radioButtonGold, radioButtonCyan;
    private Button buttonAdd, buttonDel, buttonUpdate,
            buttonCopy, buttonClear;

    @Override
    public void start(Stage stage) throws Exception {
        ArrayList<String> items = new ArrayList<>();
        items.add("Ahmad");
        items.add("Noor");
        listViewScr = new ListView<>(FXCollections
                .observableArrayList(items));

        listViewDes = new ListView<>();
        listViewScr.setPrefSize(150, 100);
        listViewDes.setPrefSize(150, 100);
        HBox hBoxListViews = new HBox(15, listViewScr, listViewDes);
        textFieldItem = new TextField();
        checkBoxMultSel = new CheckBox("Muttiple Sel");
        radioButtonGold = new RadioButton("Gold");
        radioButtonCyan = new RadioButton("Cyan");
        ToggleGroup toggleGroup = new ToggleGroup();
        radioButtonGold.setToggleGroup(toggleGroup);
        radioButtonCyan.setToggleGroup(toggleGroup);
        HBox hBoxRadios = new HBox(20, radioButtonGold, radioButtonCyan);
        hBoxRadios.setAlignment(Pos.CENTER);
        buttonAdd = new Button("Add");
        buttonDel = new Button("Del");
        buttonUpdate = new Button("Update");
        buttonCopy = new Button("Copy");
        buttonClear = new Button("Clear");
        HBox hBoxButtons = new HBox(10, buttonAdd, buttonDel, buttonUpdate,
                buttonCopy, buttonClear);
        hBoxButtons.setAlignment(Pos.CENTER);
        VBox vBoxAll = new VBox(10, hBoxListViews, textFieldItem,
                checkBoxMultSel, hBoxRadios, hBoxButtons);
        vBoxAll.setPadding(new Insets(20));
        FlowPane flowPane = new FlowPane(vBoxAll);
        hBoxListViews.setAlignment(Pos.CENTER);
        vBoxAll.setAlignment(Pos.CENTER);
        flowPane.setAlignment(Pos.CENTER);
        Scene scene = new Scene(flowPane, 600, 400);
        stage.setScene(scene);
        scene.getStylesheets().add(getClass().
                getResource("styles1.css").toExternalForm());
        stage.setTitle("More Advanced Controls");
        stage.show();
        radioButtonGold.setOnAction(e -> {
            vBoxAll.setStyle("-fx-background-color: Gold");
        });
        radioButtonCyan.setOnAction(event -> {
            vBoxAll.setStyle("-fx-background-color: Cyan");
        });
        listViewScr.getSelectionModel().selectedItemProperty().
                addListener(e -> {
                    textFieldItem.setText(listViewScr.getSelectionModel().getSelectedItem());
                    if (checkBoxMultSel.isSelected()) {
                        textFieldItem.setText(listViewScr.getSelectionModel().getSelectedItems().toString());
                    }
                });
        buttonAdd.setOnAction(e -> {
            listViewScr.getItems().add(textFieldItem.getText());
            textFieldItem.setText("");


//            items.add(textFieldItem.getText());
//            listViewScr.setItems(FXCollections.observableArrayList(items));
        });
        buttonDel.setOnAction(event -> {
//            items.remove(listViewScr.getSelectionModel().getSelectedItem());
//            listViewScr.setItems(FXCollections.observableArrayList(items));


            listViewScr.getItems().remove(listViewScr.getSelectionModel().getSelectedItem());
            textFieldItem.setText("");


        });
        buttonUpdate.setOnAction(e -> {
//            int index=items.indexOf(listViewScr.getSelectionModel().getSelectedItem());
//            items.set(index,textFieldItem.getText());
//            listViewScr.setItems(FXCollections.observableArrayList(items));


            listViewScr.getItems().set(listViewScr.getSelectionModel().getSelectedIndex(), textFieldItem.getText());
            textFieldItem.setText("");

        });
        buttonCopy.setOnAction(e -> {
            listViewDes.getItems().addAll(listViewScr.getSelectionModel().getSelectedItems());
        });

        buttonClear.setOnAction(e -> {
            listViewScr.getItems().clear();
            listViewDes.getItems().clear();
        });

        checkBoxMultSel.setOnAction(e -> {
            if (checkBoxMultSel.isSelected()) {
                listViewScr.getSelectionModel().setSelectionMode(SelectionMode.MULTIPLE);
            } else {
                listViewScr.getSelectionModel().setSelectionMode(SelectionMode.SINGLE);
            }
        });


    }


    public static void main(String[] args) {
        launch(args);
    }
}
