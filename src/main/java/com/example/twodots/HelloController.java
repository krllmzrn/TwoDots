package com.example.twodots;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;

import java.io.IOException;

public class HelloController {


    @FXML
    private Button LowLvl;
    @FXML
    public void LowLvl(ActionEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("low-lvl.fxml"));
        Parent root = loader.load();
        Scene scene = LowLvl.getScene();
        scene.setRoot(root);
    }

    @FXML
    private Button MediumLvl;
    @FXML
    public void MediumLvl(ActionEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("medium-lvl.fxml"));
        Parent root = loader.load();
        Scene scene = MediumLvl.getScene();
        scene.setRoot(root);
    }


    @FXML
    private Button HardLvl;
    @FXML
    public void HardLvl(ActionEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("hard-lvl.fxml"));
        Parent root = loader.load();
        Scene scene = HardLvl.getScene();
        scene.setRoot(root);
    }



}