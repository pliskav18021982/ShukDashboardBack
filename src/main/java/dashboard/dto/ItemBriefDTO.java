package dashboard.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ItemBriefDTO {
	
	Integer id;
	String name;
	float price;
	String image;
	Integer quantity;
	Integer limit;
	Long totalItemsAddon;
	
	
	
		
	

}
