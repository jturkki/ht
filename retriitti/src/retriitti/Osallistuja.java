package retriitti;

import java.io.OutputStream;
import java.io.PrintStream;

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
    private int puhelinnro    = 0;
    private String email      = "";
    
    private static int seuraavaNro = 1;
    
      
    
    /**
     * Tulostetaan osallistujan tiedot
     * @param out tietovirta johon tulostetaan
     */
    public void tulosta(PrintStream out) {
        out.println(String.format("%03d", id, 3) + " " + etunimi + " " + 
                sukunimi + " " + hetu);
        out.println(" " + katuosoite + " " + postiosoite + " " + " " +
                puhelinnro + " " + email);
    }
    
    
    /**
     * Tulostetaan osallistujan tiedot
     * @param os tietovirta johon tulostetaan
     */
    public void tulosta(OutputStream os) {
        tulosta(new PrintStream(os));
    }
    
    
    /**
     * Antaa osallistujalle seuraavan rekisterinumeron.
     * @return osallistujan uusi id
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
     * @param args ei käytetä
     */
    public static void main(String[] args) {
    Osallistuja aku = new Osallistuja();
    Osallistuja aku2 = new Osallistuja();
    aku.rekisteroi();
    aku2.rekisteroi();
    aku.tulosta(System.out);
 //   aku.asetaAkuA();
 //   aku.tulosta(System.out);
    
 //   aku2.asetaAkuA();
    aku2.tulosta(System.out);
    
    }

}
