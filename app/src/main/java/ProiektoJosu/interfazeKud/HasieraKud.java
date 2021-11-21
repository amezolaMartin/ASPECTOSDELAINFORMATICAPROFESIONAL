package ProiektoJosu.interfazeKud;

import ProiektoJosu.Main;
import javafx.fxml.FXML;
import javafx.scene.control.PasswordField;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;

import java.io.IOException;

public class HasieraKud {

    @FXML
    private PasswordField txtPasahitza;

    private char[] pass;

    private static HasieraKud instance=new HasieraKud();

    public HasieraKud(){
    }

    public static HasieraKud getInstance() {
        return instance;
    }


    private Main main;

    public void setMain(Main main) {
        this.main = main;
    }



    private void getPasahitzaIntern(){
        pass=txtPasahitza.getText().toCharArray();
    }

    @FXML
    void mousePressed() throws IOException {
        //HasieraKud.getInstance().getPasahitzaIntern();
        getPasahitzaIntern();
        main.aldatu();
    }


    @FXML
    void onKeyPressed(KeyEvent event) throws IOException {
        if (event.getCode().equals(KeyCode.ENTER)) {
            mousePressed();
        }
    }

    public char[] getPasahitza() {
        return pass;
    }
}
