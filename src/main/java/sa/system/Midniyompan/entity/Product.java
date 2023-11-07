package sa.system.Midniyompan.entity;
import jakarta.persistence.*;
import lombok.Data;
import sa.system.Midniyompan.common.Status;

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
    private int inventory;
    @Lob
    @Column(columnDefinition = "MEDIUMBLOB")
    private String image;
    @ManyToOne
    private Category category;
    private String wareHouse;
    private String brand;
    private String detail;
    private int increaseInventory;
    private int decreaseInventory;




}


