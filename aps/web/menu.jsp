<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@ page import="com.apceps.domain.UserCdo"%>
<%@ page import="com.apceps.domain.User"%>
<%@ page import="com.apceps.domain.Menu"%>
<%@ page import="com.apceps.domain.RoleMenu"%>
<%@ page import="java.util.List"%>
<%
UserCdo userCdo = (UserCdo) request.getSession().getAttribute("userCdo");
List<Menu> menuList = userCdo.getMenuList();
String context = request.getContextPath();
%>
<html lang="en">
<head>
<title>Access policy consolidation in event processing</title>
<link rel="stylesheet" type="text/css" href=" css/redmond/jquery-ui-1.10.4.css" />
<script type="text/javascript" src="js/jquery-1.10.2.js"></script>
<script type="text/javascript" src="js/jquery-ui-1.10.4.js"></script>
  <style>
    body { font-size: 62.5%; }
    label, input { display:block; }
    input.text { margin-bottom:12px; width:95%; padding: .4em; }
    fieldset { padding:0; border:0; margin-top:25px; }
    h1 { font-size: 1.2em; margin: .6em 0; }
    .ui-dialog .ui-state-error { padding: .3em; }
    .validateTips { border: 1px solid transparent; padding: 0.3em; }
 
  .demoHeaders {
		margin-top: 2em;
	}
  </style>
 <script>
  $(function() {
    $( "#menu" ).menu();
//	$( "#button" ).button();
  });
  </script>
  <style>
  .ui-menu { width: 100%;}
  </style>
</head>
<body>
 
  <ul id="menu">
  <% for(Menu menu : menuList) { %>
  <li><a href="<%=context+menu.getMenuUrl()%>" target="maincontent"><%=menu.getMenuName()%></a></li>
  <% } %>
 </ul> 
 
  
</body>
</html>