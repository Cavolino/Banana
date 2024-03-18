<head>
<title><%=pageContext.getServletContext().getInitParameter("titolo")%></title>
</head>
<body>
	<jsp:include page="/fragments/navbar.jsp"></jsp:include>
	<div>
		<h2>Cambia la passowrd</h2>
		<%
		if (request.getAttribute("success") != null) {
			boolean success = (boolean) request.getAttribute("success");
			if (success) {
		%>
		<div>Hai modificato la password con successo!</div>
		<%
		} else {
		%>
		<div><%=(String) request.getAttribute("error")%>!
		</div>
		<%
		}
		}
		%>
		<form action="<%=request.getContextPath()%>/changePassword"
			method="post">
			<label> Old Password: <input type="password" name="password" />
			</label><br /> <label> New Password: <input type="password"
				name="newPassword" />
			</label><br /> <input type="submit" />
		</form>
	</div>
</body>
