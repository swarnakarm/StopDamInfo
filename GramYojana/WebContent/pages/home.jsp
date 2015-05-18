<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@page session="true"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<meta name="_csrf" content="${_csrf.token}"/>
<meta name="_csrf_header" content="${_csrf.headerName}"/>
<script type="text/javascript" src="../scripts/jquery.js"></script>
<script type="text/javascript" src="../scripts/jquery.json-2.4.js"></script>
<script type="text/javascript">

var isNewEntry = false;
var villageCode;
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
	if(docID == 'stateSelect'){
		$('#stateSelect').html('<option >Select </option>');
		$('#districtSelect').html('<option >Select </option>');
		$('#blockSelect').html('<option >Select </option>');
		$('#panchayatSelect').html('<option >Select </option>');
		$('#villageSelect').html('<option >Select </option>');
	} else if (docID == 'districtSelect'){
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
			console.log(gateInfo);
			villageCode = villageID;
			$('[name="Upload"]').prop("disabled",false);
			$('#fileInput').prop("disabled",false);
			if(gateInfo.openDate!=null && gateInfo.closeDate==null){
				var openDate = new Date(gateInfo.openDate);
				var openMonth = (openDate.getMonth()+1);
				$('#openDate').val(openDate.getFullYear()+"-"+(openMonth<10?"0"+openMonth:openMonth)+"-"+openDate.getDate());	
				$('#closeDate').prop("disabled",false);
				isNewEntry = false;			
			}
			else if(gateInfo.openDate!=null && gateInfo.closeDate!=null){
				$('#openDate').prop( "disabled", false );
				isNewEntry = true;
			}else {
				$('#openDate').prop( "disabled", false );
				isNewEntry = true;
			}
        }
    });
}

function insertOrUpdateGateInfo(){
	var header = $("meta[name='_csrf_header']").attr("content");
	var token = $("meta[name='_csrf']").attr("content");
	var action;
	if(isNewEntry){
		action = "../gateInfo/newEntry";
	} else{
		action = "../gateInfo/updateCloseGate";
	}
	var openDate = new Date($('#openDate').val());
	var closeDate = new Date($('#closeDate').val());
	if((openDate<closeDate && closeDate<=(new Date())) || isNewEntry){
		var formData = new FormData(document.forms.namedItem("GateInfo"));
		formData.append("villageCode",villageCode);
		 $.ajax({
			url: action,
			type: 'POST',
			data: formData,
			async: false,
			cache: false,
			contentType: false,
			processData: false,
			beforeSend: function(xhr) {
					xhr.setRequestHeader(header, token);
			},
			success: function (returndata) {
				alert(returndata);
				$('#openDate').val("");
				$('#closeDate').val("");
				$('#fileInput').after($('#fileInput').clone(true)).remove();
				$('#fileInput').prop("disabled",true);
				$('[name="Upload"]').prop("disabled",true);
				$('#openDate').prop("disabled",true);
				$('#closeDate').prop("disabled",true);
				getList('/getStateList','stateSelect');
			},
			error: function (xhr, ajaxOptions, thrownError) {
				alert('The File Size is greater then allowed.');
			  }
		});
	}else{
		if(openDate>closeDate){
			alert('Close Date cannot be earlier then Open Date');
		} else{
			alert('Close Date cannot be future date.');
		}
	}
}

function logout(paramName,token){
	var logoutForm = document.createElement('form');
	$('<input>').attr({type: 'hidden', name: paramName, value: token}).appendTo(logoutForm);
	logoutForm.action = "../j_spring_security_logout";
	logoutForm.method = "POST";
	logoutForm.submit();
}
</script>
<title>STOPDAM - GATE INFO</title>
</head>
<body onLoad="javascript:getList('/getStateList','stateSelect');">
<div style="text-align: right;">
	<c:if test="${pageContext.request.userPrincipal.name != null}">
		Welcome : ${pageContext.request.userPrincipal.name} 
		| <c:url var="logoutAction" value="/j_spring_security_logout"></c:url>
		<a href="javascript:logout('${_csrf.parameterName}','${_csrf.token}');">Logout</a>	
	</c:if>
</div>
<div style="text-align:center;"><h1>STOP DAM GATE INFORMATION</h1></div>
<div style="height: 600px;">
	<div style="width: 35%; float: left;border: solid 1px; height:100%;">
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
		</table>
	</form>
	</div>
	<div style="border: solid 1px; width:60%; overflow-x:scroll; height:100%;">
		<div style="height:5%; text-align: center;"><h2>Gate Information</h2></div>
			<form method="POST" enctype="multipart/form-data" name="GateInfo">
				<input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}" />
				<table style="text-align:center;">
					<tr>
						<td>Open Date: <span><input name="openDate" id="openDate" disabled=true type="date" /></span></td>
					</tr>
					<tr>
						<td>Close Date: <span><input name="closeDate" id="closeDate" disabled=true type="date" /></span></td>
					</tr>
					<tr>
						<td colspan=2><input id="fileInput" disabled=true type="file" name="file"></td>
					<tr>
						<td colspan=2><input type=button disabled=true name="Upload" value="Upload" onClick="javascript:insertOrUpdateGateInfo();"/></td>
					</tr>
				</table>
			</form>
	</div>
</div>
</body>
</html>