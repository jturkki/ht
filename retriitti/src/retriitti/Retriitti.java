package retriitti;

/**
 * @author jyrit
 * @version 12.7.2022
 *
 */
public class Retriitti {
    
    private final Osallistujat osallistujat = new Osallistujat();
    
    

    /**
     * Palautetaan retriitin osallistujien lukumäärä
     * @return osallistujien lukumäärä
     */
    public int getOsallistujia() {
        return osallistujat.getLkm();
    }
    
    
    /**
     * Antaa i:nnen osallistujan
     * @param i monesko osallistuja palautetaan
     * @return viite i:nteen osallistujaan
     * @throws IndexOutOfBoundsException jos i ei sallitulla alueella
     */
    public Osallistuja annaOsallistuja(int i) throws IndexOutOfBoundsException {
        return osallistujat.anna(i);
    }
    
    
    /**
     * lisää osallistujan retriittiin
     * @param osallistuja joka lisätään
     * @throws SailoException jos tietorakenne täysi
     */
    public void lisaa(Osallistuja osallistuja) throws SailoException {
        this.osallistujat.lisaa(osallistuja);
    }

    /**
     * @param args ei käytössä
     */
    public static void main(String[] args) {
        Retriitti retriitti = new Retriitti();
        
        Osallistuja aku = new Osallistuja();
        Osallistuja aku2 = new Osallistuja();
        aku.rekisteroi();
        aku2.rekisteroi();
        aku.asetaAkuA();
        aku2.asetaAkuA();
        
        try {
            retriitti.lisaa(aku);
            retriitti.lisaa(aku2);
        } catch (SailoException e) {
            System.err.println(e.getMessage());
        }
        
        
        for (int i= 0; i<retriitti.getOsallistujia(); i++) {
            Osallistuja osallistuja = retriitti.annaOsallistuja(i);
            osallistuja.tulosta(System.out);
        }
    
    }

}
