package huffle.puff.wand.repositories;

//import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

//import huffle.puff.wand.dtos.CategoryDto;
//import huffle.puff.wand.entities.Category;
import huffle.puff.wand.entities.Category;

public interface CategoryRepository extends JpaRepository<Category , String>{

//    List<Category> findByNameContaining(String key);

}
