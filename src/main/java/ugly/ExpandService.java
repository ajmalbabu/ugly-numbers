package ugly;

import java.util.List;

/**
 * Service to expand the provided expandable request.
 */
class ExpandService {

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
