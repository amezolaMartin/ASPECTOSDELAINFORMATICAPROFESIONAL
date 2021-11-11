package ProiektoJosu;


import ProiektoJosu.interfazeKud.HasieraKud;
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
 * guztiok diskak modifikatzearen beldur garelako eta ere grafiko batean segurtasun handiagoa dugu :)
 */
public class Main extends Application {

    //Stage
    private Stage stageMain;

    private Scene sceneMain;

    private Parent mainUI;

    private HasieraKud mainController = HasieraKud.getInstance();




    @Override
    public void start(Stage primaryStage) throws Exception {
        stageMain = primaryStage;
        initialize();

        stageMain.setScene(sceneMain);
        stageMain.setResizable(false);
    }

    private void initialize() throws IOException {
        FXMLLoader loaderMain = new FXMLLoader(getClass().getResource("/scenak/MainScena.fxml"));
        mainUI = loaderMain.load();
        sceneMain = new Scene(mainUI);
        stageMain.show();

    }
}
