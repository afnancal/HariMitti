package com.afnan.harimitti.service;

import java.util.List;

import com.afnan.harimitti.model.Login;
import com.afnan.harimitti.model.Maintainer;
import com.afnan.harimitti.model.ReturnMsg;

public interface MaintainerService {

	public List<Maintainer> getListMaintainer();

	public List<Maintainer> findMaintainerByName(String name);
	
	public List<Maintainer> findMaintainerById(String maintainer_id);

	public Login loginMaintainer(Maintainer maintainer);

	public ReturnMsg createMaintainer(Maintainer maintainer);

	public ReturnMsg maintainerExist(String contact_no);

	public ReturnMsg updateMaintainerPassword(Maintainer maintainer);

	public ReturnMsg updateMaintainer(Maintainer maintainer);

	public ReturnMsg deleteMaintainer(String maintainer_id);

}
