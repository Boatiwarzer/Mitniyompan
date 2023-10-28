package sa.system.Midniyompan.entity;
import jakarta.persistence.*;
import lombok.Data;
import java.sql.Blob;

import java.util.UUID;


@Data
@Entity
public class Product {
    @Id
    @GeneratedValue
    private UUID id;
    private String name;
    private double price;
    @Lob
    @Column(columnDefinition = "MEDIUMBLOB")
    private String image;
    @ManyToOne
    private Category category;
    private String wareHouse;
    private String status;
    private String brand;


}


