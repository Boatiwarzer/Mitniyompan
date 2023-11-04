package sa.system.Midniyompan.service;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import sa.system.Midniyompan.entity.Category;
import sa.system.Midniyompan.entity.FormPO;
import sa.system.Midniyompan.entity.Product;
import sa.system.Midniyompan.entity.Receipt;
import sa.system.Midniyompan.model.ProductRequest;
import sa.system.Midniyompan.model.ReceiptRequest;
import sa.system.Midniyompan.repository.CategoryRepository;
import sa.system.Midniyompan.repository.FormPORepository;
import sa.system.Midniyompan.repository.ProductRepository;
import sa.system.Midniyompan.repository.ReceiptRepository;

import java.io.IOException;
import java.util.Base64;
import java.util.List;
import java.util.UUID;

@Service
public class ReceiptService {
    @Autowired
    private FormPORepository formPORepository;
    @Autowired
    private ModelMapper modelMapper;
    @Autowired
    private ReceiptRepository receiptRepository;
    public Receipt getOneById(UUID id) {
        return receiptRepository.findById(id).get();
    }
    public List<Receipt> listAll(){
        return receiptRepository.findAll();
    }

    public void createReceipt(ReceiptRequest request) {
        Receipt record = modelMapper.map(request, Receipt.class);
        FormPO formPO =
                formPORepository.findById(request.getFormPOId()).get();
        record.setFormPO(formPO);
        receiptRepository.save(record);
    }

}
