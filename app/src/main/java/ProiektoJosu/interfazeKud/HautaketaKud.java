package ProiektoJosu.interfazeKud;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.Slider;
import javafx.scene.control.TextField;

import java.io.*;
import java.net.URL;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.ResourceBundle;

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
    void onClick() {

    }

    public void hasieratu() {
        try {
            String line;
            Process pb = new ProcessBuilder("sudo", "ls", "-l").start();

            String pasahitza=HasieraKud.getInstance().getPasahitza();
            System.out.println(pasahitza);
            System.out.println("-------");

            OutputStreamWriter output = new OutputStreamWriter(pb.getOutputStream());
            InputStreamReader input = new InputStreamReader(pb.getInputStream());




            int bytes, tryies = 0;
            char buffer[] = new char[1024];
            while ((bytes = input.read(buffer, 0, 1024)) != -1) {
                if (bytes == 0)
                    continue;
                //Output the data to console, for debug purposes
                String data = String.valueOf(buffer, 0, bytes);
                System.out.println(data);
                // Check for password request
                if (data.contains("[sudo] password")) {
                    output.write(pasahitza);
                    output.write('\n');
                    //output.flush();
                    tryies++;
                }
            }




            BufferedReader input2 =
                    new BufferedReader(new InputStreamReader(pb.getInputStream()));
            while ((line = input2.readLine()) != null) {
                System.out.println(line);
            }
            input2.close();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }


}
