package sa.system.Midniyompan.model;

import lombok.Data;

import java.util.UUID;

@Data
public class AddProductRequest {
    private UUID categoryId;
    private int remain;
}
