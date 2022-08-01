package retriitti;

import java.io.OutputStream;
import java.io.PrintStream;

import kanta.HetuTarkistus;

import static kanta.HetuTarkistus.*;

/**
 * Retriitin osallistuja
 * @author jyrit
 * @version 12.7.2022
 *
 */
public class Osallistuja {
    
    private int id;
    private String sukunimi   = "";
    private String etunimi    = "";
    private String hetu       = "";
    private String katuosoite = "";
    private String postiosoite= "";
    private String puhelinnro = "";
    private String email      = "";
    
    private static int seuraavaNro = 1;
    
      
    
    /**
     * Tulostetaan osallistujan tiedot
     * @param out tietovirta johon tulostetaan
     */
    public void tulosta(PrintStream out) {
        out.println(String.format("%03d", id, 3) + "| " + etunimi + "| " + 
                sukunimi + "| " + hetu + "| " + katuosoite + "| " + postiosoite + "| " +
                puhelinnro + "| " + email);
    }
    
    
    /**
     * Tulostetaan osallistujan tiedot
     * @param os tietovirta johon tulostetaan
     */
    public void tulosta(OutputStream os) {
        tulosta(new PrintStream(os));
    }
    
    /**
     * apumetodi osallistujan testausta varten, hetu arvottu
     * @param apuhetu arvottu hetu
     */
    public void asetaAkuA(String apuhetu) {
        etunimi = "Aku";
        sukunimi = "Ankka" + HetuTarkistus.rand(1, 100);
        hetu = apuhetu;
        katuosoite = "Paratiisitie 13";
        postiosoite = "00100 Ankkis";
        puhelinnro = "0400111222";
        email = "aku@ankkalinna.fi";
    }
    
    /**
     * Apumetodi antaa testiarvot osallistujalle.
     * Henkilötunnus arvotaan, jotta eri osallistujalla eri.
     * 
     */
    public void asetaAkuA() {
        String apuhetu = arvoHetu();
        asetaAkuA(apuhetu);
    }
    
    
    /**
     * Antaa osallistujalle seuraavan rekisterinumeron.
     * @return osallistujan uusi id
     * @example
     * <pre name="test">
     * Osallistuja aku1 = new Osallistuja();
     * aku1.getId() === 0;
     * aku1.rekisteroi();
     * Osallistuja aku2 = new Osallistuja();
     * aku2.rekisteroi();
     * int n1 = aku1.getId();
     * int n2 = aku2.getId();
     * </pre>
     */
    public int rekisteroi() {
        id = seuraavaNro;
        seuraavaNro++;
        return id;
    }
    
    /**
     * palauttaa osallistujan id:n
     * @return osallistujan id-nro
     */
    public int getId() {
        return id;
    }
    
    /**
     * @return osallistujan nimi muodossa sukunimi etunimi
     */
    public String getNimi() {
        return sukunimi + " " + etunimi;
    }
    
  
    /**
     * @param args ei käytetä
     */
    public static void main(String[] args) {
    Osallistuja aku = new Osallistuja();
    Osallistuja aku2 = new Osallistuja();
    aku.rekisteroi();
    aku2.rekisteroi();
    aku.tulosta(System.out);
    aku.asetaAkuA();
    aku.tulosta(System.out);
    
    aku2.asetaAkuA();
    aku2.tulosta(System.out);
    
    }

}
