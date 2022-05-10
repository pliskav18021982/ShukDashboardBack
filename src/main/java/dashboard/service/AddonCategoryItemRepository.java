package dashboard.service;


import org.springframework.data.repository.CrudRepository;

import dashboard.dao.AddonCategories;
import dashboard.dao.AddonCategoryItem;

public interface AddonCategoryItemRepository  extends CrudRepository<AddonCategoryItem, Integer> {
	
//	AddonCategoryItem findByItemId(int itemId);
	
	AddonCategoryItem findById(int id);
	
	AddonCategoryItem findByItemId(int itemId);
	
}
	
