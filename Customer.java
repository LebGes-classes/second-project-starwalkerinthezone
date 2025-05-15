import java.util.*;

public class Customer {
    private HashMap<String, List<HashMap<String, Integer>>> PosToProdBought;
    private final String id = UUID.randomUUID().toString();
    private final String name;

    public Customer(String name) {
        this.name = name;
    }

    //покупка товара покупателем
    public void buy(String posId, String idToProduct){
        PointOfSale pos = (PointOfSale) StorageManager.getIdToStorage().get(posId);
        int amount = pos.sellProduct(idToProduct);
        if(PosToProdBought.containsKey(posId)){
            boolean isPairExist = false;
            for(HashMap<String, Integer> hashMap : PosToProdBought.get(posId)){
                if (hashMap.containsKey(idToProduct)){
                    hashMap.put(idToProduct, hashMap.get(idToProduct)+amount);
                    isPairExist = true;
                }
            }
            if(!isPairExist){
                HashMap<String, Integer> prodBought = new HashMap<>();
                prodBought.put(idToProduct, amount);
                PosToProdBought.get(posId).add(prodBought);
            }
        }
        else{
            HashMap<String, Integer> prodBought = new HashMap<>();
            prodBought.put(idToProduct, amount);
            List<HashMap<String, Integer>> prods = new ArrayList<>();
            prods.add(prodBought);
            PosToProdBought.put(posId, prods);
        }
    }


    public HashMap<String, List<HashMap<String, Integer>>> getPosToProdBought() {
        return PosToProdBought;
    }

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public void returnProduct(String posId, String idToProduct, int amount){
        PointOfSale pos = (PointOfSale) StorageManager.getIdToStorage().get(posId);
        for (HashMap<String, Integer> prodToAmount : PosToProdBought.get(posId)){
            if (!prodToAmount.containsKey(idToProduct)){
                continue;
            }
            if (prodToAmount.get(idToProduct)-amount < 0){
                System.out.println("максимум можете вернуть: " + prodToAmount.get(idToProduct));
                return;
            }
            if (prodToAmount.get(idToProduct)-amount==0){
                prodToAmount.remove(idToProduct);
                pos.returnProduct(idToProduct, amount);
                return;
            }
            prodToAmount.put(idToProduct, prodToAmount.get(idToProduct)-amount);
            pos.returnProduct(idToProduct, amount);

        }



    }


    }