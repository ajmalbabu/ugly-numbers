package ugly;

import java.util.ArrayList;
import java.util.List;

/**
 * Container for ExpandableElement.
 */
class ExpandableElement {

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


    public Expanded getExpanded() {
        return expanded;
    }

    /**
     * Expands by expanding the expandable art of this element and merges the existing expanded part
     * with the expanded part from the expanded expressionResult.
     *
     * @return The List of newly created ExpandableElement after expanding expanding expandable part and
     * merging each expanded part with the existing expanded expressionResult.
     */
    public List<ExpandableElement> expand() {

        List<ExpandableElement> result = new ArrayList<>();

        for (ExpandableElement next : expandable.expand()) {
            result.add(new ExpandableElement(expanded.merge(next.getExpanded()), next.expandable));

        }

        return result;
    }
}
