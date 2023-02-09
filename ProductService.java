package huffle.puff.wand.services;

import java.util.List;

import huffle.puff.wand.dtos.PageableResponse;
import huffle.puff.wand.dtos.ProductDto;

public interface ProductService {

	
	//CREATE 
	ProductDto create(ProductDto productDto);
	
	
	// UPDATE
	ProductDto update(String productId, ProductDto productDto);
	
	// GET ALL
	PageableResponse<ProductDto> getAll(int pageNumber,int pageSize,String sortBy, String sortDir);
	
	// GET BY ID
	ProductDto getById(String productId );
	
	//DELETE
	void delete(String productId);
	
	//GET ALL THAT LIVE
	List<ProductDto> getAllLive(boolean stock);
	
	// SERACH PRODUCT
	List<ProductDto> searchByTitle(String subTitle);
	
}
