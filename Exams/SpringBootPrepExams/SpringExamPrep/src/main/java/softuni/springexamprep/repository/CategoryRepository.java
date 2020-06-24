package softuni.springexamprep.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import softuni.springexamprep.model.entity.Category;
import softuni.springexamprep.model.entity.CategoryName;

@Repository
public interface CategoryRepository extends JpaRepository<Category,String> {
    Category findByName(CategoryName categoryName);
}
