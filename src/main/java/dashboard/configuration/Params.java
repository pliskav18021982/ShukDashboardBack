package dashboard.configuration;


import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;

import lombok.Getter;

@Getter
@Configuration
public class Params {
	
	@Value("${default_PageNum}")
	int default_page_number;
	@Value("${default_ItemCount}")
	int default_items_count;
public void a() {}
}
