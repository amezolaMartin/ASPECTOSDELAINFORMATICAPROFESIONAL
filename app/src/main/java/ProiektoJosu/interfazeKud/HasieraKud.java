package ProiektoJosu.interfazeKud;

import ProiektoJosu.Main;
import javafx.fxml.FXML;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;

public class HasieraKud {

    @FXML
    private ImageView imageHDD;

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

    @FXML
    void mousePressed() {
        main.aldatu();
    }

}
