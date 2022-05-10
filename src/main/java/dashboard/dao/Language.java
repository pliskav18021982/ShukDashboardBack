package dashboard.dao;

import java.sql.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "translations")
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class Language {
	@Id
	int id;
	String language_name;
	String data;
	@Column(name = "is_default")
	int isDefault;
	int is_active;
}
