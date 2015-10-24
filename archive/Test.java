/**
 * Created by iky215 on 10/23/15.
 */
public class Test {

    public static void main(String[] str) {
        System.out.println("isUgly: " + isUgly(-12345678901234L));
    }

    public static boolean isUgly(Long result) {
        return result == 0 || result % 2 == 0 || result % 3 == 0 || result % 5 == 0 || result % 7 == 0;
    }
}
