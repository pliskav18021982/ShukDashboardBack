package dashboard.dao;


import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "addon_categories")
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class AddonCategories {
@Id
	int id;
	String name;
	String type;
	int user_id;
	String description;
	int addon_limit;
	
}
