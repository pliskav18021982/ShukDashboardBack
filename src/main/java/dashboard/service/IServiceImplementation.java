package dashboard.service;

import java.sql.Date;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

import org.apache.tomcat.util.json.JSONParser;
import org.apache.tomcat.util.json.ParseException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Slice;
import org.springframework.stereotype.Service;

import com.google.gson.JsonObject;

import dashboard.configuration.Params;
import dashboard.dao.AddonCategories;
import dashboard.dao.AddonCategoryItem;
import dashboard.dao.Addons;
import dashboard.dao.Item;
import dashboard.dao.Language;
import dashboard.dao.OrderItem;
import dashboard.dao.OrderItemAddons;
import dashboard.dao.Orders;
import dashboard.dao.Users;
import dashboard.dto.ItemBriefDTO;
import dashboard.dto.ItemDTO;
import dashboard.dto.ItemData;
import dashboard.dto.OrderBaseResponseDTO;
import dashboard.dto.OrderDTO;
import dashboard.dto.OrderPageDTO;
import dashboard.dto.OrderResponseDTO;
import dashboard.dto.PageDTO;
import dashboard.dto.UserOrderDTO;
import dashboard.exceptions.BadRequestException;
import dashboard.exceptions.NotFoundException;
import utils.WhatsappMessage;

@Service
public class IServiceImplementation implements IService, IOrders, IOrderItems{


	@Autowired
	UserRepositorySql userRepo;
	@Autowired
	OrderRepositorySql orderRepo;
	@Autowired
	OrderItemRepository orderItemRepository;
	@Autowired
	ItemRepository itemRepository;
	@Autowired
	Params params;
	@Autowired
	TranslateReposutorySql translateRepositorySql;
	@Autowired
	OrderItemAddonsRepository orderItemAddonsRepository;
	@Autowired
	AddonCategoriesRepository addonCategoriesRepository;
	@Autowired
	AddonCategoryItemRepository addonCategoryItemRepository;
	@Autowired
	AddonsRepository addonsRepository;
	
	
	
	@Override
	public PageDTO findOrdersByFilters(String userEmail, String userPhone, String userName, String orderDate, String dateFrom, String dateTo,
			Integer storeId, List<String> orderItem, Integer current_page, Integer items_on_page) {
		
			
		if(current_page == null) {
			
			current_page=params.getDefault_page_number();
		}
		if(items_on_page==null) {
			
			items_on_page=params.getDefault_items_count();
		}
		if (userEmail==null||userEmail=="") {
			userEmail=null;
		}
		if (userPhone==null||userPhone=="") {
			userPhone=null;
		}
		if (userName==null||userName=="") {
			userName=null;
		}
		if (orderDate==null||orderDate=="") {
			orderDate=null;
		}
		if(orderItem == null || orderItem.size() == 0) {
			orderItem = null;
		}
		if(dateFrom == null || dateFrom== "") {
			dateFrom = null;
		}
		if(dateTo == null || dateTo == "") {
			dateTo = null;
		}
		if(userPhone != null) {
			if(userPhone.charAt(0)==32) {
				userPhone = "+" + userPhone.substring(1);
			}
		}
//		if(orderItem!=null) {
//			orderItem = "%".concat(orderItem).concat("%");
//		}
		if(current_page!=null) {System.out.println("current page + " + current_page);}
		Pageable pageable = PageRequest.of(current_page, items_on_page);
		List<Integer>responeIds = orderRepo.findAllOrderIdsWithAllFilters(
				orderItem,
				userEmail, 
				userPhone, 
				userName, 
				orderDate,
				storeId==null ? null : storeId.toString(), 
				dateFrom,
				dateTo,
				pageable);
		responeIds.stream().forEach(id -> System.out.println("orderid - " + id));
		
		Page<OrderBaseResponseDTO> result = orderRepo.findAllOrdersJoinUsersWithAllFilters(
				orderItem,
				userEmail, 
				userPhone, 
				userName, 
				orderDate,
				storeId==null ? null : storeId.toString(), 
				dateFrom,
				dateTo,
				pageable
				);
		List<OrderBaseResponseDTO> responsePage = orderRepo.findAllOrdersById(responeIds);
		responsePage.stream().forEach(order -> System.out.println("order -> " + order));
		
//		result = orderRepo.testFindAllOrdersJoinUsersWithAllFilters(
//				orderItem,
//				userEmail, 
//				userPhone, 
//				userName, 
//				orderDate,
//				storeId==null ? null : storeId.toString(), 
//				dateFrom,
//				dateTo,
//				pageable);
		
//		if(userEmail==null && userName==null && userPhone==null && orderDate==null && storeId==null && orderItem==null && dateFrom == null && dateTo == null) {
//			result = orderRepo.findAllOrdersJoinUsersWithAllFilters(
//					orderItem,
//					userEmail, 
//					userPhone, 
//					userName, 
//					orderDate,
//					storeId==null ? null : storeId.toString(), 
//					dateFrom,
//					dateTo,
//					pageable
//					);
//		}
//		else if(orderItem!=null){
//			result = orderRepo.findAllOrdersJoinUsersWithAllFilters(
//					orderItem,
//					userEmail, 
//					userPhone, 
//					userName, 
//					orderDate,
//					storeId==null ? null : storeId.toString(), 
//					dateFrom,
//					dateTo,
//					pageable
//					);
//		}
//		else{
//			
//			result = orderRepo.findAllOrdersJoinUsersWithAllFilters(
//					orderItem,
//					userEmail, 
//					userPhone, 
//					userName, 
//					orderDate,
//					storeId==null ? null : storeId.toString(), 
//					dateFrom,
//					dateTo,
//					pageable
//					);
//			
//		}
		
		Long totalCount = orderRepo.findCountofAllOrdersJoinUsersWithAllFilters(
				orderItem,
				userEmail, 
				userPhone, 
				userName, 
				orderDate,
				storeId==null ? null : storeId.toString(), 
				dateFrom,
				dateTo
				);
		
		
		List<OrderResponseDTO> res = new ArrayList<OrderResponseDTO>(responsePage.stream()
																		.map(this::convertToOrderResponseDTO)
																		.collect(Collectors.toList()));
		System.out.println(res.size());
		
		Set<Integer> itemIdSet = res
									.stream()
									.flatMap(order -> order.getOrderItemsDtos()
															.stream()
															.map(data -> data.getId()))
									.collect(Collectors.toSet());

		List<Item> listItems = itemRepository.findAllById(itemIdSet);
	
		return PageDTO.builder()
				.current_page(current_page)
				.items_on_page(items_on_page > res.size() ? res.size() : items_on_page)
				.total_count(totalCount)
				.orderPage(OrderPageDTO
						.builder()
						.orders(res)
						.items(listItems)
						.build())
				.build();
	}
	private List<String> prepareStringArrayToStringForFilter(String[] orderItem) {
//		List<String> result = null;
		
//		[z,b,c,v]
//		i=0: z + |
//		i=1: z + | + b + |
//		i=2: z + | + b + | + c + |
//		i=3: z + | + b + | + c + |
//		i=4: z + | + b + | + c + | + v
		
		return Arrays.asList(orderItem);
	}
	
	
	@Override
	public PageDTO getAllOrdersByUserId(int userId) {
		List<OrderBaseResponseDTO> results =orderRepo.findAllOrdersJoinUserId(userId);
		List<OrderResponseDTO> res = new ArrayList<OrderResponseDTO>();

		for(OrderBaseResponseDTO order : results) {
			res.add(convertToOrderResponseDTO(order));
		}
		Set<Integer> itemIdSet = res
				.stream()
				.flatMap(order -> order.getOrderItemsDtos()
										.stream()
										.map(data -> data.getId()))
				.collect(Collectors.toSet());

List<Item> listItems = itemRepository.findAllById(itemIdSet);
		
		return PageDTO.builder()
				.orderPage(OrderPageDTO
						.builder()
						.orders(res)
						.items(listItems)
						.build())
				.build();
	}

	private OrderResponseDTO convertToOrderResponseDTO(OrderBaseResponseDTO item){
		
		 
		
		OrderDTO order = null;
		try {
			order = new OrderDTO(item.getId(), item.getUnique_order_id(), item.getOrderstatus_id(), item.getUser_id(), item.getCoupon_name(), item.getAddress(), item.getLocation(), 
					item.getTax(), item.getRestaurant_charge(), item.getDelivery_charge(), item.getTotal(), item.getPayment_mode(), item.getOrder_comment(), item.getRestaurant_id(), 
					item.getTransaction_id(), item.getDelivery_type(), item.getPayable(), item.getWallet_amount(), item.getTip_amount(), item.getTax_amount(), 
					item.getCoupon_amount(), item.getSub_total(), item.getIs_scheduled(), item.getOrderDate(), item.getOrderTime(), item.getCreatedAt(),
					new SimpleDateFormat("yyyy-MM-dd").parse(new JSONParser(item.getOrderDate()).parseObject().get("date").toString()).getTime()
					
					);
		} catch (java.text.ParseException | ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.out.println(order.getId());
		
		UserOrderDTO user = new UserOrderDTO(item.getUser_id(), item.getUserName(), item.getEmail(), item.getPhone(), item.getDefault_address_id(), 
				item.getDelivery_pin(), item.getDelivery_guy_detail_id(), item.getAvatar(), item.getUser_is_active(), item.getTax_number());
		
		String goodString = item.getGoods();
		if (goodString.endsWith(";")) {
			goodString = goodString.substring(0, (goodString.length()-1)); 
		}
		String[] strArray = goodString.split(";,");
		List<String> arrItems = Arrays.asList(strArray);
		
		List<ItemData> goodsList = arrItems.stream().map(arrayString -> arrayString.split(","))
		.map(array -> {
			OrderItemAddons orderItemAddons = null;
					if(Float.parseFloat(array[2])==0) {
						orderItemAddons = orderItemAddonsRepository.findByOrderItemId(Integer.parseInt(array[4]));
					}
			return new ItemData(Integer.parseInt(array[0]),
								orderItemAddons!=null?orderItemAddons.getAddonName():Integer.parseInt(array[1]),
								item.getId(), 
								orderItemAddons!=null?orderItemAddons.getAddonPrice():Float.parseFloat(array[2]),
								array[3],
								Integer.parseInt(array[4]),
								orderItemAddons==null?0:1);
		})
		.collect(Collectors.toList());
		
				
		
		return new OrderResponseDTO(order, user, goodsList);
	}


	@Override
	public List<Users> findAllUsers() {
		return userRepo.findAll();
	}

	@Override
	public Users findUserById(int Id) {
		return userRepo.findById(Id);
	}



	@Override
	public List<Users> findAllUsersById(Iterable<Integer> ids) {
		// TODO Auto-generated method stub
		return null;
	}


	@Override
	public OrderDTO editOrder(Integer orderId, OrderResponseDTO orderData) {
		if (orderData == null || orderId == null) {
			throw new BadRequestException();
		}
		OrderDTO orderSent = null;
		if(orderData.getOrder()!=null) {
			 orderSent = orderData.getOrder();

			Orders orderEdit = orderRepo.findById(orderId).orElseThrow(() -> new BadRequestException());

			if(!orderSent.getAddress().equals(null)) {
				orderEdit.setAddress(orderSent.getAddress());
			}
			if(orderSent.getOrderstatus_id()!=null) {
				orderEdit.setOrderstatus_id(orderSent.getOrderstatus_id());
			}
			if(orderSent.getCoupon_amount()!= null) {
				orderEdit.setCoupon_amount(orderSent.getCoupon_amount());
			}
			if(orderSent.getCoupon_name()!=null) {
				orderEdit.setCoupon_name(orderSent.getCoupon_name());
			}
			if(orderSent.getTax()!=null) {
				orderEdit.setTax(orderSent.getTax());
			}
			if(orderSent.getRestaurant_charge()!=null) {
				orderEdit.setRestaurant_charge(orderSent.getRestaurant_charge());
			}
			if(orderSent.getDelivery_charge()!=null) {
				orderEdit.setDelivery_charge(orderSent.getDelivery_charge());
			}
			if(orderSent.getTotal()!=null) {
				orderEdit.setTotal(orderSent.getTotal());
			}
			if(orderSent.getPayment_mode()!=null) {
				orderEdit.setPayment_mode(orderSent.getPayment_mode());
			}
			if(orderSent.getOrder_comment()!=null) {
				orderEdit.setOrder_comment(orderSent.getOrder_comment());
			}
			if(orderSent.getRestaurant_id()!=null) {
				orderEdit.setRestaurant_id(orderSent.getRestaurant_id());
			}
			if(orderSent.getTransaction_id()!=null) {
				orderEdit.setTransaction_id(orderSent.getTransaction_id());
			}
			if(orderSent.getDelivery_type()!=null) {
				orderEdit.setDelivery_type(orderSent.getDelivery_type());
			}
			if(orderSent.getPayable()!=null) {
				orderEdit.setPayable(orderSent.getPayable());
			}
			if(orderSent.getWallet_amount()!=null) {
				orderEdit.setWallet_amount(orderSent.getWallet_amount());
			}
			if(orderSent.getTip_amount()!=null) {
				orderEdit.setTip_amount(orderSent.getTip_amount());
			}
			if(orderSent.getTax_amount()!=null) {
				orderEdit.setTax_amount(orderSent.getTax_amount());
			}
			if(orderSent.getSub_total()!=null) {
				orderEdit.setSub_total(orderSent.getSub_total());
			}
			Date date = Date.valueOf(LocalDate.now());
			orderEdit.setUpdated_at(date);
			orderRepo.save(orderEdit);
		}else {
			System.out.println("no new data on order itself");
		}
		
		if(orderData.getOrderItemsDtos()!=null) {
			Orders orderEdit = orderRepo.findById(orderId).orElseThrow(() -> new BadRequestException());
		
			String totalPrice = (orderData.getOrderItemsDtos()
					.stream()
					.map(item ->  item.getType()==0? item.getPrice()*item.getQuantity():item.getPrice())
					.reduce((float)0, Float::sum)).toString();
			orderEdit.setTotal(totalPrice);
			orderRepo.save(orderEdit);
		}
		
		List<ItemData> list = orderData.getOrderItemsDtos();
		List<Integer> itemIdList = list.stream().map(data -> data.getId()).collect(Collectors.toList());


		List<OrderItem> itemList = orderItemRepository.getAllByOrderIdAndItemIdIn(orderId, itemIdList);
		

		itemList.stream().forEach(orderItem -> {
		
			ItemData itemEdited = list.stream()
					.filter(item ->(item.getId()==orderItem.getItemId()) && (item.getOrderId()==orderItem.getOrderId()))
					.findFirst().orElseThrow(() -> new NotFoundException());
			OrderItemAddons orderItemAddons = orderItemAddonsRepository.findByOrderItemId(itemEdited.getOrderItemId());
			if(orderItemAddons!=null) {
				orderItemAddons.setAddonName(itemEdited.getQuantity());
				orderItemAddons.setAddonPrice(itemEdited.getPrice());
			}
			orderItem.setQuantity(itemEdited.getQuantity());
		});	
		
	
		orderItemRepository.saveAll(itemList);
		return orderSent;
	}


	@Override
	public void changeOrderStatus(Integer orderId, Integer orderStatus) {
		if(orderId==null || orderStatus==null) {
			throw new BadRequestException();
		}
		Orders orderEdit = orderRepo.findById(orderId).orElseThrow(()-> new NotFoundException());
		orderEdit.setOrderstatus_id(orderStatus);
		orderRepo.save(orderEdit);
	}


	@Override
	public List<ItemBriefDTO> getAllBriefDTOs() {
		List<ItemBriefDTO> list = orderItemRepository.findAllNewItems();
		return list;
	}

	public Boolean sendWhatsapp(String orderId) {
		
		
		  Orders order = orderRepo.findByUniqueOrderId(orderId); 
		  int userId =  order.getUser_id();
		 
		
		
		Users user = userRepo.findById(userId);
		Language language = null;
		if(user!=null&&user.getDefault_language()!=0) {
			
			language	 = translateRepositorySql.findById(user.getDefault_language());
		}else {
			language	 = translateRepositorySql.findByIsDefault(1);
		}
		
		String text = null;
    	if(language!=null) {
    		try {
    			LinkedHashMap languageText =  new JSONParser(language.getData()).parseObject();
    			
    			
				 text = languageText.get("whatsappMessage").toString();
				System.out.println(text);
			} catch (ParseException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
    	}
		
		if(text!=null) {
					WhatsappMessage.getInstance().sendWhatsappMessage(user.getPhone(),text);
		}
		return true;
	}

	@Override
	public Boolean setDefaultLanguageForUser(int userId, int languageId) {
		Users user = userRepo.findById(userId);
		
		return true;
	}
	@Override
	public String createPaymentMeshulam(String orderId) {
		// TODO Auto-generated method stub
		return null;
	}
	@Override
	public ItemDTO getItemById(int id) {
		List<Addons> addonsList = null;
		AddonCategoryItem addonCategoriesItem = null;
		AddonCategories addonCategories  = null;
		Item item  = itemRepository.findById(id);
		if(item.getPrice()==0) {
		addonCategoriesItem = addonCategoryItemRepository.findByItemId(item.getId());
		addonCategories = addonCategoriesRepository.findById(addonCategoriesItem.getAddonCategoryId());
		addonsList = addonsRepository.findAllByAddonCategoryId(addonCategoriesItem.getAddonCategoryId());
		}
		ItemDTO itemDTO =new ItemDTO(item,addonsList,addonCategories,addonCategoriesItem);
		return itemDTO;
	}



	



	



}
