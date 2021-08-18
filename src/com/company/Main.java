package com.company;

import java.util.*;
import java.lang.*;


class Solution {

    public static boolean isBetween(int x, int lower, int upper) {
        return lower <= x && x <= upper;
    }


    public static boolean isInteger(String str) {
        try {
            Integer.parseInt(str);
            return true;
        } catch (NumberFormatException e) {
            return false;
        }
    }

    public static void addLastTwoIntegers(String op, ArrayList filteredOps) {
        int firstNumber=0;
        int firstNumberIndex;
        int secondNumber=0;

        for (int i = 1; i < (filteredOps.indexOf(op) + 1); i++) {
            if (isInteger((String) filteredOps.get((filteredOps.indexOf(op) - i)))) {


                firstNumberIndex = (filteredOps.indexOf(op) - i);
                firstNumber = Integer.parseInt((String) filteredOps.get((filteredOps.indexOf(op) - i)));
                System.out.println("First number is " + firstNumber);


                for (int j = 1; i < (firstNumberIndex + 1); i++) {


                    if (isInteger((String) filteredOps.get((firstNumberIndex - i)))) {


                        secondNumber = Integer.parseInt((String) filteredOps.get((firstNumberIndex - i)));
                        System.out.println("Second number is " + secondNumber);

                        break;

                    }


                }

                break;
            }
        }

        System.out.println("The summ of " +firstNumber+" and "+secondNumber +" is " + (firstNumber+secondNumber));

    }


    public static int getIndexForCop(String op, ArrayList filteredOps) {
//        if (filteredOps.indexOf(op) == 0) {
//            System.out.println("Score list must contain at least one number");
//            return 0;
//        }

        int index = -1;

        for (int i = 1; i < (filteredOps.indexOf(op) + 1); i++) {
            if (isInteger((String) filteredOps.get((filteredOps.indexOf(op) - i)))) {
                index = (filteredOps.indexOf(op) - i);
                return index;
            }
        }
        return index;
    }


    public static int calPoints(String[] ops) {
        int result = 0;

        String[] filteredOpsArray = Arrays.stream(ops).filter(element -> !element.equals("")).toArray(String[]::new);
        ArrayList<String> filteredOpsArrayList = new ArrayList<>(Arrays.asList(filteredOpsArray));

        for (String op : filteredOpsArray) {

            if (isInteger(op)) {

                if (isBetween(Integer.parseInt(op), (-3 * 104), (3 * 104))) {
                    System.out.println("this is a number");
                }
            } else if (op.equalsIgnoreCase("d")) {
                System.out.println("this is d");
            } else if (op.equalsIgnoreCase("+")) {
                addLastTwoIntegers(op, filteredOpsArrayList);
                System.out.println("this is +");
            } else if (op.equalsIgnoreCase("c")) {
                try {
                    filteredOpsArrayList.remove(getIndexForCop(op, filteredOpsArrayList));
                } catch (Exception e) {
                    System.out.println(e);
                }
                filteredOpsArrayList.remove(op);
            }


        }


        System.out.println(filteredOpsArrayList);


        return result;
    }
}

public class Main {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        String[] ops = sc.nextLine().split(" ");
        System.out.println(Solution.calPoints(ops));
    }
}
