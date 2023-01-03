package com.green.nowon.service;

import com.green.nowon.domain.dto.memberDTO.AddressInsertDTO;
import com.green.nowon.domain.dto.memberDTO.MemberInsertDTO;

public interface MemberService {
	void save(MemberInsertDTO mdto,AddressInsertDTO adto);
}
