package dashboard.service;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import dashboard.dao.OrderItem;
import dashboard.dto.ItemBriefDTO;

@Repository
public interface OrderItemRepository extends CrudRepository<OrderItem, Integer> {
	
	List<OrderItem> findAll();
	
	List<OrderItem> findAllById(Iterable<Integer> ids);
	
	List<OrderItem> getAllByOrderIdAndItemIdIn(int orderId, Iterable<Integer> ids);
	
	List<OrderItem> findAllByOrderId(int orderid);
	
	OrderItem findById(int id);
	
	@Query(value = "SELECT new dashboard.dto.ItemBriefDTO(orderItems.id, orderItems.itemName, orderItems.price, item.image, orderItems.quantity, item.limit, "+
	"coalesce( SUM(orderItems.quantity), SUM(addons.addonName)))" +
			"FROM OrderItem orderItems " + 
			"left join OrderItemAddons addons on orderItems.id=addons.orderItemId "+
			"inner join Item item on orderItems.itemName=item.name "+
			"group by orderItems.itemName",
			nativeQuery = false)
	List<ItemBriefDTO> findAllNewItems();
	
	
}