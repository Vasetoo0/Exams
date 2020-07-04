package softuni.springexam.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import softuni.springexam.model.entity.Category;
import softuni.springexam.model.entity.CategoryName;

@Repository
public interface CategoryRepository extends JpaRepository<Category,String> {

    Category findCategoryByName(CategoryName name);

}
