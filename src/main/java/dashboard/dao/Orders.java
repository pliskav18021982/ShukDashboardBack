package dashboard.dao;

import java.sql.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "orders")
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class Orders {
	
	@Id
	int id;
	@Column(name = "unique_order_id")
	String uniqueOrderId;
	int orderstatus_id =0;
	int user_id=0;
	String coupon_name;
	String address;
	String location;
	String tax;
	String restaurant_charge;
	String delivery_charge;
	String total;
	String created_at;
	Date updated_at;
//	@Column(name = "order_date")
//	String orderDate;
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
	@Column(name = "schedule_date")
	String orderDate;
	@Column(name = "schedule_slot")
	String orderTime;
			

}
