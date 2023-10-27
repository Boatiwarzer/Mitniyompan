package sa.system.Midniyompan.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import sa.system.Midniyompan.entity.Category;
import sa.system.Midniyompan.entity.Product;

import java.util.List;
import java.util.UUID;

public interface ProductRepository extends JpaRepository<Product, UUID> {

//    List<Product> findByCategory(@Param("category")Category category);


    @Query("SELECT p FROM Product p WHERE "
            + "CONCAT(p.price, p.name, p.category.name)" + "LIKE %?1%")
    List<Product> search(@Param("keyword")String keyword);

}
