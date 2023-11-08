package sa.system.Midniyompan.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import sa.system.Midniyompan.entity.Product;
import sa.system.Midniyompan.entity.Receipt;
import sa.system.Midniyompan.model.ProductRequest;
import sa.system.Midniyompan.model.ReceiptRequest;
import sa.system.Midniyompan.service.FormPOService;
import sa.system.Midniyompan.service.ReceiptService;

import java.util.List;
import java.util.UUID;

@Controller
@RequestMapping("receipt")
public class ReceiptController {
    @Autowired
    private FormPOService formPOService;
    @Autowired
    private ReceiptService receiptService;
//    @GetMapping
//    public String getAll(Model model){
//
//    }
    @GetMapping
    public String getAll(Model model){
        model.addAttribute("receipts",receiptService.listAll());
        return "receipt-all";
    }

    @GetMapping("/{id}")
    public String getReceiptForm( @PathVariable UUID id,Model model) {
        model.addAttribute("receipt", receiptService.getOneById(id) );
        return "receipt-view";
    }
    @PostMapping
    public String createReceipt(@ModelAttribute ReceiptRequest formPOId) {
        receiptService.createReceipt(formPOId);
        return "redirect:/receipt";
    }


}
