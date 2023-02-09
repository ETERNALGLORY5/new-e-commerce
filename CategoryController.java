package huffle.puff.wand.controller;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.util.StreamUtils;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import jakarta.servlet.http.HttpServletResponse;
import jakarta.validation.Valid;

import huffle.puff.wand.dtos.CategoryDto;
import huffle.puff.wand.dtos.ImageResponse;
import huffle.puff.wand.services.CategoryService;
import huffle.puff.wand.services.FileService;
import huffle.puff.wand.dtos.ApiResponseMessage;
import huffle.puff.wand.dtos.PageableResponse;

@RestController
@RequestMapping("/categories")
public class CategoryController {

    @Autowired
    private CategoryService categoryService;
    
    @Autowired
	private FileService fileService;
    
    @Value("${project.image}")
	private String imageUploadPath;

    private Logger logger = LoggerFactory.getLogger(CategoryController.class);
    
    

    //CREATE
    @PostMapping
    public ResponseEntity<CategoryDto> create( @Valid @RequestBody CategoryDto categoryDto)
    {
    	//call service to create object
        CategoryDto category1 = categoryService.create(categoryDto);
        return new ResponseEntity<>(category1, HttpStatus.CREATED);
    }


    //UPDATE
    @PutMapping("/{categoryId}")
    public ResponseEntity<CategoryDto> update(
        @PathVariable String categoryId , 
        @Valid 
        @RequestBody CategoryDto categoryDto)
    {
        CategoryDto updatedCategory = categoryService.update(categoryId, categoryDto);
        return new ResponseEntity<>(updatedCategory, HttpStatus.OK);

    }


    //DELETE
    @DeleteMapping("/{categoryId}")
    public ResponseEntity<ApiResponseMessage> delete(@PathVariable("categoryId") String categoryId)
    {
        this.categoryService.delete(categoryId);
        ApiResponseMessage message = ApiResponseMessage
                                      .builder()
                                      .message("Category Is deleted successfully")
                                      .success(true)
                                      .status(HttpStatus.OK)
                                      .build();
        return new ResponseEntity<>(message ,HttpStatus.OK);
    }


    //GET ALL
    @GetMapping
    public ResponseEntity<PageableResponse<CategoryDto>> getAll
    (@RequestParam (value ="pageNumber",defaultValue = "0", required = false) int pageNumber,
     @RequestParam(value="pageSize",defaultValue="10",required=false) int pageSize,
     @RequestParam(value ="sortBy", defaultValue = "categoryTitle",required = false) String sortBy,
     @RequestParam(value = "sortDir",defaultValue = "ASC",required = false) String sortDir
    )
    {
        return new ResponseEntity<>(categoryService.getAll(pageNumber, pageSize,sortBy,sortDir),HttpStatus.OK);
    }


    //GET SINGLE
    @GetMapping("/categoryId")
    public ResponseEntity<CategoryDto> get(@PathVariable String categoryId)
    {
       CategoryDto cat = categoryService.get(categoryId);
        return new ResponseEntity<CategoryDto>(cat, HttpStatus.OK);
    }


    //SEARCH BY TITLE
    @GetMapping("/search/{keywords}")
    public ResponseEntity<List<CategoryDto>> searchCategory(@PathVariable String keywords)
    {
        List<CategoryDto> result = categoryService.searchCategory(keywords);
        return new ResponseEntity<>(result, HttpStatus.OK);
    }
    
    
    // UPLOAD COVER IMAGE
    @PostMapping("/coverImage/categoryId")    
    public ResponseEntity<ImageResponse> uplaodCoverImage 
          (  
        		  @RequestParam("categoryImage") MultipartFile image,
        		  @PathVariable String categoryId
        		 ) throws IOException 
          {
    	       String imageName =   fileService.uploadFile(image,imageUploadPath);
    	       
    	       //to save image on database
    	       
    	       CategoryDto category = categoryService.get(categoryId);
    	       category.setCoverImage(imageName);
    	       
    	       ImageResponse imageResponse = ImageResponse.builder()
    	    		                          .imageName(imageName)
    	    		                          .success(true)
    	    		                          .status(HttpStatus.OK)
    	    		                          .build();
    	       return new ResponseEntity<>(imageResponse, HttpStatus.CREATED);
    	
          }
    
    
    //SERVE IMAGE COVER
    @GetMapping("coverImage/{categoryId}")
    public void serveCoverImage 
        (@PathVariable String categoryId, HttpServletResponse response) throws IOException 
        {
    	   CategoryDto category = categoryService.get(categoryId);
    	   logger.info("Image Served Successfully, with name : {} ", category.getCoverImage());
    	   InputStream resource = fileService.getResource(imageUploadPath, category.getCoverImage());
    	   response.setContentType(MediaType.IMAGE_JPEG_VALUE);
       	   StreamUtils.copy(resource,response.getOutputStream());
    	
        }
    
    

}
