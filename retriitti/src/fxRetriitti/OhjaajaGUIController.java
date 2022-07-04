package fxRetriitti;

import fi.jyu.mit.fxgui.Dialogs;
import javafx.fxml.FXML;

/**
 * @author jyrit
 * @version 19.6.2022
 *
 */
public class OhjaajaGUIController {
   
    @FXML void handlePeru() {
        Dialogs.showMessageDialog("Perutaan heti kun osataan");
    }

    @FXML
    void handleTallenna() {
        Dialogs.showMessageDialog("ei osata vielä");
    }

}