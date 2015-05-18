package com.daminfo.controller;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.daminfo.common.util.AppLogger;
import com.daminfo.common.util.EnvironmentalConstants;
import com.daminfo.common.util.UIConstants;
import com.daminfo.service.GateInfoService;

@Controller
public class FileController {

	
	@Autowired(required=true)
	@Qualifier(value="gateInfoService")
	private GateInfoService gateInfoService;
	
	
	public void setGateInfoService(GateInfoService gateInfoService) {
		this.gateInfoService = gateInfoService;
	}
	
	
	@RequestMapping(value="/newEntry", method=RequestMethod.GET)
    public @ResponseBody String provideUploadInfo() {
        return "You can upload a file by posting to this same URL.";
    }

    @RequestMapping(value="/newEntry", method=RequestMethod.POST)
    public @ResponseBody String handleFileUpload(@RequestParam("villageCode") String villageCode, @RequestParam("openDate") String openDate,
            @RequestParam("file") MultipartFile file) throws Exception{
    	AppLogger.getLogger().debug("villageCode - "+villageCode);
    	AppLogger.getLogger().debug("openDate - "+openDate);
    	SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
    	Date strToDate = format.parse(openDate);
    	gateInfoService.insertNewGateEntry(Long.parseLong(villageCode), strToDate);
    	String fileName = UIConstants.OPEN_GATE_IMAGE;
        if (!file.isEmpty()) {
            try {
            	File pfile = new File(EnvironmentalConstants.getContextPath()
                		+File.separator+UIConstants.IMAGE_STORE_FOLDER+File.separator+villageCode+File.separator+openDate);
            	if(!pfile.exists()){
            		pfile.mkdirs();
            	}
            	
                byte[] bytes = file.getBytes();
                BufferedOutputStream stream =
                        new BufferedOutputStream(new FileOutputStream(new File(pfile,fileName)));
                stream.write(bytes);
                stream.close();
                return "Gate Information Updated Successfully!";
            } catch (Exception e) {
                return "Failed to upload " + fileName + " => " + e.getMessage();
            }
        } else {
            return "Failed to upload " + fileName + " because the file was empty.";
        }
    }
    
    
    @RequestMapping(value="/updateCloseGate", method=RequestMethod.POST)
    public @ResponseBody String handleCloseGate(@RequestParam("villageCode") String villageCode, 
    		@RequestParam("closeDate") String closeDate,  @RequestParam("file") MultipartFile file) throws Exception{
    	AppLogger.getLogger().debug("villageCode - "+villageCode);
    	AppLogger.getLogger().debug("closeDate - "+closeDate);
    	SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
    	Date strToDate = format.parse(closeDate);
    	gateInfoService.insertCloseDate(Long.parseLong(villageCode), strToDate);
    	String fileName = UIConstants.CLOSE_GATE_IMAGE;
        if (!file.isEmpty()) {
            try {
            	File pfile = new File(EnvironmentalConstants.getContextPath()
                		+File.separator+UIConstants.IMAGE_STORE_FOLDER+File.separator+villageCode+File.separator+closeDate);
            	if(!pfile.exists()){
            		pfile.mkdirs();
            	}
            	
                byte[] bytes = file.getBytes();
                BufferedOutputStream stream =
                        new BufferedOutputStream(new FileOutputStream(new File(pfile,fileName)));
                stream.write(bytes);
                stream.close();
                return "Gate Information Updated Successfully!";
            } catch (Exception e) {
                return "Failed to upload " + fileName + " => " + e.getMessage();
            }
        } else {
            return "Failed to upload " + fileName + " because the file was empty.";
        }
    }
}
