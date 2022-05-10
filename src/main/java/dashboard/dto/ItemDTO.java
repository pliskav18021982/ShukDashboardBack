package dashboard.dto;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.Table;

import dashboard.dao.AddonCategories;
import dashboard.dao.AddonCategoryItem;
import dashboard.dao.Addons;
import dashboard.dao.Item;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class ItemDTO {
Item item;
List<Addons> addons;
AddonCategories addonCategories;
AddonCategoryItem addonCategoryItem;
}
