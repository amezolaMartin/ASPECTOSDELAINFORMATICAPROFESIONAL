package ProiektoJosu;


import ProiektoJosu.interfazeKud.HasieraKud;
import ProiektoJosu.interfazeKud.HautaketaKud;
import ProiektoJosu.utils.Terminal;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

import java.io.IOException;

/***
 * Proiektu honetan fdisk ubuntu komandoa landuko da, honen interfaze grafikoa sortzeko
 * komandoa oso intuitiboa ez denez, eskertzekoa izango litzateke GUI sinple bat izatea,
 * guztiok diskak modifikatzearen beldur garelako eta era grafiko batean segurtasun handiagoa dugulako :)
 */
public class Main extends Application {

    //Stage
    private Stage stageMain;

    private Scene sceneMain;
    private Scene sceneHautatu;

    private Parent mainUI;
    private Parent hautatuUI;


    private HasieraKud mainController=HasieraKud.getInstance();
    private HautaketaKud hautaketaController=HautaketaKud.getInstance();



    private static final Main instance=new Main();

    public static Main getInstance() {
        return instance;
    }


    @Override
    public void start(Stage primaryStage) throws Exception {
        stageMain = primaryStage;
        initialize();

        stageMain.setScene(sceneMain);
        stageMain.show();
        stageMain.setResizable(false);
    }

    private void initialize() throws IOException {

        //Hasiera pantaila kargatu
        FXMLLoader loaderMain = new FXMLLoader(getClass().getResource("/scenak/MainScena.fxml"));
        mainUI = loaderMain.load();
        sceneMain = new Scene(mainUI);
        mainController=loaderMain.getController();
        mainController.setMain(this);

        Terminal.getInstance().setMain(this);

    }

    public void aldatu() throws IOException {
        //hautaketa pantaila kargatu
        FXMLLoader loaderHautatu = new FXMLLoader(getClass().getResource("/scenak/HautaketaScena.fxml"));
        hautatuUI = loaderHautatu.load();
        sceneHautatu = new Scene(hautatuUI);
        hautaketaController=loaderHautatu.getController();

        hautaketaController.hasieratu();


        stageMain.setScene(sceneHautatu);
    }

    public HasieraKud getMainController() {
        return mainController;
    }


}