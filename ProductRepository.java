package huffle.puff.wand.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import huffle.puff.wand.entities.Product;


public interface ProductRepository extends JpaRepository<Product, String>{

	List<Product> findByTitleContaining(String subTitle);
	
	//List<Product> findByLive(boolean live);
	List<Product> findByLiveTrue();
}
