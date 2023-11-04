package sa.system.Midniyompan.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import sa.system.Midniyompan.entity.Product;
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

    @GetMapping
    public String getReceiptForm(UUID formPOId, Model model) {
        model.addAttribute("PO", formPOService.getOneById(formPOId) );
        return "receipt";
    }
    @PostMapping
    public String createReceipt(@ModelAttribute ReceiptRequest request) {
        receiptService.createReceipt(request);
        return "redirect:/main";
    }
}
