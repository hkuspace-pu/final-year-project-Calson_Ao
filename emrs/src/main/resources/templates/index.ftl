<#import "/spring.ftl" as spring />

<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<title>EMRS Homepage</title>
</head>

<body>
	<h1>Welcome to EMRS</h1>
	<p><a href="<@spring.url '/patient/list'/>">Patient Information</a></p>
</body>
</html>