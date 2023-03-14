/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Lecture11;

import java.io.*;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;

import javafx.scene.Scene;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.scene.control.Slider;
import javafx.scene.control.TextArea;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.web.WebView;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

/**
 * @author aashgar
 */
public class MainScreen extends Stage {
    private MenuBar menuBar;
    private Menu menuFile, menuColor, menuAbout;
    private MenuItem menuItemOpen, menuItemClear, menuItemSave, menuItemExit,
            menuItemGold, menuItemCyan, menuItemGrey, menuItemAbout;
    private TextArea textArea;
    private Slider sliderFontSize;
    private File fileSelected;
private String filePath;
    public MainScreen() {
        menuBar = new MenuBar();

        menuFile = new Menu("File");
        menuColor = new Menu("Color");
        menuAbout = new Menu("About");

        menuItemOpen = new MenuItem("Open");
        menuItemOpen.setOnAction(e->{
            FileChooser fileChooser=new FileChooser();
            fileSelected=fileChooser.showOpenDialog(this);
            filePath =fileSelected.getAbsolutePath();
            try {
                Scanner fileReader=new Scanner(fileSelected);
                textArea.clear();
                while (fileReader.hasNextLine()){
                    textArea.appendText(fileReader.nextLine()+"\n");
                }
                fileReader.close();
            } catch (FileNotFoundException ex) {
                throw new RuntimeException(ex);
            }
        });

        menuItemClear = new MenuItem("Clear");
        menuItemClear.setOnAction(e-> textArea.setText(""));

        menuItemSave = new MenuItem("Save");
        menuItemSave.setOnAction(e->{
            try {
                File file = new File(filePath);
                FileWriter fileWriter = new FileWriter(file);
                PrintWriter printWriter = new PrintWriter(fileWriter);
                printWriter.print(textArea.getText());
                fileWriter.close();
                printWriter.close();
            } catch (FileNotFoundException ex) {
                throw new RuntimeException(ex);
            } catch (IOException ex) {
                throw new RuntimeException(ex);
            }
        });

        menuItemExit = new MenuItem("Exit");
        menuItemExit.setOnAction(e-> this.close());


        menuItemGold = new MenuItem("Gold");
        menuItemCyan = new MenuItem("Cyan");
        menuItemGrey = new MenuItem("Grey");
        // menu color handle
        menuItemGold.setOnAction(e
                -> {
            textArea.setStyle("-fx-text-fill:gold;-fx-font-size: "
                    + sliderFontSize.getValue() + "pt");
        });
        menuItemCyan.setOnAction(e
                -> {
            textArea.setStyle("-fx-text-fill:cyan;-fx-font-size: "
                    + sliderFontSize.getValue() + "pt");
        });
        menuItemGrey.setOnAction(e
                -> {
            textArea.setStyle("-fx-text-fill:grey;-fx-font-size: "
                    + sliderFontSize.getValue() + "pt");
        });

        menuItemAbout = new MenuItem("About");
        menuItemAbout.setOnAction(event -> {
            textArea.setText("Name: Ahmad Alketnani \nID : 120200317 \nAge : 20 \nI'm MERN Stack Developer");
        });

        menuFile.getItems().addAll(menuItemOpen, menuItemSave, menuItemClear, menuItemExit);
        menuColor.getItems().addAll(menuItemGold, menuItemCyan, menuItemGrey);
        menuAbout.getItems().addAll(menuItemAbout);

        menuBar.getMenus().addAll(menuFile, menuColor, menuAbout);


        textArea = new TextArea("Playing with JavaFX Advanced Controls");
        textArea.setMaxHeight(300);
        textArea.setMaxWidth(500);
        sliderFontSize = new Slider(5, 35, 12);
        sliderFontSize.setMajorTickUnit(5);
        sliderFontSize.setMinorTickCount(4);
        sliderFontSize.setShowTickLabels(true);
        sliderFontSize.setShowTickMarks(true);
        sliderFontSize.setSnapToTicks(true);
        ComboBox<String> comboBoxLinks = new ComboBox<>();
        comboBoxLinks.getItems().addAll("Instructor GitHub",
                "JavaFX Guide", "My GitHub");
        comboBoxLinks.getSelectionModel().select(1);
        WebView webView = new WebView();

        HBox hBox1 = new HBox(15, comboBoxLinks, webView);

        VBox vBox1 = new VBox(15, textArea, sliderFontSize,
                hBox1);

        BorderPane borderPane = new BorderPane();
        borderPane.setTop(menuBar);
        borderPane.setCenter(vBox1);

        Scene scene = new Scene(borderPane, 1000, 700);
        this.setScene(scene);
        this.setTitle("Advanced JavaFX Screen");
        sliderFontSize.valueProperty().addListener(e -> {
            textArea.setStyle("-fx-font-size: " +
                    sliderFontSize.getValue() + "pt");
        });
        comboBoxLinks.setOnAction(e -> {
            if (comboBoxLinks.getSelectionModel()
                    .getSelectedIndex() == 0) {
                webView.getEngine()
                        .load("https://github.com/aashgar");
            } else if (comboBoxLinks.getSelectionModel()
                    .getSelectedIndex() == 1) {
                webView.getEngine().load("https://openjfx.io/openjfx-docs/");
            } else {
                webView.getEngine().load("https://github.com/AhmadAlketnani");
            }
        });

    }

}
