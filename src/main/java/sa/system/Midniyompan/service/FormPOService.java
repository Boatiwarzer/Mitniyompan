package sa.system.Midniyompan.service;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import sa.system.Midniyompan.entity.Category;
import sa.system.Midniyompan.entity.Customer;
import sa.system.Midniyompan.entity.FormPO;
import sa.system.Midniyompan.entity.PurchaseOrder;
import sa.system.Midniyompan.model.FromRequest;
import sa.system.Midniyompan.repository.CustomerRepository;
import sa.system.Midniyompan.repository.FormPORepository;
import sa.system.Midniyompan.repository.PurchaseOrderRepository;

import java.io.IOException;
import java.util.Base64;

@Service
public class FormPOService {
    @Autowired
    private FormPORepository formPORepository;
    @Autowired
    private CustomerRepository customerRepository;
    @Autowired
    private PurchaseOrderRepository purchaseOrderRepository;
    @Autowired
    private ModelMapper modelMapper;
    public void createForm(FromRequest request) {
        FormPO record = modelMapper.map(request, FormPO.class);
        PurchaseOrder purchaseOrder =
                purchaseOrderRepository.findById(request.getPurchaseOrderId()).get();
        record.setPurchaseOrder(purchaseOrder);
        Customer customer = customerRepository.findById(request.getCustomerId()).get();
        record.setCustomer(customer);
        formPORepository.save(record);
    }
}
