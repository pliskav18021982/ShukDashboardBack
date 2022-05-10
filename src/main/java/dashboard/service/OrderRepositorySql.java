package dashboard.service;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import dashboard.dao.Orders;
import dashboard.dto.OrderBaseResponseDTO;

@Repository
public interface OrderRepositorySql extends CrudRepository<Orders, Integer> {

	Orders findByUniqueOrderId(String unique_order_id);
	
	List<Orders> findAll();
	
	Orders findById(int id);
	

	@Query(value = "select count(distinct o.id) as total_count "
			 
			+ "FROM Orders o "
			+ "inner join Users u on o.user_id=u.id "
			+ "inner join OrderItem items on o.id=items.orderId "
			+ "where (:email is null or lower(u.email) LIKE lower(CONCAT('%',:email,'%'))) "
			+ "and (:phone is null or u.phone LIKE CONCAT('%',:phone,'%')) "
			+ "and (:username is null or lower(u.userName) LIKE lower(CONCAT('%',:username,'%'))) "
			+ "and (:date is null or date(json_unquote(json_extract(o.orderDate, '$.date'))) = date(:date)) "
			+ "and (:restaurantId is null or o.restaurant_id = :restaurantId) "
			+ "and (:dateFrom is null or date(json_unquote(json_extract(o.orderDate, '$.date'))) between date(:dateFrom) and date(:dateTo))"
			+ "and :itemName is null or lower(items.itemName) in (:itemName) "
			+ "order by o.created_at desc",
			nativeQuery = false)
	Long findCountofAllOrdersJoinUsersWithAllFilters(
														@Param(value = "itemName") List<String> itemName,
														@Param(value = "email") String email,
														@Param("phone") String phone,
														@Param("username") String userName,
														@Param("date") String orderDate,
														@Param("restaurantId") String restaurantId,
														@Param("dateFrom") String from,
														@Param("dateTo") String to
														);

	
	@Query(value = "SELECT new dashboard.dto.OrderBaseResponseDTO(o.id, o.uniqueOrderId, o.orderstatus_id, o.user_id, o.coupon_name, o.address, o.location, o.tax, "
			+ "o.restaurant_charge, o.delivery_charge, o.total, o.payment_mode, o.order_comment, o.restaurant_id, o.transaction_id, o.delivery_type, o.payable, "
			+ "o.wallet_amount, o.tip_amount, o.tax_amount, o.coupon_amount, o.sub_total, o.created_at, o.is_scheduled, o.orderDate, o.orderTime, "
			+ "u.userName, u.phone, u.email, u.avatar, u.default_address_id, u.delivery_pin, u.delivery_guy_detail_id, u.userIs_active, u.tax_number, "
			+ "group_concat(items.itemId, ',', items.quantity, ',', items.price, ',', items.itemName,  ',', items.id, ';') as goods) "
			 
			+ "FROM Orders o "
			+ "inner join Users u on o.user_id=u.id "
			+ "inner join OrderItem items on o.id=items.orderId "
			//+ "right join OrderItemAddons addon on  (items.price=0 ) "
			
			//+ "(NOT(addon.addonName==null) then (items.quantity) else (addon.addonName)  end)   as total_items_addon"
			
			+ "where (:email is null or u.email=:email) "
			+ "and (:phone is null or u.phone = :phone) "
			+ "and (:username is null or u.userName = :username) "
			+ "and (:date is null or date(json_unquote(json_extract(o.orderDate, '$.date'))) = date(:date)) "
			+ "and (:restaurantId is null or o.restaurant_id = :restaurantId) "
			+ "and (:dateFrom is null or date(json_unquote(json_extract(o.orderDate, '$.date'))) between date(:dateFrom) and date(:dateTo))"
			+ "and :itemName is null or lower(items.itemName) in (:itemName) "
			+ "group by o.id "
//			+ "having lower(group_concat(items.itemId, ',', items.quantity, ',', items.price, ',', items.itemName, ';')) like lower(:orderGoods) "
//			+ "having lower(group_concat(items.itemName)) in lower (:orderGoods) "
			+ "order by o.created_at desc",
			nativeQuery = false)
	Page<OrderBaseResponseDTO> findAllOrdersJoinUsersWithAllFilters(
																@Param(value = "itemName") List<String> itemName,
																@Param(value = "email") String email,
																@Param("phone") String phone,
																@Param("username") String userName,
																@Param("date") String orderDate,
																@Param("restaurantId") String restaurantId,
																@Param("dateFrom") String from,
																@Param("dateTo") String to,
																Pageable pageable);
	
	@Query(value = "SELECT new dashboard.dto.OrderBaseResponseDTO(o.id, o.uniqueOrderId, o.orderstatus_id, o.user_id, o.coupon_name, o.address, o.location, o.tax, "
			+ "o.restaurant_charge, o.delivery_charge, o.total, o.payment_mode, o.order_comment, o.restaurant_id, o.transaction_id, o.delivery_type, o.payable, "
			+ "o.wallet_amount, o.tip_amount, o.tax_amount, o.coupon_amount, o.sub_total, o.created_at, o.is_scheduled, o.orderDate, o.orderTime, "
			+ "u.userName, u.phone, u.email, u.avatar, u.default_address_id, u.delivery_pin, u.delivery_guy_detail_id, u.userIs_active, u.tax_number, "
			+ "group_concat(items.itemId, ',', items.quantity, ',', items.price, ',', items.itemName,  ',', items.id, ';') as goods) "
			 
			+ "FROM Orders o "
			+ "inner join Users u on o.user_id=u.id "
			+ "inner join OrderItem items on o.id=items.orderId "
			+ "where (:email is null or u.email=:email) "
			+ "and (:phone is null or u.phone = :phone) "
			+ "and (:username is null or u.userName = :username) "
			+ "and (:date is null or date(json_unquote(json_extract(o.orderDate, '$.date'))) = date(:date)) "
			+ "and (:restaurantId is null or o.restaurant_id = :restaurantId) "
			+ "and (:dateFrom is null or date(json_unquote(json_extract(o.orderDate, '$.date'))) between date(:dateFrom) and date(:dateTo))"
			+ "and :itemName is null or lower(items.itemName) in (:itemName) "
			//+ "right join OrderItemAddons addon on  (items.price=0 ) "
			
			//+ "(NOT(addon.addonName==null) then (items.quantity) else (addon.addonName)  end)   as total_items_addon"
//			+ "where :itemName is null or items.itemName in :itemName "
//			
			+ "group by o.id "
//			+ "having lower(group_concat(items.itemId, ',', items.quantity, ',', items.price, ',', items.itemName, ';')) like lower(:orderGoods) "
//			+ "having lower(group_concat(items.itemName)) in (:itemName) "
			+ "order by o.created_at desc",
			nativeQuery = false)
	Page<OrderBaseResponseDTO> testFindAllOrdersJoinUsersWithAllFilters(
																@Param(value = "itemName") List<String> itemName,
																@Param(value = "email") String email,
																@Param("phone") String phone,
																@Param("username") String userName,
																@Param("date") String orderDate,
																@Param("restaurantId") String restaurantId,
																@Param("dateFrom") String from,
																@Param("dateTo") String to,
																Pageable pageable);
	
	
	@Query(value = "SELECT new dashboard.dto.OrderBaseResponseDTO(o.id, o.uniqueOrderId, o.orderstatus_id, o.user_id, o.coupon_name, o.address, o.location, o.tax, "
			+ "o.restaurant_charge, o.delivery_charge, o.total, o.payment_mode, o.order_comment, o.restaurant_id, o.transaction_id, o.delivery_type, o.payable, "
			+ "o.wallet_amount, o.tip_amount, o.tax_amount, o.coupon_amount, o.sub_total, o.created_at, o.is_scheduled, o.orderDate, o.orderTime, "
			+ "u.userName, u.phone, u.email, u.avatar, u.default_address_id, u.delivery_pin, u.delivery_guy_detail_id, u.userIs_active, u.tax_number, "
			+ "group_concat(items.itemId, ',', items.quantity, ',', items.price, ',', items.itemName,  ',', items.id,';') as goods) "
			 
			+ "FROM Orders o "
			+ "inner join Users u on o.user_id=u.id "
			+ "inner join OrderItem items on o.id=items.orderId "
			//+ "right join OrderItemAddons addon on ( (addon.addonName=null)) "
			+ "where (:email is null or u.email=:email) "
			+ "and (:phone is null or u.phone = :phone) "
			+ "and (:username is null or u.userName = :username) "
			+ "and (:date is null or date(json_unquote(json_extract(o.orderDate, '$.date'))) = date(:date)) "
			+ "and (:restaurantId is null or o.restaurant_id = :restaurantId) "
			+ "and (:dateFrom is null or date(json_unquote(json_extract(o.orderDate, '$.date'))) between date(:dateFrom) and date(:dateTo))"
			+ "group by o.id "
			+ "order by o.created_at desc",
			nativeQuery = false)
	Page<OrderBaseResponseDTO> findAllOrdersJoinUserswithFilters(@Param(value = "email") String email, 
																@Param("phone") String phone,
																@Param("username") String userName,
																@Param("date") String orderDate,
																@Param("restaurantId") String restaurantId,
																@Param("dateFrom") String from,
																@Param("dateTo") String to,
																Pageable pageable);
	
	@Query(value = "SELECT new dashboard.dto.OrderBaseResponseDTO(o.id, o.uniqueOrderId, o.orderstatus_id, o.user_id, o.coupon_name, o.address, o.location, o.tax, "
			+ "o.restaurant_charge, o.delivery_charge, o.total, o.payment_mode, o.order_comment, o.restaurant_id, o.transaction_id, o.delivery_type, o.payable, "
			+ "o.wallet_amount, o.tip_amount, o.tax_amount, o.coupon_amount, o.sub_total, o.created_at, o.is_scheduled, o.orderDate, o.orderTime, "
			+ "u.userName, u.phone, u.email, u.avatar, u.default_address_id, u.delivery_pin, u.delivery_guy_detail_id, u.userIs_active, u.tax_number, "
			+ "group_concat(items.itemId, ',', items.quantity, ',', items.price, ',', items.itemName,  ',', items.id, ';') as goods) "
			+ "FROM Orders o "
			+ "inner join Users u on o.user_id=u.id "
			+ "inner join OrderItem items on o.id=items.orderId " 
			
			+ "group by o.id "
			+ "order by o.created_at desc",
			nativeQuery = false)
	Page<OrderBaseResponseDTO> findAllOrdersJoinUsers(Pageable pageable);
	
	
	
	
	
	@Query(value = "select count(*)as total_count from orders",nativeQuery = true)
	Long findTotalCountOrders();
	
	
	@Query(value = "SELECT new dashboard.dto.OrderBaseResponseDTO(o.id, o.uniqueOrderId, o.orderstatus_id, o.user_id, o.coupon_name, o.address, o.location, o.tax, "
			+ "o.restaurant_charge, o.delivery_charge, o.total, o.payment_mode, o.order_comment, o.restaurant_id, o.transaction_id, o.delivery_type, o.payable, "
			+ "o.wallet_amount, o.tip_amount, o.tax_amount, o.coupon_amount, o.sub_total, o.created_at, o.is_scheduled, o.orderDate, o.orderTime, "
			+ "u.userName, u.phone, u.email, u.avatar, u.default_address_id, u.delivery_pin, u.delivery_guy_detail_id, u.userIs_active, u.tax_number, "
			+ "group_concat(items.itemId, ',', items.quantity, ',', items.price, ',', items.itemName,  ',', items.id, ';') as goods) "
			 
			+ "FROM Orders o "
			+ "inner join Users u on o.user_id=u.id "
			+ "inner join OrderItem items on o.id=items.orderId "
			//+ "right join OrderItemAddons addon on  (items.price=0 ) "
			
			//+ "(NOT(addon.addonName==null) then (items.quantity) else (addon.addonName)  end)   as total_items_addon"
			+ "where (:id is null or u.id=:id) "
			
			+ "group by o.id "
			
			+ "order by o.created_at desc",
			nativeQuery = false)
	List<OrderBaseResponseDTO> findAllOrdersJoinUserId(@Param("id") int id);

	
	
	
	@Query(value = "SELECT "
			+ "o.id "
//			+ "new dashboard.dto.OrderBaseResponseDTO(o.id, o.uniqueOrderId, o.orderstatus_id, o.user_id, o.coupon_name, o.address, o.location, o.tax, "
//			+ "o.restaurant_charge, o.delivery_charge, o.total, o.payment_mode, o.order_comment, o.restaurant_id, o.transaction_id, o.delivery_type, o.payable, "
//			+ "o.wallet_amount, o.tip_amount, o.tax_amount, o.coupon_amount, o.sub_total, o.created_at, o.is_scheduled, o.orderDate, o.orderTime, "
//			+ "u.userName, u.phone, u.email, u.avatar, u.default_address_id, u.delivery_pin, u.delivery_guy_detail_id, u.userIs_active, u.tax_number, "
//			+ "group_concat(items.itemId, ',', items.quantity, ',', items.price, ',', items.itemName,  ',', items.id, ';') as goods) "
			 
			+ "FROM Orders o "
			+ "inner join Users u on o.user_id=u.id "
			+ "inner join OrderItem items on o.id=items.orderId "
			//+ "right join OrderItemAddons addon on  (items.price=0 ) "
			
			//+ "(NOT(addon.addonName==null) then (items.quantity) else (addon.addonName)  end)   as total_items_addon"
			
			+ "where (:email is null or lower(u.email) LIKE lower(CONCAT('%',:email,'%'))) "
			+ "and (:phone is null or u.phone LIKE CONCAT('%',:phone,'%')) "
			+ "and (:username is null or lower(u.userName) LIKE lower(CONCAT('%',:username,'%'))) "
			+ "and (:date is null or date(json_unquote(json_extract(o.orderDate, '$.date'))) = date(:date)) "
			+ "and (:restaurantId is null or o.restaurant_id = :restaurantId) "
			+ "and (:dateFrom is null or date(json_unquote(json_extract(o.orderDate, '$.date'))) between date(:dateFrom) and date(:dateTo))"
			+ "and :itemName is null or lower(items.itemName) in (:itemName) "
			+ "group by o.id "
//			+ "having lower(group_concat(items.itemId, ',', items.quantity, ',', items.price, ',', items.itemName, ';')) like lower(:orderGoods) "
//			+ "having lower(group_concat(items.itemName)) in lower (:orderGoods) "
			+ "order by o.created_at desc",
			nativeQuery = false)
	List<Integer> findAllOrderIdsWithAllFilters(
			@Param(value = "itemName") List<String> itemName,
			@Param(value = "email") String email,
			@Param("phone") String phone,
			@Param("username") String userName,
			@Param("date") String orderDate,
			@Param("restaurantId") String restaurantId,
			@Param("dateFrom") String from,
			@Param("dateTo") String to,
			Pageable pageable
			);
	@Query(value = "SELECT "
			
			+ "new dashboard.dto.OrderBaseResponseDTO(o.id, o.uniqueOrderId, o.orderstatus_id, o.user_id, o.coupon_name, o.address, o.location, o.tax, "
			+ "o.restaurant_charge, o.delivery_charge, o.total, o.payment_mode, o.order_comment, o.restaurant_id, o.transaction_id, o.delivery_type, o.payable, "
			+ "o.wallet_amount, o.tip_amount, o.tax_amount, o.coupon_amount, o.sub_total, o.created_at, o.is_scheduled, o.orderDate, o.orderTime, "
			+ "u.userName, u.phone, u.email, u.avatar, u.default_address_id, u.delivery_pin, u.delivery_guy_detail_id, u.userIs_active, u.tax_number, "
			+ "group_concat(items.itemId, ',', items.quantity, ',', items.price, ',', items.itemName,  ',', items.id, ';') as goods) "
			 
			+ "FROM Orders o "
			+ "inner join Users u on o.user_id=u.id "
			+ "inner join OrderItem items on o.id=items.orderId "	
			+ "where (o.id in (:ids)) "
			+ "group by o.id "
			+ "order by o.created_at desc",
			nativeQuery = false)
	List<OrderBaseResponseDTO> findAllOrdersById(@Param(value = "ids") List<Integer> responeIds);
	                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                       
}








