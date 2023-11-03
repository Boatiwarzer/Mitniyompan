package sa.system.Midniyompan.model;



import jakarta.persistence.Lob;
import jakarta.persistence.Column;
import jakarta.persistence.ManyToOne;
import lombok.Data;
import org.springframework.web.multipart.MultipartFile;
import sa.system.Midniyompan.entity.Category;

import java.sql.Blob;
import java.util.UUID;


@Data
public class ProductRequest {
    private UUID categoryId;
    private String name;
    private double price;
    private String image;
    private int remain;
    private String brand;
    private String warehouse;
    private String detail;
}
