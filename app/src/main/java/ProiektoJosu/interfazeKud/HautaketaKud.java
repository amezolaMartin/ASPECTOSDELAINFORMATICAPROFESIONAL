package ProiektoJosu.interfazeKud;

import ProiektoJosu.Main;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
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
    int diskaTamaina; //tamaina zenbakia
    String diskaTamainaLetra; //tamaina(GB,MB,...)

    @FXML
    void onClick() {

    }

    public void hasieratu() {
        diskaIzendatu();

        sliderLandu();

    }

    private void sliderLandu() {
        //https://docs.oracle.com/javafx/2/ui_controls/slider.htm
        // slider egiteko goiko programaren kode zati bat erabili da
        sliderGB.setMax(diskaTamaina);
        sliderGB.valueProperty().addListener(new ChangeListener<Number>() {
            public void changed(ObservableValue<? extends Number> ov,
                                Number old_val, Number new_val) {

                txtGB.setText(String.format("%.2f", new_val));
            }
        });

    }

    //FIXME: terminal izeneko klase berria sortu txukunago egoteko
    private void diskaIzendatu() {
        try {
            String line;
            Process pb = new ProcessBuilder(new String[]{"/bin/bash", "-c", "sudo -S parted -l"}).start();

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
                    // Disk /dev/nvme0n1: 512GB lerroa txukundu programarako
                    String diskaTam=line;
                    diskaTam=diskaTam.replace("Disk ", "");
                    String[] balioak=diskaTam.split(": ");
                    diskaTam= balioak[0];//TODO:regex erabili txukunago izateko

                    diskBalioak(balioak[1]);

                    lblDiskaIzena.setText(diskaTam);
                    bool=true;
                }
            }
            input2.close();


        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    private void diskBalioak(String s) {
        String[] balioak=s.split("(?<=\\d)(?=\\D)");
        diskaTamaina=Integer.parseInt(balioak[0]);
        diskaTamainaLetra=balioak[1];
    }


    public void setMain(Main main) {
        this.main=main;
    }


}
