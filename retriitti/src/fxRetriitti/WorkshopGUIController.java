package fxRetriitti;

import fi.jyu.mit.fxgui.Dialogs;
import javafx.fxml.FXML;

/**
 * @author jyrit
 * @version 19.6.2022
 *
 */
public class WorkshopGUIController {
    
    @FXML
    void handlePeru() {
        Dialogs.showMessageDialog("Peruttaisiin");
    }

    @FXML
    void handleTallenna() {
        Dialogs.showMessageDialog("Tallennettaisiin");
    }

}