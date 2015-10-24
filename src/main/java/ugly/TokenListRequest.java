package ugly;

import java.util.ArrayList;
import java.util.List;

/**
 * Request with value of the format "011".
 */
class TokenListRequest implements Expandable {

    private final String value;

    public TokenListRequest(String value) {
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

        List<ExpandableElement> expandableElementList = new ArrayList<>();

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
        return TokenList.create(value.substring(0, withLength));
    }

    /**
     * @param withLength length to be used to determine the expandable part of enclosed value.
     * @return Expandable part of the enclosed value expanded using the passed parameter.
     */
    private Expandable expandable(int withLength) {
        if (withLength == length()) {
            return null;
        } else {
            return new TokenListRequest(value.substring(withLength));
        }
    }

}
