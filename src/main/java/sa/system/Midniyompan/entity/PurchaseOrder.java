package sa.system.Midniyompan.entity;

import jakarta.persistence.*;
import sa.system.Midniyompan.common.Status;
import lombok.Data;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
@Data
@Entity
public class PurchaseOrder {
    @Id
    @GeneratedValue
    private UUID id;


    private LocalDateTime timestamp;
    private Status status;
    @OneToMany(mappedBy = "purchaseOrder")
    private List<OrderItem> items = new ArrayList<>();
    @ManyToOne
    private Customer customer;


    public double getTotal() {
        double total = 0;
        for (OrderItem item : items)
            total += item.getSubtotal();
        return total;
    }
    public double getTax(){
        double total = 0;
        for (OrderItem item : items)
            total += item.getSubtotalTax();
        return total;
    }
    public double getSubtotalRealPrice(){
        double total = 0;
        for (OrderItem item : items)
            total += item.getSubtotalRealPrice();
        return total;
    }


}
