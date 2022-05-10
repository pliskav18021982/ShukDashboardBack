package dashboard.dao;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "order_item_addons")
@Getter
@Setter
public class OrderItemAddons {
	@Id
	int id;
	@Column(name = "orderitem_id")
	int orderItemId;
	@Column(name = "addon_category_name")
	String addonCategoryName;
	@Column(name = "addon_name")
	int addonName;
	@Column(name = "addon_price")
	float addonPrice;
	String created_at;
	String updated_at;
	public OrderItemAddons(int id, int orderItemId, String addonCategoryName, int addonName, float addonPrice,
			String created_at, String updated_at) {
		super();
		this.id = id;
		this.orderItemId = orderItemId;
		this.addonCategoryName = addonCategoryName;
		this.addonName = addonName;
		this.addonPrice = addonPrice;
		this.created_at = created_at;
		this.updated_at = updated_at;
	}
	public OrderItemAddons() {
		super();
	}
	

}
