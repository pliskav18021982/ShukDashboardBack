package dashboard.dao;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "users")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class Users {
	@Id
	int id;
	@Column(name = "name")
	String userName;
	String email;
	String email_verified_at;
	String password;
	String remember_token; 
	String created_at;
	String updated_at; 
	String auth_token; 
	String phone;
	String second_phone;
	String default_address_id; 
	String delivery_pin;
	String delivery_guy_detail_id; 
	String avatar;
	@Column(name = "is_active")
	String userIs_active; 
	String tax_number;
	int default_language;
	
	
	
	
	
	
	
	
}
