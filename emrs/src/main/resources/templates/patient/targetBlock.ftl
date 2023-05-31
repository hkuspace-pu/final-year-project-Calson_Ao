<#import "/spring.ftl" as spring />

<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<title>Hack Patient Information</title>
	<link rel="stylesheet" type="text/css" href="<@spring.url '/css/common.css'/>" />
</head>

<body>
	<h1>Hack Patient Information</h1>
	
	<form name="hack-patient-form" action="<@spring.url '/hackPatient/${patientHKID}/${blockIndex}'/>" method="POST">
		<table>
			<tr>
				<td><label for="hkid">HKID: </label></td>
				<td><input type="text" name="hkid" value="${targetBlock.hkid}" disabled="disabled"></td>
			</tr>
			
			<tr>
				<td><label for="name">Patient Name: </label></td>
				<td><input type="text" name="name" value="${targetBlock.name}"></td>
			</tr>
			
			<tr>
				<td><label for="sex">Sex: </label></td>
				<td><input type="text" name="sex" value="${targetBlock.sex}"></td>
			</tr>
			
			<tr>
				<td><label for="consultationData">Consultation Notes:</label></td>
				<td>
					<textarea name="consultationData" rows="30" cols="100">${targetBlock.consultationData!""}</textarea>
				</td>
			</tr>
		</table>
		
		<br>
		
		<input type="submit" value="Save">
	</form>
</body>
</html>