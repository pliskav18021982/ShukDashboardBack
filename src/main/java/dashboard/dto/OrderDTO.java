package dashboard.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class OrderDTO {
	
	int id;
	String unique_order_id;
	Integer orderstatus_id;
	Integer user_id;
	String coupon_name;
	String address;
	String  location;
	String tax;
	String restaurant_charge;
	String delivery_charge;
	String total;
	String payment_mode;
	String order_comment;
	String restaurant_id;
	String transaction_id;
	String delivery_type;
	String payable;
	String wallet_amount;
	String tip_amount;
	String tax_amount;
	String coupon_amount;
	String sub_total;
	String is_scheduled;
	String orderDate;
	String orderTime;
	String createdAt;
	
	long orderDateTimeStamp;
	
	@Override
	public String toString() {
		return "OrderDTO [id=" + id + ", createdAt=" + createdAt + ", orderDate=" + orderDate + "]";
	}
	
	
	
	

}
