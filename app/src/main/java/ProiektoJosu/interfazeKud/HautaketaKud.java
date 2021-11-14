package ProiektoJosu.interfazeKud;

import ProiektoJosu.Main;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.Slider;
import javafx.scene.control.TextField;

import java.io.*;
import java.util.Arrays;

public class HautaketaKud {

    @FXML
    private Label lblDiskaIzena;

    @FXML
    private Label lblGBAldatu;

    @FXML
    private Slider sliderGB;

    @FXML
    private TextField txtGB;

    private Main main;

    private static HautaketaKud instance=new HautaketaKud();

    public static HautaketaKud getInstance() {
        return instance;
    }

    //programa
    int diskaTamaina;

    @FXML
    void onClick() {

    }

    public void hasieratu() {
        diskaIzendatu();

        //sliderGB.set

    }

    //FIXME: terminal izeneko klase berria sortu txukunago egoteko
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
                    String[] balioak=unekoa.split(": ");
                    unekoa= balioak[0];//TODO:regex erabili txukunago izateko
                    diskaTamaina=Integer.parseInt(balioak[1]
                            .replace("[]","")); //TODO: lerro hau bukatu

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
