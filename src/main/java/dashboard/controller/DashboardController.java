package dashboard.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import dashboard.configuration.Params;
import dashboard.dto.ItemBriefDTO;
import dashboard.dto.ItemDTO;
import dashboard.dto.OrderBaseResponseDTO;
import dashboard.dto.OrderDTO;
import dashboard.dto.OrderResponseDTO;
import dashboard.dto.PageDTO;
import dashboard.service.IOrderItems;
import dashboard.service.IOrders;
import dashboard.service.IService;
import dashboard.service.ItemRepository;
import dashboard.service.OrderItemRepository;
import dashboard.service.OrderRepositorySql;
import dashboard.service.TranslateReposutorySql;
import dashboard.service.UserRepositorySql;


@CrossOrigin
@RestController
@RequestMapping()
public class DashboardController {
	
	@Autowired
	OrderRepositorySql repositorySql;
	@Autowired
	UserRepositorySql userRepositorySql;
	@Autowired
	IService service;
	@Autowired
	IOrders serviceOrders;
	@Autowired
	IOrderItems itemsService;
	@Autowired
	ItemRepository itemRepo;
	@Autowired
	OrderItemRepository orderItemRepo;
	@Autowired
	Params params;
	
	
	@GetMapping("/")
	public PageDTO getAllOrders(
			@RequestParam(required=false) Integer current_page,
			@RequestParam(required=false) Integer items_on_page
			){
		return serviceOrders.findOrdersByFilters(null, null, null, null, null, null, null, null, current_page, items_on_page);
	}
	
	@GetMapping("/orders/user")
	public PageDTO getAllOrdersByUserId(@RequestParam(required=true) Integer userId){
		return serviceOrders.getAllOrdersByUserId(userId);
	}
	
	@PostMapping("/payments/meshulam")
	public String getPaymentsMeshulam() {
		serviceOrders.createPaymentMeshulam("");
		return "test";
	}
	
	@GetMapping("/orders/search")
	public PageDTO getOrdersbyFilters(
		@RequestParam(required = false) String userEmail,
		@RequestParam(required = false) String userPhone,
		@RequestParam(required = false) String userName, 
		@RequestParam(required = false) String orderDate,
		@RequestParam(required = false) String dateFrom,
		@RequestParam(required = false) String dateTo,
		
		@RequestParam(required = false) List<String> orderItem,
		@RequestParam(required = false) Integer storeId,
			
		@RequestParam(required = false) Integer current_page, 
		@RequestParam(required = false) Integer items_on_page){
		
			return serviceOrders.findOrdersByFilters(userEmail, userPhone, userName, orderDate, dateFrom, dateTo, storeId, orderItem, current_page, items_on_page);
		
		
	}
	
	
	@PutMapping("/order/{orderId}")
	public OrderDTO editOrder(
			@RequestBody OrderResponseDTO order,
			@PathVariable Integer orderId
			) {
		return	serviceOrders.editOrder(orderId, order);
	}
	
	@PutMapping("/order/{orderId}/status")
	public void editOrderStatus(
			@RequestBody String orderStatus,
			@PathVariable Integer orderId
			) {
		serviceOrders.changeOrderStatus(orderId, Integer.parseInt(orderStatus));
	}
	
	@GetMapping("/items")
	public List<ItemBriefDTO> getAllItems(){
		return itemsService.getAllBriefDTOs();
	}
	
@GetMapping("/items/addon")
public ItemDTO getItemById(@RequestParam(required = true) int id) {
	return itemsService.getItemById(id);
	
}

	@PostMapping("/send-whatsapp")
  public Boolean sendWhatsapp(@RequestBody String orderId) {
		 serviceOrders.sendWhatsapp(orderId);
		 return true;
		 
	}
	@GetMapping("/set-default-lng")
	  public Boolean setDefaultLanguageForUser(@RequestParam int userId,@RequestParam int languageId) {
			 serviceOrders.setDefaultLanguageForUser(userId, languageId);
			 return true;
			 
		}
	
	
//	@GetMapping("/items")
//	public List<Item> getallItems(){
//		return itemRepo.findAll();
//	}
//	
//	@GetMapping("/orderitems")
//	public List<OrderItem> getAllOrderItems(){
//		return orderItemRepo.findAll();
//	}
//	
//	
//	@GetMapping("/users")
//	public List<UserSql> getallUserSqls(){
//		return userRepositorySql.findAll();
//	}
//	
//	@PostMapping("/users")
//	public List<UserSql> getallUsersByIds(@RequestBody Iterable<Integer> ids) {
//		return service.findAllUsersById(ids);	
//	}
//	@PostMapping("/user/{id}")
//	public UserSql getuserbyid(@PathVariable int id) {
//		return userRepositorySql.findById(id);
//	}
//	@PostMapping("/order/{id}")
//	public OrderSql getorder(@PathVariable int id) {
//		return repositorySql.findById(id);
//	}
	
 
}
