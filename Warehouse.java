import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

public class Warehouse implements Storage {
    private final String id;
    private final Map<WarehouseCell, Product> cellToProduct;
    private final Map<String, WarehouseCell> idToWarehouseCell;
    private final Map<String, Product> idToProduct;
    private final Map<Product, WarehouseCell> productToCell;
    private final Map<String, List<String>> positionToEmployee;
    private String address;
    private int income;

    public Warehouse(String address){
        cellToProduct = new HashMap<>();
        idToWarehouseCell = new HashMap<>();
        idToProduct = new HashMap<>();
        productToCell = new HashMap<>();
        id = UUID.randomUUID().toString();
        positionToEmployee = new HashMap<>();
        this.address = address;
    }

    //сделать добавление ячеек склада
    public void addWarehouseCell(String id){
        if(!idToWarehouseCell.containsKey(id)){
            idToWarehouseCell.put(id, new WarehouseCell(id));
            return;
        }
        System.out.println("Данная ячейка уже есть на складе");

    }

    public String getId(){
        return id;
    }

    public Map<String, List<String>> getPositionToEmployee() {
        return positionToEmployee;
    }

    @Override
    public int getIncome() {
        return income;
    }

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

    public Map<String, Product> getIdToProduct() {
        return idToProduct;
    }

    public Map<String, WarehouseCell> getIdToWarehouseCell() {
        return idToWarehouseCell;
    }

    public void purchaseProduct(Product product, String id){
        if (product==null){
            System.out.println("нет продукта");
            return;
        }
        if(idToWarehouseCell.containsKey(id)) {
            WarehouseCell cell = idToWarehouseCell.get(id);
            if (cellToProduct.containsKey(cell)){
                if (cellToProduct.get(cell) != null) {
                    System.out.println("Данная ячейка занята другим товаром");
                    return;
                }
            }
            
            idToProduct.put(product.getId(), product);
            cellToProduct.put(cell, product);
            productToCell.put(product, cell);
            return;
        }
        System.out.println("Данной ячейки не существует");
    }

    public void moveProductInWarehouse(String product_id, String cell_id) {
        if(!idToProduct.containsKey(product_id)) {
            System.out.println("Нет такого товара");
            return;
        }

        if(!idToWarehouseCell.containsKey(cell_id)){
            System.out.println("Нет ячейки перемещения");
            return;
        }
        Product product = idToProduct.get(product_id);
        WarehouseCell fromCell = productToCell.get(product);
        if(fromCell == null){
            System.out.println("товара нет ни в одной ячейке");
            return;
        }
        WarehouseCell toCell = idToWarehouseCell.get(cell_id);
        if(fromCell.equals(toCell)){
            System.out.println("Товар уже в этой ячейке");
            return;
        }
        if(cellToProduct.get(toCell) != null){
            System.out.println("Другой товар в данной ячейке");
            return;
        }
        cellToProduct.put(fromCell, null);
        cellToProduct.put(toCell, product);
        productToCell.put(product, toCell);
    }

    public void moveProductToPointSale(String productId, String pointId) {
        // Валидация
        if(productId == null || pointId == null) {
            System.out.println("ID не может быть null");
            return;
        }

        // Проверка товара
        Product product = idToProduct.get(productId);
        if(product == null) {
            System.out.println("Товар не найден");
            return;
        }

        // Проверка ячейки
        WarehouseCell cell = productToCell.get(product);
        if(cell == null) {
            System.out.println("Товар не размещен на складе");
            return;
        }

        // Проверка точки продаж
        PointOfSale point = (PointOfSale) StorageManager.getIdToStorage().get(pointId);
        if(point == null) {
            System.out.println("Товар не размещен на складе");
            return;
        }

        // Перемещение
        cellToProduct.put(cell, null);
        productToCell.remove(product);
        point.addProduct(product, this.id, cell.getId());
    }

    @Override
    public void aboutProducts() {
        System.out.println("адрес: " + address);
        int counter = 0;
        for (Product product : idToProduct.values()){
            System.out.println(++counter + product.getId());
        }
    }

    @Override
    public void about() {
        for (Product product : idToProduct.values()){
            System.out.println("id: " + product.getId() + "; name: " + product.getName() + "; amount: " + product.getAmount() + "; price: " + product.getPrice());
        }
    }


    public class WarehouseCell{
        private final String id;

        public WarehouseCell(String id){
            this.id = id;
        }

        public String getId(){
            return this.id;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;
            WarehouseCell that = (WarehouseCell) o;
            return id.equals(that.id);
        }

        @Override
        public int hashCode() {
            return id.hashCode();
        }
    }
}
