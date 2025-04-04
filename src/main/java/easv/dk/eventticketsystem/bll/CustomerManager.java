package easv.dk.eventticketsystem.bll;


import easv.dk.eventticketsystem.be.Customer;
import easv.dk.eventticketsystem.dal.db.CustomerDAODB;

public class CustomerManager {

    private final CustomerDAODB customerDAO = new CustomerDAODB();

    public int getOrCreateCustomerId(String name, String email) throws Exception {
        Customer existing = customerDAO.getCustomerByEmail(email);
        if (existing != null) {
            return existing.getCustomerId();
        }

        Customer newCustomer = new Customer(0, name, email, null);
        return customerDAO.createCustomer(newCustomer);
    }
}
