package com.daminfo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.daminfo.common.json.Gate;
import com.daminfo.common.json.GateList;
import com.daminfo.common.json.LocationList;
import com.daminfo.service.GateInfoService;
import com.daminfo.service.LocationService;

@Controller
public class AppController {

	@Autowired(required=true)
	@Qualifier(value="locationService")
	private LocationService locationService;
	
	@Autowired(required=true)
	@Qualifier(value="gateInfoService")
	private GateInfoService gateInfoService;
	
	
	public void setLocationService(LocationService locationService) {
		this.locationService = locationService;
	}

	
	public void setGateInfoService(GateInfoService gateInfoService) {
		this.gateInfoService = gateInfoService;
	}
	
	@RequestMapping(value="/getStateList", method = RequestMethod.GET, headers = {"Accept=text/xml, application/json"}, produces="application/json")
    public @ResponseBody LocationList getState(){
		return locationService.getStateList();
    }
	
	@RequestMapping(value="/getDistrictList", method = RequestMethod.GET, headers = {"Accept=text/xml, application/json"}, produces="application/json")
	public @ResponseBody LocationList getDistrict(@RequestParam(required=true) Integer stateCode){
//		System.out.println("STATE CODE: "+stateCode);
		return locationService.getDistrictList(stateCode);
	}

	@RequestMapping(value="/getBlockList", method = RequestMethod.GET, headers = {"Accept=text/xml, application/json"}, produces="application/json")
	public  @ResponseBody LocationList getBlockList(@RequestParam(required=true) long districtCode ){
//		System.out.println("District Code: "+districtCode);
		return locationService.getBlockList(districtCode);
	}
	
	@RequestMapping(value="/getPanchayatList", method = RequestMethod.GET, headers = {"Accept=text/xml, application/json"}, produces="application/json")
	public  @ResponseBody LocationList getPanchayatList(@RequestParam(required=true) Integer blockCode ){
		return locationService.getPanchayatList(blockCode);
	}
	
	@RequestMapping(value="/getVillageList", method = RequestMethod.GET, headers = {"Accept=text/xml, application/json"}, produces="application/json")
	public  @ResponseBody LocationList getVillageList(@RequestParam(required=true) Integer panchayatCode ){
		return locationService.getVillageList(panchayatCode);
	}
	
	@RequestMapping(value="/getLastUpdateGateInfo", method = RequestMethod.GET, headers = {"Accept=text/xml, application/json"}, produces="application/json")
	public @ResponseBody Gate getLastGateInfo(@RequestParam(required=true) Long villageCode){
		return gateInfoService.getLastUpdateGate(villageCode);
	}
	
	@RequestMapping(value="/getAllGateInfo", method = RequestMethod.GET, headers = {"Accept=text/xml, application/json"}, produces="application/json")
	public @ResponseBody GateList getAllGateInfo(@RequestParam(required=true) Long villageCode){
		return gateInfoService.getGateInfoList(villageCode);
	}
}
