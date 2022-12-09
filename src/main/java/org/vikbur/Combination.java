package org.vikbur;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class Combination {
    private List<Row> rows;

    public Combination() {
        rows = new ArrayList<>();
    }

    public Combination(Combination combination) {
        rows = new ArrayList<>(combination.getRows());
    }
    public void addRow(Row row){
        if (!rows.contains(row)){
            rows.add(row);
        }
    }

    public List<Row> getRows() {
        return rows;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Combination that = (Combination) o;
        return Objects.equals(rows, that.rows);
    }

    @Override
    public int hashCode() {
        return Objects.hash(rows);
    }

    public List<String> getResult(){
        List<String> result = new ArrayList<>();

        for (int i = 0; i < rows.get(0).getElements().size(); i ++){
            for(Row row: rows){
                if (row.getElements().get(i) != null) {
                    result.add(row.getElements().get(i));
                    break;
                }
            }
            if (result.size() == i){
                result.add(null);
            }
        }
        return result;
    }

    @Override
    public String toString() {
        return getResult().toString();
    }
}
