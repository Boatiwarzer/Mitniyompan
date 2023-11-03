package sa.system.Midniyompan.model;

import lombok.Data;

import java.util.UUID;

@Data
public class FromRequest {
    private UUID purchaseOrderId;
    private UUID customerId;
//    private String nameCustomer;
//    private String phone;
//    private String address;
//    private String tax_id;
    private UUID accountId;
}
