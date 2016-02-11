<%@ page language="java" contentType="text/html; charset=ISO-8859-1"  pageEncoding="ISO-8859-1"%>
<%@ page import="java.util.List"%>
<%@ page import="java.util.ArrayList"%>
<%@ page import="java.util.Map"%>
<%@ page import="java.util.Map.Entry"%>
<%@ page import="com.apceps.domain.User"%>
<%@ page import="com.apceps.util.StringUtil"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%
 
 String message = (String)request.getAttribute("message");

 List<User> userList = (List<User>)request.getAttribute("userList");

 if(userList == null) userList = new ArrayList<User>();
 
 String tabName = request.getParameter("tabName");

 User user = (User)request.getAttribute("user");

 if(user == null ) user = new User();
 
 Map<Long,String> roleMap = (Map<Long,String>)request.getAttribute("roleMap");
  
 %>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Users</title>
<link rel="stylesheet" type="text/css" href="css/redmond/jquery-ui-1.10.4.css" />
<script type="text/javascript" src="js/jquery-1.10.2.js"></script>
<script type="text/javascript" src="js/jquery-ui-1.10.4.js"></script>
<script type="text/javascript" src="js/apscombobox.js"></script>



 <style>
    body { font-size: 62.5%; }
    label, input { display:block; }
    input.text { margin-bottom:12px;   padding: .4em; }
    fieldset { padding:0; border:0; margin-top:25px; }
    h1 { font-size: 1.2em; margin: .6em 0; }
    .ui-dialog .ui-state-error { padding: .3em; }
    .validateTips { border: 1px solid transparent; padding: 0.3em; }
    
	.custom-combobox {
		position: relative;
		display: inline-block;
	}
	.custom-combobox-toggle {
		position: absolute;
		top: 0;
		bottom: 0;
		margin-left: -1px;
		padding: 0;
		/* support: IE7 */
		*height: 1.7em;
		*top: 0.1em;
	}
	.custom-combobox-input {
		margin: 0;
		padding: 0.3em;
	}
		      
  </style>
  

<script type='text/javascript'>

var tabName  = "<%=tabName%>";

var userListSize = "<%=userList.size()%>";
 
$(function() {
	$( "#tabs" ).tabs();
	
	$( "#create-user" )
	.button()
	.click(function() {
		 document.forms[0].actionName.value = "details";
		 document.forms[0].submit();	
	});
	
	$( "#save" )
	.button()
	.click(function() {
		document.forms[0].roleId.value = document.forms[0].roleIdSelect.value;
		document.forms[0].actionName.value = "save";
		document.forms[0].submit();	
      });
 
	$( "#delete" )
	.button()
	.click(function() {
		 document.forms[0].actionName.value = "delete";
		 document.forms[0].submit();	
	});
	if(tabName=='details') {
		$( "#tabs").tabs({ active: 1 }); 
	}else {
		$( "#tabs").tabs({ active: 0 });
	}
	
 	
	$("#listTabLi").on("click", function() {
		if(userListSize>0) return ;
		document.forms[0].actionName.value = "list";
		document.forms[0].submit();	
	});
 
	$( "#roleIdSelect" ).combobox();   

 
 });
 
function loadDetails(userId){
	 document.forms[0].userId.value = userId;
	 document.forms[0].actionName.value = "details";
 	 document.forms[0].submit();
} 
  
</script>

</head>
<body>
<form method="post" action="adminController">
<h3></h3>




<div id="tabs">
  <ul>
    <li id="listTabLi"><a href="#listTab">User List</a></li>
    <li><a href="#detailTab">Details</a></li>
 </ul>
  <div id="listTab">
  
	   <%if(!StringUtil.isEmpty(message)) { %>
	<div class="ui-widget">
		<div class="ui-state-highlight ui-corner-all" style="margin-top: 20px; padding: 0 .7em;">
			<p><span class="ui-icon ui-icon-info" style="float: left; margin-right: .3em;"></span>
			<%=message!=null?message:""%></p>
		</div>
	</div>
	<%} %>

  <table id="users" class="ui-widget ui-widget-content">
    <thead>
      <tr class="ui-widget-header ">
        <th>User</th>
        <th>Role</th>
        <th>Email</th>
      </tr>
    </thead>
    <tbody>
	<% for(User usr : userList) {%>
      <tr>
       <td><a href="javascript:loadDetails('<%=usr.getUserId()%>');"><%=usr.getUserName()%></a>
       <td><%=roleMap.get(usr.getRoleId())%></td>
       <td><%=usr.getEmail()%></td>
      </tr>
	<%} %>
  
  <tr><td><button id="create-user">Create new user</button></td></tr>
 
    </tbody>
  </table>
 
  </div>
  <div id="detailTab">
    <%if(!StringUtil.isEmpty(message)) { %>
	<div class="ui-widget">
		<div class="ui-state-highlight ui-corner-all" style="margin-top: 20px; padding: 0 .7em;">
			<p><span class="ui-icon ui-icon-info" style="float: left; margin-right: .3em;"></span>
			<%=message!=null?message:""%></p>
		</div>
	</div>
	<%} %>
  <fieldset>
    <label for="name">Name</label>
    <input type="text" name="userName" id="userName" value='<%=user.getUserName()==null?"":user.getUserName()%>' class="text ui-widget-content ui-corner-all" maxlength=40 size=40>
    <label for="password">Password</label>
    <input type="password" name="userPassword" id="userPassword" value='<%=user.getUserPassword()==null?"":user.getUserPassword()%>' class="text ui-widget-content ui-corner-all" maxlength=40 size=40>
    
    
   <label for="role">Role</label>
   <select id="roleIdSelect">
   <option value="">Select one...</option>
   <%
   for(Entry<Long,String> entry : roleMap.entrySet()){
	Long roleId = entry.getKey();	
	String roleName = entry.getValue();
	String selectedStr = user.getRoleId()==null ? "" : user.getRoleId().longValue() == roleId.longValue() ? "selected" : "";  
	%>
    <option value='<%=roleId%>' <%=selectedStr%>><%=roleName%></option>
    <%} %>
  </select>
  <input type="hidden" id="roleId" name="roleId" value="<%=user.getRoleId()==null?"":user.getRoleId()%>">
	    
    
    <label for="email">Email</label>
    <input type="text" name="email" id="email" value='<%=user.getEmail()==null?"":user.getEmail()%>' class="text ui-widget-content ui-corner-all" maxlength=100 size=100>
    
    
  </fieldset>
 
   <button id="save">Save</button>
   <button id="delete">Delete</button>
 
  </div>
 </div>

	<input type="hidden" name="actionName" value="">
	<input type="hidden" name="userId" value="<%=user.getUserId()==null?"":user.getUserId()%>">
	<input type="hidden" name="module" value="users">
 

</form>
</body>
</html>