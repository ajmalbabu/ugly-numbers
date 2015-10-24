package ugly;

/**
 * Enum to represent various arithmetic operators.
 */
enum OperatorEnum {

    PLUS(" + "), MINUS(" - ");

    private String value;

    OperatorEnum(String value) {
        this.value = value;
    }

    @Override
    public String toString() {
        return value;
    }
}
