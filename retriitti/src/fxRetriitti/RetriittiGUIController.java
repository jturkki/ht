package fxRetriitti;

import fi.jyu.mit.fxgui.Dialogs;
import fi.jyu.mit.fxgui.ModalController;
import javafx.application.Platform;
import javafx.fxml.FXML;

/**
 * Luokka käyttöliittymän tapahtumien hoitamiseksi
 * 
 * @author jyrit
 * @version 11.6.2022
 *
 */
public class RetriittiGUIController {
    /**
     * Käsitellään uuden jäsenen lisääminen
     */
    @FXML private void handleUusiOsallistuja() {
        ModalController.showModal(RetriittiGUIController.class.getResource("OsallistujaGUIView.fxml"), "Osallistuja", null, "");
    }
    
    /**
     * Käsitellään jäsenen poistaminen
     */
    @FXML private void handlePoistaOsallistuja() {
        Dialogs.showMessageDialog("Ei osata vielä poistaa");
    }
    
    /**
     * Käsitellään tallennuskäsky
     */
    @FXML private void handleTallenna() {
        tallenna();
    }
    
    
    /**
     * Käsitellään lopetuskäsky
     */
    @FXML private void handleLopeta() {
        tallenna();
        Platform.exit();
    }

    
    /**
     * Uuden workshopin lisääminen
     */
    @FXML private void handleLisaaWorkshop() {
        ModalController.showModal(RetriittiGUIController.class.getResource("WorkshopGUIView.fxml"), "Workshop", null, "");
    }
    
    /**
     * Workshopin poisto
     */
    @FXML private void handlePoistaWorkshop() {
        Dialogs.showMessageDialog("Workshopin poisto tästä");
    }
    
    /**
     * Ohjaajan muokkaus
     */
    @FXML private void handleMuokkaaOhjaaja() {
        ModalController.showModal(RetriittiGUIController.class.getResource("OhjaajaGUIView.fxml"), "Ohjaaja", null, "");
    }
    
    /**
     * Apua 
     */
    @FXML private void handleApua() {
        Dialogs.showMessageDialog("Apua apua");
    }
    
    /**
     * Tietoja
     */
    @FXML private void handleTietoja() {
        Dialogs.showMessageDialog("Tämä on retriittirekisteri.");
    }
    
    /**
     * Osallistujan lisääminen workshoppiin 
     */
   @FXML private void handleLisaaOsallistujalleWorkshop() {
        Dialogs.showMessageDialog("Tarttis lisätä workshop. Vaan eipä toimi vielä");
    }

    /**
     * Osallistujan poistaminen workshopista
     */
   @FXML private void handlePoistaOsallistujaltaWorkshop() {
        Dialogs.showMessageDialog("Tarttis poistaa workshop. Vaan eipä toimi vielä");
    }

   /**
    * Käsitellään Avaa-käsky 
    */
   @FXML private void handleAvaaTiedosto() {
      avaa();
   }
   
   /**
    * Käsitellään tulostuskäsky
    */
   @FXML private void handleTulosta() {
       tulosta();
   }
  
//====================================================================   
   String retriitinNimi;
   
   /**
    * Tietojen tallennus
    */
   private void tallenna() {
       Dialogs.showMessageDialog("Tallennetetaan! Mutta ei toimi vielä");
   }
   
    /**
     * Tarkistetaan onko tallennus tehty
     * @return true jos saa sulkea sovelluksen, false jos ei
     */
    public boolean voikoSulkea() {
        tallenna();
        return true;
    }
    
    private void tulosta() {
        Dialogs.showMessageDialog("Tästä aukeaa oma tulostusikkuna");
    }
    
    
    /**
     * lukee tiedostosta retriitin tiedot
     * @param nimi retriitin nimi
     */
    protected void lueTiedosto(String nimi) {
        retriitinNimi = nimi;
      //  setTitle("Retriitti - " + retriitinNimi);
        String virhe = "Ei osata vielä lukea " + retriitinNimi;        // TODO tiedoston luku
       // if (virhe != null)
            Dialogs.showMessageDialog(virhe);
    }
    
    /**
     * avaa tiedoston
     * @return false jos ei nimeä, true jos avataan tiedosto
     */
    public boolean avaa() {
        String uusinimi = AloitusViewGUIController.kysyNimi(null, retriitinNimi);
        if (uusinimi == null) return false;
        lueTiedosto(uusinimi);
        return true;
    }
}