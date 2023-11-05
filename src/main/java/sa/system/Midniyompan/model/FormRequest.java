package sa.system.Midniyompan.model;

import lombok.Data;

import java.util.UUID;

@Data
public class FormRequest {
    private UUID purchaseOrderId;
    private String managerName;
}
