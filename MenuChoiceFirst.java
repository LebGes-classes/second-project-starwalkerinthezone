import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class MenuChoiceFirst {
    private static final Scanner scanner = new Scanner(System.in);
    //первый выбор в меню
    static void menuChoice1(){
        while(true) {
            System.out.println("1 - открытие нового склада");
            System.out.println("2 - закрытие склада");
            System.out.println("3 - информация о складе");
            System.out.println("4 - информация о товарах на складе");
            System.out.println("5 - перемещение товара по складу");
            System.out.println("6 - перемещение товара на пункт продажи");
            System.out.println("7 - назад");
            System.out.println("8 - доходность склада");
            System.out.println(" 9 - Закупка товара");
            System.out.println("10 - добавить ячейку склада");



            try{
                int choice;
                while(true) {
                    choice = scanner.nextInt();
                    scanner.nextLine();
                    if (choice < 1 || choice > 10) {
                        System.out.println("неверная команда");
                        continue;
                    }
                    break;
                }
                //выборы в подменю
                //открыть склад
                if (choice == 1){
                    System.out.println("Введите адрес склада:");
                    String address = scanner.nextLine().trim();
                    StorageManager.addStorage(new Warehouse(address));
                }
                //закрыть склад
                if(choice == 2){
                    System.out.println("Выберите какой склад закрыть:");
                    List<Warehouse> warehouseList = Counters.warehouseCounter();
                    while(true){
                        try{
                            int choiceToClose = scanner.nextInt();
                            scanner.nextLine();
                            if (choiceToClose < 1 || choiceToClose > warehouseList.size()) {
                                System.out.println("неверная команда");
                                continue;
                            }
                            StorageManager.closeStorage(warehouseList.get(choiceToClose-1).getId());
                            break;
                        } catch (RuntimeException e) {
                            System.out.println("неверная команда");
                        }
                    }
                }
                //инфо о складе
                if (choice == 3){
                    List<Warehouse> warehouseList = Counters.warehouseCounter();
                    while(true){
                        try{
                            int choiceToClose = scanner.nextInt();
                            scanner.nextLine();
                            if (choiceToClose == 0){
                                return;
                            }
                            else if (choiceToClose < 1 || choiceToClose > warehouseList.size()) {
                                System.out.println("неверная команда");
                                continue;
                            }

                            StorageManager.getStorage(warehouseList.get(choiceToClose-1).getId()).about();
                            break;
                        } catch (RuntimeException e) {
                            System.out.println("неверная команда");
                        }
                    }
                }
                //инфо о товарах на складе
                if (choice == 4){
                    List<Warehouse> warehouseList = Counters.warehouseCounter();
                    while(true){
                        try{
                            int choiceToClose = scanner.nextInt();
                            scanner.nextLine();
                            if (choiceToClose < 1 || choiceToClose > warehouseList.size()) {
                                System.out.println("неверная команда");
                                continue;
                            }
                            StorageManager.getStorage(warehouseList.get(choiceToClose-1).getId()).aboutProducts();
                            break;
                        } catch (RuntimeException e) {
                            System.out.println("неверная команда");
                        }
                    }
                }
                //перемещение товара по складу
                if (choice == 5){
                    System.out.println("Выберите товар и ячейку");
                    List<Warehouse> warehouseList = Counters.warehouseCounter();
                    while(true){
                        try{
                            int choiceToClose = scanner.nextInt();
                            scanner.nextLine();
                            if (choiceToClose < 1 || choiceToClose > warehouseList.size()) {
                                System.out.println("неверная команда");
                                continue;
                            }
                            Warehouse warehouse = (Warehouse) StorageManager.getStorage(warehouseList.get(choiceToClose-1).getId());
                            List<Product> products = new ArrayList<>();
                            int counter = 0;
                            for(Product product : warehouse.getIdToProduct().values()){
                                System.out.println(++counter + " - " + product.getName());
                                products.add(product);
                            }
                            int choiceProd;
                            while(true) {
                                choiceProd = scanner.nextInt();
                                scanner.nextLine();
                                if (choiceProd < 1 || choiceProd > counter){
                                    continue;
                                }
                                break;
                            }
                            int counter1 = 0;
                            List<Warehouse.WarehouseCell> warehouseCells = new ArrayList<>();
                            for(Warehouse.WarehouseCell warehouseCell : warehouse.getIdToWarehouseCell().values()){
                                System.out.println(++counter1 + " - " + warehouseCell.getId());
                                warehouseCells.add(warehouseCell);
                            }
                            int choiceCell;
                            while(true) {
                                choiceCell = scanner.nextInt();
                                scanner.nextLine();
                                if (choiceCell < 1 || choiceCell > counter1){
                                    continue;
                                }
                                break;
                            }
                            warehouse.moveProductInWarehouse(products.get(choiceProd-1).getId(), warehouseCells.get(choiceCell-1).getId());

                            break;
                        } catch (RuntimeException e) {
                            System.out.println("неверная команда");
                        }
                    }
                }
                //перемещение товара на пункт продаж
                if (choice == 6){
                    System.out.println("Выберите склад, товар и пункт продаж");
                    List<Warehouse> warehouseList = Counters.warehouseCounter();
                    int choiceToClose = ChoiceFrom.choiceFromList(warehouseList);
                    Warehouse warehouse = (Warehouse) StorageManager.getStorage(warehouseList.get(choiceToClose-1).getId());
                    List<Product> products = new ArrayList<>();
                    int counter = 0;
                    for(Product product : warehouse.getIdToProduct().values()){
                        System.out.println(++counter + " - " + product.getName());
                        products.add(product);
                    }
                    int choiceProd;
                    while(true) {
                        choiceProd = scanner.nextInt();
                        scanner.nextLine();
                        if (choiceProd < 1 || choiceProd > counter){
                            continue;
                        }
                        break;
                    }
                    int choicePos;
                    List<PointOfSale> poses = Counters.posCounter();
                    while(true){
                        choicePos = scanner.nextInt();
                        scanner.nextLine();
                        if (choicePos < 1 || choicePos>poses.size()){
                            continue;
                        }
                        break;
                    }
                    Warehouse neededWarehouse = (Warehouse) StorageManager.getIdToStorage().get(warehouse.getId());
                    neededWarehouse.moveProductToPointSale(products.get(choiceProd-1).getId(), poses.get(choicePos-1).getId());
                }
                //выйти
                if(choice == 7){
                    return;
                }
                //узнать доходность
                if(choice==8){
                    List<Warehouse> warehouseList = Counters.warehouseCounter();
                    Warehouse warehouse = warehouseList.get(ChoiceFrom.choiceFromList(warehouseList)-1);
                    System.out.println(warehouse.getIncome());
                }
                //закупка продукта
                if(choice == 9){
                    System.out.println("Введите имя продукта");
                    String productName = scanner.nextLine().trim();
                    System.out.println("Введите цену продукта");
                    int productPrice = scanner.nextInt();
                    scanner.nextLine();
                    System.out.println("Введите кол-во продукта");
                    int productAmount = scanner.nextInt();
                    Product newProduct = Product.newProduct(productName, productPrice, productAmount);
                    List<Warehouse> warehouseList = Counters.warehouseCounter();
                    System.out.println("В какой склад?");
                    Warehouse warehouse = warehouseList.get(ChoiceFrom.choiceFromList(warehouseList)-1);
                    List<Warehouse.WarehouseCell> warehouseCells = new ArrayList<>();
                    int counterCell = 0;
                    System.out.println("Выберите ячейку");
                    for (Warehouse.WarehouseCell Cell : warehouse.getIdToWarehouseCell().values()) {
                        System.out.println(++counterCell + " - " + Cell.getId());
                        warehouseCells.add(Cell);

                    }
                    int choiceCell;
                    while (true){
                        choiceCell = scanner.nextInt();
                        scanner.nextLine();
                        if (choiceCell < 1 || choiceCell > warehouseCells.size()){
                            System.out.println("выберите из данного");
                            continue;
                        }
                        break;
                    }
                    warehouse.purchaseProduct(newProduct, warehouseCells.get(choiceCell-1).getId());
                }
                //создать ячейку склада
                if (choice == 10){
                    System.out.println("в каком складе?");
                    List<Warehouse> warehouseList = Counters.warehouseCounter();
                    Warehouse warehouse = (Warehouse) StorageManager.getStorage(warehouseList.get(ChoiceFrom.choiceFromList(warehouseList)-1).getId());
                    System.out.println("Введите наименование ячейки");
                    String name = scanner.nextLine().trim();
                    warehouse.addWarehouseCell(name);
                }
            } catch (RuntimeException e) {
                System.out.println("неверная команда");
            }
        }
    }
}
