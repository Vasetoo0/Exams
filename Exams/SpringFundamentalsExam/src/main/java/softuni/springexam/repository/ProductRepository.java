package softuni.springexam.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import softuni.springexam.model.entity.Product;

@Repository
public interface ProductRepository extends JpaRepository<Product,String> {
}
