package numbercombiner;

import java.util.*;
import java.util.Map.Entry;
import static java.util.stream.Collectors.toMap;

public class NumberCombiner {

    public static final int UPPER_BOUND = 100;
    private final Combination scores = new Combination(40, 70, 100);
//    private final Combination scores = new Combination(13, 27, 49, 89);
//    private final Combination scores = new Combination(13, 27, 49, 52, 67, 89);
//    private final Combination scores = new Combination(10, 20, 30, 33, 40, 50, 60, 70, 80, 90, 100);

    private void run() {
        System.out.println("Build all combinations of the scores which lead to at least 100 signature points:\n");
        Set<Combination> combinations = new HashSet<>();
        for (Integer base : scores) {
            System.out.println("Working on base " + base);

            for (Integer friend : scores) {
                Combination combination = new Combination();
                int sum = 0;
                combination.add(base);
                sum += base;
                while (sum < UPPER_BOUND) {
                    sum += friend;
                    combination.add(friend);
                }
                Collections.sort(combination);
                System.out.println("Combination found: " + combination);
                combinations.add(combination);
            }
        }

        System.out.println("=========\n\n");
        System.out.println("Sort the possible combinations and Print them\n");
        List<Combination> sortedList = new ArrayList<>(combinations.size());
        sortedList.addAll(combinations);
        Collections.sort(sortedList);
        int i = 1;
        for (List<Integer> combination : sortedList) {
            System.out.println(
                    "Final Combination " + i + ": "
                    + combination + " = "
                    + combination.stream().mapToInt(Integer::intValue).sum());
            i++;
        }

        // Print it in a more readable way:
        System.out.println("=========\n");
        System.out.println("Resolved to a  SignatureCard that will be:\n");
        i = 1;

        for (Integer grp : scores) {
            System.out.println("  1 SignatoryGroup holding all Signatories with " + grp + " score points");
        }
        System.out.println("\n");

        for (List<Integer> combination : sortedList) {
            Map<Integer, Integer> countMap = new HashMap<>();
            System.out.println(
                    "  SignatureRuleSet " + i + ": "
                    + combination + " = "
                    + combination.stream().mapToInt(Integer::intValue).sum());

            // Count the number of required Signatories by "score/group"
            for (Integer score : combination) {
                Integer count = countMap.getOrDefault(score, 0);
                count += 1;
                countMap.put(score, count);
            }

            // Sort Map by values
            Map<Integer, Integer> sortedMap = countMap.entrySet().stream()
                    .sorted(Entry.comparingByValue())
                    .collect(toMap(Entry::getKey, Entry::getValue,
                            (e1, e2) -> e1, LinkedHashMap::new));

            int ruleCounter = 1;
            for (Integer score : sortedMap.keySet()) {
                final Integer count = countMap.get(score);
                String pluralSingular = count > 1 ? "ies" : "y";
                System.out.println(
                        "      -> SignatureRule " + ruleCounter + ": "
                        + count
                        + " Signator" + pluralSingular
                        + " from SignatoryGroup with " + score + " score points");
                ruleCounter++;
            }

            i++;
            System.out.println("\n");
        }

    }

    public static void main(String[] args) {
        NumberCombiner nc = new NumberCombiner();
        nc.run();
    }

}
