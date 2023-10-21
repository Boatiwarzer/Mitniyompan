    package sa.system.Midniyompan.entity;

    import jakarta.persistence.*;
    import lombok.Data;


    import java.util.List;
    import java.util.UUID;


    @Data
    @Entity
    public class Category {


        @Id
        @GeneratedValue
        private UUID id;


        private String name;


        @OneToMany(mappedBy = "category")
        List<Product> product;
        public boolean isEmpty() {
            // Implement your own logic to determine if the category is empty.
            // For example, you might check if the category name is empty.
            return  name == null || name.isEmpty();
        }
    }

