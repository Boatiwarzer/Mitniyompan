package sa.system.Midniyompan.service;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import sa.system.Midniyompan.entity.Customer;
import sa.system.Midniyompan.entity.FormPO;
import sa.system.Midniyompan.entity.PurchaseOrder;
import sa.system.Midniyompan.model.FormRequest;
import sa.system.Midniyompan.repository.CustomerRepository;
import sa.system.Midniyompan.repository.FormPORepository;
import sa.system.Midniyompan.repository.PurchaseOrderRepository;

import java.util.UUID;

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
    public void createForm(FormRequest request) {
        FormPO record = modelMapper.map(request, FormPO.class);
        PurchaseOrder purchaseOrder =
                purchaseOrderRepository.findById(request.getPurchaseOrderId()).get();
        record.setPurchaseOrder(purchaseOrder);
        formPORepository.save(record);
    }
    public FormPO getOneById(UUID id) {
        return formPORepository.findById(id).get();
    }

}
