/**
 * 
 */
package retriitti;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;

/**
 * @author jyrit
 * @version 26.7.2022
 *
 */
public class OsallistumisetWorkshoppeihin implements Iterable<OsallistuuWorkshoppiin> {
    
    private final Collection<OsallistuuWorkshoppiin> alkiot = new ArrayList<OsallistuuWorkshoppiin>();
    
   
    
    
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
     * @param args ei käytössä
     */
    public static void main(String[] args) {
    // TODO Auto-generated method stub
    
    }

}
