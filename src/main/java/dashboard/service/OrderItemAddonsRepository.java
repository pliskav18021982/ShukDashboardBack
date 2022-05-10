package dashboard.service;

import java.util.List;
import java.util.Set;

import org.springframework.data.repository.CrudRepository;

import dashboard.dao.OrderItemAddons;

public interface OrderItemAddonsRepository extends CrudRepository<OrderItemAddons, Integer> {
	
	OrderItemAddons findByOrderItemId(int orderItemId);

}
