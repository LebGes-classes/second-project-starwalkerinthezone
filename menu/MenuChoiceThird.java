package menu;

import Serialization.DeserializerJson;

import java.io.IOException;
import java.util.Scanner;

public class MenuChoiceThird {
    private final Scanner scanner = new Scanner(System.in);
    //выбор в меню 3
     public void menuChoice3() {
        while (true) {
            try{
                System.out.println("Введите путь к работникам.json");
                String employee = scanner.nextLine().trim();

                System.out.println("Введите путь к покупателям");
                String customer = scanner.nextLine().trim();
                System.out.println("Введите путь к хранилищам");
                String storage = scanner.nextLine().trim();
                DeserializerJson.deserialize(employee, customer, storage);
                break;
            } catch (IOException e) {
                System.out.println("неверно введен путь");
            }
        }
    }
}
