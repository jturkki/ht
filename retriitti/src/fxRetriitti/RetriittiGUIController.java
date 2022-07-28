package fxRetriitti;

import java.io.PrintStream;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

import fi.jyu.mit.fxgui.Dialogs;
import fi.jyu.mit.fxgui.ListChooser;
import fi.jyu.mit.fxgui.ModalController;
import fi.jyu.mit.fxgui.TextAreaOutputStream;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextArea;
import javafx.scene.text.Font;
import retriitti.Osallistuja;
import retriitti.OsallistuuWorkshoppiin;
import retriitti.Retriitti;
import retriitti.SailoException;
import retriitti.Workshop;

/**
 * Luokka käyttöliittymän tapahtumien hoitamiseksi
 * 
 * @author jyrit
 * @version 11.6.2022
 *
 */
public class RetriittiGUIController implements Initializable {
    
    @FXML private ListChooser<Osallistuja> chooserOsallistujat;
    @FXML private ScrollPane panelOsallistuja;
    
    /**
     * @param url s
     * @param bundle f
     */
    @Override
    public void initialize(URL url, ResourceBundle bundle) {
        alusta();
    }
    
    /**
     * Käsitellään uuden jäsenen lisääminen
     */
    @FXML private void handleUusiOsallistuja() {
       // ModalController.showModal(RetriittiGUIController.class.getResource("OsallistujaGUIView.fxml"), "Osallistuja", null, "");
        uusiOsallistuja();
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
     
        uusiWorkshop();
        
        //   ModalController.showModal(RetriittiGUIController.class.getResource("WorkshopGUIView.fxml"), "Workshop", null, "");
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
        lisaaOsallistujalleWorkshop();
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
 //  String retriitinNimi;
   
   private Retriitti retriitti;
//   private Osallistuja osallistujaKohdalla;
   private TextArea areaOsallistuja = new TextArea();  // TODO: poista lopuksi

   
   
   
   private void alusta() {
       panelOsallistuja.setContent(areaOsallistuja);
       areaOsallistuja.setFont(new Font("Courier New", 12));
       panelOsallistuja.setFitToHeight(true);
       chooserOsallistujat.clear();
       chooserOsallistujat.addSelectionListener(e -> naytaOsallistuja());
   }
   
   
   private void naytaOsallistuja() {
       Osallistuja osallistujaKohdalla = chooserOsallistujat.getSelectedObject();
       
       
       if (osallistujaKohdalla == null) return;
       
       areaOsallistuja.setText("");
       try (PrintStream os = TextAreaOutputStream.getTextPrintStream(areaOsallistuja)) {
           osallistujaKohdalla.tulosta(os);
      
       ArrayList<Integer> osWS = new ArrayList<Integer>();
        osWS = retriitti.annaWorkshopit(osallistujaKohdalla);
       
        for (Integer i: osWS) {
        
               Workshop workshop = retriitti.annaWorkshop(i.intValue());
               workshop.tulosta(os);
           }
       }
       
               
   }
   
   
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
    
    
   private void avaa() {
       Dialogs.showMessageDialog("tämä jää pois");
   }
   
   
   /**
    * @param retriitti Retriitti jota käytetään tässä käyttöliittymässä
    */
   public void setRetriitti(Retriitti retriitti) {
       this.retriitti = retriitti;
   }
   
   private void hae(int osnro) {
       chooserOsallistujat.clear();
       
       int index = 0;
       for (int i = 0; i < retriitti.getOsallistujia(); i++) {
           Osallistuja osallistuja = retriitti.annaOsallistuja(i);
           if (osallistuja.getId()== osnro) index = i;
           chooserOsallistujat.add("" + osallistuja.getNimi(), osallistuja);
       }
       chooserOsallistujat.setSelectedIndex(index);
   }
   
   
  
   /**
    * Lisätään retriittiin uusi osallistuja
    */
   private void uusiOsallistuja() {
       Osallistuja uusi = new Osallistuja();
       uusi.rekisteroi();
       uusi.asetaAkuA();
       try {
        retriitti.lisaa(uusi);
    } catch (SailoException e) {
        Dialogs.showMessageDialog("Ongelma uuden luomisessa: " + e.getMessage());
    }
       hae(uusi.getId());
   }
   
   /**
    * Lisätään retriittiin uusi Workshop
    */
   private void uusiWorkshop() {
       Workshop uusi = new Workshop();
       uusi.rekisteroi();
       uusi.asetaJoku();
       try {
        retriitti.lisaa(uusi);
    } catch (SailoException e) {
        Dialogs.showMessageDialog("Ongelma uuden luomisessa: " + e.getMessage());
    }
      
   }
   
   private void lisaaOsallistujalleWorkshop() {
       Osallistuja osallistujaKohdalla = chooserOsallistujat.getSelectedObject();
       if (osallistujaKohdalla == null) return;
       
       OsallistuuWorkshoppiin uusi = new OsallistuuWorkshoppiin();
       uusi.asetaJoku(osallistujaKohdalla);
       retriitti.lisaaOsWs(uusi);
       hae(osallistujaKohdalla.getId());
   }
}