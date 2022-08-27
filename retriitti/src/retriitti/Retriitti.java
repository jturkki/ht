package retriitti;

import java.util.ArrayList;
import java.util.Collection;


/**
 * Retriitti-luokka, joka huolehtii osallistujista, workshopeista sekä 
 * osallistumisista workshoppeihin.
 * 
 * @author jyrit
 * @version 12.7.2022
 *@example
 * <pre name="test">
 * Retriitti retriitti = new Retriitti();
 * Osallistuja aku = new Osallistuja();
 * Osallistuja aku2 = new Osallistuja();
 * Workshop homma1 = new Workshop();
 * Workshop homma2 = new Workshop();
 * aku.parse("1|Ankka|Aku|010101-111D| Akukatu 1 | 11111 Alinna | 099111222 | aku@alinna");
 * aku2.parse("2|Ankka|Tupu|030201-111C| Tupukatu 2 | 21112 Alinna | 099222222 | tupu@alinna");
 * homma1.parse(" 1 |  kailotus | verstas   | 2| Arvo Naakka| 1 | 12.12.2022| 14:30");
 * homma2.parse(" 2 |  hoilotus | kellari   | 3| Kaapo Kakko| 3 | 11.12.2022| 12:30");
 * retriitti.lisaa(aku);
 * retriitti.lisaa(aku2);
 * retriitti.lisaa(homma1);
 * retriitti.lisaa(homma2);
 * retriitti.getOsallistujia() === 2;
 * retriitti.getWorkshoppeja() === 2;
 * OsallistuuWorkshoppiin o1 = new OsallistuuWorkshoppiin();
 * OsallistuuWorkshoppiin o2 = new OsallistuuWorkshoppiin();
 * OsallistuuWorkshoppiin o3 = new OsallistuuWorkshoppiin();
 * o1.parse("1|1|1");
 * o2.parse("2|1|2");
 * o3.parse("3|2|1");
 * retriitti.lisaaOsWs(o1);
 * retriitti.lisaaOsWs(o2);
 * retriitti.lisaaOsWs(o3);
 * retriitti.annaOsallistuja(1) === aku2;
 * retriitti.annaWorkshop(1) === homma2;
 * #import java.util.ArrayList;
 * #import java.util.Arrays;
 * ArrayList<Integer> lista = new ArrayList<Integer>();
 * lista = retriitti.annaWorkshopit(aku);
 * lista.toString() === "[1, 2]";
 * </pre>
 */
public class Retriitti {
    
    private final Osallistujat osallistujat = new Osallistujat();
    private final Workshopit workshopit = new Workshopit();
    private final OsallistumisetWorkshoppeihin osallistumiset = new OsallistumisetWorkshoppeihin();
     
    

    /**
     * Palautetaan retriitin osallistujien lukumäärä
     * @return osallistujien lukumäärä
     */
    public int getOsallistujia() {
        return osallistujat.getLkm();
    }
    
    
    /**
     * Palautetaan retriitin workshoppien lukumäärä
     * @return workshoppien lukumäärä
     */
    public int getWorkshoppeja() {
        return workshopit.getLkm();
    }
    
    
    /**
     * Antaa i:nnen osallistujan
     * @param i monesko osallistuja palautetaan
     * @return viite i:nteen osallistujaan
     * @throws IndexOutOfBoundsException jos i ei sallitulla alueella
     */
    public Osallistuja annaOsallistuja(int i) throws IndexOutOfBoundsException {
        return osallistujat.anna(i);
    }
    
    
    /**
     * Antaa i:nnen workshopin
     * @param i monesko workshop palautetaan
     * @return viiten i:nteen workshoppiin
     * @throws IndexOutOfBoundsException jos i ei sallitulla alueella
     */
    public Workshop annaWorkshop(int i) throws IndexOutOfBoundsException {
        return workshopit.anna(i);
    }
    
    /**
     * listaa osallistujan workshoppien id:t
     * @param osallistuja jonka osallistumiset listataan
     * @return kokonaislukutaulukko jossa osallistujan workshoppien id:t
     */
    public ArrayList<Integer> annaWorkshopit(Osallistuja osallistuja) {
        ArrayList<Integer> wsLista = osallistumiset.annaWSlistaus(osallistuja);
        return wsLista;
    }
    
    
   
    
    /**
     * lisää osallistujan retriittiin
     * @param osallistuja joka lisätään
     */
    public void lisaa(Osallistuja osallistuja) {
        this.osallistujat.lisaa(osallistuja);
    }
    
    /**
     * @param osPois osallistuja joka poistetaan
     */
    public void poistaOsallistuja(Osallistuja osPois) {
        poistaOsWs(osPois, null);
        osallistujat.poista(osPois);  
    }
    
    
    /**
     * lisaa workshopin retriittiin
     * @param workshop joka lisätään
     */
    public void lisaa(Workshop workshop) {
        this.workshopit.lisaa(workshop);
    }
    
    /**
     * poistaa workshopin retriitistä
     * @param wsnr workshopin id joka poistetaan
     */
    public void poistaWorkshop(int wsnr) {
        if (wsnr > workshopit.getLkm()) return;
        Workshop w = workshopit.anna(wsnr-1);
        poistaOsWs(null, w);
        workshopit.poista(w);
        
    }
    
    
    /**
     * lisää osallistujalle workshop
     * @param osallistuminen workshoppiin joka lisätään
     */
    public void lisaaOsWs(OsallistuuWorkshoppiin osallistuminen) {
        this.osallistumiset.lisaaOsWs(osallistuminen);
    }
    
    
    /**
     * @param os osallistuja jolta poistetaan osallistuminen workshoppiin 
     * @param ws workshop joka poistetaan osallistujalta
     */
    public void poistaOsWs(Osallistuja os, Workshop ws) {
        osallistumiset.poista(os, ws);
        
    }
    
    /**
     * tallentaa retriitin tiedot tiedostoihin
     * @param tnimi hakemiston nimi johon tallennetaan
     */
    public void tallenna(String tnimi) {
        try {
            osallistujat.tallenna(tnimi);
            workshopit.tallenna(tnimi);
            osallistumiset.tallenna(tnimi);
            
            
        } catch (SailoException e) {
            
            e.printStackTrace();
        }
        
    }
    
    
    /**
     * lukee retriitin tiedot tiedostoista
     * @param tnimi hakemiston nimi johon tiedot talletettu
     */
    public void lueTiedosto(String tnimi) {
        try {
            osallistujat.lueTiedostosta(tnimi);
            workshopit.lueTiedostosta(tnimi);
            osallistumiset.lueTiedostosta(tnimi);
        } catch (SailoException e) {
            e.printStackTrace();
        }
    }
   
    /**
     * Korvaa osallistujan tietorakenteessa. Ottaa osallistujan
     * omistukseensa. Etsitään samalla id:llä oleva osallistuja.
     * Jos ei löydy, niin lisätään uutena osallistujana
     * @param osall lisättävän osallistujan viite. huom. tietorakenne muuttuu omistajaksi
     */
    public void korvaaTaiLisaa(Osallistuja osall) {
        osallistujat.korvaaTaiLisaa(osall);
        
    }
    
    
    /**
     * @param ehto jota vastaavat osallistujat listataan
     * @param k kentän indeksi mistä etsitään
     * @return lista osallistujista
     */
    public Collection<Osallistuja> etsi(String ehto, int k) {
        return osallistujat.etsi(ehto, k);
    }

    /**
     * @param args ei käytössä
     */
    public static void main(String[] args) {
        Retriitti retriitti = new Retriitti();
        
        Osallistuja aku = new Osallistuja();
        Osallistuja aku2 = new Osallistuja();
        
        Workshop homma1 = new Workshop();
        Workshop homma2 = new Workshop();
        
        aku.rekisteroi();
        aku2.rekisteroi();
        aku.asetaAkuA();
        aku2.asetaAkuA();
        
        homma1.rekisteroi();
        homma2.rekisteroi();
        homma1.asetaJoku();
        homma2.asetaJoku();
        
       
            retriitti.lisaa(aku);
            retriitti.lisaa(aku2);
            retriitti.lisaa(homma1);
            retriitti.lisaa(homma2);
        
        
        for (int i= 0; i<retriitti.getOsallistujia(); i++) {
            Osallistuja osallistuja = retriitti.annaOsallistuja(i);
            osallistuja.tulosta(System.out);
        }
    
        for (int i= 0; i<retriitti.getWorkshoppeja(); i++) {
            Workshop workshop = retriitti.annaWorkshop(i);
            workshop.tulosta(System.out);
        }
    }


   
}
