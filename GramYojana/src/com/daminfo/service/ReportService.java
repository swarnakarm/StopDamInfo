package com.daminfo.service;

import com.daminfo.common.json.ReportList;

public interface ReportService {

	ReportList getBlockReport(long blockCode);
	ReportList getPanchayatReport(long panchayatCode);
}
