<#import "/spring.ftl" as spring />

<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<title>Edit Patient Information</title>
	<link rel="stylesheet" type="text/css" href="<@spring.url '/css/common.css'/>" />
</head>

<body>
	<h1>Edit Patient Information</h1>
	<p><a href="<@spring.url '/'/>">Back to home</a></p>
	
	<form name="edit-patient-form" action="<@spring.url '/patient/${patientBlock.hkid}/update'/>" method="POST">
		<input type="hidden" name="hkid" value="${patientBlock.hkid}">
		
		<table>
			<tr>
				<td><label for="displayOnlyHKID">HKID: </label></td>
				<td><input type="text" name="displayOnlyHKID" value="${patientBlock.hkid}" disabled="disabled"></td>
			</tr>
			
			<tr>
				<td><label for="name">Patient Name: </label></td>
				<td><input type="text" name="name" value="${patientBlock.name}"></td>
			</tr>
			
			<tr>
				<td><label for="sex">Sex: </label></td>
				<td><input type="text" name="sex" value="${patientBlock.sex}"></td>
			</tr>
			
			<tr>
				<td><label for="consultationData">Consultation Notes:</label></td>
				<td>
					<textarea name="consultationData" rows="30" cols="100">${patientBlock.consultationData!""}</textarea>
				</td>
			</tr>
		</table>
		
		<br>
		
		<input type="submit" value="Save">
	</form>
</body>
</html>