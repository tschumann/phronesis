package org.natural_selection.www;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class VersionController {

    /**
     * @see https://github.com/unknownworlds/NS/blob/master/main/source/mod/AvHCurl.cpp
     *
     * @return The game version
     */
    @GetMapping({"/auth.txt", "/auth/version.txt"})
    public String getGameVersionString() {
        return "v3.2.0";
    }
}
