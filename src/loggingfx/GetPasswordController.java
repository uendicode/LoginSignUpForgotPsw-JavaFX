/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package loggingfx;

import com.sun.java.swing.plaf.windows.resources.windows;
import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.Observable;
import java.util.ResourceBundle;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author hoxha
 */
public class GetPasswordController implements Initializable {
   
    PreparedStatement ps,ps1; 
     String ans;
       String pass;
    

    @FXML
    private TextField usernametxt;

    @FXML
    private TextField nametxt;

    @FXML
    private TextField questiontxt;

    @FXML
    private TextField answertxt;

    @FXML
    private TextField passtxt;

    @FXML
    private Button searchBtn;

    @FXML
    private Button getpswBtn;

    @FXML
    private Button backBtn;
    
    @FXML
    private Label errorLb;
    
    @FXML
    private Label errorAnswer;

    
     @FXML
    void searchPsw(ActionEvent event) throws IOException {
        ConnectSql connect = new ConnectSql();
       try {
            Connection con = connect.getconnection();
            
            passtxt.setText("");
            answertxt.setText("");
            
            String u_name = usernametxt.getText().trim();
            if(u_name.isEmpty()){
                errorLb.setText("Please insert username");
            }
            else {
                String sql = "select name, s_ques, answer, password from admin where username=?";
                PreparedStatement ps = con.prepareStatement(sql);
                ps.setString(1, u_name);
                
                ResultSet rs = ps.executeQuery();
                if(rs.next()){
                    nametxt.setText(rs.getString(1));
                    questiontxt.setText(rs.getString(2));
                    ans = rs.getString(3);
                    pass = rs.getString(4);
                    errorLb.setText("");
                    
                    ps.close();
                    rs.close();
                }
                else {
                    errorLb.setText("Error: Username is incorrect");
                }
                
                
            }
            
        } catch (Exception ex) {
            System.out.println("something wrong" + ex);
        } 
            
    }
    
     @FXML
    void retrivePsw(ActionEvent event) throws IOException {
        
          if(ans.equals(answertxt.getText().trim())){
            passtxt.setText(pass);
        }
        else {
         errorAnswer.setText("Your answer is wrong. Please try again");
        }
            
    }
    
    @FXML
    void backLogin(ActionEvent event) throws IOException {
       Parent view4=FXMLLoader.load(getClass().getResource("FXMLDocument.fxml"));
                Scene scene4=new Scene(view4);
                Stage window =(Stage)((Node)event.getSource()).getScene().getWindow();
                window.setScene(scene4);
                window.show();
     
    }
    
    

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    
    
}
