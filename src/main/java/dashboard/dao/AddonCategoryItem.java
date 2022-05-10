package dashboard.dao;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "addon_category_item")
@Getter
@Setter
public class AddonCategoryItem {
@Id
	int id;
	@Column(name = "addon_category_id")
	int addonCategoryId;
	@Column(name = "item_id")
	int itemId;
}
