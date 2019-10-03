<%@ include file="header.jsp"%>
<%@page import="com.org.entity.User"%>
<!-- Contact Section -->
<section class="page-section" id="contact">
	<div class="container">
		<!-- Contact Section Form -->
		<div class="row">
			<div class="col-lg-8 mx-auto" style="margin-top: 20px">
				<div class="control-group">
					<div class="control-group">
						<%
							User user = (User) session.getAttribute("userNow");
						%>
						<h3 style="font-size: 25px">Your Profile</h3>
						<div>
							<%
								if (user != null) {
							%>
							<p>
								Username:
								<%=user.getUsername()%></p>
							<p>
								Password:
								<%=user.getPassword()%></p>
							<%
								}
							%>
						</div>
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