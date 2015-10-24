package ugly;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.TreeSet;

/**
 * Container to hold the expanded tokens.
 */
class ExpandedList {


    private final Set<Expanded> elements;

    public ExpandedList() {
        this(new TreeSet<Expanded>());
    }

    public ExpandedList(Set<Expanded> elements) {
        this.elements = elements;
    }

    public ExpandedList add(Expanded element) {
        Set<Expanded> newElements = new TreeSet<>(elements);
        newElements.add(element);
        return new ExpandedList(newElements);
    }

    public ExpandedList add(ExpandedList expandedList) {
        Set<Expanded> newElements = new TreeSet<>(elements);
        newElements.addAll(expandedList.elements);
        return new ExpandedList(newElements);
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
