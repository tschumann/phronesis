package org.natural_selection.www;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class AuthenticationController {

    /**
     * @see https://github.com/unknownworlds/NS/blob/master/main/source/mod/AvHCurl.cpp
     *
     * @return WON/Steam users and their roles.
     */
    @GetMapping({"/auth.php", "/auth/"})
    public String getAuthentication() {
        final List<Authentication> users = new ArrayList<>() {{
            add(new Authentication("dev", "123", "STEAM_0:0:11101"));
            add(new Authentication("guide", "125", "STEAM_0:0:11102"));
        }};

        return users.stream()
                .map(Object::toString)
                .collect(Collectors.joining());
    }
}
