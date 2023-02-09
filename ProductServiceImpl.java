package huffle.puff.wand.services.impl;

import java.util.List;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import huffle.puff.wand.dtos.PageableResponse;
import huffle.puff.wand.dtos.ProductDto;
import huffle.puff.wand.entities.Product;
import huffle.puff.wand.repositories.ProductRepository;
import huffle.puff.wand.services.ProductService;


@Service
public class ProductServiceImpl implements ProductService{
	

     @Autowired
     private ProductRepository productRepository;
     
     @Autowired
     private ModelMapper mapper;

	@Override
	public ProductDto create(ProductDto productDto) {
		//convert productDto to product entity
	 Product product =	mapper.map(productDto , Product.class);
	    // save new product to product entity
		Product newProduct = productRepository.save(product);
		// convert entity to dto
     return mapper.map(newProduct, ProductDto.class);
	}
	
	

	@Override
	public ProductDto update(String productId, ProductDto productDto) {
		
		return null;
	}

	@Override
	public PageableResponse<ProductDto> getAll(int pageNumber, int pageSize, String sortBy, String sortDir) {
		
		return null;
	}

	@Override
	public ProductDto getById(String productId) {
		
		return null;
	}

	@Override
	public void delete(String productId) {
		
		
	}

	@Override
	public List<ProductDto> getAllLive(boolean stock) {
		
		return null;
	}

	@Override
	public List<ProductDto> searchByTitle(String subTitle) {
		
		return null;
	}

	
	
}
