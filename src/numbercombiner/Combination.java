package numbercombiner;

import java.util.ArrayList;
import java.util.Arrays;

/**
 *
 * @author Christian
 */
public class Combination extends ArrayList<Integer> implements Comparable<Combination> {

    public Combination(Integer... numbers) {
        super();
        addAll(Arrays.asList(numbers));
    }

    @Override
    public int compareTo(Combination other) {
//        System.out.println("Compare for Combination: " + hashCode() + " --> " + toString());
        for (int i = 0; i < this.size(); i++) {
            Integer thisNum;
            Integer otherNum;

            if (i > (this.size() - 1)) {
                thisNum = 0;
            } else {
                thisNum = this.get(i);
            }

            if (i > (other.size() - 1)) {
                otherNum = 0;
            } else {
                otherNum = other.get(i);
            }

            final int compResult = thisNum.compareTo(otherNum);
//            System.out.println("" + thisNum + " vs " + otherNum + " = " + compResult);
            if (compResult != 0) {
                return compResult;
            }
        }

        return 0;
    }

}
