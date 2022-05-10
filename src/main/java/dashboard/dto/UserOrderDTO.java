package dashboard.dto;


public class UserOrderDTO {
	int id;
	String name;
	String email;
	String phone;
//	String secondPhone;
	String default_address_id;
    String delivery_pin;
    String delivery_guy_detail_id;
    String avatar;
    String is_active;
    String tax_number;
    
	public int getId() {
		return id;
}
//	public void setId(int id) {
//		this.id = id;
//	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getPhone() {
		return phone;
	}
	public void setPhone(String phone) {
		this.phone = phone;
	}
	public String getDefault_address_id() {
		return default_address_id;
	}
	public void setDefault_address_id(String default_address_id) {
		this.default_address_id = default_address_id;
	}
	public String getDelivery_pin() {
		return delivery_pin;
	}
	public void setDelivery_pin(String delivery_pin) {
		this.delivery_pin = delivery_pin;
	}
	public String getDelivery_guy_detail_id() {
		return delivery_guy_detail_id;
	}
	public void setDelivery_guy_detail_id(String delivery_guy_detail_id) {
		this.delivery_guy_detail_id = delivery_guy_detail_id;
	}
	public String getAvatar() {
		return avatar;
	}
	public void setAvatar(String avatar) {
		this.avatar = avatar;
	}
	public String getIs_active() {
		return is_active;
	}
	public void setIs_active(String is_active) {
		this.is_active = is_active;
	}
	public String getTax_number() {
		return tax_number;
	}
	public void setTax_number(String tax_number) {
		this.tax_number = tax_number;
	}
	
	
	public UserOrderDTO(int id, String name, String email, String phone,
//			String second_phone, 
			String default_address_id, String delivery_pin,
			String delivery_guy_detail_id, String avatar, String is_active, String tax_number) {
		super();
		this.id = id;
		this.name = name;
		this.email = email;
		this.phone = phone;
//		this.secondPhone = second_phone;
		this.default_address_id = default_address_id;
		this.delivery_pin = delivery_pin;
		this.delivery_guy_detail_id = delivery_guy_detail_id;
		this.avatar = avatar;
		this.is_active = is_active;
		this.tax_number = tax_number;
	}
	public UserOrderDTO() {
		super();
	}
    
    
}
