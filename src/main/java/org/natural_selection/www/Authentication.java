package org.natural_selection.www;

public class Authentication {

    /**
     * A value from the TagToAuthMask function in https://github.com/unknownworlds/NS/blob/master/main/source/mod/AvHCurl.cpp
     */
    private String tag;

    /**
     * WON IDs are apparently integers: https://forums.unknownworlds.com/discussion/13092/won-id-question
     */
    private String wonID;

    /**
     * In STEAM_X:Y:Z format
     */
    private String steamID;

    public Authentication(String tag, String wonID, String steamID) {
        this.tag = tag;
        this.wonID = wonID;
        this.steamID = steamID;
    }

    public String getTag() {
        return tag;
    }

    public String getWonID() {
        return wonID;
    }

    public String getSteamID() {
        return steamID;
    }

    public String toString() {
        return tag + " " + wonID + " " + steamID + "\n";
    }
}
