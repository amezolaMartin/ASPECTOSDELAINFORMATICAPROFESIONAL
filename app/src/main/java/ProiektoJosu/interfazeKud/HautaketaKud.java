package ProiektoJosu.interfazeKud;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.Slider;
import javafx.scene.control.TextField;

import java.io.*;
import java.net.URL;
import java.util.Arrays;
import java.util.ResourceBundle;

public class HautaketaKud implements Initializable {

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
    void onClick() {

    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        try {
            String line;
            String cmd1="sudo parted -l";
            System.out.println("kaixooo");
            Process pb = new ProcessBuilder("sudo", "ls", "-l").start();

            char[] pasahitza=HasieraKud.getInstance().getPasahitza();

            OutputStreamWriter output = new OutputStreamWriter(pb.getOutputStream());

            output.write(pasahitza);
            output.write('\n');
            output.flush();


            Process p = Runtime.getRuntime().exec(cmd1);
            p.waitFor();

            BufferedReader input =
                    new BufferedReader(new InputStreamReader(p.getInputStream()));
            while ((line = input.readLine()) != null) {
                System.out.println(line);
            }
            input.close();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }


}
