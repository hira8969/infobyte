package com.game.model;

public class GuessResult {
    private final GuessOutcome outcome;
    private final String message;
    private final boolean roundComplete;

    public GuessResult(GuessOutcome outcome, String message, boolean roundComplete) {
        this.outcome = outcome;
        this.message = message;
        this.roundComplete = roundComplete;
    }

    public GuessOutcome getOutcome() {
        return outcome;
    }

    public String getMessage() {
        return message;
    }

    public boolean isRoundComplete() {
        return roundComplete;
    }
}
