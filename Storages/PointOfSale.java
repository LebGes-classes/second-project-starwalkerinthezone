package Storages;

import Managers.StorageManager;
import Products.Product;

import java.util.*;

public class PointOfSale implements Storage {
    private final Map<String, Product> idToProduct;
    private final Map<Product, String> productToWarehouse;
    private final Map<Product, String> productToWarehouseCell;
    private final String id;
    private String address;
    private int income;
    private final Scanner scanner = new Scanner(System.in);
    private final Map<String, List<String>> positionToEmployee;


    public PointOfSale(String address){
        this.id = UUID.randomUUID().toString();
        this.idToProduct = new HashMap<>();
        this.productToWarehouse = new HashMap<>();
        this.productToWarehouseCell = new HashMap<>();
        this.positionToEmployee = new HashMap<>();
        this.address = address;
    }
    public Map<String, Product> getIdToProduct() {
        return idToProduct;
    }

    //добавить товар
    public void addProduct(Product product, String WarehouseId, String WarehouseCellId){
        idToProduct.put(product.getId(), product);
        productToWarehouse.put(product, WarehouseId);
        productToWarehouseCell.put(product, WarehouseCellId);
    }
    //продать товар
    public int sellProduct(String id){
        Product fromProduct = idToProduct.get(id);
        System.out.println("введите количество(доступно " + ")") ;
        int amount;
        while(true) {
            amount = scanner.nextInt();
            if (amount > fromProduct.getAmount()) {
                System.out.println("ввели больше чем можно");
                continue;
            }
            break;
        }
        fromProduct.setAmount(fromProduct.getAmount()-amount);
        this.setIncome(income+(amount*fromProduct.getPrice()));
        Warehouse warehouse = (Warehouse) StorageManager.getStorage(productToWarehouse.get(idToProduct.get(id)));
        warehouse.setIncome(warehouse.getIncome()+(amount*fromProduct.getPrice()));
        return amount;


    }
    //вернуть товар
    public void returnProduct(String id, int amount){
        Product neededProduct = idToProduct.get(id);
        neededProduct.setAmount(neededProduct.getAmount()+amount);
    }
    //взять id
    public String getId(){
        return id;
    }
    //взять
    public int getIncome() {
        return income;
    }

    //поставить адрес
    @Override
    public void setAddress(String address) {
        this.address = address;
    }

    @Override
    public String getAddress() {
        return address;
    }

    @Override
    public void setIncome(int income) {
        this.income = income;
    }

    public Map<String, List<String>> getPositionToEmployee() {
        return positionToEmployee;
    }

    public void about(){
        System.out.println("адрес: " + address);
        int counter = 0;
        for (Product product : idToProduct.values()){
            System.out.println(++counter + product.getId());
        }
    }

    public void aboutProducts(){
        for (Product product : idToProduct.values()){
            System.out.println("id: " + product.getId() + "; name: " + product.getName() + "; amount: " + product.getAmount() + "; price: " + product.getPrice());
        }
    }


}
