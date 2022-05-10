package dashboard.service;

import org.springframework.data.repository.CrudRepository;

import dashboard.dao.AddonCategories;

public interface AddonCategoriesRepository extends CrudRepository<AddonCategories, Integer> {
	
	AddonCategories findById(int id);
	
	
	
}
