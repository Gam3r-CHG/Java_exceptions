package Lesson2;

import java.io.FileNotFoundException;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {

        /* Задание 1.
        Реализуйте метод, который запрашивает у пользователя ввод дробного числа (типа float),
        и возвращает введенное значение. Ввод текста вместо числа не должно приводить к падению
        приложения, вместо этого, необходимо повторно запросить у пользователя ввод данных.*/
        System.out.println(getFloat());

        /* Задание 2.
        Если необходимо, исправьте данный код
        Дан следующий код, исправьте его там, где требуется
        */

        int[] intArray = {1, 2, 3, 4, 5, 6, 7, 8, 9};
        try {
            int d = 0;
            double catchedRes1 = intArray[8] / d;
            System.out.println("catchedRes1 = " + catchedRes1);
        } catch (ArithmeticException e) {
            System.out.println("Catching exception: " + e);
        }

        /* Для 3 задания */
        try {
            main2(null);
        } catch (Exception e) {
            System.out.println(e);
        }

        /* Для 4 задания
        Разработайте программу, которая выбросит Exception,
        когда пользователь вводит пустую строку.
        Пользователю должно показаться сообщение,
        что пустые строки вводить нельзя */
        String temp = getLine();
    }

    /* Задание 3.
    Если необходимо, исправьте данный код
    Дан следующий код, исправьте его там, где требуется
    */
    public static void main2(String[] args) throws Exception {
        try {
            int a = 90;
            int b = 3;
            System.out.println(a / b);
            printSum(23, 234);
            int[] abc = {1, 2};
            abc[3] = 9;
        /*} catch (NullPointerException ex) {
            System.out.println("Указатель не может указывать на null!");*/    // Зачем это exception!?
        } catch (IndexOutOfBoundsException ex) {
            System.out.println("Массив выходит за пределы своего размера!");
        } catch (Throwable ex) {
            System.out.println("Что-то пошло не так...");
        }
    }

    public static void printSum(Integer a, Integer b) /*throws FileNotFoundException*/ {
        System.out.println(a + b);
    }


    // Метод для задания 1
    public static float getFloat() {
        Scanner scanner = new Scanner(System.in);
        do {
            System.out.println("Введите дробное число (float)");
            String s = scanner.nextLine();
            try {
                float number = Float.parseFloat(s);
                return number;
            } catch (Exception e) {
                System.err.println("Вы ввели неправильное число!");
            }
        } while (true);
    }

    // Метод для задания 4.
    // Разработайте программу, которая выбросит Exception,
    // когда пользователь вводит пустую строку.
    // Пользователю должно показаться сообщение,
    // что пустые строки вводить нельзя
    public static String getLine() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Введите текст:");
        String s = scanner.nextLine();
        if (s.trim().equals("")) throw new RuntimeException("Пустые строки вводить нельзя!");
        return s;
    }
}