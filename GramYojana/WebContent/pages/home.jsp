<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@page session="false"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<script type="text/javascript" src="../scripts/jquery.js"></script>
<script type="text/javascript" src="../scripts/jquery.json-2.4.js"></script>
<script type="text/javascript">


function readList(location,docID){
    var locations = location.locations;
	var options = "";
	for(var i=0;i<locations.length;i++){
		data = locations[i];
		options += "<option value=\""+data.loc_code+"\">"+data.loc_Name+"</option>";
	}
	$('#'+docID).append(options);
}

function getList(locationType,docID){
	clearRemaining(docID);
	$.ajax({
        url: "../app"+locationType,
        type: "GET",
        beforeSend: function(xhr) {
            xhr.setRequestHeader("Accept", "application/json");
            xhr.setRequestHeader("Content-Type", "application/json");
        },
        success: function(location) {
        	readList(location,docID);
        }
    });
}

function clearRemaining(docID){
	if(docID == 'stateSelect' || docID == 'districtSelect'){
		$('#districtSelect').html('<option >Select </option>');
		$('#blockSelect').html('<option >Select </option>');
		$('#panchayatSelect').html('<option >Select </option>');
		$('#villageSelect').html('<option >Select </option>');
	}else if(docID == 'blockSelect'){
		$('#blockSelect').html('<option >Select </option>');
		$('#panchayatSelect').html('<option >Select </option>');
		$('#villageSelect').html('<option >Select </option>');
	}else if(docID == 'panchayatSelect'){
		$('#panchayatSelect').html('<option >Select </option>');
		$('#villageSelect').html('<option >Select </option>');
	}else if(docID == 'villageSelect'){
		$('#villageSelect').html('<option >Select </option>');
	}
}


function insertNew(){
	var villageID = $("#villageSelect").val();
	$.ajax({
        url: "../app/getLastUpdateGateInfo?villageCode="+villageID,
        type: "GET",
        beforeSend: function(xhr) {
            xhr.setRequestHeader("Accept", "application/json");
            xhr.setRequestHeader("Content-Type", "application/json");
        },
        success: function(gateInfo) {
            //console.log(gateInfo);   
			if(gateInfo.openDate!=null && gateInfo.closeDate==null){
				var openDate = new Date(gateInfo.openDate);
				//var closeDate = new Date(gateInfo.closeDate);
				var openMonth = (openDate.getMonth()+1);
				//var closeMonth = (closeDate.getMonth()+1);
				$('#openDate').val(openDate.getFullYear()+"-"+(openMonth<10?"0"+openMonth:openMonth)+"-"+openDate.getDate());	
				//$('#closeDate').val(closeDate.getFullYear()+"-"+(closeMonth<10?"0"+closeMonth:closeMonth)+"-"+closeDate.getDate());				
			}
			else if(gateInfo.openDate!=null && gateInfo.closeDate!=null){
				$('#openDate').prop( "disabled", false );
				$('#closeDate').prop("disabled",false);
				//$('#openDate').css("disabled","false");
				//$('#closeDate').css("disabled","false");
			}
        }
    });
	return false;
}
</script>
<title>DAM - GATE INFO</title>
</head>
<body onLoad="javascript:getList('/getStateList','stateSelect');">
<div style="text-align:center;"><h1>STOP DAM GATE INFORMATION</h1></div>
<table style="width: 100%; height:100%; min-width:800px; min-height: 600px;">
<tr>
	<td style="padding:0 0 0 0; min-height:780px;  border:solid 1px;">
	<form name="LocationInfo">
		<table style="text-align: center; height:100%; width:100%;">
			<tr>
				<td>State:</td><td><select id="stateSelect" style="width:110px;" onChange="javascript:getList('/getDistrictList?stateCode='+this.value,'districtSelect');"><option >Select </option></select></td>
			</tr>
			<tr>
				<td>District:</td><td><select id="districtSelect" style="width:110px;" onChange="javascript:getList('/getBlockList?districtCode='+this.value,'blockSelect');"><option >Select </option></select></td>
			</tr>
			<tr>
				<td>Block:</td><td><select id="blockSelect" style="width:110px;" onChange="javascript:getList('/getPanchayatList?blockCode='+this.value,'panchayatSelect');"><option >Select </option></select></td>
			</tr>
			<tr>
				<td>Panchayat:</td><td><select id="panchayatSelect" style="width:110px;" onChange="javascript:getList('/getVillageList?panchayatCode='+this.value,'villageSelect');"><option >Select </option></select></td>
			</tr>
			<tr>
				<td>Village:</td><td><select id="villageSelect" style="width:110px;" ><option >Select </option></select></td>
			</tr>
			<tr></tr>
			<tr>
				<td colspan=2 style="text-align:center;"><input type="button" value="Insert Open/Close Date" onClick="javascript:insertNew();" /></td>
			</tr>
			<tr>
				<td colspan=2 style="text-align:center;"><input type="button"  value="Show History" onClick="javascript:displayLast();" /></td>
			</tr>
			<!-- <input type=submit value="Insert GateInfo" /></td><td><input type=submit value="Display Info" /></td>
			</tr>-->
		</table>
	</form>
	</td>
	<td style="border: solid 1px; width:65%; min-height:780px;">
	<form >
	<table style="text-align:center;">
		<tr>
			<td><h2>Gate Information</h2></td>
		</tr>
		<tr>
			<td>Open Date: <span><input id="openDate" disabled=true type="date" /></span></td>
		</tr>
		<tr>
			<td>Close Date: <span><input id="closeDate" disabled=true type="date" /></span></td>
		</tr>
		<tr>
			<td colspan=2><input type=button disabled=true value="Insert"/></td>
		</tr>
	</table>
	</form>
	</td>
	</tr>
	</table>
</body>
</html>