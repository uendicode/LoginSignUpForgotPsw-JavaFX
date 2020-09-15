/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package loggingfx;

import java.io.IOException;
import java.net.URL;
import java.util.Optional;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author hoxha
 */
public class WelcomePageController implements Initializable {

    @FXML
    private Button logoutBtn;
       
    
    @FXML
    void logout(ActionEvent event) throws IOException {
        
        Alert alert =new Alert(AlertType.CONFIRMATION);
        alert.setTitle("Confirm logout");
        alert.setHeaderText(null);
        alert.setContentText("Continue logging out?");
        Optional <ButtonType> action =alert.showAndWait();
        
        if (action.get()==ButtonType.OK){
        Parent view5=FXMLLoader.load(getClass().getResource("FXMLDocument.fxml"));
                Scene scene5=new Scene(view5);
                Stage window =(Stage)((Node)event.getSource()).getScene().getWindow();
                window.setScene(scene5);
                window.show();
        }
      
    }
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    
    
}
