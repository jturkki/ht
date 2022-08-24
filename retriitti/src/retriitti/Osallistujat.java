/**
 * 
 */
package retriitti;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.PrintStream;
import java.util.Arrays;
import java.util.Scanner;

/**
 * Retriitin Osallistujat
 * 
 * @author jyrit
 * @version 12.7.2022
 *
 */
public class Osallistujat {
    
    private static final int MAX_OSALLISTUJIA = 5;
    
    private int lkm = 0;
    private Osallistuja[] alkiot;
    private boolean muutettu = false;
    
    
    /**
     * Oletusmuodostaja
     */
    public Osallistujat() {
        alkiot = new Osallistuja[MAX_OSALLISTUJIA];
    }
    
    /**
     * Palauttaa retriitin osallistujien lukumäärän
     * @return osallistujien lukumäärä
     */
    public int getLkm() {
        return lkm;
    }
    
    /**
     * Lisää uuden osallistujan tietorakenteeseen. Ottaa osallistujan
     * omistukseensa.
     * @param osallistuja lisättävän osallistujan viite. Huom tietorakenne muuttuu osallistujaksi.
     * @example
     * <pre name="test">
     * Osallistujat osallistujat = new Osallistujat();
     * Osallistuja aku1 = new Osallistuja(), aku2 = new Osallistuja();
     * osallistujat.getLkm() === 0;
     * osallistujat.lisaa(aku1); osallistujat.getLkm() === 1;
     * osallistujat.lisaa(aku2); osallistujat.getLkm() === 2;
     * osallistujat.lisaa(aku1); osallistujat.getLkm() === 3;
     * osallistujat.anna(0) === aku1;
     * osallistujat.anna(1) === aku2;
     * osallistujat.anna(2) === aku1;
     * osallistujat.anna(1) == aku1 === false;
     * osallistujat.anna(1) == aku2 === true;
     * osallistujat.anna(3) === aku1; #THROWS IndexOutOfBoundsException 
     * osallistujat.lisaa(aku1); osallistujat.getLkm() === 4;
     * osallistujat.lisaa(aku1); osallistujat.getLkm() === 5;
     * osallistujat.lisaa(aku1);  
     * </pre>
     */
    public void lisaa(Osallistuja osallistuja) {
        if (lkm >= alkiot.length) alkiot = Arrays.copyOf(alkiot, lkm+20);
        alkiot[lkm] = osallistuja;
        lkm++;
        muutettu = true;
    }
    
    
    /**
     * Korvaa osallistujan tietorakenteessa. Ottaa osallistujan omistukseensa.
     * Etsitään samalla id:llä oleva osallistuja. Jos ei löydy,
     * niin lisätään uutena osallistujana.
     * @param osall osallistujan viite. Huom. tietorakenne muuttuu omistajaksi.
     * @example
     * <pre name="test">
     * #THROWS CloneNotSupportedException
     * Osallistujat ost = new Osallistujat();
     * Osallistuja os1 = new Osallistuja(), os2 = new Osallistuja();
     * os1.rekisteroi(); os2.rekisteroi();
     * ost.getLkm() === 0;
     * ost.korvaaTaiLisaa(os1); ost.getLkm() === 1; 
     * ost.korvaaTaiLisaa(os2); ost.getLkm() === 2;
     * Osallistuja os3 = os1.clone();
     * os3.setKatuosoite("kukkuu 2");
     * ost.korvaaTaiLisaa(os3); ost.getLkm() === 2;
     * </pre>
     */
    public void korvaaTaiLisaa(Osallistuja osall) {
        int id = osall.getId();
        for (int i=0; i<lkm; i++) {
            if ( alkiot[i].getId() == id) {
                alkiot[i] = osall;
                muutettu = true;
                return;
            }
        }
        lisaa(osall);
    }
    
    
    /**
     * Palauttaa viitteen i:nteen osallistujaan
     * @param i monennenko osallistujan viiten halutaan
     * @return viite osallistujaan, jonka indeksi on i
     * @throws IndexOutOfBoundsException jos i ei ole sallitulla alueella
     */
    public Osallistuja anna(int i) throws IndexOutOfBoundsException {
        if ( i < 0 || this.lkm <= i)
            throw new IndexOutOfBoundsException("Laiton indeksi: " + i);
        return alkiot[i];
    }
    
    
    /**
     * Tallentaa osallistujien tiedot tiedostoon
     * Tiedot muodossa:
     * <pre>
     * 1 |Naakka |Aimo      |421160-122T  |Naakankatu 6    |55511 Naakkala  |0881234567  |naakat@linnut.fi
     * 2 |Kaali  |Heli      |552279-1443  |Kaalimaa 3      |11112 Vihannes  |0112233445  |heli@kaalimaa.fi
     * </pre>
     * @param tiedNimi tallennettavan tiedoston hakemisto
     * @throws SailoException jos tallennus epäonnistuu
     */
    public void tallenna(String tiedNimi) throws SailoException {
        if (!muutettu) return;
        File fileNimi = new File(tiedNimi);
        try (PrintStream fo = new PrintStream(new FileOutputStream(fileNimi + "/nimet.txt", false))){
            for (int i=0; i<getLkm(); i++) {
                Osallistuja os = anna(i);
                os.tulosta(fo);
            }
        } catch (FileNotFoundException e) {
            throw new SailoException("Tiedosto " + fileNimi.getAbsolutePath() + " ei löydy");
        }
    }
    
    
    /**
     * lukee Osallistujien tiedot tiedostosta
     * @param tiedNimi hakemiston nimi jossa tiedot sijaitsevat
     * @throws SailoException jos tiedoston luku ei onnistu
     */
    public void lueTiedostosta(String tiedNimi) throws SailoException {
        String nimi = tiedNimi + "/nimet.txt";
        File ftied = new File(nimi);
        
        try (Scanner fi = new Scanner(new FileInputStream(ftied))) {
            while (fi.hasNext()) {
                String s = "";
                s = fi.nextLine();
                Osallistuja os = new Osallistuja();
                os.parse(s);
                lisaa(os);
            }
        } catch ( FileNotFoundException e ) {
            throw new SailoException("Ei saa luettua tiedostoa " + nimi);
        }
        muutettu = false;
    }

    /**
     * @param args ei käytössä
     */
    public static void main(String[] args) {
    Osallistujat osallistujat = new Osallistujat();
    
    try {
        osallistujat.lueTiedostosta("testi");
    } catch (SailoException ex) {
        System.err.println(ex.getMessage());
    }
    Osallistuja aku = new Osallistuja();
    Osallistuja aku2 = new Osallistuja();
    
    aku.rekisteroi();
    aku2.rekisteroi(); 
    aku.asetaAkuA();
    aku2.asetaAkuA();

    osallistujat.lisaa(aku);
    osallistujat.lisaa(aku2);
    
    System.out.println("=============== Osallistujat testi =========");
    
    for (int i = 0; i < osallistujat.getLkm(); i++ ) {
        Osallistuja osallistuja = osallistujat.anna(i);
        System.out.println("Osallistuja indeksi: " + i);
        osallistuja.tulosta(System.out);
    }
    try {
        osallistujat.tallenna("testi");
    } catch (SailoException e) {
        
        e.printStackTrace();
    }
    
    
    }

   

}
