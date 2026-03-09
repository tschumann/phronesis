package org.natural_selection.www;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class VictoryStatsController {

    /**
     * @see https://github.com/unknownworlds/NS/blob/master/main/source/mod/AvHCurl.cpp
     * @see https://github.com/unknownworlds/NS/blob/master/releases/3.05f/source/mod/AvHGamerules.cpp
     *
     * @return Nothing
     */
    @PostMapping("/cgi-bin/VictoryStats.pl")
    public String victoryStats() {
        // TODO: save stats

        return "";
    }

    /**
     * @see https://github.com/unknownworlds/NS/blob/master/main/source/mod/AvHCurl.cpp
     * @see https://github.com/unknownworlds/NS/blob/master/releases/3.05f/source/mod/AvHGamerules.cpp
     *
     * @return Nothing
     */
    @PostMapping("/cgi-bin/ikonboard/ikonboard.cgi")
    public String ikonBoard() {
        // TODO: save stats

        return "";
    }
}
