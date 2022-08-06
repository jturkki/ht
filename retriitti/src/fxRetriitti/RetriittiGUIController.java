package fxRetriitti;




import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;


import fi.jyu.mit.fxgui.Dialogs;
import fi.jyu.mit.fxgui.ListChooser;
import fi.jyu.mit.fxgui.ModalController;
import fi.jyu.mit.fxgui.StringGrid;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import retriitti.Osallistuja;
import retriitti.OsallistuuWorkshoppiin;
import retriitti.Retriitti;
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
    
    @FXML TextField editSukunimi;
    @FXML TextField editEtunimi;
    @FXML TextField editHetu;
    @FXML StringGrid<Workshop> tableWorkshopit;
    
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
        uusiOsallistuja();
    }
    
    
    @FXML private void handleMuokkaaOsallistuja() {
        muokkaa();
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

   private TextField[] edits;
   
   
   private Retriitti retriitti;
   
   
   
   private void alusta() {

       chooserOsallistujat.clear();
       chooserOsallistujat.addSelectionListener(e -> naytaOsallistuja());
       TextField[] edts1 = {editSukunimi, editEtunimi, editHetu};
       edits = edts1;
   }
   
   
   private void naytaOsallistuja() {
       Osallistuja osallistujaKohdalla = chooserOsallistujat.getSelectedObject();
       if (osallistujaKohdalla == null) return;
       
     
       OsallistujaGUIController.naytaOsallistuja(edits, osallistujaKohdalla);
       naytaWorkshopit(osallistujaKohdalla);
       
   }
    
   
   private void naytaWorkshopit(Osallistuja osallistuja) {
       tableWorkshopit.clear();
       if (osallistuja == null) return;
       
       ArrayList<Integer> osWS = new ArrayList<Integer>();
       osWS = retriitti.annaWorkshopit(osallistuja);
       if (osWS.size() == 0 ) return;
       
       for (Integer i: osWS) {
           Workshop workshop = retriitti.annaWorkshop(i.intValue());
           naytaWorkshop(workshop);      
       }
   }
 
   
   private void naytaWorkshop(Workshop ws) {
       String[] rivi = ws.toString().split("\\|");
       tableWorkshopit.add(ws, rivi[1], rivi[2], rivi[3], rivi[4] );
   }
               
   
   
   
   /**
    * Tietojen tallennus
    */
   private void tallenna() {
       retriitti.tallenna("ohjelmointi");
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
     * Avaa retriitin tiedot lukemalla ne tiedostoista
     */
   private void avaa() {
       
           retriitti.lueTiedosto("ohjelmointi");
           hae(0);
       
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
       
       retriitti.lisaa(uusi);
    
       hae(uusi.getId());
   }
   
   private void muokkaa() {
       Osallistuja osallistujaKohdalla = chooserOsallistujat.getSelectedObject();
       if (osallistujaKohdalla == null) return;
       OsallistujaGUIController.kysyOsallistuja(null, osallistujaKohdalla);
   }
   
   
   /**
    * Lisätään retriittiin uusi Workshop
    */
   private void uusiWorkshop() {
       Workshop uusi = new Workshop();
       uusi.rekisteroi();
       uusi.asetaJoku();
      
       retriitti.lisaa(uusi); 
   }
   
   
   /**
    * lisätään osallistujalle uusi workshop
    */
   private void lisaaOsallistujalleWorkshop() {
       Osallistuja osallistujaKohdalla = chooserOsallistujat.getSelectedObject();
       if (osallistujaKohdalla == null) return;
       
       OsallistuuWorkshoppiin uusi = new OsallistuuWorkshoppiin();
       uusi.asetaJoku(osallistujaKohdalla);
       retriitti.lisaaOsWs(uusi);
       hae(osallistujaKohdalla.getId());
   }
}