import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.HashMap;
import java.util.Set;


/* 
Алгоритмы-числа
*/

public class Solution {
    private static Map<Integer, Map<Integer, Long>> multDigits;
    private static Set<Long> armstrongNumbers;

    static {
        armstrongNumbers = new HashSet<>();
        multDigits = new HashMap<>();

        for (int num = 2; num < 10; num++) {
            multDigits.put(num, new HashMap<>());

            for (int len = 1; len < 20; len++) {
                long mult;

                if (len == 1) mult = num;
                else mult = multDigits.get(num).get(len - 1) * num;

                multDigits.get(num).put(len, mult);
            }
        }
    }

    public static long[] getNumbers(long N) {
        for (long i = 1; i < N; i++) {
            if (isArmstrong(i)) armstrongNumbers.add(i);
        }

        List<Long> arrayRes = armstrongNumbers.stream().sorted().toList();
        long[] result = new long[arrayRes.size()];

        for (int i = 0; i < arrayRes.size(); i++) {
            result[i] = arrayRes.get(i);
        }

        return result;
    }

    private static boolean isArmstrong(long num) {
        String stringNum = String.valueOf(num);
        int len = stringNum.length();
        if (len >= 20) throw new IllegalArgumentException();

        long res = 0;
        for (char digOfNum: stringNum.toCharArray()) {
            int dig = Integer.parseInt(String.valueOf(digOfNum));
            res += getMultOfDigit(dig, len);
        }
        return res == num;
    }

    private static long getMultOfDigit(int dig, int len) {
        if (dig == 1) return 1L;
        if (dig == 0) return 0L;
        return multDigits.get(dig).get(len);
    }

    public static void main(String[] args) {
        long a = System.currentTimeMillis();
        System.out.println(Arrays.toString(getNumbers(9000)));
        long b = System.currentTimeMillis();
        System.out.println("memory " + (Runtime.getRuntime().totalMemory() - Runtime.getRuntime().freeMemory()) / (8 * 1024));
        System.out.println("time = " + (b - a) / 1000);

        a = System.currentTimeMillis();
        System.out.println(Arrays.toString(getNumbers(9000000)));
        b = System.currentTimeMillis();
        System.out.println("memory " + (Runtime.getRuntime().totalMemory() - Runtime.getRuntime().freeMemory()) / (8 * 1024));
        System.out.println("time = " + (b - a) / 1000);
    }
}