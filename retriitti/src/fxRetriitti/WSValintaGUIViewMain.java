package fxRetriitti;

import javafx.application.Application;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.fxml.FXMLLoader;


/**
 * @author jyrit 
 * @version 14.8.2022
 *
 */
public class WSValintaGUIViewMain extends Application {
    @Override
    public void start(Stage primaryStage) {
        try {
            FXMLLoader ldr = new FXMLLoader(getClass().getResource("WSValintaGUIView.fxml"));
            final Pane root = ldr.load();
            //final WSValintaGUIViewGUIController wsvalintaguiviewCtrl = (WSValintaGUIViewGUIController) ldr.getController();
            Scene scene = new Scene(root);
            scene.getStylesheets().add(getClass().getResource("wsvalintaguiview.css").toExternalForm());
            primaryStage.setScene(scene);
            primaryStage.setTitle("WSValintaGUIView");
            primaryStage.show();
        } catch(Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * @param args Ei k�yt�ss�
     */
    public static void main(String[] args) {
        launch(args);
    }
}