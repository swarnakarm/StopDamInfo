package com.daminfo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.daminfo.common.json.ReportList;
import com.daminfo.service.ReportService;

@Controller
public class ReportController {
	
	@Autowired(required=true)
	@Qualifier(value="reportService")
	private ReportService reportService = null;
	
	
	public void setReportService(ReportService reportService) {
		this.reportService = reportService;
	}

	@RequestMapping(value="/getBlockReport", method = RequestMethod.GET, headers = {"Accept=text/xml, application/json"}, produces="application/json")
    public @ResponseBody ReportList getBlockReport(@RequestParam(required=true)long blockCode){
		return reportService.getBlockReport(blockCode);
    }
	
	@RequestMapping(value="/getPanchayatReport", method = RequestMethod.GET, headers = {"Accept=text/xml, application/json"}, produces="application/json")
    public @ResponseBody ReportList getPanchayatReport(@RequestParam(required=true)long panchayatCode){
		return reportService.getPanchayatReport(panchayatCode);
    }
	
}
