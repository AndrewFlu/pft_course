package ru.moneta.pft.sandbox;

public class Primes {

    // цикл for
    public static boolean isPrimesLoopFor(int n) {
        for (int i = 2; i < n; i++) {
            if (n % i == 0) {
                return false;
            }
        }
        return true;
    }

    public static boolean isPrimesLoopFor(long n) {
        for (long i = 2; i < n; i++) {
            if (n % i == 0) {
                return false;
            }
        }
        return true;
    }

    public static boolean isPrimesLoopForFast(int n) {
        int j = (int) Math.sqrt(n);
        for (int i = 2; i < j; i++) {
            if (n % i == 0) {
                return false;
            }
        }
        return true;
    }

    // цикл while
    public static boolean isPrimesLoopWhile(int n) {
        int i = 2;
        while (i < n && n % i != 0) {
            i++;
        }
        return i == n;
    }
}
