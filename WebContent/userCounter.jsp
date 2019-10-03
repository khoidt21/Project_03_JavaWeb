<%@ include file="header.jsp"%>
<%@page import="com.org.entity.User"%>
<%@page import="com.org.model.UsersData"%>
<!-- Contact Section -->
<section class="page-section" id="contact">
	<div class="container">
		<!-- Contact Section Form -->
		<div class="row">
			<div class="col-lg-8 mx-auto" style="margin-top: 20px">
				<div class="control-group">
					<div class="control-group">
						<%
							UsersData userdata = (UsersData) application.getAttribute("users");
						%>
						<h1>
							<b>Accounts Online</b>
						</h1>
						<%--Hien thi so User dang ket noi toi Client--%>
						<%
							if (userdata != null) {
						%>
						<p>
							Total Accounts Online:
							<%=userdata.getUsersOnline().size()%></p>
						<h3>All Accounts:</h3>
						<p><%=userdata.usernamesToString()%>
						</p>
						<%
							}
						%>
					</div>
					<div class="control-group">
						<div
							class="form-group floating-label-form-group controls mb-0 pb-2"
						>
							<p></p>
						</div>
					</div>
				</div>
			</div>
		</div>
	</div>
</section>
<%@ include file="footer.jsp"%>
</body>
<style>
.floating-label-form-group label {
	opacity: 1 !important;
}

.bg-secondary {
	background-color: #000 !important;
}

.copyright {
	background-color: #000 !important;
}

.footer {
	background-color: rgba(5, 42, 62, 1) !important;
}
</style>
</html>
</html>