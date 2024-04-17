import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Введите данные в формате: Фамилия Имя Отчество дата_рождения номер_телефона пол");

        String input = scanner.nextLine();

        String[] data = input.split(" ");
        if (data.length != 6) {
            System.out.println("Ошибка: Неверное количество данных");
            return;
        }

        String surname = data[0];
        String name = data[1];
        String patronymic = data[2];
        String birthDate = data[3];
        String phoneNumber = data[4];
        String gender = data[5];

        if (!isValidDate(birthDate)) {
            System.out.println("Ошибка: Неверный формат даты рождения (ожидается dd.mm.yyyy)");
            return;
        }

        try {
            long number = Long.parseLong(phoneNumber);
        } catch (NumberFormatException e) {
            System.out.println("Ошибка: Неверный формат номера телефона (ожидается целое число)");
            return;
        }

        if (!gender.equals("f") && !gender.equals("m")) {
            System.out.println("Ошибка: Неверный формат пола (ожидается 'f' или 'm')");
            return;
        }

        String fileName = surname + ".txt";
        try (FileWriter writer = new FileWriter(fileName)) {
            String output = surname + " " + name + " " + patronymic + " " + birthDate + " " + phoneNumber + " " + gender;
            writer.write(output);
            System.out.println("Данные успешно записаны в файл: " + fileName);
        } catch (IOException e) {
            System.out.println("Ошибка при записи в файл: " + e.getMessage());
        }
    }

    private static boolean isValidDate(String date) {
        String regex = "\\d{2}\\.\\d{2}\\.\\d{4}";
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(date);
        return matcher.matches();
    }
}