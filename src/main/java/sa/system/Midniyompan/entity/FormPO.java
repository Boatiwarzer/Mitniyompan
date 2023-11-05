package sa.system.Midniyompan.entity;

import jakarta.persistence.*;
import lombok.Data;
import sa.system.Midniyompan.common.Status;

import java.time.LocalDateTime;
import java.util.UUID;

@Data
@Entity
public class FormPO {
    @Id
    @GeneratedValue
    private UUID id;
    private LocalDateTime timestamp;
    private Status status;
    private String managerName;
    @OneToOne
    PurchaseOrder purchaseOrder;
    @ManyToOne
    Customer customer;



}
