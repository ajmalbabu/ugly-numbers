package ugly;

import java.util.List;

/**
 * Provides a set of services for determining ugly numbers.
 */
class UglyService {

    private ExpandService expandService = new ExpandService();

    /**
     * Returns the number of ugly numbers for the provided input String.
     * This is pure object oriented version, for large numbers
     * <code>findUglyNumbersCountOptimized</code> performs orders of
     * magnitude faster.
     *
     * @param forRequest of the format "011" or 123" etc.
     * @return number of ugly numbers count possible for the provided number.
     */
    public Long findUglyNumbersCount(String forRequest) {

        ExpandedList expressions = findExpressions(forRequest);

        return findUglyNumbersCount(expressions);

    }


    /**
     * Returns all the candidate expressions for the provided input String.
     *
     * @param forRequest of the format "011" or 123" etc.
     * @return All expressions possible for the provided number.
     * e.g. [(1+2+3), (1+2-3), (1+23), (1-23) etc.]
     */
    public ExpandedList findExpressions(String forRequest) {

        return findExpressions(findTokenList(forRequest));
    }

    /**
     * Returns the TokenList for the provided in put.
     *
     * @param forRequest of the format "011" or 123" etc.
     * @return All tokens possible for the provided number.
     * e.g. [(1,2,3), (1,23), (12,3), (123)]
     */
    public ExpandedList findTokenList(String forRequest) {

        return expandService.expand(new TokenListRequest(forRequest));
    }

    private ExpandedList findExpressions(ExpandedList forExpandedList) {

        ExpandedList expandedList = new ExpandedList();
        for (Expanded expanded : forExpandedList.getElements()) {
            expandedList = expandedList.add(expandService.expand((Expandable) expanded));
        }
        return expandedList;

    }


    private Long findUglyNumbersCount(ExpandedList forExpressions) {

        Long count = 0L;
        for (Expanded expanded : forExpressions.getElements()) {

            if (isUglyNumber((Long) expanded.result())) {
                count++;
            }
        }
        return count;
    }

    /**
     * This is the optimized version, the original pure object oriented version is
     * <code>findUglyNumbersCount</code> but this version performs orders
     * of magnitude faster than object oriented version.
     *
     * @param forRequest of the format "011" or 123" etc.
     * @return number of ugly numbers count possible for the provided number.
     */
    public Long findUglyNumbersCountOptimized(String forRequest) {

        Long uglyCount = 0L;

        ExpandedList tokenList = findTokenList(forRequest);

        for (Expanded expanded : tokenList.getElements()) {

            List<ExpandableElement> expandableElements = ((Expandable) expanded).expand();

            for (int i = 0; i < expandableElements.size(); i++) {

                ExpandableElement expandableElement = expandableElements.get(i);

                if (expandableElement.fullyExpanded()) {
                    if (isUglyNumber((Long) expandableElement.getExpanded().result()))
                        uglyCount++;
                } else {
                    expandableElements.addAll(expandableElement.expand());
                }
            }

        }

        return uglyCount;

    }

    private boolean isUglyNumber(Long number) {
        return number == 0 || number % 2 == 0 || number % 3 == 0 || number % 5 == 0 || number % 7 == 0;
    }

}
