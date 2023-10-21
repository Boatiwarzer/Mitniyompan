package sa.system.Midniyompan.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import sa.system.Midniyompan.entity.Category;
import sa.system.Midniyompan.entity.Product;


import java.util.List;
import java.util.UUID;


@Repository
public interface CategoryRepository extends JpaRepository<Category, UUID> {

}

