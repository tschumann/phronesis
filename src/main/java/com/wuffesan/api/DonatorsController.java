package com.wuffesan.api;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class DonatorsController {

    /**
     * See https://github.com/Monochrome-Inc/ZombiePanic-HL/blob/master/src/game/client/gameui/gameui_viewport.cpp
     *
     * @param steamid
     * @param v
     * @return
     */
    @GetMapping("/donators/check/")
    public String donatorsCheck(@RequestParam(required = false) String steamid, @RequestParam(required = false) String v) {
        int version = Integer.parseInt(v);

        if (version == 2) {
            return "{\"steamid\":\"" + steamid + "\",\"type\":0,\"tier\":0,\"typename\":\"Invalid\",\"tiername\":\"Invalid\"}";
        }
        else if (version == 3) {
            return "{\"steamid\":\"" + steamid + "\",\"name\":\"\",\"type\":{\"name\":\"Invalid\",\"id\":0},\"tier\":{\"name\":\"Invalid\",\"id\":0},\"special\":{\"key\":0,\"value\":\"\"}}";
        }
        else {
            return "{\"steamid\":\"" + steamid + "\",\"type\":0,\"tier\":0}";
        }
    }
}
