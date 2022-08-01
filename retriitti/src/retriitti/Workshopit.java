package retriitti;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.PrintStream;

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
     * lisää uuden workshopin tietorakenteeseen. ottaa workshopin omistukseensa.
     * @param workshop lisättävän workshopin viite
     * @throws SailoException jos tietorakenne on jo täynnä
     */
    public void lisaa(Workshop workshop) throws SailoException {
        if (lkm >= alkiot.length) throw new SailoException("Liikaa alkioita");
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
        try (PrintStream fo = new PrintStream(new FileOutputStream("data/" + fileNimi, false))){
            for (int i=0; i<getLkm(); i++) {
                Workshop ws = anna(i);
                ws.tulosta(fo);
            }
        } catch (FileNotFoundException e) {
            throw new SailoException("Tiedosto " + fileNimi.getAbsolutePath() + " ei löydy");
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

        try {
            workshopit.lisaa(homma1);
            workshopit.lisaa(homma2);
        } catch (SailoException e) {
            System.err.println(e.getMessage());
        }

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
