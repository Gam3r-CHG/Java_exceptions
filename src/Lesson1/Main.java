package Lesson1;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.Arrays;

public class Main {
    public static void main(String[] args) {

        /*���������� 3 ������, ����� � ������ �� ��� �������� ������ ����������*/
        Exception1();
        Exception2();
        Exception3();

        /*���������� �����, ����������� � �������� ���������� ��� ������������� �������,
        � ������������ ����� ������, ������ ������� �������� ����� �������� ��������� ����
        �������� �������� � ��� �� ������. ���� ����� �������� �� �����, ���������� ���-��
        ���������� ������������.*/

        int[] array1 = {5, 10, 15, 20, 25};
        int[] array2 = {5, 4, 3, 2};
        int[] resultArray = getDifference(array1, array2);
        System.out.println(Arrays.toString(resultArray));

        /* ���������� �����, ����������� � �������� ���������� ��� ������������� �������,
        � ������������ ����� ������, ������ ������� �������� ����� �������� ��������� ����
        �������� �������� � ��� �� ������. ���� ����� �������� �� �����, ���������� ���-��
        ���������� ������������. �����: ��� ���������� ������ ������������ ����������,
        ������� ������������ ����� ������� - RuntimeException, �.�. ���� */

        int[] array3 = {5, 10, 15, 20, 25};
        int[] array4 = {5, 4, 3, 2};
        double[] resultArray2 = getQuotient(array3, array4);
        System.out.println(Arrays.toString(resultArray2));
    }

    public static void Exception1() {
        int[] array = {1, 2, 3, 4, 5};
        try {
            System.out.println(array[array.length]);
        } catch (IndexOutOfBoundsException e) {
            System.out.println("Exception: " + e);
        }
    }

    public static void Exception2() {
        try {
            File file = new File("1.txt");
            FileReader fileReader = new FileReader(file);
        } catch (IOException e) {
            System.out.println("Exception: " + e);
        }
    }

    public static void Exception3() {
        int a = 10, b = 0;
        try {
            int result = a / b;
        } catch (ArithmeticException e) {
            System.out.println("Exception: " + e);
        }
    }

    /*���������� �� ���, � ��������� ������� ������ ����� ���������� �� ��� ������� ��������?*/
    public static int sum2d(String[][] arr) { // NullPointerException ���� �������� �� ����������� ������
        int sum = 0;
        for (int i = 0; i < arr.length; i++) {
            for (int j = 0; j < 5; j++) { // IndexOutOfBoundsException ���� ������ ������
                int val = Integer.parseInt(arr[i][j]); // NumberFormatException ���� �� ��������� ��������
                sum += val;
            }
        }
        return sum;
    }

    public static int[] getDifference(int[] array1, int[] array2) {
        if (array1.length != array2.length) {
            System.out.println("������: ����� �������� �� �����!");
            return null;
        }
        int[] resultArray = new int[array1.length];
        for (int i = 0; i < array1.length; i++) {
            resultArray[i] = array1[i] - array2[i];
        }

        return resultArray;
    }

    public static double[] getQuotient(int[] array1, int[] array2) {
        if (array1.length != array2.length) {
            throw new RuntimeException("����� �������� �� �����!");
        }
        double[] resultArray = new double[array1.length];
        for (int i = 0; i < array1.length; i++) {
                resultArray[i] = array1[i] / (double)array2[i];
        }

        return resultArray;
    }
}
