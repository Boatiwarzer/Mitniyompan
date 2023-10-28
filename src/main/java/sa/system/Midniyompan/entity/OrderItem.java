package sa.system.Midniyompan.entity;
import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
public class OrderItem {


    @EmbeddedId
    private OrderItemKey id;


    private int quantity;


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


}

