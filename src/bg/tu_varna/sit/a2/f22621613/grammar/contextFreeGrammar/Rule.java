package bg.tu_varna.sit.a2.f22621613.grammar.contextFreeGrammar;

/**
 * Represents a production rule in a context-free grammar.
 */
public class Rule {
    private int number;
    private String left;
    private String right;

    /**
     * Default constructor for the Rule class.
     */
    public Rule() {}

    /**
     * Constructor for the Rule class with parameters.
     *
     * @param number The rule number.
     * @param left The left-hand side of the production rule.
     * @param right The right-hand side of the production rule.
     */
    public Rule(int number, String left, String right) {
        this.number = number;
        this.left = left;
        this.right = right;
    }

    /**
     * Gets the rule number.
     *
     * @return The rule number.
     */
    public int getNumber() {
        return number;
    }

    /**
     * Sets the rule number.
     *
     * @param number The rule number to set.
     */
    public void setNumber(int number) {
        this.number = number;
    }

    /**
     * Gets the left-hand side of the production rule.
     *
     * @return The left-hand side of the production rule.
     */
    public String getLeft() {
        return left;
    }

    /**
     * Sets the left-hand side of the production rule.
     *
     * @param left The left-hand side of the production rule to set.
     */
    public void setLeft(String left) {
        this.left = left;
    }

    /**
     * Gets the right-hand side of the production rule.
     *
     * @return The right-hand side of the production rule.
     */
    public String getRight() {
        return right;
    }
}
