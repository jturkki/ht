package retriitti;

import java.io.OutputStream;
import java.io.PrintStream;

import fi.jyu.mit.ohj2.Mjonot;
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
        out.println(this.toString());
    }
    
    
    @Override
    public String toString() {
        return "" + id + "| " + sukunimi + "| " + 
                etunimi + "| " + hetu + "| " + katuosoite + "| " + postiosoite + "| " +
                puhelinnro + "| " + email;
    }
    
    
    /** Asettaa id:n ja varmistaa että seuraava numero on 
     * suurempi kuin tähän mennessä suurin id-numero
     * @param i asetettava id-nro
     */
    public void setId(int i) {
        id = i;
        if (i >= seuraavaNro) seuraavaNro = i+1;
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
     * aku2.getId() - aku1.getId() === 1;
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
     * @return osallistujan sukunimi
     */
    public String getSukunimi() {
        return sukunimi;
    }
    
    
    /**
     * asettaa osallistujan sukunimen
     * @param s sukunimi joka asetetaan
     * @return virheteksti jos epäsopiva
     */
    public String setSukunimi(String s) {
        sukunimi = s;
        return null;
    }
    
    /**
     * @return osallistujan etunimi
     */
    public String getEtunimi() {
        return etunimi;
    }
    
    /**
     * @return osallistujan hetu
     */
    public String getHetu() {
        return hetu;
    }
    
    /**
     * saa merkkijonorivin josta erotellaan osallistujan tiedot
     * ja lisätään ne osallistujalle
     * @param rivi josta tiedot luetaan
     * @example
     * <pre name="test">
     * Osallistuja os = new Osallistuja();
     * os.parse("  2 | Ankka | Aku | 010101-111D | Akukatu 1 | 11111 Alinna | 099111222 | aku@alinna");
     * os.getId() === 2;
     * os.toString() === "2| Ankka| Aku| 010101-111D| Akukatu 1| 11111 Alinna| 099111222| aku@alinna";
     * </pre>
     */
    public void parse(String rivi) {
        var sb = new StringBuilder(rivi);
        setId(Mjonot.erota(sb, '|', getId()));
        sukunimi = Mjonot.erota(sb, '|', sukunimi);  
        etunimi  = Mjonot.erota(sb, '|', etunimi);
        hetu     = Mjonot.erota(sb, '|', hetu);  
        katuosoite = Mjonot.erota(sb, '|', katuosoite);
        postiosoite = Mjonot.erota(sb, '|', postiosoite);
        puhelinnro = Mjonot.erota(sb, '|', puhelinnro);
        email    = Mjonot.erota(sb, '|', email);

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
