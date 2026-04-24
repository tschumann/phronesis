package org.natural_selection.www;

public class NaturalSelectionValidationException extends RuntimeException {

    private final String reason;

    public NaturalSelectionValidationException(String reason) {
        this.reason = reason;
    }

    public String getReason() {
        return reason;
    }
}
