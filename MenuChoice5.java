import java.io.IOException;
import java.util.Scanner;

public class MenuChoice5 {
    private static final Scanner scanner = new Scanner(System.in);
    //меню выбор 5
    //сериализация
    static void menuChoice5() {

        try {
            System.out.println("1 - сохранить");
            System.out.println("2 - выйти");
            while (true) {
                int choice = scanner.nextInt();
                scanner.nextLine();
                if (choice == 1) {
                    SerializerJson.serialize(EmployeeManager.getIdToEmployee(),
                            StorageManager.getIdToStorage(), CustomerManager.getIdToCustomer());
                    break;
                }
                if (choice == 2) {
                    break;
                }
            }
        }catch (IOException e){
            System.out.println("не удалось сохранить файлы");
        }
    }
}
