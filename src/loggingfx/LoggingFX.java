/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package loggingfx;

import java.io.IOException;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

/**
 *
 * @author hoxha
 */
public class LoggingFX extends Application {
   private Stage primaryStage;
   

    @Override
    public void start(Stage primaryStage) {
        this.primaryStage = primaryStage; // connect primary stage
        
        mainWindow();
    }

    // main window
    public void mainWindow() {
        try {
            // view
            FXMLLoader loader = new FXMLLoader(LoggingFX.class.getResource("FXMLDocument.fxml"));
            AnchorPane pane = loader.load();

            // controller
            FXMLDocumentController mainWindowController = loader.getController();
            mainWindowController.setMain(this);

            // scene on stage
            Scene scene = new Scene(pane);
            primaryStage.setScene(scene);
            primaryStage.initStyle(StageStyle.UNDECORATED);
            primaryStage.show();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    
    public static void main(String[] args) {
        launch(args);
    }
    
}
