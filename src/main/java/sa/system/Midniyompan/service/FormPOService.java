package sa.system.Midniyompan.service;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import sa.system.Midniyompan.common.Status;
import sa.system.Midniyompan.entity.Customer;
import sa.system.Midniyompan.entity.FormPO;
import sa.system.Midniyompan.entity.PurchaseOrder;
import sa.system.Midniyompan.model.FormRequest;
import sa.system.Midniyompan.repository.CustomerRepository;
import sa.system.Midniyompan.repository.FormPORepository;
import sa.system.Midniyompan.repository.PurchaseOrderRepository;

import java.time.LocalDateTime;
import java.util.List;
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
        PurchaseOrder purchaseOrder = purchaseOrderRepository.findById(request.getPurchaseOrderId()).get();
        Customer customer = customerRepository.findById(purchaseOrder.getCustomer().getId()).get();
        record.setPurchaseOrder(purchaseOrder);
        record.setTimestamp(LocalDateTime.now());
        record.setCustomer(customer);
        if (purchaseOrder.getStatus() != Status.RESERVE){
            record.setStatus(Status.InProcess);

        }else {
            record.setStatus(Status.RESERVE);

        }
        formPORepository.save(record);
    }
    public FormPO getOneById(UUID id) {
        return formPORepository.findById(id).get();
    }
    public List<FormPO> getAll() {
        return formPORepository.findAll();
    }
    public void finishOrder(UUID orderId) {
        FormPO record = formPORepository.findById(orderId).get();
        record.setStatus(Status.FINISH);
        formPORepository.save(record);
    }

}
