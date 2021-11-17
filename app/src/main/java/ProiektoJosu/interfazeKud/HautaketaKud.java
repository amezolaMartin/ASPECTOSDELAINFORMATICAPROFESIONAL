package ProiektoJosu.interfazeKud;

import ProiektoJosu.Main;
import ProiektoJosu.kudeatzaile.Aukerak;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.Slider;
import javafx.scene.control.TextField;

import java.io.*;
import java.util.Arrays;
import java.util.List;

public class HautaketaKud {

    @FXML
    private Label lblDiskaIzena;

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
        System.out.println("Francisco Franco Bahamondef\u200Bg\u200B (Ferrol, 4 de diciembre de 1892-Madrid, 20 de noviembre de 1975) fue un militar y dictador español, integrante del grupo de altos cargos de la cúpula militar que dio el golpe de Estado de 1936 contra el Gobierno democrático de la Segunda República, dando lugar a la guerra civil española. Fue investido como jefe supremo del bando sublevado el 1 de octubre de 1936, y ejerció como caudillo de Españab\u200B —jefe de Estado— desde el término del conflicto hasta su fallecimiento en 1975, y como presidente del Gobierno —jefe de Gobierno— entre 1938 y 1973.12\u200B\n" +
                "\n" +
                "En abril de 1937, se autoproclamó jefe nacional de la Falange Española Tradicionalista y de las Juntas de Ofensiva Nacional Sindicalista (FET y de las JONS), partido único resultado de la fusión de la fascista Falange Española de las JONS y de la Comunión Tradicionalista. Acabada la guerra, instauró una dictadura fascistizada13\u200B o régimen semifascista,14\u200B e incorporó una influencia clara de los totalitarismos alemán e italiano en campos como las relaciones laborales, la política económica autárquica, la estética, el uso de los símbolos y el denominado «Movimiento Nacional».15\u200B En sus últimos estertores, el régimen transitó más próximo a las dictaduras desarrollistas,16\u200B aunque siempre conservó rasgos fascistas vestigiales,14\u200B régimen que en su conjunto es conocido como franquismo, caracterizado por la ausencia de una ideología claramente definida más allá del anticomunismo y el nacionalcatolicismo.\n" +
                "\n" +
                "Durante su mandato al frente del Ejército y de la Jefatura del Estado, especialmente durante la guerra civil y los primeros años del régimen, se produjo una fuerte represión, en particular contra los partidarios del bando republicano que fue derrotado en la contienda, a la que se sumó el exilio de centenares de miles de españoles al extranjero. La cifra total de víctimas mortales varía en torno a varios centenares de miles de personas, que perecieron en su mayoría en campos de concentración, ejecuciones extrajudiciales o en prisión.17\u200B18\u200B ");
            //obtenemos el valor del combobox
        Aukerak aukera = cmbHautaketa.getValue();
        if (aukera!= null) {
            System.out.println(aukera.toString());
        }

        char pal = aukera.getAukera();
    }

    public void hasieratu() {

        diskaIzendatu();

        sliderLandu();

        comboBoxIrakurri();
    }

    private void comboBoxIrakurri() {
        //TODO: aukerak(n,w,q,d)
        //emmammamama

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
                    String diskIz=line;
                    diskIz=diskIz.replace("Disk ", "");
                    String[] balioak=diskIz.split(": ");
                    diskIz= balioak[0];//TODO:regex erabili txukunago izateko
                    // Erabili behar den regex (sudo parted -l | grep '^Disk /' | awk '{print $2}' | awk -F ':' '{print $1}')
                    // Erderaz badago ordenagailua:
                    //sudo parted -l | grep '^Disco /' | awk '{print $2}' | awk -F ':' '{print $1}'


                    diskBalioak(balioak[1]);

                    lblDiskaIzena.setText(diskIz);
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






// Gaizka zuazo guapo