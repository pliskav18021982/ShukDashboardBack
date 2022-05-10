package dashboard.service;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import dashboard.dao.Language;
import dashboard.dao.Users;

@Repository
public interface TranslateReposutorySql extends CrudRepository<Language, Integer>{

	Language  findById(int id);
	Language findByIsDefault(int isDefault);
}
