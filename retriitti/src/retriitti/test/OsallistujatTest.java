package retriitti.test;
// Generated by ComTest BEGIN
import static org.junit.Assert.*;
import org.junit.*;
import retriitti.*;
// Generated by ComTest END

/**
 * Test class made by ComTest
 * @version 2022.08.24 19:11:00 // Generated by ComTest
 *
 */
@SuppressWarnings({ "all" })
public class OsallistujatTest {



  // Generated by ComTest BEGIN
  /** testLisaa50 */
  @Test
  public void testLisaa50() {    // Osallistujat: 50
    Osallistujat osallistujat = new Osallistujat(); 
    Osallistuja aku1 = new Osallistuja(), aku2 = new Osallistuja(); 
    assertEquals("From: Osallistujat line: 53", 0, osallistujat.getLkm()); 
    osallistujat.lisaa(aku1); assertEquals("From: Osallistujat line: 54", 1, osallistujat.getLkm()); 
    osallistujat.lisaa(aku2); assertEquals("From: Osallistujat line: 55", 2, osallistujat.getLkm()); 
    osallistujat.lisaa(aku1); assertEquals("From: Osallistujat line: 56", 3, osallistujat.getLkm()); 
    assertEquals("From: Osallistujat line: 57", aku1, osallistujat.anna(0)); 
    assertEquals("From: Osallistujat line: 58", aku2, osallistujat.anna(1)); 
    assertEquals("From: Osallistujat line: 59", aku1, osallistujat.anna(2)); 
    assertEquals("From: Osallistujat line: 60", false, osallistujat.anna(1) == aku1); 
    assertEquals("From: Osallistujat line: 61", true, osallistujat.anna(1) == aku2); 
    try {
    assertEquals("From: Osallistujat line: 62", aku1, osallistujat.anna(3)); 
    fail("Osallistujat: 62 Did not throw IndexOutOfBoundsException");
    } catch(IndexOutOfBoundsException _e_){ _e_.getMessage(); }
    osallistujat.lisaa(aku1); assertEquals("From: Osallistujat line: 63", 4, osallistujat.getLkm()); 
    osallistujat.lisaa(aku1); assertEquals("From: Osallistujat line: 64", 5, osallistujat.getLkm()); 
    osallistujat.lisaa(aku1); 
  } // Generated by ComTest END


  // Generated by ComTest BEGIN
  /** 
   * testKorvaaTaiLisaa82 
   * @throws CloneNotSupportedException when error
   */
  @Test
  public void testKorvaaTaiLisaa82() throws CloneNotSupportedException {    // Osallistujat: 82
    Osallistujat ost = new Osallistujat(); 
    Osallistuja os1 = new Osallistuja(), os2 = new Osallistuja(); 
    os1.rekisteroi(); os2.rekisteroi(); 
    assertEquals("From: Osallistujat line: 87", 0, ost.getLkm()); 
    ost.korvaaTaiLisaa(os1); assertEquals("From: Osallistujat line: 88", 1, ost.getLkm()); 
    ost.korvaaTaiLisaa(os2); assertEquals("From: Osallistujat line: 89", 2, ost.getLkm()); 
    Osallistuja os3 = os1.clone(); 
    os3.setKatuosoite("kukkuu 2"); 
    ost.korvaaTaiLisaa(os3); assertEquals("From: Osallistujat line: 92", 2, ost.getLkm()); 
  } // Generated by ComTest END
}