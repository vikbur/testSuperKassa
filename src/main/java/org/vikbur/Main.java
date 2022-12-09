package org.vikbur;


import org.json.JSONArray;

import java.util.*;
import java.util.stream.Collectors;

public class Main {

    public static void main(String[] args) {
        JSONArray jsonArray = new JSONArray(JSONHelper.getJSONArray());
        printParsedArray(jsonArray);
        System.out.println("________________________");
        JSONArray jsonArray2 = new JSONArray(JSONHelper.getJSONArray2());
        printParsedArray(jsonArray2);
    }

    private static void printParsedArray(JSONArray jsonArray){
        List<Row> array = JSONHelper.parseJSONArray(jsonArray);
        printJoinCombination(findCombinations(array));
    }
    private static void printJoinCombination(List<Combination> combinations) {
        combinations.forEach(System.out::println);
    }
    private static List<Combination> findCombinations(List<Row> array){
        List<Combination> combinations = new ArrayList<>();

        for (int i = 0; i < array.size(); i++){
            if (array.get(i).getMask() == 15) {
                Combination combination = new Combination();
                combination.addRow(array.get(i));
                combinations.add(combination);
                continue;
            }

            while (true){
                Combination nextCombination = findNextCombination(combinations, array, i);

                if (nextCombination == null) break;

                combinations.add(nextCombination);
            }
        }

        return combinations;
    }

    private static Combination findNextCombination(List<Combination> combinations, List<Row> array, int i){
        int currentSum = array.get(i).getMask();
        Combination combination = new Combination();
        combination.addRow(array.get(i));

        Row nextRow;
        int iterator = 1;
        while (currentSum != 15 && (nextRow = findNextRow(currentSum, iterator, array, combination.getRows().stream().map(Row::getMask).collect(Collectors.toSet()))) != null){

            Combination newCombination = new Combination(combination);
            newCombination.addRow(nextRow);

            if (combinationNotExist(combinations, newCombination)){
                currentSum += nextRow.getMask();
                combination.addRow(nextRow);
            }
            iterator++;
        }

        if (currentSum == 15){
            return combination;
        } else {
            return null;
        }

    }
    private static Row findNextRow(int currentSum, int i, List<Row> array, Set<Integer> masks){
        for (int j = i; j < array.size(); j++){
            if (masks.contains(array.get(j).getMask()) || array.get(j).getMask() == 0 || array.get(j).getMask() + currentSum > 15) continue;

            return array.get(j);
        }
        return null;
    }

    private static boolean combinationNotExist(List<Combination> combinations, Combination currentComb){
        return combinations.stream().noneMatch(comb -> comb.getResult().equals(currentComb.getResult()));
    }
}