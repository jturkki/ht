/**
 * 
 */
package retriitti;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.PrintStream;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Scanner;

/**
 * Pitää kirjaa osallistujien osallistumisista workshoppeihin
 * 
 * @author jyrit
 * @version 26.7.2022
 *@example
 * <pre name="test">
 * OsallistumisetWorkshoppeihin osws = new OsallistumisetWorkshoppeihin();
 * OsallistuuWorkshoppiin o1 = new OsallistuuWorkshoppiin();
 * OsallistuuWorkshoppiin o2 = new OsallistuuWorkshoppiin();
 * OsallistuuWorkshoppiin o3 = new OsallistuuWorkshoppiin();
 * o1.parse("1|1|1");
 * o2.parse("2|1|2");
 * o3.parse("3|2|1");
 * osws.lisaaOsWs(o1);
 * osws.lisaaOsWs(o2);
 * osws.lisaaOsWs(o3);
 * osws.anna(2) === o3;
 * </pre>
 */
public class OsallistumisetWorkshoppeihin implements Iterable<OsallistuuWorkshoppiin> {

    private final ArrayList<OsallistuuWorkshoppiin> alkiot = new ArrayList<OsallistuuWorkshoppiin>();
    private boolean muutettu = false;



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
        muutettu = true;
    }
    
    

    /**
     * @param os osallistuja jonka workshop poistetaan
     * @param ws workshop joka poistetaan
     */
    public void poista(Osallistuja os, Workshop ws) {
        Iterator<OsallistuuWorkshoppiin> iter = iterator();
        ArrayList<OsallistuuWorkshoppiin> poistot = new ArrayList<OsallistuuWorkshoppiin>();
        while (iter.hasNext()) {
            OsallistuuWorkshoppiin osws = iter.next();
            if (os.getId() == osws.getOsallistuja() && ws.getId() == osws.getWorkshop())
                    poistot.add(osws);          
        }
        
        for (OsallistuuWorkshoppiin i: poistot)
            alkiot.remove(i);
        
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
        if (muutettu) {
            File fileNimi = new File(tiedNimi);
            try (PrintStream fo = new PrintStream(new FileOutputStream(fileNimi + "/osallistuu.txt", false))){
                for (int i=0; i < alkiot.size(); i++) {
                    OsallistuuWorkshoppiin osws = anna(i);
                    osws.tulosta(fo);
                    muutettu = false;
                }
            } catch (FileNotFoundException e) {
                throw new SailoException("Tiedosto " + fileNimi.getAbsolutePath() + " ei löydy");
            }
        }
    }


    /**
     * lukee osallistumiset workshoppeihin tiedostosta
     * @param tiedNimi hakemiston nimi jossa tiedot talletettuna
     * @throws SailoException jos lukeminen epäonnistuu
     */
    public void lueTiedostosta(String tiedNimi) throws SailoException {
        String nimi = tiedNimi + "/osallistuu.txt";
        File fnimi = new File(nimi);

        try (Scanner fi = new Scanner(new FileInputStream(fnimi))) {
            while (fi.hasNext()) {
                String s = fi.nextLine();
                OsallistuuWorkshoppiin osws = new OsallistuuWorkshoppiin();
                osws.parse(s);
                lisaaOsWs(osws);
            }
        } catch ( FileNotFoundException e ) {
            throw new SailoException("Ei pysty lukemma tiedostoa " + nimi);
        }
    }

    /**
     * @param args ei käytössä
     */
    public static void main(String[] args) {
        // TODO Auto-generated method stub

    }

}
