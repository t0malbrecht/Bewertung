package com.company.gui.inProgress;


import com.company.services.Driver;
import com.company.services.SetRating;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.scene.control.ProgressBar;
import javafx.scene.control.TextArea;

import java.io.IOException;

public class InProgressPresenter {
    @FXML
    TextArea outputFailure;
    @FXML
    TextArea outputSuccess;
    @FXML
    ProgressBar progress;

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

    }

    public void setSizeOfArray(int size){
        SizeOfArray = size;
    }

    public void setProgressBar(){
        currentlyFinished = currentlyFinished+1;
        double progress1 = currentlyFinished/SizeOfArray;
        progress.setProgress(progress1);
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
