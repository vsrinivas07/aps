<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@ page import="com.apceps.domain.UserCdo"%>
<%@ page import="com.apceps.domain.User"%>
<%@ page import="com.apceps.domain.Role"%>
<%@ page import="com.apceps.domain.Menu"%>
<%@ page import="com.apceps.domain.RoleMenu"%>


<%
	UserCdo userCdo = (UserCdo) request.getSession().getAttribute("userCdo");
	User user = userCdo.getUser();
	Role role = userCdo.getRole();
%>
<html>
<head>
<title>Access Policy Consolidation in Event Processing</title>
<link rel="stylesheet" type="text/css" href="css/redmond/jquery-ui-1.10.4.css" />
<script type="text/javascript" src="js/jquery-1.10.2.js"></script>
<script type="text/javascript" src="js/jquery-ui-1.10.4.js"></script>


<style>
body { font-size: 80%; 	background-color: #e8e8e8 }
label, input { display:block; }
input.text { margin-bottom:12px; width:95%; padding: .4em; }
fieldset { padding:0; border:0; margin-top:25px; }
h1 { font-size: 1.2em; margin: .6em 0; }
.ui-dialog .ui-state-error { padding: .3em; }
.validateTips { border: 1px solid transparent; padding: 0.3em; }


#icons {
		margin: 0;
		padding: 0;
	}
	#icons li {
		margin: 2px;
		position: relative;
		padding: 4px 0;
		cursor: pointer;
		float: left;
		list-style: none;
	}
	#icons span.ui-icon {
		float: left;
		margin: 0 4px;
	}
	.fakewindowcontain .ui-widget-overlay {
		position: absolute;
	}
 
#headerContent {
	position: absolute;
	top: 0;
	left: 0;
	border: 1px;
	height: 50px;
	width: 100%;
}
 	
#menuContent {
	position: absolute;
	top: 50px;
	left: 0;
	height: 800px;
	width: 190px;
	border: 0;
	border-right: 1px solid gray;
}

#maincontent {
	position: absolute;
	top: 50px;
	left: 200px;
	border: 0;
	height: 800px;
	width: 80%;
}

</style>

	<script>
	 var userRole = '<%=role.getRoleId()%>';
	 var userRoleName = '<%=role.getRoleName()%>';
	$(function() {
		// Hover states on the static widgets
		$( "#dialog-link, #icons li" ).hover(
			function() {
				$( this ).addClass( "ui-state-hover" );
			},
			function() {
				$( this ).removeClass( "ui-state-hover" );
			}
		);
		$('#homeIcon').click(function(){ 
			 document.forms[0].action = "home.jsp";
			 document.forms[0].submit(); 
		});
		$('#logoutIcon').click(function(){ 
		 document.forms[0].actionName.value = "logoff";
		 document.forms[0].submit(); 
		 });
});
	
	
	
	</script>

  
 </head>

<body>
	<form method="post" action="loginController">
	<div id="headerContent">
	<table width="100%">
	<tr>
		<td><h3>Welcome  <%=user.getUserName()%></h3></td>
		<td align="right">
		<ul id="icons" class="ui-widget ui-helper-clearfix">
			<li id="homeIcon" class="ui-state-default ui-corner-all" title="Home"><span class="ui-icon ui-icon-home"></span></li>
			<li class="ui-state-default ui-corner-all" title="Profile"><span class="ui-icon ui-icon-person"></span></li>
			<li class="ui-state-default ui-corner-all" title="Print"><span class="ui-icon ui-icon-print"></span></li>
			<li id="logoutIcon" class="ui-state-default ui-corner-all" title="Logout"><span class="ui-icon ui-icon-extlink"></span></li>
		</ul></td>
	</tr>
	</table>
 	</div>
		<iframe id="menuContent" src="menu.jsp"></iframe>
		<iframe id="maincontent" name="maincontent" src="main.jsp"></iframe>
		<input type="hidden" name="actionName" value="">
 	</form>
</body>

</html>
