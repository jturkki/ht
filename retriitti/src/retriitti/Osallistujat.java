/**
 * 
 */
package retriitti;

/**
 * @author jyrit
 * @version 12.7.2022
 *
 */
public class Osallistujat {
    
    private static final int MAX_OSALLISTUJIA = 5;
    
    private int lkm = 0;
    private Osallistuja[] alkiot;
    
    
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
     * @throws SailoException jos tietorakenne on jo täynnä
     * @example
     * <pre name="test">
     * #THROWS SailoException 
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
     * osallistujat.lisaa(aku1);  #THROWS SailoException
     * </pre>
     */
    public void lisaa(Osallistuja osallistuja) throws SailoException {
        if (lkm >= alkiot.length) throw new SailoException("Liikaa alkioita");
        alkiot[lkm] = osallistuja;
        lkm++;
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
     * @param args ei käytössä
     */
    public static void main(String[] args) {
    Osallistujat osallistujat = new Osallistujat();
    Osallistuja aku = new Osallistuja();
    Osallistuja aku2 = new Osallistuja();
    
    aku.rekisteroi();
    aku2.rekisteroi(); 
    aku.asetaAkuA();
    aku2.asetaAkuA();
    
    try {
        osallistujat.lisaa(aku);
        osallistujat.lisaa(aku2);
    } catch (SailoException e) {
       System.err.println(e.getMessage());
    }
    
    
    System.out.println("=============== Osallistujat testi =========");
    
    for (int i = 0; i < osallistujat.getLkm(); i++ ) {
        Osallistuja osallistuja = osallistujat.anna(i);
        System.out.println("Osallistuja indeksi: " + i);
        osallistuja.tulosta(System.out);
    }
    
    }

}
