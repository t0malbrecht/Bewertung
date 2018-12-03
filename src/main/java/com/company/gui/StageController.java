package com.company;

import com.company.gui.setup.SetupView;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;


public class StageController extends Application {
    private static Scene scene;
    private static Stage window;

    public static void launcher(String[] args) {
        launch(args);
    }

    public static Scene getScene() {
        return scene;
    }
    public static Stage getWindow() {
        return window;
    }

    @Override
    public void start(Stage stage) {
        window = stage;

        SetupView setupScreen = new SetupView();
        Scene scene = new Scene(setupScreen.getView());
        this.scene = scene;
        window.setScene(scene);
        window.setTitle("ScotlandYard");
        window.show();

    }

}
