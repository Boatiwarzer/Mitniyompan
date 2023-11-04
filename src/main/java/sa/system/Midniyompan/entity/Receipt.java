package sa.system.Midniyompan.entity;

import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.UUID;

@Data
@Entity
public class Receipt {
    @Id
    @GeneratedValue
    private UUID id;
    private LocalDateTime timestamp;

    @OneToOne
    private FormPO formPO;

}
