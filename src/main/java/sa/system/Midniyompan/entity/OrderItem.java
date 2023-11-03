package sa.system.Midniyompan.entity;
import jakarta.persistence.*;
import lombok.Data;
import sa.system.Midniyompan.common.Status;

@Data
@Entity
public class OrderItem {


    @EmbeddedId
    private OrderItemKey id;


    private int quantity;
    private Status status;


    @ManyToOne
    @MapsId("orderId")
    @JoinColumn(name = "order_id")
    private PurchaseOrder purchaseOrder;


    @ManyToOne
    @MapsId("productId")
    @JoinColumn(name = "product_id")
    private Product product;
    public double getSubtotal() {
        return product.getPrice() * quantity;
    }
    public double getSubtotalRealPrice() {
        return (product.getPrice()*(100/107)) * quantity;
    }
    public double getSubtotalTax() {
        return (product.getPrice()*(7/107)) * quantity;
    }
    public int getDecrease(){
        return (product.getRemain() - quantity);
    }


}

