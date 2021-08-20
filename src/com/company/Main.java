package com.company;

import java.util.*;
import java.lang.*;
import java.util.stream.Collectors;

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

    public static int getSumOfLastTwoIntegers(String op, ArrayList filteredOps) {
        int firstNumber = 0;
        int firstNumberIndex;
        int secondNumber = 0;

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

        System.out.println("The sum of " + firstNumber + " and " + secondNumber + " is " + (firstNumber + secondNumber));
        return firstNumber + secondNumber;
    }

    public static Integer getLastIntegerDoubled(String op, ArrayList filteredOps) {


        try {
            List<String> integersBeforeD = (List) filteredOps.stream().filter(e -> filteredOps.indexOf(e) < filteredOps.indexOf(op)).collect(Collectors.toList());

            int lastElement = Integer.parseInt(integersBeforeD.get(integersBeforeD.size() - 1));
            System.out.println("Double of " + lastElement + " is " + (lastElement * 2));

            return lastElement * 2;
        } catch (Exception e) {

            if (e.toString().equals("java.lang.ArrayIndexOutOfBoundsException: -1"))

                System.out.println("provide number before " + op + " operation");
            else System.out.println(e);

            return null;
        }

    }


    public static int calPoints(String[] ops) {
        int result;
        String[] filteredOpsArray = Arrays.stream(ops).filter(element -> !element.equals("")).toArray(String[]::new);
        ArrayList<String> filteredOpsArrayList = new ArrayList<>(Arrays.asList(filteredOpsArray));

        for (String op : filteredOpsArray) {
            if (isInteger(op)) {
                if (isBetween(Integer.parseInt(op), (-3 * 104), (3 * 104))) {
                    System.out.println(op + " has been added");
                }
            } else if (op.equalsIgnoreCase("d")) {
Integer doubleOfLastInteger = getLastIntegerDoubled(op, filteredOpsArrayList);

                if (doubleOfLastInteger != null) {
                    filteredOpsArrayList.set(filteredOpsArrayList.indexOf(op), String.valueOf(doubleOfLastInteger));
                } else {
                    filteredOpsArrayList.remove(op);
                }
            } else if (op.equalsIgnoreCase("+")) {
                System.out.println("this is +");
                filteredOpsArrayList.set(filteredOpsArrayList.indexOf(op), String.valueOf(getSumOfLastTwoIntegers(op, filteredOpsArrayList)));
            } else if (op.equalsIgnoreCase("c")) {
                try {

                    filteredOpsArrayList.remove(filteredOpsArrayList.indexOf(op) - 1);
                } catch (Exception e) {

                    if (e.toString().equals("java.lang.ArrayIndexOutOfBoundsException: -1"))

                        System.out.println("provide number before " + op + " operation");
                    else System.out.println(e);


                }


                filteredOpsArrayList.remove(op);
            } else {
                System.out.println("Invalid entry");
                filteredOpsArrayList.remove(op);
            }
        }
        System.out.println(filteredOpsArrayList);

        result = filteredOpsArrayList.stream().mapToInt(x -> Integer.parseInt(x)).sum();


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