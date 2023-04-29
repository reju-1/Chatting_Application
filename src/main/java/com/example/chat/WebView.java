package com.example.chat;

import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.web.WebEngine;
import javafx.scene.web.WebHistory;

import java.net.URL;
import java.util.ResourceBundle;

public class WebView implements Initializable {


    @FXML
    private javafx.scene.web.WebView webView;

    private WebEngine engine;
    private double zoom;
    private WebHistory history;

    @FXML
    void goBack() {
        history = engine.getHistory();
        ObservableList<WebHistory.Entry> list   = history.getEntries();
        history.go(-1);
    }

    @FXML
    void goForward(){
        history = engine.getHistory();
        ObservableList<WebHistory.Entry> list   = history.getEntries();
        history.go(1);
    }

    @FXML
    void google() {
        engine.load("https://google.com/");
    }

    @FXML
    void reloadPage(ActionEvent event) {
        engine.reload();
    }

    @FXML
    void zoomIn() {
        zoom += .25;
        webView.setZoom(zoom);
    }

    @FXML
    void zoomOut() {
        zoom -= .25;
        webView.setZoom(zoom);
    }


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        zoom = 1;
        engine = webView.getEngine();
        engine.load("https://www.crazygames.com/");
    }




}