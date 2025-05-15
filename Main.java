import java.util.Scanner;

public class Main {
    private static final Scanner scanner = new Scanner(System.in);
    public static void main(String[] args) {
        //цикл работы программы
        while (true){
            int choice = Menu.menu();
            if (choice == 1) {
                MenuChoiceFirst.menuChoice1();
            }
            else if (choice == 2){
                MenuChoiceSecond.menuChoice2();
            }
            else if (choice == 3){
                MenuChoiceThird.menuChoice3();
            }

            else if(choice == 4){
                MenuChoice4.menuChoice4();
            }

            else if(choice == 5){
                MenuChoice5.menuChoice5();
            }
            else if(choice == 6){
                System.exit(0);
            }
        }
    }


}
