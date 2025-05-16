package Serialization;

import Customers.Customer;
import Managers.CustomerManager;
import Managers.EmployeeManager;
import Managers.StorageManager;
import Storages.Storage;
import com.fasterxml.jackson.databind.ObjectMapper;
import Employee.Employee;

import java.io.File;
import java.io.IOException;
import java.util.Map;

public class SerializerJson {

    public static void serialize(Map<String, Employee> idToEmployee, Map<String, Storage> idToStorage, Map<String, Customer> idToCustomer) throws IOException {
        ObjectMapper mapper = new ObjectMapper();
        File Employees = new File("Employees.json");
        mapper.writerWithDefaultPrettyPrinter().writeValue(Employees, idToEmployee);
        File Customers = new File("Customers.json");
        File Storages = new File("Storages.json");
        mapper.writerWithDefaultPrettyPrinter().writeValue(Customers, idToCustomer);
        mapper.writerWithDefaultPrettyPrinter().writeValue(Storages, idToStorage);
        EmployeeManager.getIdToEmployee().clear();
        CustomerManager.getIdToCustomer().clear();
        StorageManager.getIdToStorage().clear();
    }
}
