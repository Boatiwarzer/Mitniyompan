package sa.system.Midniyompan.entity;

import jakarta.persistence.*;
import lombok.*;

import java.io.Serializable;
import java.util.UUID;

@Data
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
@Embeddable
public class OrderItemKey implements Serializable {


    @Column(name = "order_id")
    private UUID orderId;


    @Column(name = "product_id")
    private UUID productId;
}

