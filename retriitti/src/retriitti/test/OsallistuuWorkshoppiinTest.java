package retriitti.test;
// Generated by ComTest BEGIN
import static org.junit.Assert.*;
import org.junit.*;
import retriitti.*;
// Generated by ComTest END

/**
 * Test class made by ComTest
 * @version 2022.08.24 19:25:13 // Generated by ComTest
 *
 */
@SuppressWarnings({ "all" })
public class OsallistuuWorkshoppiinTest {


  // Generated by ComTest BEGIN
  /** testOsallistuuWorkshoppiin16 */
  @Test
  public void testOsallistuuWorkshoppiin16() {    // OsallistuuWorkshoppiin: 16
    OsallistuuWorkshoppiin osws = new OsallistuuWorkshoppiin(); 
    osws.parse("2|1|3"); 
    assertEquals("From: OsallistuuWorkshoppiin line: 19", 2, osws.getId()); 
    assertEquals("From: OsallistuuWorkshoppiin line: 20", "2| 1| 3", osws.toString()); 
    assertEquals("From: OsallistuuWorkshoppiin line: 21", 1, osws.getOsallistuja()); 
    assertEquals("From: OsallistuuWorkshoppiin line: 22", 3, osws.getWorkshop()); 
  } // Generated by ComTest END


  // Generated by ComTest BEGIN
  /** testParse131 */
  @Test
  public void testParse131() {    // OsallistuuWorkshoppiin: 131
    OsallistuuWorkshoppiin osws = new OsallistuuWorkshoppiin(); 
    osws.parse("2|1|3"); 
    assertEquals("From: OsallistuuWorkshoppiin line: 134", 2, osws.getId()); 
    assertEquals("From: OsallistuuWorkshoppiin line: 135", "2| 1| 3", osws.toString()); 
  } // Generated by ComTest END
}