package ugly;

import java.util.ArrayList;
import java.util.List;

/**
 * Holds an TokenList for e.g. for a number of the format 012 the tokens held are (0,1,2).
 */
class TokenList implements Expanded, Expandable, Comparable<TokenList> {

    private final List<String> tokens;

    public TokenList(List<String> tokens) {
        this.tokens = tokens;
    }

    /**
     * {@inheritDoc}
     * Merges the current TokenList tokens by merging with the tokens of the other.
     */
    @Override
    public TokenList merge(Expanded other) {

        TokenList tokenList = (TokenList) other;
        List<String> newElements = new ArrayList<>(this.tokens);
        newElements.addAll(tokenList.getTokens());
        return new TokenList(newElements);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public List<String> result() {
        return tokens;
    }

    /**
     * {@inheritDoc}
     * TokenList is also expandable, for e.g. if the TokenList is of the format
     * (1,2,3) it can be expanded to an Expression {[1 +, (2,3)],[1 -, (2,3)]}.
     * <p/>
     * The expanded Expression has an expanded part and expandable part, the
     * expandable part can be further expanded to expanded and expandable.
     */
    @Override
    public List<ExpandableElement> expand() {

        List<ExpandableElement> expandableElementList = new ArrayList<>();

        Expandable right = expandable(1);

        if (right != null) {

            expandableElementList.add(new ExpandableElement(expanded(1, OperatorEnum.PLUS), right));
            expandableElementList.add(new ExpandableElement(expanded(1, OperatorEnum.MINUS), right));

        } else {
            expandableElementList.add(new ExpandableElement(expanded(1, null), null));

        }

        return expandableElementList;
    }


    public Expanded expanded(int withLength, OperatorEnum operator) {
        return Expression.create(tokens.get(withLength - 1), operator);
    }

    public Expandable expandable(int withLength) {
        if (withLength == size()) {
            return null;
        } else {
            return new TokenList(tokens.subList(withLength, size()));
        }
    }


    public List<String> getTokens() {
        return new ArrayList<>(tokens);
    }

    public int size() {
        return tokens.size();
    }

    @Override
    public String toString() {
        return tokens.toString();
    }

    // Factory method

    public static TokenList create(String digits) {
        List<String> elements = new ArrayList<>();
        elements.add(digits);
        return new TokenList(elements);
    }


    @Override
    public int compareTo(TokenList other) {
        return tokens.toString().compareTo(other.tokens.toString());
    }
}
