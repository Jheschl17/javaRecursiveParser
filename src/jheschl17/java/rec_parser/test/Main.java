/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package jheschl17.java.rec_parser.test;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.scene.text.Font;
import javafx.stage.Stage;
import jheschl17.java.rec_parser.Token;

/**
 *
 * @author Jonas Heschl
 */
public class Main extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception {
        AnchorPane root = new AnchorPane();
        
        Label formula = new Label("Expresssion:");
        TextField formulaInput = new TextField();
        Button check = new Button("Check this expression");
        Label checkResult = new Label("Syntactically legal:");
        
        AnchorPane.setLeftAnchor(formula, 7.0);
        AnchorPane.setTopAnchor(formula, 17.0);
        formula.setFont(new Font(13));
        
        AnchorPane.setLeftAnchor(formulaInput, 100.0);
        AnchorPane.setTopAnchor(formulaInput, 15.0);
        AnchorPane.setRightAnchor(formulaInput, 7.0);
        formulaInput.setFont(new Font(13));
        
        AnchorPane.setLeftAnchor(check, 7.0);
        AnchorPane.setTopAnchor(check, 100.0);
        check.setFont(new Font(13));
        check.setOnAction((ActionEvent e) -> {
            String input = formulaInput.getText();
            boolean legal = Token.parseString(input);
            
            if (legal) {
                checkResult.setText("Syntactically legal: true");
            } else {
                checkResult.setText("Syntactically legal: false");
            }
        });
        
        AnchorPane.setRightAnchor(checkResult, 7.0);
        AnchorPane.setTopAnchor(checkResult, 105.0);
        checkResult.setFont(new Font(13));
        
        Scene scene = new Scene(root, 400, 150);
        
        root.getChildren().addAll(formula, formulaInput, check, checkResult);
        primaryStage.setTitle("Recursive Parser");
        primaryStage.setScene(scene);
        primaryStage.setResizable(false);
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
