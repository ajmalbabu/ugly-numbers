package ugly;

/**
 * The interface to be implemented by the Expanded classes.
 */
interface Expanded<T> extends Comparable {

    /**
     * Merges current expanded value with the argument expanded value.
     *
     * @param other argument to be merged.
     * @return Merged expressionResult.
     */
    Expanded merge(Expanded other);

    /**
     * @return Result of this expanded value.
     */
    T result();


}
