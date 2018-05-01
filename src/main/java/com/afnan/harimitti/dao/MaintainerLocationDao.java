package com.afnan.harimitti.dao;

import java.util.List;

import com.afnan.harimitti.model.MaintainerLocation;
import com.afnan.harimitti.model.ReturnMsg;

public interface MaintainerLocationDao {

	public List<MaintainerLocation> findMaintainerLocationById(String maintainer_id);

	public ReturnMsg createMaintainerLocation(MaintainerLocation maintainerLocation);

	public ReturnMsg maintainerLocationExist(String maintainer_id);

	public ReturnMsg deleteMaintainerLocation(String maintainer_id);

}
