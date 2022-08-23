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
 *@example
 * <pre name="test">
 * Osallistuja aa = new Osallistuja();
 * aa.parse(" 1| Hopo| Hessu| 030201-111C|katuos 1|00001 Hopola|0400111222|hessu@hopo.fi");
 * aa.getNimi() === "Hopo Hessu";
 * aa.getEtunimi() === "Hessu";
 * aa.getSukunimi() === "Hopo";
 * aa.getHetu() === "030201-111C";
 * aa.getKatuosoite() === "katuos 1";
 * aa.getPostiosoite() === "00001 Hopola";
 * aa.getPuhelin() === "0400111222";
 * aa.getEmail() === "hessu@hopo.fi";
 * 
 * </pre>
 */
public class Osallistuja implements Cloneable {
    
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
     * asettaa osallistujalle etunimen
     * @param s nimi
     * @return null jos ok
     */
    public String setEtunimi(String s) {
        etunimi= s;
        return null;
    }
    
    /**
     * @return osallistujan hetu
     */
    public String getHetu() {
        return hetu;
    }

    
    private HetuTarkistus hetut = new HetuTarkistus();
    
    /**
     * asettaa osallistujalle hetun
     * @param s hetu
     * @return null jos ok
     * @example
     * <pre name="test">
     * Osallistuja aku = new Osallistuja();
     * aku.setHetu("401150") === "Liian suuri päivämäärä";
     * aku.setHetu("001088") === "Liian pieni päivämäärä";
     * aku.setHetu("121288-1") === "Yksilöosa liian lyhyt";
     * aku.setHetu("311212-2317") === null;
     * </pre>
     */
    public String setHetu(String s) {
        String virhe = hetut.tarkista(s);
        if (virhe != null) return virhe;
        hetu= s;
        return null;
    }
    
    
    /**
     * asettaa osallistujalle katuosoitteen
     * @param s katuosoite
     * @return null jos ok
     */
    public String setKatuosoite(String s) {
        katuosoite = s;
        return null;
    }
    
    
    /**
     * @return katuosoite
     */
    public String getKatuosoite() {
        return katuosoite;
    }

    /**
     * @return postiosoite
     */
    public String getPostiosoite() {
        return postiosoite;
    }

    
    
     /**
      * asettaa osallistujalle postiosoitteen
      * @param s postiosoite
      * @return null jos ok
      */
     public String setPostiosoite(String s) {
         postiosoite = s;
         return null;
     }
     
      /**
       * asettaa osallistujalle puhelinnumeron
       * @param s puhelinnumero
       * @return null jos ok
       */
      public String setPuhelin(String s) {
          puhelinnro = s;
          return null;
      } 
      
      /**
       * @return puhelinnumero
       */
      public String getPuhelin() {
          return puhelinnro;
      }
      
      
      /**
       * @return email
       */
      public String getEmail() {
          return email;
      }


      
      /**
       * asettaa osallistujalle emailin
       * @param s email
       * @return null jos ok
       */
      public String setEmail(String s) {
          email = s;
          return null;
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
    
    
    @Override
    public Osallistuja clone() throws CloneNotSupportedException {
        Osallistuja uusi;
        uusi = (Osallistuja) super.clone();
        return uusi;
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
