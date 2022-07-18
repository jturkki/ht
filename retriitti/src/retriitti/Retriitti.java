package retriitti;

/**
 * @author jyrit
 * @version 12.7.2022
 *
 */
public class Retriitti {
    
    private final Osallistujat osallistujat = new Osallistujat();
    private final Workshopit workshopit = new Workshopit();
    
    

    /**
     * Palautetaan retriitin osallistujien lukumäärä
     * @return osallistujien lukumäärä
     */
    public int getOsallistujia() {
        return osallistujat.getLkm();
    }
    
    
    /**
     * Palautetaan retriitin workshoppien lukumäärä
     * @return workshoppien lukumäärä
     */
    public int getWorkshoppeja() {
        return workshopit.getLkm();
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
     * Antaa i:nnen workshopin
     * @param i monesko workshop palautetaan
     * @return viiten i:nteen workshoppiin
     * @throws IndexOutOfBoundsException jos i ei sallitulla alueella
     */
    public Workshop annaWorkshop(int i) throws IndexOutOfBoundsException {
        return workshopit.anna(i);
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
     * lisaa workshopin retriittiin
     * @param workshop joka lisätään
     * @throws SailoException jos tietorakenne täysi
     */
    public void lisaa(Workshop workshop) throws SailoException {
        this.workshopit.lisaa(workshop);
    }

    /**
     * @param args ei käytössä
     */
    public static void main(String[] args) {
        Retriitti retriitti = new Retriitti();
        
        Osallistuja aku = new Osallistuja();
        Osallistuja aku2 = new Osallistuja();
        
        Workshop homma1 = new Workshop();
        Workshop homma2 = new Workshop();
        
        aku.rekisteroi();
        aku2.rekisteroi();
        aku.asetaAkuA();
        aku2.asetaAkuA();
        
        homma1.rekisteroi();
        homma2.rekisteroi();
        homma1.asetaJoku();
        homma2.asetaJoku();
        
        try {
            retriitti.lisaa(aku);
            retriitti.lisaa(aku2);
            retriitti.lisaa(homma1);
            retriitti.lisaa(homma2);
        } catch (SailoException e) {
            System.err.println(e.getMessage());
        }
        
        
        for (int i= 0; i<retriitti.getOsallistujia(); i++) {
            Osallistuja osallistuja = retriitti.annaOsallistuja(i);
            osallistuja.tulosta(System.out);
        }
    
        for (int i= 0; i<retriitti.getWorkshoppeja(); i++) {
            Workshop workshop = retriitti.annaWorkshop(i);
            workshop.tulosta(System.out);
        }
    }

}
