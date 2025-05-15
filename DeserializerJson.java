import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.*;
import java.util.List;
import java.util.Map;

public class DeserializerJson {
    //десериализация
    public static void deserialize(String employeePath, String customerPath, String storagePath) throws IOException{
        ObjectMapper mapper = new ObjectMapper();
        Map<String, Employee> employeeMap = mapper.readValue(new File(employeePath), new TypeReference<>(){});
        Map<String, Customer> customerMap = mapper.readValue(new File(customerPath), new TypeReference<>(){});
        Map<String, Storage> storageMap = mapper.readValue(new File(storagePath), new TypeReference<>(){});
        EmployeeManager.setIdToEmployee(employeeMap);
        CustomerManager.setIdToCustomer(customerMap);
        StorageManager.setIdToStorage(storageMap);
    }

}
