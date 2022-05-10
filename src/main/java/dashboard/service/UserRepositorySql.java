package dashboard.service;

import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import dashboard.dao.Users;
@Repository
public interface UserRepositorySql extends CrudRepository<Users, Integer> {
	
	List<Users> findAll();
	
	Users findById(int Id);
	
	List<Users> findAllById (Iterable<Integer> ids);

}
