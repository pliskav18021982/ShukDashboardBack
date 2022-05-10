package dashboard.service;

import java.util.List;

import dashboard.dao.Users;

public interface IService {
	
	public List<Users> findAllUsers();
	
	public Users findUserById(int Id);
	
	List<Users> findAllUsersById(Iterable<Integer> ids);

	
	}
