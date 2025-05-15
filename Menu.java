import java.util.Scanner;

public class Menu {
    //изначальное меню
    private static final Scanner scanner = new Scanner(System.in);
    static int menu(){
        System.out.println("Команды:");
        System.out.println("1 - работа со складами");
        System.out.println("2 - работа с пунктами продаж");
        System.out.println("3 - десериализация из json");
        System.out.println("4 - работа с сотрудниками");
        System.out.println("5 - сериализация в json");
        System.out.println("6 - закрыть программу");
        while(true) {
            //проверка на правилльность вводимого значения
            try{
                int choice = scanner.nextInt();
                scanner.nextLine();
                if (choice < 1 || choice > 6) {
                    System.out.println("неверная команда");
                    continue;
                }
                return choice;
            } catch (RuntimeException e) {
                System.out.println("неверная команда");
            }
        }
    }
}
