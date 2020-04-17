package sample;

import java.util.ArrayList;

import java.io.File;
import java.io.FileWriter;
import java.io.FileNotFoundException;
import java.util.Scanner;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import javafx.scene.layout.VBox;
import javafx.scene.layout.HBox;
import javafx.scene.control.Label;
import javafx.scene.control.Button;

public class Main extends Application {

    private static ArrayList<Password> passwords = new ArrayList<Password>();

    @Override
    public void start(Stage primaryStage) throws Exception{
        //Parent root = FXMLLoader.load(getClass().getResource("sample.fxml"));
        primaryStage.setTitle("Password Manager");

        VBox root = new VBox();
        Label name = new Label("Password Manager");
        Button enter = new Button("Enter");
        enter.setOnMouseClicked(e -> {
            showPasswords(primaryStage);
        });

        root.getChildren().addAll(name, enter);

        primaryStage.setScene(new Scene(root));
        primaryStage.show();
    }

    public static void showPasswords(Stage primaryStage) {
        primaryStage.setTitle("Password Manager");

        passwords.add(new Password("Bickor", "martin.heberling.dalies@gmail.com", "martin1"));
        passwords.add(new Password("Bickor2", "martin.heberling.dalies@gmail.com2", "martin2"));

        //Holder for all passwords
        VBox holder = new VBox();

        //Headers
        HBox headers = new HBox();
        Label userName = new Label("Username");
        Label email = new Label("Email");
        Label password = new Label("Password");
        headers.getChildren().addAll(userName, email, password);
        holder.getChildren().add(headers);

        //FIXME
        // - when i add passwords, and come back, passwords are repeated.
        //Add every password to list
        for (int i = 0; i < passwords.size(); i++) {

            holder.getChildren().add(new HBox(new Label(passwords.get(i).getUserName()),
                    new Label(passwords.get(i).getEmail()),
                    new Label(passwords.get(i).getPassword())));
        }

        //Button to add passwords
        Button add = new Button("Add Password");
        add.setOnMouseClicked(e -> {
            addPasswordPage(primaryStage);
        });

        holder.getChildren().add(add);
        primaryStage.setScene(new Scene(holder));
    }

    public static void addPasswordPage(Stage primaryStage) {
        primaryStage.setTitle("Add Password");
        VBox root = new VBox();

        //Username
        HBox userNameInput = new HBox();
        Label username = new Label("Username");
        TextField usernameText = new TextField("");
        userNameInput.getChildren().addAll(username, usernameText);

        //Email
        HBox emailInput = new HBox();
        Label email = new Label("Email");
        TextField emailText = new TextField("");
        emailInput.getChildren().addAll(email, emailText);

        //Password
        HBox passwordInput = new HBox();
        Label password = new Label("Password");
        TextField passwordText = new TextField("");
        passwordInput.getChildren().addAll(password, passwordText);

        Button done = new Button("Done");
        done.setOnMouseClicked(e -> {
            if (usernameText.getText() != null
                    && emailText.getCharacters() != ""
                    && passwordText.getCharacters() != "") {
                passwords.add(new Password(usernameText.getCharacters().toString(),
                        emailText.getCharacters().toString(),
                        passwordText.getCharacters().toString()));
                showPasswords(primaryStage);
            } else {
                System.out.println("NEED TO FILL SPACES");
            }
        });

        root.getChildren().addAll(userNameInput, emailInput, passwordInput, done);

        Scene addPassword = new Scene(root);
        primaryStage.setScene(addPassword);
    }

    public static void main(String[] args) {
        launch(args);
    }
}
