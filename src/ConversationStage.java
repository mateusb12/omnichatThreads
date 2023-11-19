public class ConversationStage {
    private final String stage;
    private final String input;
    private final String expectedOutput;

    public ConversationStage(String stage, String input, String expectedOutput) {
        this.stage = stage;
        this.input = input;
        this.expectedOutput = expectedOutput;
    }

    // Getters
    public String getStage() {
        return stage;
    }

    public String getInput() {
        return input;
    }

    public String getExpectedOutput() {
        return expectedOutput;
    }
}
