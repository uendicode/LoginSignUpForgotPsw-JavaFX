/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package loggingfx;

import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;

/**
 * FXML Controller class
 *
 * @author hoxha
 */
public class SignupPageController implements Initializable {
    
     
   
    ObservableList<String> questionBoxList = FXCollections.observableArrayList("What's your pet's name?","What's your favorite food?","Who was your childhood hero?");
   
    
PreparedStatement ps,ps1; 

    @FXML
    private Button closeBtn;

    @FXML
    private TextField nameTxt;

    @FXML
    private TextField usernameTxt;

    @FXML
    private PasswordField passwordTxt;

    @FXML
    private ComboBox questionBox;

    @FXML
    private TextField answerTxt;

    @FXML
    private Button createBtn;
    
    @FXML
    private Label errorMessage;
    
    
     @FXML
    void register(ActionEvent event) throws IOException {
       ConnectSql connect = new ConnectSql();
        
        try{
            
            Connection con = connect.getconnection();
            
            String username = usernameTxt.getText().trim();
            String password = passwordTxt.getText().trim();
            String name = nameTxt.getText().trim();
            String ques = questionBox.getValue().toString();
            String ans = answerTxt.getText().trim();
            
            if(username.isEmpty() || password.isEmpty() || name.isEmpty() || ques.isEmpty() || ans.isEmpty()){
                errorMessage.setText("Please complete all the fills");
            }
            else {
               if (password.length()<6){
                   errorMessage.setText("Password is too weak, please choose atleast 6 characters");
               }
            
            else {
                   
            String sql = "select * from admin where username = ?";
            ps = con.prepareStatement(sql);
            ps.setString(1, username);
            
            ResultSet rs = ps.executeQuery();
            if(rs.next()){
                errorMessage.setText("Username already taken, please try another username");
            }
            
            else{
                
            String sql2 = "insert into admin (username, password, name, s_ques, answer) values(?,?,?,?,?)";     
            ps = con.prepareStatement(sql2);
            
            ps.setString(1, usernameTxt.getText().trim());
            ps.setString(2, passwordTxt.getText().trim());
            ps.setString(3, nameTxt.getText().trim());
            ps.setString(4, questionBox.getValue().toString());
            ps.setString(5, answerTxt.getText().trim());
            
            ps.execute();
            
            errorMessage.setText("Account successfully registered");
         
        }
       }
        }
       }
        catch(Exception e)
        {
            System.out.println("error" + e);
        }
    }
    
    
    
    

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        questionBox.setValue("Choose your question");
    questionBox.setItems(questionBoxList);
    }  
    
  
    
}
