package Lesson3;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.Scanner;

public class Main {
    public static String line = "";
    public static String fio = "";
    public static LocalDate date = LocalDate.parse("1900-01-01");
    public static double phone = 0;
    public static String sex = "";
    public static int countOfRightData = 0;

    public static void main(String[] args) {

        /*
         * Напишите приложение, которое будет запрашивать у пользователя следующие данные
         * в произвольном порядке, разделенные пробелом:Фамилия Имя Отчество, дата рождения,
         * номер телефона, пол
         * Форматы данных: фамилия, имя, отчество - строки
         * дата рождения - строка формата dd.mm.yyyy
         * номер телефона - целое беззнаковое число без форматирования.
         * Например 71112223344
         * пол - символ латиницей f или m.
         * Приложение должно проверить введенные данные по количеству.
         * Если количество не совпадает с требуемым, вернуть код ошибки,
         * обработать его и показать пользователю сообщение,
         * что он ввел меньше и больше данных, чем требуется.
         * Приложение должно попытаться распарсить полученные значения
         * и выделить из них требуемые параметры. Если форматы данных
         * не совпадают, нужно бросить исключение, соответствующее типу
         * проблемы. Можно использовать встроенные типы java или создать свои.
         * Исключение должно быть корректно обработано, пользователю выведено сообщение
         * с информацией, что именно неверно.
         * Если всё введено и обработано верно, должен создаться файл с названием,
         * равным фамилии, в него в одну строку должны записаться полученные данные,
         * вида <Фамилия><Имя><Отчество><дата рождения><номер телефона><пол>
         * Однофамильцы должны записаться в один и тот же файл, в отдельные строки.
         * Не забудьте закрыть соединение с файлом.
         * При возникновении проблемы с чтением-записью в файл, исключение должно быть корректно обработано,
         * пользователь должен увидеть стектрейс ошибки.
         *
         * Примеры строк для теста:
         * Фамилия Имя Отчество, 25.04.2000, 74951234567, f
         * 25.04.2000, Фамилия Имя Отчество, 74951234567, f
         * 74951234567, Фамилия Имя Отчество, 25.04.2000, f
         * f, Фамилия Имя Отчество, 25.04.2000, 74951234567
         * f, Имя Фамилия Отчество, 25.04.2000, 74951234567
         *
         */

        getLine();
        String[] linesArray = parseString();
        printLines(linesArray);
        parseArray(linesArray);
        testData();
        printData();
        if(countOfRightData == 4) writeData();
    }

    public static void printData() {
        System.out.println("Обработанные данные: ");
        System.out.println("ФИО: " + fio);
        System.out.println("Дата: " + date);
        System.out.println(String.format("Телефон: %.0f", phone));
        System.out.println("Пол: " + sex);
    }

    public static void printLines(String[] array) {
        System.out.print("Вы ввели: ");
        for (String s : array) {
            System.out.print("[" + s + "] ");
        }
        System.out.print("\n");
    }

    public static void getLine() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Введите строку с данными разделенными запятой и пробелом.");
        System.out.println("Формат: Фамилия Имя Отчество, дата рождения, номер телефона, пол");
        line = scanner.nextLine();
    }

    public static void parseArray(String[] array) {
        for (int i = 0; i < array.length; i++) {
            if (isName(array[i])) fio = array[i];
            if (isDate(array[i])) date = LocalDate.parse(array[i], DateTimeFormatter.ofPattern("dd.MM.yyyy"));
            if (isPhone(array[i])) phone = Double.parseDouble(array[i]);
            if (isSex(array[i])) sex = array[i];
        }
    }

    public static void testData() {
        if (fio.equals("")) System.err.println("Не удалось распарсить ФИО!");
        else countOfRightData++;
        if (date.isEqual(LocalDate.parse("1900-01-01"))) System.err.println("Не удалось распарсить дату!");
        else countOfRightData++;
        if (phone == 0) System.err.println("Не удалось распарсить телефон!");
        else countOfRightData++;
        if (sex.equals("")) System.err.println("Не удалось распарсить пол!");
        else countOfRightData++;
        if (countOfRightData == 4) System.out.println("Все данные успешно распарсены!");
    }

    public static boolean isName(String s) {
        String[] tempArray = s.split(" ");
        return (tempArray.length == 3);
    }

    public static boolean isDate(String s) {
        try {
            date = LocalDate.parse(s, DateTimeFormatter.ofPattern("dd.MM.yyyy"));
        } catch (DateTimeParseException e) {
        }
        return (date == LocalDate.parse("1900-01-01"));
    }

    public static boolean isPhone(String s) {
        if (s.contains(".")) return false;
        try {
            phone = Double.parseDouble(s);
            return true;
        } catch (NumberFormatException e) {
        }
        return false;

    }

    public static boolean isSex(String s) {
        return (s.toLowerCase().equals("f") || s.toLowerCase().equals("m"));
    }

    public static String[] parseString() {
        String[] lines = line.split(", ");
        if (lines.length != 4) throw new RuntimeException("Ошибка! Введено неверное количество данных.");
        return lines;
    }

    public static void writeData() {
        String fileName = fio.split(" ")[0] + ".txt";
        File f = new File(fileName);
        if (f.exists() && !f.isDirectory()) {
            System.out.println("Файл уже существует и будет дозаписан.");
        }
        try (FileWriter writer = new FileWriter(fileName, true)) {
            String text = "<" + fio + ">" + "<" + date + ">" + "<" +
                    String.format("%.0f", phone) + ">" + "<" + sex + ">";
            System.out.println("В файл " + fileName + " будет записаны: " + text);
            writer.append(text);
            writer.append('\n');
            writer.flush();
            System.out.println("Данные успешно записаны!");
        } catch (IOException ex) {
            ex.printStackTrace();
        }

    }
}
