package huffle.puff.wand.entities;

import java.util.Date;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
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
@Entity
@Table(name = "products")
public class Product {

	@Id
	private String productId;
	
	@Column(name = "product_title", length = 1000)
	private String subTitle;
	
	@Column(name ="product_prescription", length =1000)
	private String productPrescription;
	
	@Column(name="price", length=60)
	private int price;
	
	@Column(name="quantity", length=60)
	private int quantity;
	
    @Column(name="mfg_date", length = 30)
	private Date mfgDate;
	
	@Column(name="exp_date", length=30)
	private Date expDate;
	
	@Column(name="")
	private boolean live;
	
	@Column(name="in stock",length = 10)
	private boolean stock;
}
