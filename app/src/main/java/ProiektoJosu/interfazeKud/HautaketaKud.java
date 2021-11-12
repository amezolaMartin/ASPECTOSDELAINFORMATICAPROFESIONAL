package ProiektoJosu.interfazeKud;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.Slider;
import javafx.scene.control.TextField;

public class HautaketaKud {

    @FXML
    private Label lblDiskaIzena;

    @FXML
    private Label lblGBAldatu;

    @FXML
    private Slider sliderGB;

    @FXML
    private TextField txtGB;

    private static HautaketaKud instance=new HautaketaKud();

    public static HautaketaKud getInstance() {
        return instance;
    }

    @FXML
    void onClick(ActionEvent event) {

    }

}
