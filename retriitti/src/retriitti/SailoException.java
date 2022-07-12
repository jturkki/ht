package retriitti;

/**
 * Poikkeusluokka tietorakenteesta aiheutuville poikkeuksille
 * @author jyrit
 * @version 12.7.2022
 *
 */
public class SailoException extends Exception {
    private static final long serialVersionUID = 1L;
    
    /**
     * Poikkeuksen muodostaja jolle tuodaan poikkeuksessa
     * käytetty viesti
     * @param viesti Poikkeuksen viesti
     */
    public SailoException(String viesti) {
        super(viesti);
    }
}
