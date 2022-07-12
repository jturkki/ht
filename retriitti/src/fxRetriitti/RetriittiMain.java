package fxRetriitti;

import javafx.application.Application;
import javafx.stage.Stage;
import retriitti.Retriitti;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.fxml.FXMLLoader;


/**
 * Pääohjelma retriitti-ohjelman käynnistämiseksi
 * 
 * @author jyrit
 * @version 11.6.2022
 *
 */
public class RetriittiMain extends Application {
    @Override
    public void start(Stage primaryStage) {
        try {
            FXMLLoader ldr = new FXMLLoader(getClass().getResource("RetriittiGUIView.fxml"));
            final Pane root = ldr.load();
            final RetriittiGUIController retriittiCtrl = (RetriittiGUIController) ldr.getController();
            final Scene scene = new Scene(root);
            scene.getStylesheets().add(getClass().getResource("retriitti.css").toExternalForm());
            primaryStage.setScene(scene);
            primaryStage.setTitle("Retriitti");
            primaryStage.setOnCloseRequest((event) -> {
                // Kutsutaan voikoSulkea-metodia
                if ( !retriittiCtrl.voikoSulkea() ) event.consume(); 
            });
            
            Retriitti retriitti = new Retriitti();
            retriittiCtrl.setRetriitti(retriitti);
            primaryStage.show();
        } catch(Exception e) {
            e.printStackTrace();
        }
        

    }

    /**
     * Käynnistää käyttöliittymän
     * @param args komentorivin parametrit
     */
    public static void main(String[] args) {
        launch(args);
    }
}