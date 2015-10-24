import java.util.*;
import java.io.*;
import java.util.concurrent.atomic.AtomicLong;

/**
 * Main class method to invoke the Ugly Service. All the classes are code in same file because of upload constraint,
 * JDK 1.8 good features are not used because of upload constraints.
 */
public class Main1 {

    // 90% scored working version

    public static void main(String[] args) throws IOException {

        test();

//        File file = new File(args[0]);
//        BufferedReader buffer = new BufferedReader(new FileReader(file));
//        String line;
//        while ((line = buffer.readLine()) != null) {
//            line = line.trim();
//            System.out.println(new UglyService().findUglyNumbersCount(line));
//        }
    }

    private static void test() {

long time1 =  System.currentTimeMillis();
ExpandedList expandedList = new UglyService().findAllCandidateUglyNumberEquations("9999999999999");

//System.out.println();
//
//System.out.println(expandedList);
//
//System.out.println();
//
//System.out.println(expandedList.size());
//
//System.out.println();

System.out.println(new UglyService().findAllUglyNumbers(expandedList));

long time2 =  System.currentTimeMillis();

System.out.println((time2 - time1)/1000 + " seconds");

    }

    /**
     * Provides a set of services for determining ugly numbers.
     */
    static class UglyService {

        private ExpandService expandService = new ExpandService();

        /**
         * Returns the number of ugly numbers for the provided input String.
         *
         * @param forRequest of the format "011" or 123" etc.
         * @return number of ugly numbers count possible for the provided number.
         */
        public Long findUglyNumbersCount(String forRequest) {

            ExpandedList candidateUglyNumbers = findAllCandidateUglyNumberEquations(forRequest);

            return findAllUglyNumbers(candidateUglyNumbers);

        }


        /**
         * Returns call the candidate ugly number equations for the provided input String.
         *
         * @param forRequest of the format "011" or 123" etc.
         * @return All ugly number candidate equations possible for the provided number.
         */
        public ExpandedList findAllCandidateUglyNumberEquations(String forRequest) {

//System.out.println(expandService.expand(new UglyRequest(forRequest)));
            return findAllCandidateUglyNumberEquations(expandService.expand(new UglyRequest(forRequest)));
        }


        private ExpandedList findAllCandidateUglyNumberEquations(ExpandedList forExpandedList) {

            ExpandedList expandedList = new ExpandedList();
            for (Expanded expanded : forExpandedList.getElements()) {
                expandedList = expandedList.add(expandService.expand((Expandable) expanded));
            }
            return expandedList;

        }


        private Long findAllUglyNumbers(ExpandedList candidateUglyNumbers) {

            Long count = 0L;
            for (Expanded expanded : candidateUglyNumbers.getElements()) {

                Long result = (Long) expanded.result();
                if (result == 0 || result % 2 == 0 || result % 3 == 0 || result % 5 == 0 || result % 7 == 0) {
                    count++;
                }
            }
            return count;
        }


    }

    /**
     * Request with value of the format "011".
     */
    static class UglyRequest implements Expandable {

        private final String value;

        public UglyRequest(String value) {
            this.value = value;
        }

        /**
         * {@inheritDoc}
         * <p/>
         * For a value of the format "011" this method returns an expandable list with following values
         * [(0, 11),
         * (01, 1),
         * (011, null)]
         */
        @Override
        public List<ExpandableElement> expand() {

            List<ExpandableElement> expandableElementList = new ArrayList<ExpandableElement>();

            for (int length = 1; length <= length(); length++) {
                expandableElementList.add(new ExpandableElement(expanded(length), expandable(length)));
            }

            return expandableElementList;
        }

        public int length() {
            return value.length();
        }

        /**
         * @param withLength length to be used for to determine the expanded part of enclosed value.
         * @return Expanded part of the enclosed value expanded using the passed parameter.
         */
        private Expanded expanded(int withLength) {
            return Expression.create(Long.valueOf(value.substring(0, withLength)));
        }

        /**
         * @param withLength length to be used to determine the expandable part of enclosed value.
         * @return Expandable part of the enclosed value expanded using the passed parameter.
         */
        private Expandable expandable(int withLength) {
            if (withLength == length()) {
                return null;
            } else {
                return new UglyRequest(value.substring(withLength));
            }
        }

    }

    /**
     * Service to expand the provided expandable request.
     */
    static class ExpandService {

        /**
         * @param expandable The method fully expands the request
         *                   and returns with the ExpandedList.
         * @return The completely expanded list.
         */
        public ExpandedList expand(Expandable expandable) {

            ExpandedList expandedList = new ExpandedList();

            List<ExpandableElement> expandableElements = expandable.expand();

            for (int i = 0; i < expandableElements.size(); i++) {

                ExpandableElement expandableElement = expandableElements.get(i);

                if (expandableElement.fullyExpanded()) {
                    expandedList = expandedList.add(expandableElement.getExpanded());
                } else {
                    expandableElements.addAll(expandableElement.expand());
                }
            }

            return expandedList;
        }
    }

    /**
     * Implementation classes can implement this interface and provide the logic on how to expand.
     * Framework takes care of expanding the implementation classes untill they are fully expanded.
     */
    interface Expandable {

        /**
         * Implementors should provide the logic to expand.
         *
         * @return The next expandable elements.
         */
        List<ExpandableElement> expand();


    }

    /**
     * Container for ExpandableElement.
     */
    static class ExpandableElement {

        /**
         * Th part that is expanded.
         */
        private final Expanded expanded;

        /**
         * The part that can be expanded further.
         */
        private final Expandable expandable;

        public ExpandableElement(Expanded expanded, Expandable expandable) {
            this.expanded = expanded;
            this.expandable = expandable;
        }

        /**
         * @return true if there is no expandable part to left to expand.
         */
        public boolean fullyExpanded() {
            return expandable == null;
        }

        public Expandable getExpandable() {
            return expandable;
        }

        public Expanded getExpanded() {
            return expanded;
        }

        /**
         * Expands by expanding the expandable art of this element and merges the existing expanded part
         * with the expanded part from the expanded result.
         *
         * @return The List of newly created ExpandableElement after expanding expanding expandable part and
         * merging each expanded part with the existing expanded result.
         */
        public List<ExpandableElement> expand() {

            List<ExpandableElement> result = new ArrayList<ExpandableElement>();

            for (ExpandableElement next : expandable.expand()) {
                result.add(new ExpandableElement(expanded.merge(next.getExpanded()), next.expandable));

            }

            return result;
        }
    }

    /**
     * Container to hold the expanded elements.
     */
    static class ExpandedList {


        private final Set<Expanded> elements;

        public ExpandedList() {
            this(new TreeSet<Expanded>());
        }

        public ExpandedList(Set<Expanded> elements) {
            this.elements = elements;
        }

        public ExpandedList add(Expanded element) {
            Set<Expanded> neweElements = new TreeSet<>(elements);
            neweElements.add(element);
            return new ExpandedList(neweElements);
        }

        public ExpandedList add(ExpandedList expandedList) {
            Set<Expanded> neweElements = new TreeSet<>(elements);
            neweElements.addAll(expandedList.elements);
            return new ExpandedList(neweElements);
        }

        public List<Expanded> getElements() {
            return new ArrayList<>(elements);
        }

        public int size() {
            return elements.size();
        }

        @Override
        public String toString() {
            StringBuilder message = new StringBuilder("ExpandedList{");
            for (Expanded expanded : elements) {
                message.append(System.lineSeparator()).append("Element: ").append(expanded);
            }
            message.append('}');

            return message.toString();
        }
    }

    /**
     * The interface to be implemented by the Expanded classes.
     */
    interface Expanded<T> {

        /**
         * Merges current expanded value with the argument expanded value.
         *
         * @param other argument to be merged.
         * @return Merged result.
         */
        Expanded merge(Expanded other);

        /**
         * @return Result of this expanded value.
         */
        T result();


    }


    /**
     * Holds an expression for e.g. List of numbers (1,2,3).
     */
    static class Expression implements Expanded, Expandable, Comparable<Expression> {

        private final List<Long> elements;

        public Expression(List<Long> elements) {
            this.elements = elements;
        }

        /**
         * {@inheritDoc}
         * Merges the current expression element by merging with the element of the other.
         */
        @Override
        public Expression merge(Expanded other) {

            Expression expression = (Expression) other;
            List<Long> newElements = new ArrayList<Long>(this.elements);
            newElements.addAll(expression.getElements());
            return new Expression(newElements);
        }

        /**
         * {@inheritDoc}
         */
        @Override
        public List<Long> result() {
            return elements;
        }

        /**
         * {@inheritDoc}
         * This expression is expandable too, for e.g. if the expression is of the format
         * (1,2,3) it can be expanded to the format {[1 +, (2,3)],[1 -, (2,3)]}.
         * <p/>
         * Later the expanded values can be further expanded.
         */
        @Override
        public List<ExpandableElement> expand() {

            List<ExpandableElement> expandableElementList = new ArrayList<ExpandableElement>();

            Expandable right = expandable(1);

            if (right != null) {

                expandableElementList.add(new ExpandableElement(expanded(1, OperatorEnum.PLUS), right));
                expandableElementList.add(new ExpandableElement(expanded(1, OperatorEnum.MINUS), right));

            } else {
                expandableElementList.add(new ExpandableElement(expanded(1, null), right));

            }

            return expandableElementList;
        }


        public Expanded expanded(int withLength, OperatorEnum operator) {
            return Equation.create(elements.get(withLength - 1), operator);
        }

        public Expandable expandable(int withLength) {
            if (withLength == size()) {
                return null;
            } else {
                return new Expression(elements.subList(withLength, size()));
            }
        }


        public List<Long> getElements() {
            return new ArrayList<Long>(elements);
        }

        public int size() {
            return elements.size();
        }

        @Override
        public String toString() {
            return elements.toString();
        }

        // Factory method

        public static Expression create(Long digits) {
            List<Long> elements = new ArrayList<Long>();
            elements.add(digits);
            return new Expression(elements);
        }


        @Override
        public int compareTo(Expression o) {
            return elements.toString().compareTo(o.elements.toString());
        }
    }

    /**
     * Holds an equation for e.g. of the format "0+1-1" and result of the equation calculated.
     */
    static class Equation implements Expanded, Comparable<Equation> {

        private static AtomicLong idGenerator = new AtomicLong(1);

        private Long id = idGenerator.incrementAndGet();

        private Long result;
        private String associatedEquation;
        private OperatorEnum mergeOperator;


        public Equation(Long withValue, String associatedEquation, OperatorEnum withOperator) {
            this.result = withValue;
            this.associatedEquation = associatedEquation;
            this.mergeOperator = withOperator;
        }


        /**
         * {@inheritDoc}
         * Merges other with current by using the merge operator.
         *
         * @Override
         */
        public Equation merge(Expanded otherArg) {

            Equation other = (Equation) otherArg;

            Long combinedValue = 0L;

            switch (mergeOperator) {
                case PLUS:
                    combinedValue = result + other.result;
                    break;
                case MINUS:
                    combinedValue = result - other.result;
                    break;
            }

            return new Equation(combinedValue, associatedEquation + other.associatedEquation, other.mergeOperator);
        }

        /**
         * {@inheritDoc}
         */
        @Override
        public Long result() {
            return result;
        }

        @Override
        public String toString() {
            return "Equation{" +
                    "result=" + result +
                    ", associatedEquation='" + associatedEquation + '\'' +
                    ", mergeOperator=" + mergeOperator +
                    '}';
        }

        public static Equation create(Long withValue, OperatorEnum withOperator) {

            String operatorTag = "";
            OperatorEnum operator = null;

            if (withOperator != null) {
                operatorTag = withOperator.toString();
                operator = withOperator;
            }

            return new Equation(withValue, withValue + operatorTag, operator);
        }


        @Override
        public int compareTo(Equation o) {
            return id.compareTo(o.id);
//            return associatedEquation.compareTo(o.associatedEquation);
        }
    }

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
}