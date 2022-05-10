package dashboard.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class OrderBaseResponseDTO {
	int id;
	String unique_order_id;
	Integer orderstatus_id;
	int user_id;
	String coupon_name;
	String address;
	String location;
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
	String createdAt;
	
	String is_scheduled;
	String orderDate;
	String orderTime;
	
	String userName;
	String phone;
//	String second_phone;
	String email;
	String avatar;
	String default_address_id;
	String delivery_pin;
	String delivery_guy_detail_id;
	String user_is_active;
	String tax_number;
	//int orderItemId;
	String goods;

	
	@Override
	public String toString() {
		return "OrderBaseResponseDTO [id=" + id + ", unique_order_id=" + unique_order_id + ", orderstatus_id="
				+ orderstatus_id + ", user_id=" + user_id + ", coupon_name=" + coupon_name + ", address=" + address
				+ ", tax=" + tax + ", restaurant_charge=" + restaurant_charge + ", delivery_charge=" + delivery_charge
				+ ", total=" + total + ", payment_mode=" + payment_mode + ", order_comment=" + order_comment
				+ ", restaurant_id=" + restaurant_id + ", transaction_id=" + transaction_id + ", delivery_type="
				+ delivery_type + ", payable=" + payable + ", wallet_amount=" + wallet_amount + ", tip_amount="
				+ tip_amount + ", tax_amount=" + tax_amount + ", coupon_amount=" + coupon_amount + ", sub_total="
				+ sub_total + ", createdAt=" + createdAt + ", is_scheduled=" + is_scheduled + ", orderDate=" + orderDate
				+ ", orderTime=" + orderTime + ", userName=" + userName + ", phone=" + phone + ", email=" + email + ", avatar=" + avatar + ", default_address_id="
				+ default_address_id + ", delivery_pin=" + delivery_pin + ", delivery_guy_detail_id="
				+ delivery_guy_detail_id + ", user_is_active=" + user_is_active + ", tax_number=" + tax_number
				+ ", goods=" + goods + "]";
	}
}
