package ProiektoJosu.interfazeKud;

import ProiektoJosu.Main;
import javafx.fxml.FXML;
import javafx.scene.control.PasswordField;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;

import java.io.IOException;

public class HasieraKud {

    @FXML
    private PasswordField txtPasahitza;

    private static HasieraKud instance=new HasieraKud();

    public HasieraKud(){
    }

    private Main main;

    public void setMain(Main main) {
        this.main = main;
    }


    public static HasieraKud getInstance() {
        return instance;
    }

    public char[] getPasahitza(){
        return txtPasahitza.getText().toCharArray();
    }

    @FXML
    void mousePressed() throws IOException {
        main.aldatu();
    }

}
