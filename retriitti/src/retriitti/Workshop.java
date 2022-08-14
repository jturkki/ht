package retriitti;

import java.io.OutputStream;
import java.io.PrintStream;

import fi.jyu.mit.ohj2.Mjonot;
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
    private String max_os;
    private String ohjaaja = "";
    private String kesto = "";
    private String pvm = "";
    private String klo = "";
    
    private static int seuraavaNumero = 1;
    
    
    /**
     * tulostaa workshopin tiedot
     * @param out tietovirta johon tulostetaan
     */
    public void tulosta(PrintStream out) {
        out.println(this.toString());
    }
    
    /**
     * asettaa workshopin tiedot merkkijonoksi muotoon:
     * "id| workshop| sijainti| max_os| vetaja"
     *
     */
    @Override
    public String toString() {
        return "" + id + "| " + workshop + "| " + sijainti + "| " + max_os + "| " + ohjaaja
                + "| " + kesto + "| " + pvm + "| " + klo;
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
        
        ohjaaja = "Albert Einstein";
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
     * asettaa workshopin id-numeron ja varmistaa että seuraava numero on sitä suurempi
     * @param i workshopin id-numero
     */
    public void setId(int i) {
        id = i;
        if (i >= seuraavaNumero) seuraavaNumero = i+1;
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
    public String getNimi() {
        return workshop;
    }
    
    
    /**
     * @param nimi workshopin nimi
     * @return null jos kaikki ok
     */
    public String setNimi(String nimi) {
        this.workshop = nimi;
        return null;
    }
    
    
    /**
     * asettaa workshopille sijainnin
     * @param sijainti workshopin sijainti
     * @return null jos kaikki ok
     */
    public String setSijainti(String sijainti) {
        this.sijainti = sijainti;
        return null;
    }
    
    /**
     * @return sijainti
     */
    public String getSijainti() {
        return this.sijainti;
    }
    
    /**
     * @return maksimi osallistujamäärä
     */
    public String getMaxOs() {
        return this.max_os;
    }
    
    /**
     * asettaa workshopille maksimi osallistujamäärän
     * @param maxOs workshopin maksimi osallistujamäärä
     * @return null jos kaikki ok
     */
    public String setMaxOs(String maxOs) {
        this.max_os = maxOs;
        return null;
    }
    
    
    /**
     * asettaa workshopille ohjaajan
     * @param ohjaaja workshopin ohjaaja
     * @return null jos kaikki ok
     */
    public String setOhjaaja(String ohjaaja) {
        this.ohjaaja = ohjaaja;
        return null;
    }
    
    
    /**
     * @return ohjaaja
     */
    public String getOhjaaja() {
        return this.ohjaaja;
    }
    
    /**
     * asettaa workshopille keston
     * @param kesto workshopin kesto
     * @return null jos kaikki ok
     */
    public String setKesto(String kesto) {
        this.kesto = kesto;
        return null;
    }
    
    
    /**
     * @return sijainti
     */
    public String getKesto() {
        return this.kesto;
    }
    
    
    /**
     * asettaa workshopille päivämäärän
     * @param pvm workshopin päivämäärä
     * @return null jos kaikki ok
     */
    public String setPvm(String pvm) {
        this.pvm = pvm;
        return null;
    }
    
    
    /**
     * @return päivämäärä
     */
    public String getPvm() {
        return pvm;
    }
    
    
    /**
     * asettaa workshopille kellonajan
     * @param klo aika
     * @return null jos kaikki ok
     */
    public String setKlo(String klo) {
        this.klo = klo;
        return null;
    }
    
    
    /**
     * @return kellonaika
     */
    public String getKlo() {
        return klo;
    }
    
    /**
     * asettaa workshopin tiedot saamastaan merkkijonorivistä
     * @param rivi josta tiedot haetaan
     * @example
     * <pre name="test">
     * Workshop ws = new Workshop();
     * ws.parse(" 3 |  kailotus | verstas   | 5| Arvo Naakka");
     * ws.getId() === 3;
     * ws.toString() === "3| kailotus| verstas| 5| Arvo Naakka";
     * </pre>
     */
    public void parse(String rivi) {
        StringBuilder sb = new StringBuilder(rivi);
        setId(Mjonot.erota(sb, '|', getId()));
        workshop = Mjonot.erota(sb, '|', workshop);
        sijainti = Mjonot.erota(sb, '|', sijainti);
        max_os = Mjonot.erota(sb, '|', max_os);
        ohjaaja  = Mjonot.erota(sb, '|', ohjaaja);
        kesto = Mjonot.erota(sb, '|', kesto);
        pvm = Mjonot.erota(sb, '|', pvm);
        klo = Mjonot.erota(sb, '|', klo);
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
