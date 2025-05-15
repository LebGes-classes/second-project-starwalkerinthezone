import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.Map;

public class SerializerJson {

    public static void serialize(Map<String, Employee> idToEmployee, Map<String, Storage> idToStorage, Map<String, Customer> idToCustomer) throws IOException {
        ObjectMapper mapper = new ObjectMapper();
        File Employees = new File("Employees.json");
        mapper.writeValue(Employees, idToEmployee);
        File Customers = new File("Customers.json");
        File Storages = new File("Storages.json");
        mapper.writeValue(Customers, idToCustomer);
        mapper.writeValue(Storages, idToStorage);
        EmployeeManager.getIdToEmployee().clear();
        CustomerManager.getIdToCustomer().clear();
        StorageManager.getIdToStorage().clear();
    }
}
