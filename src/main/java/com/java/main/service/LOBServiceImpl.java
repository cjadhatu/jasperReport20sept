package com.java.main.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.java.main.dao.LOBDao;
import com.java.main.dto.LOBDefinationDTO;
import com.java.main.dto.LOBDefinationVO;

@Service
public class LOBServiceImpl implements LOBService {

	
  @Autowired	
  LOBDao lobDao; 
	
	
	
	public LOBDefinationDTO getLOBDefination() {
		// TODO Auto-generated method stub
		LOBDefinationDTO definationDTO=new LOBDefinationDTO();
		List<LOBDefinationVO> lobDefList=new ArrayList<LOBDefinationVO>();
		List<Object[]>  listLOBDef=lobDao.getLOBDefination();
		
		for(Object[] lobdef:listLOBDef)
		{
			LOBDefinationVO lobDefinationVO=new LOBDefinationVO();
			lobDefinationVO.setLobName((String) lobdef[0]);
			lobDefinationVO.setSublobName((String) lobdef[1]);
			lobDefinationVO.setForecastLobname((String) lobdef[2]);
			lobDefList.add(lobDefinationVO);
		}
		
		definationDTO.setDefinationVOs(lobDefList);
		
		return definationDTO;
	}

}
