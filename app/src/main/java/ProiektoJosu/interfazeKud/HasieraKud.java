package ProiektoJosu.interfazeKud;

import javafx.fxml.FXML;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;

public class HasieraKud {

    @FXML
    private ImageView imageHDD;

    private static HasieraKud instance=new HasieraKud();

    public static HasieraKud getInstance() {
        return instance;
    }

    @FXML
    void mousePressed(MouseEvent event) {
        System.out.println("pene");
    }

}
