package com.java.main.dao;

import java.util.List;

import com.java.main.dto.BranchInputVO;
import com.java.main.dto.CountryInputVO;

public interface AutoSubmissionDao {
	public String BranchAutoSubmission(String branchAutoQuery);
	public String CountryAutoSubmission(String branchAutoQuery);
	public String SubRegionAutoSubmission(String branchAutoQuery);
	public String RegionAutoSubmission(String branchAutoQuery);
	public String MounthlyDataPulling(String branchAutoQuery);
	public String AdminHQAutoSubmission(String branchAutoQuery);
	public String BranchAutoSubmissionTemp(String branchAutoQuery);
	}
