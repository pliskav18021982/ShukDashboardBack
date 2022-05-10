package dashboard.service;

import java.util.List;

import dashboard.dto.ItemBriefDTO;
import dashboard.dto.ItemDTO;

public interface IOrderItems {
	
	public List<ItemBriefDTO> getAllBriefDTOs();
	public ItemDTO getItemById(int id);
}
