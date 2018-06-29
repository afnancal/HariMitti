package com.afnan.harimitti.service;

import java.util.List;

import com.afnan.harimitti.model.MaintainerAllotment;
import com.afnan.harimitti.model.ReturnMsg;

public interface MaintainerAllotmentService {

	public List<MaintainerAllotment> getListMaintainerAllotment();

	public List<MaintainerAllotment> findMaintainerAllotmentByMainId(String maintainer_id);

	public List<MaintainerAllotment> findTodaysMainAllotByMainId(String maintainer_id, String date);

	public List<MaintainerAllotment> findPreviousMainAllotByMainId(String maintainer_id, String date);

	public List<MaintainerAllotment> findComingMainAllotByMainId(String maintainer_id, String date);

	public List<MaintainerAllotment> findMaintainerAllotmentByMembId(String member_id);

	public ReturnMsg createMaintainerAllotment(MaintainerAllotment maintainerAllotment);

	public ReturnMsg updateMaintainerAllotment(MaintainerAllotment maintainerAllotment);

	public ReturnMsg maintainerAllotmentExist(String maintainer_id);

}
