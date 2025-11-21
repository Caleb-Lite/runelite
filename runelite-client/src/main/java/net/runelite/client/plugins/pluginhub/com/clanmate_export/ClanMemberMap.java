package net.runelite.client.plugins.pluginhub.com.clanmate_export;

/**
 * A simple mapping of RSN -> Rank
 */
public class ClanMemberMap {

    /**
     * The runescpae player's name
     */
    private String rsn;

    /**
     * The runescape player's rank
     */
    private String rank;

    /**
     *  Date the runscape player's joined the clan
     */
    private String joinedDate;

    /**
     * Initialize a map from runescape player name to rank
     *
     * @param rsn  - the player name
     * @param rank - the player rank
     * @param joinedDate - date player joined the clan
     */
    public ClanMemberMap(String rsn, String rank, String joinedDate) {
        this.rsn = rsn;
        this.rank = rank;
        this.joinedDate = joinedDate;
    }

    /**
     * @return the runescape player's name
     */
    public String getRSN() {
        return this.rsn;
    }

    /**
     * @return the runescape player's rank
     */
    public String getRank() {
        return this.rank;
    }

    /**
     * @return the runescape player's joined date
     */
    public String getJoinedDate() { return  this.joinedDate;}
}


