package dashboard.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ItemData {
	Integer id;  //itemId table items
	Integer quantity;
	Integer orderId;
	float price;
	String name;
	int orderItemId;
	int type;      //0-regular,1-Addon
	
	@Override
	public String toString() {
		return "ItemData [id=" + id + ", quantity=" + quantity + ", orderId=" + orderId + ", price=" + price + ", name=" + name + "]";
	}
	
	
}
