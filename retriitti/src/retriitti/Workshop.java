package retriitti;

import java.io.OutputStream;
import java.io.PrintStream;

import kanta.HetuTarkistus;

/**
 * Workshop
 * @author jyrit
 * @version 18.7.2022
 *
 */
public class Workshop {
    
    private int id;
    private String workshop = "";
    private String sijainti = "";
    private int max_os;
    private String vetaja = "";
    
    private static int seuraavaNumero = 1;
    
    
    /**
     * tulostaa workshopin tiedot
     * @param out tietovirta johon tulostetaan
     */
    public void tulosta(PrintStream out) {
        out.println(String.format("%03d", id, 3) + " " + workshop + " " + sijainti + " " + max_os + " " + vetaja);
    }
    
    
    /**
     * tulostaa workshopin tiedot
     * @param os tietovirta johon tulostetaan
     */
    public void tulosta(OutputStream os) {
        tulosta(new PrintStream(os));
    }
    
    
    /**
     * Asettaa testausarvot workshopille
     */
    public void asetaJoku() {
        workshop = "Tuunaus" + HetuTarkistus.rand(1, 100);
        sijainti = "Kellari";
        max_os = 6;
        vetaja = "Albert Einstein";
    }
    
    
    /**
     * antaa workshopille seuraavan vapaan id-numeron
     * @return id numero
     */
    public int rekisteroi() {
        id = seuraavaNumero;
        seuraavaNumero++;
        return id;
    }
    
    
    /**
     * palauttaa workhsopin id:n
     * @return id
     */
    public int getId() {
        return id;
    }
    
    /**
     * palauttaa workshopin nimen
     * @return workshopin nimi
     */
    public String getWorkshop() {
        return workshop;
    }
    
    /**
     * @param args ei käytössä
     */
    public static void main(String args[]) {
        Workshop ws1 = new Workshop();
        Workshop ws2 = new Workshop();
        ws1.rekisteroi();
        ws2.rekisteroi();
        ws1.tulosta(System.out);
        ws1.asetaJoku();
        ws2.asetaJoku();
        ws1.tulosta(System.out);
        ws2.tulosta(System.out);
    }
    
}
