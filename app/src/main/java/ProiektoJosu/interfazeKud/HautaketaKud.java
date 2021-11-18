package ProiektoJosu.interfazeKud;

import ProiektoJosu.Main;
import ProiektoJosu.kudeatzaile.Aukerak;
import ProiektoJosu.utils.Terminal;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.Slider;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyEvent;

import java.io.*;
import java.util.Arrays;
import java.util.List;

public class HautaketaKud {

    @FXML
    private Label lblDiskaIzena;

    @FXML
    private Label lblDiskaIzena1;

    @FXML
    private Label lblTamaina;

    @FXML
    private Slider sliderGB;

    @FXML
    private TextField txtGB;

    @FXML
    private ComboBox<Aukerak> cmbHautaketa;


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
        //TODO: aurrera botoiak egingo duena...

        Aukerak aukera = cmbHautaketa.getValue();
        if (aukera!= null) {
            var input=Terminal.getInstance().fdiskExec(
                    aukera,
                    lblDiskaIzena.getText(),
                    txtGB.getText(),
                    lblTamaina.getText()
            );
        }

    }


    @FXML
    void onActionText(ActionEvent event) {
        sliderGB.setValue(
                Integer.parseInt(txtGB.getText())
        );
    }


    public void hasieratu() {

        diskaIzendatu();

        sliderLandu();

        comboBoxIrakurri();
    }

    private void comboBoxIrakurri() {
        //TODO: aukerak(n,w,q,d)

        List<Aukerak> aukerak=Arrays.asList(
                new Aukerak('n',"partizio berria gehitu"),
                new Aukerak('w',"gorde eta bukatu"),
                new Aukerak('q',"irten gorde gabe"),
                new Aukerak('d',"partizioa ezabatu")
        );
        ObservableList<Aukerak> aukObs = FXCollections.observableArrayList(aukerak);
        cmbHautaketa.setItems(aukObs);
    }



    private void sliderLandu() {
        // https://docs.oracle.com/javafx/2/ui_controls/slider.htm
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
            var input2=Terminal.getInstance().sudoTerminal("parted -l");
            String line;

            boolean bool=false;
            while ((line = input2.readLine()) != null && !bool) {
                if(line.contains("Disk ")){
                    // Disk /dev/nvme0n1: 512GB lerroa txukundu programarako
                    String diskIz=line;
                    diskIz=diskIz.replace("Disk ", "");
                    String[] balioak=diskIz.split(": ");
                    diskIz= balioak[0];//TODO:regex erabili txukunago izateko
                    // Erabili behar den regex (sudo parted -l | grep '^Disk /' | awk '{print $2}' | awk -F ':' '{print $1}')
                    // Erderaz badago ordenagailua:
                    //sudo parted -l | grep '^Disco /' | awk '{print $2}' | awk -F ':' '{print $1}'


                    diskBalioak(balioak[1]);

                    lblDiskaIzena.setText(diskIz);
                    lblDiskaIzena1.setText(diskIz);
                    lblTamaina.setText(diskaTamainaLetra);
                    bool=true;
                }
                else if(line.contains("Disco ")){
                    // erderaz badago SE
                    // Disk /dev/nvme0n1: 512GB lerroa txukundu programarako
                    String diskIz=line;
                    diskIz=diskIz.replace("Disco ", "");
                    String[] balioak=diskIz.split(": ");
                    diskIz= balioak[0];//TODO:regex erabili txukunago izateko

                    diskBalioak(balioak[1]);

                    lblDiskaIzena.setText(diskIz);
                    lblTamaina.setText(diskaTamainaLetra);
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