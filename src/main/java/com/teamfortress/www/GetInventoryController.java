package com.teamfortress.www;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class GetInventoryController {

    /**
     * @see https://github.com/ValveSoftware/source-sdk-2013/blob/master/src/game/client/tf/tf_gc_client.cpp
     *
     * @param appid Steam app ID
     * @param game_appid Steam app ID (legacy parameter?)
     * @param version
     * @return
     */
    @GetMapping("/ISDK/GetInventory/v0001")
    public String getInventory(@RequestParam(required = false) String appid, @RequestParam(required = false) String game_appid, @RequestParam(required = false) String version) {
        // TODO: fill out returned data - probably a JSON map as it probably has a VDF output option
        return "{}";
    }
}
