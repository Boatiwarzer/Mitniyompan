package sa.system.Midniyompan.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import sa.system.Midniyompan.common.Status;
import sa.system.Midniyompan.entity.FormPO;
import sa.system.Midniyompan.entity.OrderItem;
import sa.system.Midniyompan.entity.OrderItemKey;

import java.util.List;
import java.util.UUID;
@Repository
public interface FormPORepository extends JpaRepository<FormPO, UUID> {
    List<FormPO> findByStatus(Status status);
}
