package sa.system.Midniyompan.service;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import sa.system.Midniyompan.common.Status;
import sa.system.Midniyompan.entity.*;
import sa.system.Midniyompan.model.ProductRequest;
import sa.system.Midniyompan.model.ReceiptRequest;
import sa.system.Midniyompan.repository.CategoryRepository;
import sa.system.Midniyompan.repository.FormPORepository;
import sa.system.Midniyompan.repository.ProductRepository;
import sa.system.Midniyompan.repository.ReceiptRepository;

import java.io.IOException;
import java.time.LocalDateTime;
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
    private UUID postId;

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
        formPO.setStatus(Status.Complete);
        record.setFormPO(formPO);
        record.setTimestamp(LocalDateTime.now());
        formPORepository.save(formPO);
        receiptRepository.save(record);
        postId = record.getId();
    }
    public Receipt getOrder() {

        return receiptRepository.findById(postId).get();
    }

}
