package sa.system.Midniyompan.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import sa.system.Midniyompan.entity.Receipt;

import java.util.UUID;

public interface ReceiptRepository extends JpaRepository<Receipt, UUID> {

}
