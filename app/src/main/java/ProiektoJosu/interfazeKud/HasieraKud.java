package ProiektoJosu.interfazeKud;

import ProiektoJosu.Main;
import javafx.fxml.FXML;
import javafx.scene.control.PasswordField;

import java.io.IOException;
import java.util.ArrayList;

public class HasieraKud {

    @FXML
    private PasswordField txtPasahitza;

    private String pass;

    private static HasieraKud instance=new HasieraKud();

    public HasieraKud(){
    }

    public static HasieraKud getInstance() {
        return instance;
    }


    private Main main;

    public void setMain(Main main) {
        this.main = main;
        pass="kk";
    }



    private void getPasahitzaIntern(){
        pass=txtPasahitza.getText();
    }

    @FXML
    void mousePressed() throws IOException {
        //HasieraKud.getInstance().getPasahitzaIntern();
        getPasahitzaIntern();
        main.aldatu();
    }

    public String getPasahitza() {
        return pass;
    }
}
