package dashboard.service;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import dashboard.dao.AddonCategories;
import dashboard.dao.Addons;

public interface AddonsRepository extends CrudRepository<Addons, Integer> {
	
	//List<Addons> findAllByAddonCategoryId(int addonCategoryId);
	
	Addons findById(int id);
	List<Addons> findAllByAddonCategoryId(int addonCategoryId);
	
}
