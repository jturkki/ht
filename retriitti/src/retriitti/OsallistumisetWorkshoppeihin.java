/**
 * 
 */
package retriitti;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.PrintStream;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;

/**
 * @author jyrit
 * @version 26.7.2022
 *
 */
public class OsallistumisetWorkshoppeihin implements Iterable<OsallistuuWorkshoppiin> {
    
    private final ArrayList<OsallistuuWorkshoppiin> alkiot = new ArrayList<OsallistuuWorkshoppiin>();
    
   
    
    
    /**
     * Alustus OsallistumisetWorkshoppeihin
     */
    public OsallistumisetWorkshoppeihin() {
        //
    }
    
    
        
    /**
     * lisää osallistujan osallistumisen workshoppiin tietorakenteeseen
     * @param osallistuminen joka lisätään tietorakenteeseen
     */
    public void lisaaOsWs(OsallistuuWorkshoppiin osallistuminen) {
        alkiot.add(osallistuminen);
    }

    @Override
    public Iterator<OsallistuuWorkshoppiin> iterator() {
        
        return alkiot.iterator();
    }
    
    /**
     * antaa listan osallistujan workshoppien id:t
     * @param osallistuja jonka workshoppien id:t listataan
     * @return kokonaislukutaulukko jossa workshop id:t
     */
    public ArrayList<Integer> annaWSlistaus(Osallistuja osallistuja) {
        Iterator<OsallistuuWorkshoppiin> iter = iterator();
        
        ArrayList<Integer> listaus = new ArrayList<Integer>();
        while (iter.hasNext()) {
            OsallistuuWorkshoppiin osws = iter.next();
            if(osws.getOsallistuja() == osallistuja.getId()) listaus.add(osws.getWorkshop());
        }
        return listaus;
    }
    
    
    /**
     * palauttaa i:nnen OsallistuuWorkshoppiin
     * @param i OsallistuuWorkshoppiin indeksi
     * @return yksittäinen alkio OsallistuuWorkshoppiin
     */
    public OsallistuuWorkshoppiin anna(int i) {
        if ( i<0 || i>= alkiot.size()) throw new IndexOutOfBoundsException("Laitoin indeksi " + i);
        return alkiot.get(i);
    }
    
    /**
     * Tallentaa workshoppien tiedot tiedostoon
     * Tiedot muodossa:
     * <pre>
     * 1  | 1  | 1            
     * 2  | 1  | 2          
     * 3  | 1  | 3               
     * 4  | 2  | 2   
     * </pre>
     * @param tiedNimi tallennettavan tiedoston nimi
     * @throws SailoException jos tallennus epäonnistuu
     */
    public void tallenna(String tiedNimi) throws SailoException {
        File fileNimi = new File(tiedNimi);
        try (PrintStream fo = new PrintStream(new FileOutputStream("data/" + fileNimi, false))){
            for (int i=0; i < alkiot.size(); i++) {
                OsallistuuWorkshoppiin osws = anna(i);
                osws.tulosta(fo);
            }
        } catch (FileNotFoundException e) {
            throw new SailoException("Tiedosto " + fileNimi.getAbsolutePath() + " ei löydy");
        }
    }
    
    /**
     * @param args ei käytössä
     */
    public static void main(String[] args) {
    // TODO Auto-generated method stub
    
    }

}
