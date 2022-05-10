package dashboard.dao;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "addons")
@Getter
@Setter
public class Addons {
@Id
int id;
String name;
float price;
@Column(name = "addon_category_id")
int addonCategoryId;
int user_id;
Date created_at;
Date updated_at;
int is_active;

}
