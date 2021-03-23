package Controller;
import Domain.Employee;
import Service.Service;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

import java.io.IOException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class LogInController {
    @FXML
    private TextField txtUsername;
    @FXML
    private PasswordField txtPassword;
    @FXML
    private Label lblStatus;


    Service service;

    Stage stage;

    public void setService(Service service, Stage stage){
        this.service=service;
        this.stage=stage;
    }

    @FXML
    public void signIn() throws IOException {
        String user = txtUsername.getText();
        String pass = txtPassword.getText();
        Employee employee = service.getOneEmployee(user);
        if (user.length()!=0 &&
                pass.length()!=0 &&
                employee!=null &&
                employee.getPassword().equals(pass)) {
            loadMainStage();
            stage.close();
        }
        else {
            lblStatus.setText("Sign in failed");
            lblStatus.setTextFill(Color.web("#ba170b"));
        }

    }

    private void loadMainStage() throws IOException {
        Stage newStage = new Stage();
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("/views/MainPage.fxml"));
        AnchorPane layout = loader.load();
        newStage.setScene(new Scene(layout));
        newStage.setTitle("Flight Agency");
        newStage.getIcons().add(new Image("images/plane.png"));
        newStage.show();

        MainController mainController=loader.getController();
        mainController.setService(service, newStage, stage);
    }


    public void handleSignUp(ActionEvent actionEvent) {
    }

    public String hashPassword(String password){
        String passwordToHash=password;
        String generatedPassword=null;
        try{
            MessageDigest md=MessageDigest.getInstance("MD5");
            md.update(passwordToHash.getBytes());
            byte[] bytes=md.digest();
            StringBuilder sb=new StringBuilder();
            for (int i=0; i<bytes.length; i++){
                sb.append(Integer.toString((bytes[i]&0xff)+0x100,32).substring(1));
            }
            generatedPassword=sb.toString();
            return generatedPassword;
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
            return null;
        }
    }
}
