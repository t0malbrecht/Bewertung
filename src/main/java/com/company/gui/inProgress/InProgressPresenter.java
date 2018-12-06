package com.company.gui.inProgress;


import com.company.services.Driver;
import com.company.services.SetRating;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;

import java.io.IOException;

public class InProgressPresenter {
    @FXML
    TextArea outputFailure;
    @FXML
    TextArea outputSuccess;
    @FXML
    Label progress;

    static int SizeOfArray;
    int currentlyFinished;
    static InProgressPresenter instance;
    public InProgressPresenter(){
        instance = this;
    }

    public static InProgressPresenter getInstance(){
        return instance;
    }

    public void onAbort(){
        if (Driver.driver != null) {
            Driver.driver.quit();
        }
        Platform.exit();
        System.exit(0);
    }

    public void setSizeOfArray(int size){
        SizeOfArray = size;
    }

    public void setProgressBar(){
        currentlyFinished = currentlyFinished +1;
        progress.setText(currentlyFinished +" / "+ SizeOfArray);
    }

    public void updateOutputFailure(String Meldung){
        Platform.runLater(() -> {
            outputFailure.setText(outputFailure.getText() + Meldung + "\n");
            setProgressBar();
        });
    }

    public void updateOutputSuccess(String Meldung){
        Platform.runLater(() -> {
            outputSuccess.setText(outputSuccess.getText() + Meldung + "\n");
            setProgressBar();
        });
    }
}
