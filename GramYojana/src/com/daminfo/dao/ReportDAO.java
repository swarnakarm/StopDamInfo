package com.daminfo.dao;

import com.daminfo.common.json.ReportList;

public interface ReportDAO {

	ReportList getBlockReport(long blockCode);
	ReportList getPanchayatReport(long panchayatCode);
}
