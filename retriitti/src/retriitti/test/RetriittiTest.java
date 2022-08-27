package retriitti.test;
// Generated by ComTest BEGIN
import java.util.ArrayList;
import java.util.Arrays;
import static org.junit.Assert.*;
import org.junit.*;
import retriitti.*;
// Generated by ComTest END

/**
 * Test class made by ComTest
 * @version 2022.08.24 20:14:31 // Generated by ComTest
 *
 */
@SuppressWarnings({ "all" })
public class RetriittiTest {



  // Generated by ComTest BEGIN
  /** testRetriitti13 */
  @Test
  public void testRetriitti13() {    // Retriitti: 13
    Retriitti retriitti = new Retriitti(); 
    Osallistuja aku = new Osallistuja(); 
    Osallistuja aku2 = new Osallistuja(); 
    Workshop homma1 = new Workshop(); 
    Workshop homma2 = new Workshop(); 
    aku.parse("1|Ankka|Aku|010101-111D| Akukatu 1 | 11111 Alinna | 099111222 | aku@alinna"); 
    aku2.parse("2|Ankka|Tupu|030201-111C| Tupukatu 2 | 21112 Alinna | 099222222 | tupu@alinna"); 
    homma1.parse(" 1 |  kailotus | verstas   | 2| Arvo Naakka| 1 | 12.12.2022| 14:30"); 
    homma2.parse(" 2 |  hoilotus | kellari   | 3| Kaapo Kakko| 3 | 11.12.2022| 12:30"); 
    retriitti.lisaa(aku); 
    retriitti.lisaa(aku2); 
    retriitti.lisaa(homma1); 
    retriitti.lisaa(homma2); 
    assertEquals("From: Retriitti line: 27", 2, retriitti.getOsallistujia()); 
    assertEquals("From: Retriitti line: 28", 2, retriitti.getWorkshoppeja()); 
    OsallistuuWorkshoppiin o1 = new OsallistuuWorkshoppiin(); 
    OsallistuuWorkshoppiin o2 = new OsallistuuWorkshoppiin(); 
    OsallistuuWorkshoppiin o3 = new OsallistuuWorkshoppiin(); 
    o1.parse("1|1|1"); 
    o2.parse("2|1|2"); 
    o3.parse("3|2|1"); 
    retriitti.lisaaOsWs(o1); 
    retriitti.lisaaOsWs(o2); 
    retriitti.lisaaOsWs(o3); 
    assertEquals("From: Retriitti line: 38", aku2, retriitti.annaOsallistuja(1)); 
    assertEquals("From: Retriitti line: 39", homma2, retriitti.annaWorkshop(1)); 
    ArrayList<Integer> lista = new ArrayList<Integer>(); 
    lista = retriitti.annaWorkshopit(aku); 
    assertEquals("From: Retriitti line: 44", "[1, 2]", lista.toString()); 
  } // Generated by ComTest END
}