package com.manish.misc;

import java.io.BufferedReader;
import java.io.InputStreamReader;

import org.apache.http.Header;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.HttpClientBuilder;

public class TestSpringResponse {

	public static void main(String argsp[]) throws Exception{
//		String url = "http://localhost:8080/DAMINFO/app/getStateList";
//		String url = "http://localhost:8080/DAMINFO/app/getBlockList?districtCode=1745";
//		String url = "http://localhost:8080/GramYojana/app/getPanchayatList?blockCode=1745005";
//		String url = "http://localhost:8080/GramYojana/app/getVillageList?panchayatCode=1745005004";
		String url = "http://localhost:8080/DAMINFO/app/getLastUpdateGateInfo?villageCode=1745001008001";
		HttpClient client = HttpClientBuilder.create().build();
		HttpGet request = new HttpGet(url);
	 
		// add request header
		request.addHeader("Accept","application/json");
//		request.addHeader("User-Agent", USER_AGENT);
		HttpResponse response = client.execute(request);
	 
		System.out.println("Response Code : " 
	                + response.getStatusLine().getStatusCode());
	 
		Header hdr[] = response.getAllHeaders();
		for(Header currHdr : hdr){
			System.out.println(currHdr.getName() + " - " + currHdr.getValue());
		}
		BufferedReader rd = new BufferedReader(
			new InputStreamReader(response.getEntity().getContent()));
	 
		StringBuffer result = new StringBuffer();
		String line = "";
		while ((line = rd.readLine()) != null) {
			result.append(line);
			
		}
		System.out.println(result);
	}
}
