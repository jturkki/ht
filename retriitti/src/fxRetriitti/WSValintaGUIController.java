package fxRetriitti;

import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

import fi.jyu.mit.fxgui.ListChooser;
import fi.jyu.mit.fxgui.ModalController;
import fi.jyu.mit.fxgui.ModalControllerInterface;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.stage.Stage;
import retriitti.Workshop;

/**
 * @author jyrit
 * @version 14.8.2022
 *
 */
public class WSValintaGUIController implements ModalControllerInterface<ArrayList<Workshop>>, Initializable {
    
    @FXML private ListChooser<Workshop> chooserWorkshopit;
   
    @FXML Label lopetus;
   
    
    @FXML private void handleOK() {
        ModalController.closeStage(lopetus);
    }

    
    @FXML private void handleCancel() {
        workshop = null;
        alWs.clear();
        ModalController.closeStage(lopetus);
    }
    

  
    @Override
    public void initialize(URL url, ResourceBundle bundle) {
        alusta();
        
    }
    
    @Override
    public ArrayList<Workshop> getResult() {
        alWs.clear();
        alWs.add(workshop);
        return alWs;
    }


    @Override
    public void handleShown() {
        //
        
    }


    @Override
    public void setDefault(ArrayList<Workshop> ws) {
        this.alWs = ws;
        hae();
        
    }
    
      
    
    //==============================================
    
    private Workshop workshop;
    private ArrayList<Workshop> alWs = new ArrayList<Workshop>();
    
    private void alusta() {
        chooserWorkshopit.clear();
        chooserWorkshopit.addSelectionListener(e -> setWs());
    }
    
    private void setWs() {
     workshop = chooserWorkshopit.getSelectedObject();
    }
    
    
 

        /**
         * hakee workshopit listaan 
         */
        private void hae() {
            chooserWorkshopit.clear();
            
            int index = 0;
            for (int i = 0; i < alWs.size(); i++) {
                Workshop ws = alWs.get(i); 
                chooserWorkshopit.add("" + ws.getNimi(), ws);
            }
            chooserWorkshopit.setSelectedIndex(index);
        }
        
    

    /**
     * Luodaan ikkuna jossa listaus workshopeista ja
     * palautetaan valittu workshop tai null
     * @param modalityStage mille ollaan modaalisia, null = sovellukselle 
     * @param oletus oletus on eka workshop listalla
     * @return null jos painetaan Cancel, muuten valittu workshop
     */
    public static ArrayList<Workshop> kysyWs(Stage modalityStage, ArrayList<Workshop> oletus) {
        
        return ModalController.showModal(RetriittiGUIController.class.getResource("WSValintaGUIView.fxml"), "Workshopin valinta", modalityStage, oletus);

    }



}