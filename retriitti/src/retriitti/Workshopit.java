package retriitti;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.PrintStream;
import java.util.Arrays;
import java.util.Scanner;

/**
 * @author jyrit
 * @version 18.7.2022
 *
 */
public class Workshopit {

    private static final int MAX_WORKSHOPPEJA = 5;

    private int lkm = 0;
    private Workshop[] alkiot;


    /**
     * Oletusmuodostaja
     */
    public Workshopit() {
        alkiot = new Workshop[MAX_WORKSHOPPEJA];
    }


    /**
     * @return workhsoppien lukumäärä
     */
    public int getLkm() {
        return lkm;
    }

    /**
     * Lisää uuden workshopin tietorakenteeseen. Ottaa workshopin
     * omistukseensa.
     * @param workshop lisättävän workshopin viite. Huom tietorakenne muuttuu osallistujaksi.
     * @example
     * <pre name="test">
     * Workshopit ws = new Workshopit();
     * Workshop ws1 = new Workshop(), ws2 = new Workshop();
     * ws.getLkm() === 0;
     * ws.lisaa(ws1); ws.getLkm() === 1;
     * ws.lisaa(ws2); ws.getLkm() === 2;
     * ws.lisaa(ws1); ws.getLkm() === 3;
     * ws.anna(0) === ws1;
     * ws.anna(1) === ws2;
     * ws.anna(2) === ws1;
     * ws.anna(1) == ws1 === false;
     * ws.anna(1) == ws2 === true;
     * ws.anna(3) === ws1; #THROWS IndexOutOfBoundsException 
     * ws.lisaa(ws1); ws.getLkm() === 4;
     * ws.lisaa(ws1); ws.getLkm() === 5;
     * ws.lisaa(ws1);  
     * </pre>
     */
    public void lisaa(Workshop workshop) {
        if (lkm >= alkiot.length) alkiot = Arrays.copyOf(alkiot, lkm + 10);
        alkiot[lkm] = workshop;
        lkm++;
    }

    /**
     * Palauttaa viitteen i:nteen workshoppiin
     * @param i monennenko workshopin viiten halutaan
     * @return viite workshoppiin, jonka indeksi on i
     * @throws IndexOutOfBoundsException jos i ei ole sallitulla alueella
     */
    public Workshop anna(int i) throws IndexOutOfBoundsException {
        if ( i < 0 || this.lkm <= i)
            throw new IndexOutOfBoundsException("Laiton indeksi: " + i);
        return alkiot[i];
    }


    /**
     * Tallentaa workshoppien tiedot tiedostoon
     * Tiedot muodossa:
     * <pre>
     * 1 |Ohjelmointi | Teltta     | 6      | Tuuba Tontsa   
     * 2 |Testaus     | Kanervikko | 2      | Jurtta Jaakko 
     * </pre>
     * @param tiedNimi tallennettavan tiedoston nimi
     * @throws SailoException jos tallennus epäonnistuu
     */
    public void tallenna(String tiedNimi) throws SailoException {
        File fileNimi = new File(tiedNimi);
        try (PrintStream fo = new PrintStream(new FileOutputStream(fileNimi + "/workshopit.txt", false))){
            for (int i=0; i<getLkm(); i++) {
                Workshop ws = anna(i);
                ws.tulosta(fo);
            }
        } catch (FileNotFoundException e) {
            throw new SailoException("Tiedosto " + fileNimi.getAbsolutePath() + " ei löydy");
        }
    }

     
    /**
     * lukee workshoppien tiedot tiedostosta
     * @param tiedNimi hakemiston nimi
     * @throws SailoException jos lukeminen ei onnistu
     */
    public void lueTiedostosta(String tiedNimi) throws SailoException {
        String nimi = tiedNimi + "/workshopit.txt";
        File fnimi = new File(nimi);
        
        try (Scanner fi = new Scanner(new FileInputStream(fnimi))) {
            while (fi.hasNext()) {
                String s = fi.nextLine();
                Workshop ws = new Workshop();
                ws.parse(s);
                lisaa(ws);
            }
        } catch (FileNotFoundException e) {
            throw new SailoException("Ei saa luettua tiedostoa " + nimi);
        }
        
    }

    /**
     * @param args ei käytössä
     */
    public static void main(String[] args) {
        Workshopit workshopit = new Workshopit();
        Workshop homma1 = new Workshop();
        Workshop homma2 = new Workshop();
        homma1.rekisteroi();
        homma2.rekisteroi();
        homma1.asetaJoku();
        homma2.asetaJoku();

        workshopit.lisaa(homma1);
        workshopit.lisaa(homma2);
      

        System.out.println("=============== Workshopit testi =========");

        for (int i = 0; i < workshopit.getLkm(); i++ ) {
            Workshop workshop = workshopit.anna(i);
            System.out.println("Workshop indeksi: " + i);
            workshop.tulosta(System.out);

        }
        try {
            workshopit.tallenna("wstesti.txt");
        } catch (SailoException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }
}
