package Storages;

import java.util.List;
import java.util.Map;

public interface Storage {
    String getId();
    Map<String, List<String>> getPositionToEmployee();
    int getIncome();
    void setAddress(String address);
    String getAddress();
    void setIncome(int income);
    void aboutProducts();
    void about();
}
