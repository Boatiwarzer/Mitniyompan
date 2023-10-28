package sa.system.Midniyompan.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import sa.system.Midniyompan.entity.Customer;
import sa.system.Midniyompan.entity.OrderItem;
import sa.system.Midniyompan.entity.OrderItemKey;

import java.util.UUID;

@Repository
public interface CustomerRepository extends JpaRepository<Customer, UUID> {
}
