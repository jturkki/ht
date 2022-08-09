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
    @FXML TextField editKatuosoite;
    @FXML TextField editPostiosoite;
    @FXML TextField editPuhelin;
    @FXML TextField editEmail;
    @FXML Label labelVirhe;
    
    
    @Override
    public void initialize(URL arg0, ResourceBundle arg1) {
        alusta();
        
    }

    @Override
    public Osallistuja getResult() {
        return osallistujaKohdalla;
    }

    @Override
    public void handleShown() {
        // TODO Auto-generated method stub
        
    }

    @Override
    public void setDefault(Osallistuja oletus) {
        osallistujaKohdalla = oletus;
        naytaOsallistuja(edits, osallistujaKohdalla);
        
    }
    
    
    @FXML private void handleOK() {
        if (osallistujaKohdalla != null && osallistujaKohdalla.getSukunimi().trim().equals("")) {
            naytaVirhe("Nimi ei saa olla tyhjä");
            return;
        }
        ModalController.closeStage(editEtunimi);
    }
    
    
    @FXML private void handleCancel() {
        osallistujaKohdalla = null;
        ModalController.closeStage(editEtunimi);
    }
    
    
    // =================================================================
    
    private Osallistuja osallistujaKohdalla;
    private TextField[] edits;
    
    
    
    private void alusta() {
        edits = new TextField[]{editSukunimi, editEtunimi, editHetu, editKatuosoite, editPostiosoite, editPuhelin, editEmail};
        int i = 0;
        for (TextField edit: edits) {
            int j = i+1;
            edit.setOnKeyReleased(e -> kasitteleMuutosOsallistujaan(j, edit));
            i++;
        }
    }
    
    
    private void kasitteleMuutosOsallistujaan(int k, TextField edit) {
        if (osallistujaKohdalla == null) return;
        String s = edit.getText();
        if (s == null) return;
        String virhe = null;
        switch (k) {
        case 1: virhe = osallistujaKohdalla.setSukunimi(s); break;
        case 2: virhe = osallistujaKohdalla.setEtunimi(s); break;
        case 3: virhe = osallistujaKohdalla.setHetu(s); break;
        case 4: virhe = osallistujaKohdalla.setKatuosoite(s); break;
        case 5: virhe = osallistujaKohdalla.setPostiosoite(s); break;
        case 6: virhe = osallistujaKohdalla.setPuhelin(s); break;
        case 7: virhe = osallistujaKohdalla.setEmail(s); break;
        default:
            break;
        }
       
        if (virhe != null) {
            edit.getStyleClass().add("virhe");
            naytaVirhe(virhe);
        } else {
            edit.getStyleClass().add("normaali");
            naytaVirhe("");
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
        edits[3].setText(osallistuja.getKatuosoite());
        edits[4].setText(osallistuja.getPostiosoite());
        edits[5].setText(osallistuja.getPuhelin());
        edits[6].setText(osallistuja.getEmail());
        
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