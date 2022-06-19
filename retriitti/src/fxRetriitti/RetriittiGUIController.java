package fxRetriitti;

import fi.jyu.mit.fxgui.Dialogs;
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
        Dialogs.showMessageDialog("Ei osata vielä lisätä");
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
        Dialogs.showMessageDialog("Uuden workshopin lisäys tästä");
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
        Dialogs.showMessageDialog("Ohjaajan muokkaus");
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
       Dialogs.showMessageDialog("Ei vaan osata vielä avata tiedostoa");;
   }
   
   /**
    * Käsitellään tulostuskäsky
    */
   @FXML private void handleTulosta() {
       tulosta();
   }
  
//====================================================================   
   
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
    
}