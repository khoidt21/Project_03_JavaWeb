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
							if (session.getAttribute("isNew") != null) {
								if ((boolean) session.getAttribute("isNew")) {
									session.setAttribute("isNew", false);
						%>
						<h3>
							Welcome to the Board,
							<%=user.getUsername()%></h3>
						<p style="padding-bottom: 10px">Compile Bootstrap with your
							own asset pipeline by downloading our source Sass, JavaScript,
							and documentation files. This option requires some additional
							tooling:</p>
						<%
							} else {
						%>
						<h3>
							Welcome back,
							<%=user.getUsername()%></h3>
						<p style="padding-bottom: 10px">Compile Bootstrap with your
							own asset pipeline by downloading our source Sass, JavaScript,
							and documentation files. This option requires some additional
							tooling:</p>
						<%
							}
							}
						%>
					</div>
					<div class="control-group">
						<div
							class="form-group floating-label-form-group controls mb-0 pb-2"
						>
							<p>This section provides detailed information on key.</p>
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
