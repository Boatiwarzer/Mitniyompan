package sa.system.Midniyompan.repository;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import sa.system.Midniyompan.entity.OrderItem;
import sa.system.Midniyompan.entity.OrderItemKey;


@Repository
public interface OrderItemRepository
        extends JpaRepository<OrderItem, OrderItemKey> {
}

