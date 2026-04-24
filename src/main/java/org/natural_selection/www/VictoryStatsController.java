package org.natural_selection.www;

import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class VictoryStatsController {

    /**
     * @see https://github.com/unknownworlds/NS/blob/master/main/source/mod/AvHCurl.cpp
     * @see https://github.com/unknownworlds/NS/blob/master/releases/3.05f/source/mod/AvHGamerules.cpp
     * @note the request argument is required but make it optional so that errors are consistent for missing and invalid
     *
     * @return Nothing
     */
    @PostMapping({"/cgi-bin/VictoryStats.pl", "/cgi-bin/ikonboard/ikonboard.cgi"})
    public String victoryStats(@RequestBody(required = false) String request) {
        // TODO: add proper validation
        // TODO: guessing what the original implementation did for validation
        if (StringUtils.countOccurrencesOf(request, "?") != 7) {
            throw new NaturalSelectionValidationException("Invalid format");
        }

        // TODO: save stats

        return "";
    }

    /**
     * Handler to allow us to override the error message that gets returned.
     *
     * @param e
     * @return
     */
    @ExceptionHandler(NaturalSelectionValidationException.class)
    public ResponseEntity<String> handleValidationException(Exception e) {
        final NaturalSelectionValidationException validationException = (NaturalSelectionValidationException)e;

        return new ResponseEntity<>(validationException.getReason(), HttpStatusCode.valueOf(400));
    }
}
