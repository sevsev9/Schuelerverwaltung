package com.maisev.recursive;

import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        UE2_Methods ue2 = new UE2_Methods();
        int n = 0;
    // UE2
        System.out.print("\n\t n: ");
        n = sc.nextInt();
        System.out.println(ue2.fak_iterative(n));
        System.out.println(ue2.fak_recursive(n));
        System.out.println(ue2.fib_iterative(n));
        System.out.println(ue2.fib_recursive(n));
    // Directory
    }
}
