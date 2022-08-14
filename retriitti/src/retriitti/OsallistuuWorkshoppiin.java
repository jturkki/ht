/**
 * 
 */
package retriitti;

import java.io.PrintStream;

import fi.jyu.mit.ohj2.Mjonot;

/**
 * @author jyrit
 * @version 26.7.2022
 *
 */
public class OsallistuuWorkshoppiin {
    
    private int id;
    private int osid;
    private int wsid;
    private static int seuraavanro = 1;
    
    /**
     * alustaminen
     */
    public OsallistuuWorkshoppiin() {
        //
    }
    
    /**
     * alustaa osallistumisen workshoppiin
     * @param osid osallistujan id
     * @param wsid workshopin id
     */
    public OsallistuuWorkshoppiin(int osid, int wsid) {
        this.id = seuraavanro;
        seuraavanro++;
        this.osid = osid;
        this.wsid = wsid;
    }
    
    /** 
     * @return osallistujan id
     */
    public int getOsallistuja() {
        return osid;
    }
    
    
    /**
     * @return workshopin id
     */
    public int getWorkshop() {
        return wsid;
    }
    
    
    /**
     * @return id-numeron workshoppiin osallistumisesta
     */
    public int getId() {
        return id;
    }
    
    
    /**
     * asettaa osallistumiselle workshoppiin id-numeron ja varmistaa
     * että seuraava numero on sitä suurempi
     * @param i asetettava id-numero
     */
    public void setId(int i) {
        id = i;
        if ( i>=seuraavanro) seuraavanro = i+1; 
    }
    
    /**
     * tulostaa osallistumisenworkshoppiin
     * tulostus muotoa:
     * <pre>
     *  1 | 1 | 1
     *  1 | 2 | 1
     * </pre>
     * @param out tietovirta johon tulostetaan
     */
    public void tulosta(PrintStream out) {
        out.println(this.toString());
    }
    
    /**
     * asettaa osallistumiset workshoppeihin merkkijonoksi
     * "id| osid| wsid"
     */
    @Override
    public String toString() {
        return "" + id + "| " + osid + "| " + wsid;
    }


   
  
     /**
     * asettaa osallistujan workshoppiin
     * @param osallistuja joka liittyy workshoppiin
     * @param wsnro workshopin id,  johon osallistuu
     */
    public void asetaWs(Osallistuja osallistuja, int wsnro) {            

        this.osid = osallistuja.getId();
        this.wsid = wsnro;
        this.id = seuraavanro;
        seuraavanro++;

        
    }
    
    
    /**
     * asettaa merkkijonona tulevat osallistumiset workshoppeihin 
     * 
     * @param rivi josta tiedot luetaan
     * @example
     * <pre name="test">
     * OsallistuuWorkshoppiin osws = new OsallistuuWorkshoppiin();
     * osws.parse("2|1|3");
     * osws.getId() === 2;
     * osws.toString() === "2| 1| 3";
     * </pre>
     */
    public void parse(String rivi) {
        StringBuilder sb = new StringBuilder(rivi);
        setId(Mjonot.erota(sb, '|', getId()));
        osid = Mjonot.erota(sb,'|', osid);
        wsid = Mjonot.erota(sb, '|', wsid);
    }
    
    /**
     * @param args ei käytössä
     */
    public static void main(String[] args) {
    // TODO Auto-generated method stub
    
    }

}
