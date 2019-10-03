<%@ include file="header.jsp" %>
<!-- Contact Section -->
	<section class="page-section" id="contact">
		<div class="container">
			
			<!-- Contact Section Form -->
			<div class="row">
				<div class="col-lg-8 mx-auto" style="margin-top: 20px">
					<!-- To configure the contact form email address, go to mail/contact_me.php and update the email address in the PHP file on line 19. -->
					 <p><font color="RED">${user.errorToString()}</font></p>
					<form name="form1" id="form1" action="SignupController" method="POST">
						<div class="control-group">
							<h5 style="color: red;font-weight: bold;">SIGN-UP</h5>
						</div>
						<div class="control-group">
							<label>Name</label>
						</div>
						<div class="control-group">
							<div
								class="form-group floating-label-form-group controls mb-0 pb-2"
							>
								<input class="form-control" id="username" type="text"
									name="username" value="${user.getUsername()}">
							</div>
						</div>
						<div class="control-group">
							<label>Password</label>
						</div>
						<div class="control-group">
							<div class="form-group floating-label-form-group controls mb-0 pb-2">
								<input class="form-control" id="password" type="password" name="password" ${user.getPassword()}>								
							</div>
						</div>
						<div class="control-group">
							<p></p>
						</div>
						
						<div id="success"></div>
						<div class="form-group">
							<button type="submit" class="btn btn-primary btn-sm"
								id="sendMessageButton"
							>Submit</button>
							<button type="button" class="btn btn-danger btn-sm" onClick="javascript:window.location='login.jsp'">Cancel</button>
						</div>
					</form>
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
.bg-secondary{
	background-color: #000 !important;
}
.copyright{
	background-color: #000 !important;
} 
.footer{
	background-color: rgba(5,42,62,1) !important;

}
</style>
</html>
