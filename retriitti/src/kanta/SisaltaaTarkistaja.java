package kanta;

/**
 * Tarkistaja joka tarkistaa ett jono sislt vain valittuja merkkej.
 * Hyvksyy tyhjn jonon.
 * @author vesal
 *
 */
public class SisaltaaTarkistaja  {

    /** Numeroita vastaavat kirjaimet */
    public static final String NUMEROT = "0123456789";

    /** Desimaalilukuun kyvt kirjaimet */
    public static final String DESIMAALINUMEROT = "0123456789.,";

    /**
     * Onko jonossa vain sallittuja merkkej
     * @param jono      tutkittava jono
     * @param sallitut  merkit joita sallitaan
     * @return true jos vain sallittuja, false muuten
     * @example
     * <pre name="test">
     *   onkoVain("123","12")   === false;
     *   onkoVain("123","1234") === true;
     *   onkoVain("","1234") === true;
     * </pre> 
     */
    public static boolean onkoVain(String jono, String sallitut) {
        for (int i=0; i<jono.length(); i++)
            if ( sallitut.indexOf(jono.charAt(i)) < 0 ) return false;
        return true;
    }


    private final String sallitut;


    /**
     * Luodaan tarkistaja joka hyvksyy sallitut merkit
     * @param sallitut hyvksyttvt merkit
     */
    public SisaltaaTarkistaja(String sallitut) {
        this.sallitut = sallitut;
    }


    /**
     * Tarkistaa ett jono sislt vain valittuja numeroita
     * @param jono tutkittava jono
     * @return null jos vain valittujan merkkej, muuten virheilmoitus
     * @example
     * <pre name="test">
     *   SisaltaaTarkistaja tar = new SisaltaaTarkistaja("123");
     *   tar.tarkista("12") === null;
     *   tar.tarkista("14") === "Saa olla vain merkkej: 123";
     *   tar.tarkista("")   === null;
     * </pre>
     */
    public String tarkista(String jono) {
        if ( onkoVain(jono, sallitut) ) return null;
        return "Saa olla vain merkkej: " + sallitut;
    }

}

