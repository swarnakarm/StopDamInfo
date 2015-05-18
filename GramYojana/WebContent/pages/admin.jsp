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
<style>
tr.border_bottom td {
  border-bottom:1pt solid black;
}

tr.border_bottom th {
  border-bottom:1pt solid black;
}
</style>
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
	if(docID == 'stateSelect'){
		$('#stateSelect').html('<option >Select </option>');
		$('#districtSelect').html('<option >Select </option>');
		$('#blockSelect').html('<option >Select </option>');
		$('#panchayatSelect').html('<option >Select </option>');
		$('#villageSelect').html('<option >Select </option>');
	}else if (docID == 'districtSelect'){
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
	}
}

var reqStatus = false;
function showReports(){
	var reportBasedOn = $('input[name=reportBasedOn]:checked').val();
	var action = '../report';
	if(reportBasedOn == "0"){
		action += '/getBlockReport?blockCode='+ $('#blockSelect').val();
	} else {
		action += '/getPanchayatReport?panchayatCode='+ $('#panchayatSelect').val();
	}
	if(!reqStatus){
		reqStatus = true;
		alert('Processing Report request. It may take some time.');
		$.ajax({
			url: action,
			type: "GET",
			beforeSend: function(xhr) {
				xhr.setRequestHeader("Accept", "application/json");
				xhr.setRequestHeader("Content-Type", "application/json");
			},
			success: function(reportList) {
				$('#totalGates').html(reportList.totalGate);
				$('#openGates').html(reportList.openGate);
				for(var i=0;i<reportList.reportSet.length;i++) {
					var report = reportList.reportSet[i];
					$('#reportTable tr:last').after('<tr class="border_bottom" '+(report.openGates>0?'style="background-color: red;"':'')+'><td>'+report.location.loc_Name+'</td><td>'
							+ report.noOfGates + '</td><td>' + report.openGates +'</td></tr>');
				}
				reqStatus = false;	
			}
		});
	} else {
		alert('Request already in process.');
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
		<span>Welcome : ${pageContext.request.userPrincipal.name} 
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
					<td>Reports Based On:</td><td></td>
				</tr>
				<tr>
					<td><input type="radio" name="reportBasedOn" value="0" checked="checked">Block</input></td>
					<td><input type="radio" name="reportBasedOn" value="1">Panchayat</input></td>
				</tr>
				<tr>
					<td colspan=2 style="text-align:center;"><input type="button"  value="Show Reports" onClick="javascript:showReports();" /></td>
				</tr>
			</table>
		</form>
	</div>
	<div style="border: solid 1px; width:60%; overflow-x:scroll; height:100%;">
		<div style="height:5%; text-align: center;"><h2>Reports</h2></div>
		<table style="height:3%; padding: 0 0 0 0;">
			<tr>
				<td><b>Total Gates: </b></td><td id="totalGates"></td>
			</tr>
			<tr>
				<td><b>UnClose Gate: </b></td><td id="openGates"></td>
			</tr>
		</table>
		<table id='reportTable' style="text-align:center; height:80%;width: 100%;" >
			<tr class="border_bottom">
				<th>Panchayat/Village Name</th><th>Total Gate</th><th>UnClose Date</th>
			</tr>
		</table>
		</td>
		</tr>
	</div>
</div>
<div style="text-align: center">
	Created &amp; Designed by <a href="http://about.me/manish_swarnakar">Manish Swarnakar</a>
</div>
</body>
</html>