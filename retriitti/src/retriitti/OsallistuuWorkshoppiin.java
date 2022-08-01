/**
 * 
 */
package retriitti;

import java.io.PrintStream;

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
     * tulostaa osallistumisenworkshoppiin
     * tulostus muotoa:
     * <pre>
     *  1 | 1 | 1
     *  1 | 2 | 1
     * </pre>
     * @param out tietovirta johon tulostetaan
     */
    public void tulosta(PrintStream out) {
        out.println("" + id + "| " + osid + "| " + wsid);
    }


     private static int testi = 0;  // TODO: poista tämä
  
     /**
     * asettaa osallistujan workshoppiin
     * @param osallistuja joka liittyy workshoppiin
     */
    public void asetaJoku(Osallistuja osallistuja) {            // TODO: välitä tänne myös workshop 
        this.id = seuraavanro;
        seuraavanro++;
        this.osid = osallistuja.getId();
        this.wsid = testi;
        testi++;
        
    }
    
    /**
     * @param args ei käytössä
     */
    public static void main(String[] args) {
    // TODO Auto-generated method stub
    
    }

}
