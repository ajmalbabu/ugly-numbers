import java.util.*;
import java.io.*;
import java.util.concurrent.*;
import java.util.concurrent.atomic.AtomicLong;

/**
 * Main class method to invoke the Ugly Service. All the classes are code in same file because of upload constraint,
 * JDK 1.8 good features are not used because of upload constraints.
 */
public class Main3 {

    // The good version with threading.

    public static void main(String[] args) throws IOException {

//        test("0011");
        test("999999999999");

//        File file = new File(args[0]);
//        BufferedReader buffer = new BufferedReader(new FileReader(file));
//        String line;
//        while ((line = buffer.readLine()) != null) {
//            line = line.trim();
//            System.out.println(new UglyService().findUglyNumbersCount(line));
//        }
        System.exit(0);
    }

    private static void test(String forRequest) {

//        System.out.println(new ExpandService().expand(new TokenListRequest(forRequest)));
//
//        ExpandedList expandedList = new UglyService().findExpressions(forRequest);
//
//        System.out.println();
//
//        System.out.println(expandedList);
//
//        System.out.println();
//
//        System.out.println(expandedList.size());

        System.out.println();
        long time1 = System.currentTimeMillis();
        System.out.println(new UglyService().findUglyNumbersCount(forRequest));
        long time2 = System.currentTimeMillis();

        System.out.println((time2 - time1) / 1000 + " seconds");
    }

}

//
///**
// * Provides a set of services for determining ugly numbers.
// */
//class UglyService {
//
//    private ExpandService expandService = new ExpandService();
//
//    /**
//     * Returns the number of ugly numbers for the provided input String.
//     *
//     * @param forRequest of the format "011" or 123" etc.
//     * @return number of ugly numbers count possible for the provided number.
//     */
//    public Long findUglyNumbersCount(String forRequest) {
//
//        ExpandedList expressions = findExpressions(forRequest);
//
//        return findUglyNumbersCount(expressions);
//
//    }
//
//    /**
//     * Returns all the candidate expressions for the provided input String.
//     *
//     * @param forRequest of the format "011" or 123" etc.
//     * @return All expressions possible for the provided number.
//     * e.g. [(1+2+3), (1+2-3), (1+23), (1-23) etc.]
//     */
//    public ExpandedList findExpressions(String forRequest) {
//
//        return findExpressions(findTokenList(forRequest));
//    }
//
//    /**
//     * Returns the TokenList for the provided in put.
//     *
//     * @param forRequest of the format "011" or 123" etc.
//     * @return All tokens possible for the provided number.
//     * e.g. [(1,2,3), (1,23), (12,3), (123)]
//     */
//    public ExpandedList findTokenList(String forRequest) {
//
//        return expandService.expand(new TokenListRequest(forRequest));
//    }
//
//    private ExpandedList findExpressionsOriginal(ExpandedList forExpandedList) {
//
//        ExpandedList expandedList = new ExpandedList();
//        for (Expanded expanded : forExpandedList.getElements()) {
//            expandedList = expandedList.add(expandService.expand((Expandable) expanded));
//        }
//        return expandedList;
//
//    }
//
//    private static ExecutorService executorService = Executors.newFixedThreadPool(4);
//
//    private ExpandedList findExpressions(ExpandedList forExpandedList) {
//
//        List<Future<ExpandedList>> futures = new ArrayList<Future<ExpandedList>>();
//        ExpandedList expandedList = new ExpandedList();
//
//        for (Expanded expanded : forExpandedList.getElements()) {
//            final Expanded val = expanded;
//
//            Future f = executorService.submit(new Callable() {
//                @Override
//                public Object call() throws Exception {
//                    return expandService.expand((Expandable) val);
//                }
//            });
//
//            futures.add(f);
//
//        }
//
//        boolean done = false;
//        while (!done) {
//            done = true;
//            for (int i = 0; i < futures.size(); i++) {
//                if (!futures.get(i).isDone()) {
//                    done = false;
//                    break;
//                }
//            }
//            try {
//                Thread.sleep(30);
//            } catch (InterruptedException e) {
//                e.printStackTrace();
//            }
//        }
//
//        for (int i = 0; i < futures.size(); i++) {
//            try {
//                expandedList = expandedList.add((ExpandedList) futures.get(i).get());
//            } catch (Exception e) {
//                e.printStackTrace();
//            }
//        }
//
//        return expandedList;
//
//    }
//
//    private Long findUglyNumbersCount(ExpandedList forExpressions) {
//
//        Long count = 0L;
//        for (Expanded expanded : forExpressions.getElements()) {
//
//            Long result = (Long) expanded.result();
//            if (result == 0 || result % 2 == 0 || result % 3 == 0 || result % 5 == 0 || result % 7 == 0) {
//                count++;
//            }
//        }
//        return count;
//    }
//
//}
//
//
///**
// * Service to expand the provided expandable request.
// */
//class ExpandService {
//
//    /**
//     * @param expandable The method fully expands the request
//     *                   and returns with the ExpandedList.
//     * @return The completely expanded list.
//     */
//    public ExpandedList expand(Expandable expandable) {
//
//        ExpandedList expandedList = new ExpandedList();
//
//        List<ExpandableElement> expandableElements = expandable.expand();
//
//        for (int i = 0; i < expandableElements.size(); i++) {
//
//            ExpandableElement expandableElement = expandableElements.get(i);
//
//            if (expandableElement.fullyExpanded()) {
//                expandedList = expandedList.add(expandableElement.getExpanded());
//            } else {
//                expandableElements.addAll(expandableElement.expand());
//            }
//        }
//
//        return expandedList;
//    }
//}
//
///**
// * Implementation classes can implement this interface and provide the logic on how to expand.
// * Framework takes care of expanding the implementation classes untill they are fully expanded.
// */
//interface Expandable {
//
//    /**
//     * Implementors should provide the logic to expand.
//     *
//     * @return The next expandable tokens.
//     */
//    List<ExpandableElement> expand();
//
//
//}
//
///**
// * Container for ExpandableElement.
// */
//class ExpandableElement {
//
//    /**
//     * Th part that is expanded.
//     */
//    private final Expanded expanded;
//
//    /**
//     * The part that can be expanded further.
//     */
//    private final Expandable expandable;
//
//    public ExpandableElement(Expanded expanded, Expandable expandable) {
//        this.expanded = expanded;
//        this.expandable = expandable;
//    }
//
//    /**
//     * @return true if there is no expandable part to left to expand.
//     */
//    public boolean fullyExpanded() {
//        return expandable == null;
//    }
//
//
//    public Expanded getExpanded() {
//        return expanded;
//    }
//
//    /**
//     * Expands by expanding the expandable art of this element and merges the existing expanded part
//     * with the expanded part from the expanded expressionResult.
//     *
//     * @return The List of newly created ExpandableElement after expanding expanding expandable part and
//     * merging each expanded part with the existing expanded expressionResult.
//     */
//    public List<ExpandableElement> expand() {
//
//        List<ExpandableElement> result = new ArrayList<>();
//
//        for (ExpandableElement next : expandable.expand()) {
//            result.add(new ExpandableElement(expanded.merge(next.getExpanded()), next.expandable));
//
//        }
//
//        return result;
//    }
//}
//
///**
// * Container to hold the expanded tokens.
// */
//class ExpandedList {
//
//
//    private final Set<Expanded> elements;
//
//    public ExpandedList() {
//        this(new TreeSet<Expanded>());
//    }
//
//    public ExpandedList(Set<Expanded> elements) {
//        this.elements = elements;
//    }
//
//    public ExpandedList add(Expanded element) {
//        Set<Expanded> neweElements = new TreeSet<>(elements);
//        neweElements.add(element);
//        return new ExpandedList(neweElements);
//    }
//
//    public ExpandedList add(ExpandedList expandedList) {
//        Set<Expanded> neweElements = new TreeSet<>(elements);
//        neweElements.addAll(expandedList.elements);
//        return new ExpandedList(neweElements);
//    }
//
//    public List<Expanded> getElements() {
//        return new ArrayList<>(elements);
//    }
//
//    public int size() {
//        return elements.size();
//    }
//
//    @Override
//    public String toString() {
//        StringBuilder message = new StringBuilder("ExpandedList{");
//        for (Expanded expanded : elements) {
//            message.append(System.lineSeparator()).append("Element: ").append(expanded);
//        }
//        message.append('}');
//
//        return message.toString();
//    }
//}
//
///**
// * The interface to be implemented by the Expanded classes.
// */
//interface Expanded<T> {
//
//    /**
//     * Merges current expanded value with the argument expanded value.
//     *
//     * @param other argument to be merged.
//     * @return Merged expressionResult.
//     */
//    Expanded merge(Expanded other);
//
//    /**
//     * @return Result of this expanded value.
//     */
//    T result();
//
//
//}
//
///**
// * Request with value of the format "011".
// */
//class TokenListRequest implements Expandable {
//
//    private final String value;
//
//    public TokenListRequest(String value) {
//        this.value = value;
//    }
//
//    /**
//     * {@inheritDoc}
//     * <p/>
//     * For a value of the format "011" this method returns an expandable list with following values
//     * [(0, 11),
//     * (01, 1),
//     * (011, null)]
//     */
//    @Override
//    public List<ExpandableElement> expand() {
//
//        List<ExpandableElement> expandableElementList = new ArrayList<>();
//
//        for (int length = 1; length <= length(); length++) {
//            expandableElementList.add(new ExpandableElement(expanded(length), expandable(length)));
//        }
//
//        return expandableElementList;
//    }
//
//    public int length() {
//        return value.length();
//    }
//
//    /**
//     * @param withLength length to be used for to determine the expanded part of enclosed value.
//     * @return Expanded part of the enclosed value expanded using the passed parameter.
//     */
//    private Expanded expanded(int withLength) {
//        return TokenList.create(value.substring(0, withLength));
//    }
//
//    /**
//     * @param withLength length to be used to determine the expandable part of enclosed value.
//     * @return Expandable part of the enclosed value expanded using the passed parameter.
//     */
//    private Expandable expandable(int withLength) {
//        if (withLength == length()) {
//            return null;
//        } else {
//            return new TokenListRequest(value.substring(withLength));
//        }
//    }
//
//}
//
///**
// * Holds an TokenList for e.g. for a number of the format 012 the tokens held are (0,1,2).
// */
//class TokenList implements Expanded, Expandable, Comparable<TokenList> {
//
//    private final List<String> tokens;
//
//    public TokenList(List<String> tokens) {
//        this.tokens = tokens;
//    }
//
//    /**
//     * {@inheritDoc}
//     * Merges the current TokenList tokens by merging with the tokens of the other.
//     */
//    @Override
//    public TokenList merge(Expanded other) {
//
//        TokenList tokenList = (TokenList) other;
//        List<String> newElements = new ArrayList<>(this.tokens);
//        newElements.addAll(tokenList.getTokens());
//        return new TokenList(newElements);
//    }
//
//    /**
//     * {@inheritDoc}
//     */
//    @Override
//    public List<String> result() {
//        return tokens;
//    }
//
//    /**
//     * {@inheritDoc}
//     * TokenList is also expandable, for e.g. if the TokenList is of the format
//     * (1,2,3) it can be expanded to an Expression {[1 +, (2,3)],[1 -, (2,3)]}.
//     * <p/>
//     * The expanded Expression has an expanded part and expandable part, the
//     * expandable part can be further expanded to expanded and expandable.
//     */
//    @Override
//    public List<ExpandableElement> expand() {
//
//        List<ExpandableElement> expandableElementList = new ArrayList<>();
//
//        Expandable right = expandable(1);
//
//        if (right != null) {
//
//            expandableElementList.add(new ExpandableElement(expanded(1, OperatorEnum.PLUS), right));
//            expandableElementList.add(new ExpandableElement(expanded(1, OperatorEnum.MINUS), right));
//
//        } else {
//            expandableElementList.add(new ExpandableElement(expanded(1, null), null));
//
//        }
//
//        return expandableElementList;
//    }
//
//
//    public Expanded expanded(int withLength, OperatorEnum operator) {
//        return Expression.create(tokens.get(withLength - 1), operator);
//    }
//
//    public Expandable expandable(int withLength) {
//        if (withLength == size()) {
//            return null;
//        } else {
//            return new TokenList(tokens.subList(withLength, size()));
//        }
//    }
//
//
//    public List<String> getTokens() {
//        return new ArrayList<>(tokens);
//    }
//
//    public int size() {
//        return tokens.size();
//    }
//
//    @Override
//    public String toString() {
//        return tokens.toString();
//    }
//
//    // Factory method
//
//    public static TokenList create(String digits) {
//        List<String> elements = new ArrayList<>();
//        elements.add(digits);
//        return new TokenList(elements);
//    }
//
//
//    @Override
//    public int compareTo(TokenList other) {
//        return tokens.toString().compareTo(other.tokens.toString());
//    }
//}
//
///**
// * Holds an expression for e.g. of the format "0+1-1" and expressionResult of the expression calculated.
// */
//class Expression implements Expanded, Comparable<Expression> {
//
//    private static AtomicLong idGenerator = new AtomicLong(1);
//
//    private Long id = idGenerator.incrementAndGet();
//
//    /**
//     * Expression result is the computed value of the expression.
//     */
//    private Long expressionResult;
//
//    /**
//     * The actual expression in a human readable format
//     */
//    private String actualExpression;
//
//    /**
//     * When current expression needs to be merged with another one, this operator will be used to perform merge.
//     */
//    private OperatorEnum mergeOperator;
//
//
//    public Expression(Long withValue, String actualExpression, OperatorEnum withOperator) {
//        this.expressionResult = withValue;
//        this.actualExpression = actualExpression;
//        this.mergeOperator = withOperator;
//    }
//
//    /**
//     * {@inheritDoc}
//     * Merges other with current by using the merge operator.
//     *
//     * @Override
//     */
//    public Expression merge(Expanded otherArg) {
//
//        Expression other = (Expression) otherArg;
//
//        Long combinedValue = 0L;
//
//        switch (mergeOperator) {
//            case PLUS:
//                combinedValue = expressionResult + other.expressionResult;
//                break;
//            case MINUS:
//                combinedValue = expressionResult - other.expressionResult;
//                break;
//        }
//
//        return new Expression(combinedValue, actualExpression + other.actualExpression, other.mergeOperator);
//    }
//
//    /**
//     * {@inheritDoc}
//     */
//    @Override
//    public Long result() {
//        return expressionResult;
//    }
//
//    @Override
//    public String toString() {
//        return "Expression{" +
//                "expressionResult=" + expressionResult +
//                ", actualExpression='" + actualExpression + '\'' +
//                ", mergeOperator=" + mergeOperator +
//                '}';
//    }
//
//    public static Expression create(String withValue, OperatorEnum withOperator) {
//
//        String operatorTag = "";
//        OperatorEnum operator = null;
//
//        if (withOperator != null) {
//            operatorTag = withOperator.toString();
//            operator = withOperator;
//        }
//
//        return new Expression(Long.parseLong(withValue), withValue + operatorTag, operator);
//    }
//
//    @Override
//    public int compareTo(Expression other) {
//        return id.compareTo(other.id);
//        //return actualExpression.compareTo(other.actualExpression);
//    }
//}
//
///**
// * Enum to represent various arithmetic operators.
// */
//enum OperatorEnum {
//
//    PLUS(" + "), MINUS(" - ");
//
//    private String value;
//
//    OperatorEnum(String value) {
//        this.value = value;
//    }
//
//    @Override
//    public String toString() {
//        return value;
//    }
//}