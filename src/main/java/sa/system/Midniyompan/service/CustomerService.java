package sa.system.Midniyompan.service;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import sa.system.Midniyompan.entity.Category;
import sa.system.Midniyompan.entity.Customer;
import sa.system.Midniyompan.entity.Product;
import sa.system.Midniyompan.model.CustomerRequest;
import sa.system.Midniyompan.model.ProductRequest;
import sa.system.Midniyompan.repository.CustomerRepository;

import java.io.IOException;
import java.util.Base64;
import java.util.List;
import java.util.UUID;

@Service
public class CustomerService {
    @Autowired
    CustomerRepository customerRepository;
    @Autowired
    private ModelMapper modelMapper;
    public Customer getOneById(UUID id) {
        return customerRepository.findById(id).get();
    }

    public void createCustomer(CustomerRequest request) {
        Customer record = modelMapper.map(request, Customer.class);
        customerRepository.save(record);
    }


    public List<Customer> listAll() {
        return customerRepository.findAll();
    }
}
