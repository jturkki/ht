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
public class AloitusViewGUIController implements ModalControllerInterface<String> {
    
    @FXML private TextField textVastaus;
    private String vastaus = null;
    
    
    
    @FXML void handleLuoUusi() {
        luoUusi();
    }

    @FXML void handleOK() {
        vastaus = textVastaus.getText();
        ModalController.closeStage(textVastaus);
    }

    @FXML void handlePeru() {
        ModalController.closeStage(textVastaus);
    }

  //==================================================================
    
    @Override
    public String getResult() {
        return vastaus;
    }
    
    @Override
    public void setDefault(String oletus) {
        textVastaus.setText(oletus);
    }
    
    @Override
    public void handleShown() {
        textVastaus.requestFocus();
    }

    
    private void luoUusi() {
        Dialogs.showMessageDialog("Tässä luodaan uusi");
    }

    /**
     * Luodaan nimenkysymysdialogi ja palautetaan siihen kirjoitettu nimi tai null
     * @param modalityStage mille ollaan modaalisia, null = sovellukselle
     * @param oletus mitä nimeä käytetään oletuksena
     * @return null jos painetaan Cancel, muuten kirjoitettu nimi
     */
    public static String kysyNimi(Stage modalityStage, String oletus) {
        return ModalController.showModal(AloitusViewGUIController.class.getResource("AloitusViewGUIView.fxml"), "Retriitti", modalityStage, oletus);
    }
    
}