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
import retriitti.Workshop;

/**
 * Osallistujan käsittely
 * @author jyrit
 * @version 19.6.2022
 *
 */
public class WorkshopGUIController implements ModalControllerInterface<Workshop>, Initializable {

    
    @FXML TextField editNimi;
    @FXML TextField editSijainti;
    @FXML TextField editMaxOs;
    @FXML TextField editOhjaaja;
    @FXML TextField editKesto;
    @FXML TextField editPvm;
    @FXML TextField editKlo;
    @FXML Label labelVirhe;
    
     
    @Override
    public void initialize(URL arg0, ResourceBundle arg1) {
        alusta();
        
    }

    @Override
    public Workshop getResult() {
        return workshop;
    }

    @Override
    public void handleShown() {
        // TODO Auto-generated method stub
        
    }

    @Override
    public void setDefault(Workshop oletus) {
        workshop = oletus;
        naytaWorkshop(edits, workshop);
        
    }
    
    
    @FXML private void handleOK() {
        if (workshop != null && workshop.getNimi().trim().equals("")) {
            naytaVirhe("Nimi ei saa olla tyhjä");
            return;
        }
        ModalController.closeStage(editNimi);
    }
    
    
    @FXML private void handleCancel() {
        workshop = null;
        ModalController.closeStage(editNimi);
    }
    
    
    // =================================================================
    
    private Workshop workshop;
    private TextField[] edits;
    
    
    
    private void alusta() {
        edits = new TextField[]{editNimi, editSijainti, editMaxOs, editOhjaaja, editKesto, editPvm, editKlo};
        int i = 0;
        for (TextField edit: edits) {
            int j = i+1;
            edit.setOnKeyReleased(e -> kasitteleMuutosWorkshoppiin(j, edit));
            i++;
        }
    }
    
    
    private void kasitteleMuutosWorkshoppiin(int k, TextField edit) {
        if (workshop == null) return;
        String s = edit.getText();
        if (s == null) return;
        String virhe = null;
        switch (k) {
        case 1: virhe = workshop.setNimi(s); break;
        case 2: virhe = workshop.setSijainti(s); break;
        case 3: virhe = workshop.setMaxOs(s); break;
        case 4: virhe = workshop.setOhjaaja(s); break;
        case 5: virhe = workshop.setKesto(s); break;
        case 6: virhe = workshop.setPvm(s); break;
        case 7: virhe = workshop.setKlo(s); break;
        default:
            break;
        }
       
        if (virhe != null) {
            edit.getStyleClass().add("virhe");
            naytaVirhe(virhe);
        } else {
            edit.getStyleClass().add("normaali");
     //       naytaVirhe("");
        }
    }
    
    /**
     *  näytetään osallistujan tiedot TextFieldeihin
     * @param edits taulukko jossa tekstikenttiä
     * @param workshop jonka tiedot esitetään
     */
    public static void naytaWorkshop(TextField[] edits, Workshop workshop) {
        if (workshop == null) return;
        edits[0].setText(workshop.getNimi());
        edits[1].setText(workshop.getSijainti());
        edits[2].setText(workshop.getMaxOs());
        edits[3].setText(workshop.getOhjaaja());
        edits[4].setText(workshop.getKesto());
        edits[5].setText(workshop.getPvm());
        edits[6].setText(workshop.getKlo());
        
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
     * Luodaan ikkuna workshopin tietojen muokkaukseen ja
     * palautetaan workshop muutettuna tai null
     * @param modalityStage mille ollaan modaalisia, null = sovellukselle
     * @param oletus Workshop jonka tietoja näytetään 
     * @return null jos painetaan Cancel, muuten täytetty tietue
     */
    public static Workshop kysyWorkshop(Stage modalityStage, Workshop oletus) {
        return ModalController.showModal(RetriittiGUIController.class.getResource("WorkshopGUIView.fxml"), "Workshop", modalityStage, oletus);

    }


        
    
}