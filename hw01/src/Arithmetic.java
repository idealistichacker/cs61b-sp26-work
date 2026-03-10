import edu.princeton.cs.algs4.StdIn;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

/** Simple Arithmetic Class.
 * @author Josh Hug
 * */
public class Arithmetic {

    /** Computes product of two ints.
     * @param a Value 1
     * @param b Value 2
     * @return Product of a and b
     * */
    public static int product(int a, int b) {
        return a * b;
    }

    /** Computes sum of two ints (incorrectly).
     * @param a Value 1
     * @param b Value 2
     * @return Sum of a and b
     * */
    public static int sum(int a, int b) {
        return a + b;
    }

    /**
     * Using the example code from earlier in this worksheet, try to write a Java function below which returns
     * the difference between the maximum and minimum item in a List<Integer>. You may assume the list
     * has length at least 1. You can get the ith item of a List called L by calling L.get(0). You can get the
     * size of a list with L.size(). There are solutions that don’t use either of these functions.
     * */
    //Solution two:
//    public static int maxMinDiff(List<Integer> L) {
//        return Collections.max(L) - Collections.min(L);
//    }

    //Solution one:
    public static int maxMinDiff(List<Integer> L) {
        int maxNumber = L.get(0);
        int minNumber = L.get(0);
        for (int listNumber : L){
            if (listNumber > maxNumber){
                maxNumber = listNumber;
            }
            if (listNumber < minNumber) {
                minNumber = listNumber;
            }
        }
        return maxNumber - minNumber;
    }


    /**
     * Extra problem: Write a Java function that takes a List<String> and returns a map from each String
     * to the list of Strings that immediately follow it (i.e. come right after it). For example, if the input list is
     * ["I", "love", "java", "but", "I", "love", "python", "more"], then the output should be:
     * {
     * }
     *   "I": ["love", "love"],
     *   "love": ["java", "python"],
     *   "java": ["but"],
     *   "but": ["I"],
     *   "python": ["more"]
     */
    public static Map<String, List<String>> listOfFollowers(List<String> x){
        Map<String, List<String>> map = new TreeMap<>();
        // We loop up to size() - 1 because the very last word
        // has nothing following it.
        for (int i = 0; i < x.size() - 1; i++){
            String currentWord = x.get(i);
            String nextWord = x.get(i + 1);
            if (!map.containsKey(currentWord)) {
                map.put(currentWord, new ArrayList<>());
            }
            map.get(currentWord).add(nextWord);
        }
        return map;
    }

    public static void main(String[] args) {
        System.out.println("Give me a number! (no decimals, please)");
        int num1 = StdIn.readInt();
        System.out.println("Give me another number! (still no decimals)");
        int num2 = StdIn.readInt();

        System.out.println("The product of " + num1 + " and " + num2 + " is: " + product(num1, num2));
        System.out.println("The sum of " + num1 + " and " + num2 + " is: " + sum(num1, num2));
        List<Integer> L  = List.of(12,2,3,999,5,8,7,6);
        System.out.println("The difference" + " is: " + maxMinDiff(L));
    }
}

