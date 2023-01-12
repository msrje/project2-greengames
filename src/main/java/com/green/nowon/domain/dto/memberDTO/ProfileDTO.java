package com.green.nowon.domain.dto.memberDTO;

import com.green.nowon.domain.entity.member.ProfileEntity;

import lombok.Data;

@Data
public class ProfileDTO {
	
	private long pno;
	private String url;
	private String orgName;
	private String newName;
	private String plusImg;
	
	public ProfileDTO(ProfileEntity e) {
		this.pno=e.getPno();
		this.url=e.getUrl();
		this.orgName=e.getOrgName();
		this.newName=e.getNewName();
		this.plusImg=e.getUrl()+e.getNewName();
	}
}
