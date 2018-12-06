package com.company.gui.setup;

import com.company.gui.StageController;
import com.company.gui.inProgress.InProgressView;
import com.company.services.CSVReader;
import com.company.services.SetRating;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

public class SetupPresenter {

    @FXML
    Label info;
    private String path;


    public void onSelect(){
        FileChooser chooser = new FileChooser();
        chooser.setTitle("Open File");
        path = chooser.showOpenDialog(StageController.getWindow()).getAbsolutePath();

        CSVReader.setCsvFilePath(path);
        info.setText(path);
    }

    public void onStart(){
        Platform.runLater(() -> {
            Scene scene = StageController.getScene();
            InProgressView inProgressScreen = new InProgressView();
            scene.setRoot(inProgressScreen.getView());


            SetRating app = new SetRating();
            new Thread(app).start();
        });
    }

}
