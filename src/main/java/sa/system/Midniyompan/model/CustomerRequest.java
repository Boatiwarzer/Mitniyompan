package sa.system.Midniyompan.model;

import jakarta.persistence.Entity;
import lombok.Data;

@Data
public class CustomerRequest {
    private String name;
    private String address;
    private String phone;
    private String tax_id;

}
