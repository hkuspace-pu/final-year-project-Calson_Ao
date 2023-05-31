<#import "/spring.ftl" as spring />

<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<title>Patient Information</title>
	<link rel="stylesheet" type="text/css" href="<@spring.url '/css/common.css'/>" />
</head>

<body>
	<h1>Patient Information</h1>
	<p><a href="<@spring.url '/'/>">Back to home</a></p>
	
	<form name="create-patient-form" action="<@spring.url '/patient/create'/>" method="POST">
		<label for="name">Patient Name: </label>
		<input type="text" name="name">
		&nbsp; &nbsp; &nbsp; 
		
		<label for="sex">Sex: </label>
		<select name="sex">
			<option value="M">M</option>
			<option value="F">F</option>
		</select>
		&nbsp; &nbsp; &nbsp; 
		
		<label for="hkid">HKID: </label>
		<input type="text" name="hkid">
		&nbsp; &nbsp; &nbsp; 
		
		<input type="submit" value="Create">
	</form>
	
	<hr>
	
	<table>
		<tr>
			<td>Patient (HKID)</td>
			<td>Valid</td>
		</tr>
		
		<#list patientList as patient>
			<tr>
				<td>
					<a href="<@spring.url '/patient/${patient.hkid}/detail'/>">${patient.hkid}</a>
				</td>
				<td>
					<#if patient.valid>
						Y
					<#else>
						N
					</#if>
				</td>
			</tr>
		</#list>
	</table>
</body>
</html>
