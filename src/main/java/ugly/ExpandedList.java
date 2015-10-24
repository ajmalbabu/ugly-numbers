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
