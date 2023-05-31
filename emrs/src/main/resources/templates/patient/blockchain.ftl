<#import "/spring.ftl" as spring />

<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<title>Hack Patient Blockchain</title>
	<link rel="stylesheet" type="text/css" href="<@spring.url '/css/common.css'/>" />
</head>

<body>
	<h1>Hacker View</h1>
	<h3>Patient (HKID: ${patientHKID}) - EMR Blockchain</h3>
	
	<#if fullChain?has_content>
		<#list fullChain as block>
			<table width="500">
				<tr>
					<td colspan="2" style="text-align: center;">
						<a href="<@spring.url '/patientblock/${block.hkid}/${block?index}'/>">Block ${block?index}</a>
					</td>
				</tr>
				<tr>
					<td width="200">HKID</td>
					<td>${block.hkid}</td>
				</tr>
				<tr>
					<td>Name</td>
					<td>${block.name}</td>
				</tr>
				<tr>
					<td>Sex</td>
					<td>${block.sex}</td>
				</tr>
				<tr>
					<td>Consultation Notes</td>
					<td>${block.consultationData!""}</td>
				</tr>
			</table>
			<br>
			<br>
		</#list>
	<#else>
		<div>No record found!</div>
	</#if>
</body>
</html>
