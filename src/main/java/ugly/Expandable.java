package ugly;

import java.util.List;


/**
 * Implementation classes can implement this interface and provide the logic on how to expand.
 * Framework takes care of expanding the implementation classes untill they are fully expanded.
 */
interface Expandable {

    /**
     * Implementors should provide the logic to expand.
     *
     * @return The next expandable tokens.
     */
    List<ExpandableElement> expand();


}

