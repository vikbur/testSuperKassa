package org.vikbur;

import java.util.List;
import java.util.Objects;

public class Row {
    private final List<String> elements;
    private final int mask;

    public Row(List<String> elements, int mask) {
        this.elements = elements;
        this.mask = mask;
    }

    public List<String> getElements() {
        return elements;
    }

    public int getMask() {
        return mask;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Row row = (Row) o;
        return mask == row.mask && elements.equals(row.elements);
    }

    @Override
    public int hashCode() {
        return Objects.hash(elements, mask);
    }
}
