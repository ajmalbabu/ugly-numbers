package ugly;

/**
 * Holds an expression for e.g. of the format "0+1-1" and expressionResult of the expression calculated.
 */
class Expression implements Expanded {


    /**
     * Expression result is the computed value of the expression.
     */
    private Long expressionResult;

    /**
     * The actual expression in a human readable format
     */
    private String actualExpression;

    /**
     * When current expression needs to be merged with another one, this operator will be used to perform merge.
     */
    private OperatorEnum mergeOperator;


    public Expression(Long withValue, String actualExpression, OperatorEnum withOperator) {
        this.expressionResult = withValue;
        this.actualExpression = actualExpression;
        this.mergeOperator = withOperator;
    }

    /**
     * {@inheritDoc}
     * Merges other with current by using the merge operator.
     *
     * @Override
     */
    public Expression merge(Expanded otherArg) {

        Expression other = (Expression) otherArg;

        Long combinedValue = 0L;

        switch (mergeOperator) {
            case PLUS:
                combinedValue = expressionResult + other.expressionResult;
                break;
            case MINUS:
                combinedValue = expressionResult - other.expressionResult;
                break;
        }

        return new Expression(combinedValue, actualExpression + other.actualExpression, other.mergeOperator);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Long result() {
        return expressionResult;
    }

    @Override
    public String toString() {
        return "Expression{" +
                "expressionResult=" + expressionResult +
                ", actualExpression='" + actualExpression + '\'' +
                ", mergeOperator=" + mergeOperator +
                '}';
    }

    public static Expression create(String withValue, OperatorEnum withOperator) {

        String operatorTag = "";
        OperatorEnum operator = null;

        if (withOperator != null) {
            operatorTag = withOperator.toString();
            operator = withOperator;
        }

        return new Expression(Long.parseLong(withValue), withValue + operatorTag, operator);
    }

    @Override
    public int compareTo(Object other) {
        return actualExpression.compareTo(((Expression) other).actualExpression);
    }
}
