package sample;

import java.io.IOException;
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
            showPasswordsScreen(primaryStage);
        });

        root.getChildren().addAll(name, enter);
        loadPasswords("passwords.txt");
        primaryStage.setScene(new Scene(root, 500, 800));
        primaryStage.show();
    }

    public static void showPasswordsScreen(Stage primaryStage) {
        primaryStage.setTitle("Password Manager");

        //Holder for all passwords
        VBox holder = new VBox();

        //Headers
        HBox headers = new HBox();
        Label userName = new Label("Username");
        Label email = new Label("Email");
        Label password = new Label("Password");
        headers.getChildren().addAll(userName, email, password);
        holder.getChildren().add(headers);

        //Add every password to list
        for (int i = 0; i < passwords.size(); i++) {

            holder.getChildren().add(new HBox(new Label(passwords.get(i).getUserName()),
                    new Label(passwords.get(i).getEmail()),
                    new Label(passwords.get(i).getPassword())));
        }

        //Button to add passwords
        Button add = new Button("Add Password");
        add.setOnMouseClicked(e -> {
            addPasswordScreen(primaryStage);
        });

        holder.getChildren().add(add);
        primaryStage.setScene(new Scene(holder, primaryStage.getWidth(), primaryStage.getHeight() - 22));
    }

    public static void addPasswordScreen(Stage primaryStage) {
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

                //Add it to arraylist
                passwords.add(new Password(usernameText.getCharacters().toString(),
                        emailText.getCharacters().toString(),
                        passwordText.getCharacters().toString()));
                try {
                    FileWriter fileWriter = new FileWriter("passwords.txt", true);
                    fileWriter.append(usernameText.getCharacters().toString()
                            + "," + emailText.getCharacters().toString()
                            + "," + passwordText.getCharacters().toString());
                    fileWriter.append(System.lineSeparator());
                    fileWriter.close();
                } catch (IOException y){
                    System.out.println("An error occurred.");
                    y.printStackTrace();
                }
                showPasswordsScreen(primaryStage);
            } else {
                System.out.println("NEED TO FILL SPACES");
            }
        });

        Button back = new Button("Back");
        back.setOnMouseClicked(e -> {
            showPasswordsScreen(primaryStage);
        });

        root.getChildren().addAll(userNameInput, emailInput, passwordInput, done, back);

        Scene addPassword = new Scene(root, primaryStage.getWidth(), primaryStage.getHeight() - 22);
        primaryStage.setScene(addPassword);
    }

    public static void loadPasswords(String passwordsFile) {
        try {
            //Load all passwords into the arraylist
            File file = new File(passwordsFile);
            Scanner scanner = new Scanner(file);
            while (scanner.hasNextLine()) {
                String line = scanner.nextLine();
                String[] data = line.split(",");
                passwords.add(new Password(data[0], data[1], data[2]));
            }
            scanner.close();
        } catch (FileNotFoundException e) {
            try {
                //If file doesn't exists, create it
                File newFile = new File("passwords.txt");
                if (newFile.createNewFile()) {
                    System.out.println("File created: " + newFile.getName());
                } else {
                    System.out.println("File already exists.");
                }
            } catch (IOException y){
                System.out.println("An error occurred.");
                e.printStackTrace();
            }

        }
    }

    public static void main(String[] args) {
        launch(args);
    }
}
