package ProiektoJosu.interfazeKud;

import ProiektoJosu.Main;
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

    private Main main;


    public static HautaketaKud getInstance() {
        return instance;
    }

    @FXML
    void onClick() {

    }

    public void hasieratu() {
        try {
            String line;
            Process pb = new ProcessBuilder(new String[]{"/bin/bash", "-c", "/usr/bin/sudo /bin/ls -l"}).start();

            String pasahitza=main.getMainController().getPasahitza();

            OutputStreamWriter output = new OutputStreamWriter(pb.getOutputStream());
            InputStreamReader input = new InputStreamReader(pb.getInputStream());

            int bytes;
            char buffer[] = new char[1024];
            while ((bytes = input.read(buffer, 0, 1024)) != -1) {
                if (bytes == 0)
                    continue;
                //Output the data to console, for debug purposes
                String data = String.valueOf(buffer, 0, bytes);
                System.out.println(data);
                // Check for password request
                if (data.contains("[sudo] password")) {
                    System.out.println("pene");
                    output.write(pasahitza);
                    output.write('\n');
                    output.flush();

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


    public void setMain(Main main) {
        this.main=main;
    }
}
