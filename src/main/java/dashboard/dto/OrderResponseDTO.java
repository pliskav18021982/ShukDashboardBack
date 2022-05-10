package dashboard.dto;

import java.util.List;

public class OrderResponseDTO {
	OrderDTO order;
	UserOrderDTO user;
	List<ItemData> orderItemsDtos;
	public OrderResponseDTO(OrderDTO order, UserOrderDTO user, List<ItemData> orderItemsDtos) {
		super();
		this.order = order;
		this.user = user;
		this.orderItemsDtos = orderItemsDtos;
	}
	public OrderResponseDTO() {
		super();
	}
	public OrderDTO getOrder() {
		return order;
	}
	public void setOrder(OrderDTO order) {
		this.order = order;
	}
	public UserOrderDTO getUser() {
		return user;
	}
	public void setUser(UserOrderDTO user) {
		this.user = user;
	}
	public List<ItemData> getOrderItemsDtos() {
		return orderItemsDtos;
	}
	public void setOrderItemsDtos(List<ItemData> orderItemsDtos) {
		this.orderItemsDtos = orderItemsDtos;
	}
	@Override
	public String toString() {
		return "OrderResponseDTO [...orderItemsDtos=" + this.orderItemsDtos.toString() + "]";
	}
	
	
	
	
	
	
}
