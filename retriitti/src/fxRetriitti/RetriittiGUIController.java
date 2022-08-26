package fxRetriitti;




import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

import fi.jyu.mit.fxgui.ComboBoxChooser;
import fi.jyu.mit.fxgui.Dialogs;
import fi.jyu.mit.fxgui.ListChooser;

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
    @FXML TextField editKatuosoite;
    @FXML TextField editPostiosoite;
    @FXML TextField editPuhelin;
    @FXML TextField editEmail;
    @FXML StringGrid<Workshop> tableWorkshopit;
    @FXML TextField hakuehto;
    @FXML ComboBoxChooser<String> cbKentat;
    
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
    }
    
    /**
     * Workshopin poisto
     */
    @FXML private void handlePoistaWorkshop() {
        Dialogs.showMessageDialog("Workshopin poisto tästä");
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
   
   
   @FXML private void handleHakuehto() {
       hae(0);
   }
  
//====================================================================   

   private TextField[] edits;
   
   
   private Retriitti retriitti;
   
   
   
   
   private void alusta() {
       chooserOsallistujat.clear();
       chooserOsallistujat.addSelectionListener(e -> naytaOsallistuja());
       TextField[] edts1 = {editSukunimi, editEtunimi, editHetu, editKatuosoite, editPostiosoite, editPuhelin, editEmail};
       edits = edts1;
       
       cbKentat.clear();
       cbKentat.add("nimi", null);
       cbKentat.add("hetu", null);
       cbKentat.add("katuosoite", null);
       cbKentat.add("postiosoite", null);
       cbKentat.add("puhelin", null);
       cbKentat.add("email", null);
       cbKentat.getSelectionModel().select(0);
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
           Workshop workshop = retriitti.annaWorkshop(i.intValue()-1);
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
   
   
   /**
    * hakee osallistujan tiedot listaan
    * @param osnr osallistujan id, joka aktivoidaan haun jälkeen
    *   jos 0 niin aktivoidaan nykyinen jäsen
    */
   private void hae(int osnr) {
       int osnro = osnr;
       if (osnro == 0) {
           Osallistuja kohdalla = chooserOsallistujat.getSelectedObject();
           if (kohdalla != null)  osnro = kohdalla.getId();
       }
       chooserOsallistujat.clear();
       String ehto = hakuehto.getText();
       int index = 0;
       int ci = 0;
       for (int i = 0; i < retriitti.getOsallistujia(); i++) {
           Osallistuja osallistuja = retriitti.annaOsallistuja(i);
           if (!osallistuja.getNimi().contains(ehto)) continue;
           if (osallistuja.getId()== osnro) index = ci;
           chooserOsallistujat.add("" + osallistuja.getNimi(), osallistuja);
           ci++;
       }
       chooserOsallistujat.setSelectedIndex(index);
   }
   
   
  
   /**
    * Lisätään retriittiin uusi osallistuja
    */
   private void uusiOsallistuja() {
       
           Osallistuja uusi = new Osallistuja();
           uusi = OsallistujaGUIController.kysyOsallistuja(null, uusi);
           if (uusi == null) return;
           uusi.rekisteroi();
           retriitti.lisaa(uusi);
           hae(uusi.getId());
       
       
   }
   
   private void muokkaa() {
       Osallistuja osallistujaKohdalla = chooserOsallistujat.getSelectedObject();
       if (osallistujaKohdalla == null) return;
       
    try {
         Osallistuja osall = OsallistujaGUIController.kysyOsallistuja(null, osallistujaKohdalla.clone());
        if (osall == null) return;
        retriitti.korvaaTaiLisaa(osall);
        hae(osall.getId());
    } catch (CloneNotSupportedException e) {
        Dialogs.showMessageDialog(e.getMessage());
    }
       
   }
   
   
   /**
    * Lisätään retriittiin uusi Workshop
    */
   private void uusiWorkshop() {
       Workshop uusi = new Workshop();
       
       uusi = WorkshopGUIController.kysyWorkshop(null, uusi);
       if (uusi == null) return;
       uusi.rekisteroi();
       retriitti.lisaa(uusi);
        
   }
   
   
   /**
    * lisätään osallistujalle uusi workshop
    */
   private void lisaaOsallistujalleWorkshop() {
       Osallistuja osallistujaKohdalla = chooserOsallistujat.getSelectedObject();
       if (osallistujaKohdalla == null) return;
       
       int wsnro = valitseWorkshop();
       if (wsnro != -1) {
           OsallistuuWorkshoppiin uusi = new OsallistuuWorkshoppiin();
           uusi.asetaWs(osallistujaKohdalla, wsnro);
           retriitti.lisaaOsWs(uusi);
           hae(osallistujaKohdalla.getId());
       }
   }
   
   
   private int valitseWorkshop() {
        if (retriitti.getWorkshoppeja()<1) return -1;
        ArrayList<Workshop> alWs = new ArrayList<Workshop>();
        for (int i= 0; i<retriitti.getWorkshoppeja(); i++)
            alWs.add(retriitti.annaWorkshop(i));
    
        ArrayList<Workshop> alWs2 = new ArrayList<Workshop>();
        alWs2 = WSValintaGUIController.kysyWs(null, alWs);
        int ws = alWs2.get(0).getId();
        return ws;
    
      
   }
}