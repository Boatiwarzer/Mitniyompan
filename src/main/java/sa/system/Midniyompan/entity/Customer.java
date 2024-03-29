package sa.system.Midniyompan.entity;
import jakarta.persistence.*;
import lombok.Data;


import java.util.List;
import java.util.UUID;
@Data
@Entity
public class Customer {
    @Id
    @GeneratedValue
    private UUID id;
    private String name;
    private String address;
    private String phone;
    @OneToMany(mappedBy = "customer")
    private List<FormPO> formPO;
    @OneToMany(mappedBy = "customer")
    private List<PurchaseOrder> purchaseOrders;


}
