package fxRetriitti;

import java.net.URL;
import java.util.ResourceBundle;

import fi.jyu.mit.fxgui.ModalController;
import fi.jyu.mit.fxgui.ModalControllerInterface;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import retriitti.Osallistuja;

/**
 * Osallistujan käsittely
 * @author jyrit
 * @version 19.6.2022
 *
 */
public class OsallistujaGUIController implements ModalControllerInterface<Osallistuja>, Initializable {

    
    @FXML TextField editSukunimi;
    @FXML TextField editEtunimi;
    @FXML TextField editHetu;
    @FXML Label labelVirhe;
    
    
    @Override
    public void initialize(URL arg0, ResourceBundle arg1) {
        alusta();
        
    }

    @Override
    public Osallistuja getResult() {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public void handleShown() {
        // TODO Auto-generated method stub
        
    }

    @Override
    public void setDefault(Osallistuja oletus) {
        this.osallistujaKohdalla = oletus;
        naytaOsallistuja(edits, osallistujaKohdalla);
        
    }
    
    
    @FXML private void handleOK() {
        if (osallistujaKohdalla != null && osallistujaKohdalla.getSukunimi().trim().equals("")) {
            naytaVirhe("Nimi ei saa olla tyhjä");
            return;
        }
        ModalController.closeStage(labelVirhe);
    }
    
    
    @FXML private void handleCancel() {
        osallistujaKohdalla = null;
        ModalController.closeStage(editEtunimi);
    }
    
    
    // =================================================================
    
    private Osallistuja osallistujaKohdalla;
    private TextField[] edits;
    
    
    
    private void alusta() {
        edits = new TextField[]{editSukunimi, editEtunimi, editHetu};
        editSukunimi.setOnKeyReleased(e -> kasitteleMuutosOsallistujaan(1, editSukunimi));
    }
    
    
    private void kasitteleMuutosOsallistujaan(int k, TextField edit) {
        if (osallistujaKohdalla == null) return;
        String s = edit.getText();
        String virhe = null;
        virhe = osallistujaKohdalla.setSukunimi(s);
        if (virhe == null) {
            naytaVirhe(virhe);
        } else {
            naytaVirhe(virhe);
        }
    }
    
    /**
     *  näytetään osallistujan tiedot TextFieldeihin
     * @param edits taulukko jossa tekstikenttiä
     * @param osallistuja jonka tiedot esitetään
     */
    public static void naytaOsallistuja(TextField[] edits, Osallistuja osallistuja) {
        if (osallistuja == null) return;
        edits[0].setText(osallistuja.getSukunimi());
        edits[1].setText(osallistuja.getEtunimi());
        edits[2].setText(osallistuja.getHetu());
        
    }
    
    
    private void naytaVirhe(String virhe) {
        if (virhe == null || virhe.isEmpty() ) {
            labelVirhe.setText("");
            labelVirhe.getStyleClass().removeAll("virhe");
            return;
        }
        labelVirhe.setText(virhe);
        labelVirhe.getStyleClass().add("virhe");
    }
    
    /**
     * Luodaan ikkuna osallistujan tietojen muokkaukseen ja
     * palautetaan osallistuja muutettuna tai null
     * @param modalityStage mille ollaan modaalisia, null = sovellukselle
     * @param oletus Osallistuja jonka tietoja näytetään 
     * @return null jos painetaan Cancel, muuten täytetty tietue
     */
    public static Osallistuja kysyOsallistuja(Stage modalityStage, Osallistuja oletus) {
        return ModalController.showModal(RetriittiGUIController.class.getResource("OsallistujaGUIView.fxml"), "Osallistuja", modalityStage, oletus);

    }
}