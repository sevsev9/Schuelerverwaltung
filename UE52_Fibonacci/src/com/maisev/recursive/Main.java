package com.maisev.recursive;

import java.io.File;
import java.util.ArrayList;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        UE2_Methods ue2 = new UE2_Methods();
        Dir_Methods ue3 = new Dir_Methods();
        ArrayList<String> arrayList;
        int n = 0;
        String directory;
        File nameDir;
    // UE2
        System.out.print("\n\t n: ");
        n = sc.nextInt();
        System.out.println(ue2.fak_iterative(n));
        System.out.println(ue2.fak_recursive(n));
        System.out.println(ue2.fib_iterative(n));
        System.out.println(ue2.fib_recursive(n));
    // Directory
        while (true) {
            System.out.print("\n\nPlease enter a Directory Path: ");
            directory = sc.next();

            try {
                nameDir = new File(directory);
                break;
            } catch (NullPointerException e) {
                System.out.println("\n\t File not found! Possibly a wrong path?");
            }
        }
        arrayList = ue3.getDirectories(nameDir);

        for (String p:arrayList) {
            System.out.println(p);
        }
    }
}
