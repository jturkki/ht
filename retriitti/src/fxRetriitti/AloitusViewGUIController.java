package fxRetriitti;



import fi.jyu.mit.fxgui.Dialogs;
import fi.jyu.mit.fxgui.ModalController;
import fi.jyu.mit.fxgui.ModalControllerInterface;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

/**
 * @author jyrit
 * @version 12.6.2022
 *
 */
public class AloitusViewGUIController {
    
      
    
    
    @FXML void handleLuoUusi() {
        luoUusi();
    }

    @FXML void handleOK() {
       Dialogs.showMessageDialog("ei osata vielä");
    }

    @FXML void handlePeru() {
        Dialogs.showMessageDialog("ei osata");
    }

  //==================================================================
    
    

    
    private void luoUusi() {
        Dialogs.showMessageDialog("Tässä luodaan uusi");
    }

    
    
}