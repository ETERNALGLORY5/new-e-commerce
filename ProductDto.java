package huffle.puff.wand.dtos;

import java.util.Date;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;


@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@ToString

public class ProductDto {


	private String productId;
	
	
	private String subTitle;
	
	
	private String productPrescription;
	

	private int price;
	
	
	private int quantity;
	
    
	private Date mfgDate;
	
	
	private Date expDate;
	
	
	private boolean live;
	
	
	private boolean stock;
}
