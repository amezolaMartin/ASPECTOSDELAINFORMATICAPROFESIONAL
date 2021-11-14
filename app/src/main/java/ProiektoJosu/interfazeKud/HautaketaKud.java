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
        diskaIzendatu();


    }

    private void diskaIzendatu() {
        try {
            String line;
            Process pb = new ProcessBuilder(new String[]{"/bin/bash", "-c", "sudo -S parted -l"}).start();
            //Process pb=Runtime.getRuntime().exec("ls -l");

            char[] pasahitza=main.getMainController().getPasahitza();


            OutputStreamWriter output = new OutputStreamWriter(pb.getOutputStream());
            InputStreamReader input = new InputStreamReader(pb.getInputStream());

            output.write(pasahitza);
            Arrays.fill(pasahitza,'k');
            output.write('\n');
            output.flush();



            BufferedReader input2 =
                    new BufferedReader(new InputStreamReader(pb.getInputStream()));
            boolean bool=false;
            while ((line = input2.readLine()) != null && !bool) {
                if(line.contains("Disk ")){
                    String unekoa=line;
                    unekoa=unekoa.replace("Disk ", "");
                    unekoa=unekoa.split(": ")[0]; //TODO:regex erabili txukunago izateko
                    lblDiskaIzena.setText(unekoa);
                    bool=true;
                }
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
