package sample;

import java.util.ArrayList;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.VBox;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;

import javafx.scene.control.Label;

public class Main extends Application {

    private ArrayList<Password> passwords = new ArrayList<Password>();

    @Override
    public void start(Stage primaryStage) throws Exception{
        Parent root = FXMLLoader.load(getClass().getResource("sample.fxml"));

        passwords.add(new Password("martin1", "Bickor", "martin.heberling.dalies@gmail.com"));
        passwords.add(new Password("martin2", "Bickor2", "martin.heberling.dalies@gmail.com2"));

        //Holder for all passwords
        VBox holder = new VBox();

        //Headers
        HBox headers = new HBox();
        Label userName = new Label("Username");
        Label email = new Label("Email");
        Label password = new Label("Password");
        headers.getChildren().addAll(userName, email, password);

        holder.getChildren().add(headers);

        for (int i = 0; i < passwords.size(); i++) {

            holder.getChildren().add(new HBox(new Label(passwords.get(i).getUserName()),
                    new Label(passwords.get(i).getEmail()),
                    new Label(passwords.get(i).getPassword())));
        }
        primaryStage.setTitle("Password Manager");
        primaryStage.setScene(new Scene(holder, 300, 275));
        primaryStage.show();
    }

    //TODO Make a place to see passwords


    public static void main(String[] args) {
        launch(args);
    }
}
