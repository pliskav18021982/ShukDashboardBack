package dashboard.dao;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "items")
@Getter
@Setter
public class Item {
	@Id
	int id;
	@Column(name = "restaurant_id")
	int restaurantId;
	@Column(name = "item_category_id")
	int itemCategoryId; 
	String name;
	float price;
	float old_price; 
	String image; 
	int is_recommended; 
	int is_popular;
	int is_new; 
	String created_at; 
	String updated_at;
	String desc; 
	String placeholder_image; 
	int is_active; 
	String is_veg; 
	String order_column;
	Integer limit;
	public Item(int id, int restaurantId, int itemCategoryId, String name, float price, float old_price, String image,
			int is_recommended, int is_popular, int is_new, String created_at, String updated_at, String desc,
			String placeholder_image, int is_active, String is_veg, String order_column, Integer limit) {
		super();
		this.id = id;
		this.restaurantId = restaurantId;
		this.itemCategoryId = itemCategoryId;
		this.name = name;
		this.price = price;
		this.old_price = old_price;
		this.image = image;
		this.is_recommended = is_recommended;
		this.is_popular = is_popular;
		this.is_new = is_new;
		this.created_at = created_at;
		this.updated_at = updated_at;
		this.desc = desc;
		this.placeholder_image = placeholder_image;
		this.is_active = is_active;
		this.is_veg = is_veg;
		this.order_column = order_column;
		this.limit = limit;
	}
	public Item() {
		super();
	}
	
	
	
	
}
