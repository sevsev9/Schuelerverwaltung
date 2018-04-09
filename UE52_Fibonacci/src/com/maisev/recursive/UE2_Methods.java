package com.maisev.recursive;

public class UE2_Methods {
    public int fib_iterative(int n) {
        int sum = 1;
        if (n <= 2) { return 1; }

        for (int i = 0; i <= n;i++) {
            sum+=i;
        }
        return sum;
    }

    public int fib_recursive(int n) {
        if (n <= 2){
            return 1;
        } else return fib_recursive(n-1) + fib_recursive(n-2);
    }

    public int fak_iterative(int n) {
        int sum=1;
        for (int i = 1; i<=n;i++)
            sum*=n;
        return sum;
    }

    public int fak_recursive(int n) {
        if (n <= 0)  return 1;
        else return n * fak_recursive(n-1);
    }
}
