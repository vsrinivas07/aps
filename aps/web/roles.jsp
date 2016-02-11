<%@ page language="java" contentType="text/html; charset=ISO-8859-1"  pageEncoding="ISO-8859-1"%>
<%@ page import="java.util.List"%>
<%@ page import="java.util.ArrayList"%>
<%@ page import="java.util.Map"%>
<%@ page import="java.util.Map.Entry"%>
<%@ page import="com.apceps.domain.Role"%>
<%@ page import="com.apceps.domain.RoleMenu"%>
<%@ page import="com.apceps.domain.User"%>
<%@ page import="com.apceps.util.StringUtil"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%
 
 String message = (String)request.getAttribute("message");

 List<Role> roleList = (List<Role>)request.getAttribute("roleList");

 if(roleList == null) roleList = new ArrayList<Role>();

 List<RoleMenu> roleMenuList = (List<RoleMenu>)request.getAttribute("roleMenuList");

 if(roleMenuList == null) roleMenuList = new ArrayList<RoleMenu>();

 List<User> userList = (List<User>)request.getAttribute("userList");

 if(userList == null) userList = new ArrayList<User>();

 
 String tabName = request.getParameter("tabName");

 Role role = (Role)request.getAttribute("role");

 if(role == null ) role = new Role();
 
 Map<Long,String> menuMap = (Map<Long,String>)request.getAttribute("menuMap");

 
 %>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>menus</title>
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
  </style>
  

<script type='text/javascript'>

var tabName  = "<%=tabName%>";

var menuListSize = "<%=roleList.size()%>";

$(function() {
	 $( "#tabs" ).tabs();
	
	$( "#create-role" )
	.button()
	.click(function() {
		 document.forms[0].actionName.value = "details";
		 document.forms[0].submit();	
	});
	
	$( "#save" )
	.button()
	.click(function() {
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
			if(menuListSize>0) return ;
			document.forms[0].actionName.value = "list";
			document.forms[0].submit();	
		});

		
});


function loadDetails(roleId){
	 document.forms[0].roleId.value = roleId;
	 document.forms[0].actionName.value = "details";
 	 document.forms[0].submit();
} 
  
</script>

</head>
<body>
<form method="post" action="adminController">
 <div id="tabs">
  <ul>
    <li id="listTabLi"><a href="#listTab">Role List</a></li>
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

  <table id="menus" class="ui-widget ui-widget-content">
    <thead>
      <tr class="ui-widget-header ">
        <th>Name</th>
        <th>URL</th>
      </tr>
    </thead>
    <tbody>
	<% for(Role rle : roleList) {%>
      <tr>
       <td><a href="javascript:loadDetails('<%=rle.getRoleId()%>');"><%=rle.getRoleName()%></a>
       <td><%=rle.getRoleDesc()%></td>
      </tr>
	<%} %>
  
  <tr><td><button id="create-role">Create new role</button></td></tr>
 
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
    <input type="text" name="roleName" id="roleName" value='<%=role.getRoleName()==null?"":role.getRoleName()%>' class="text ui-widget-content ui-corner-all" maxlength=40 size=40>
    <label for="roleDesc">Description</label>
    <input type="text" name="roleDesc" id="roleDesc" value='<%=role.getRoleDesc()==null?"":role.getRoleDesc()%>' class="text ui-widget-content ui-corner-all" maxlength=100 size=100>
  </fieldset>
 
   <button id="save">Save</button>
   <button id="delete">Delete</button>
 
    <% if(role.getRoleId()!=null){ %>
   
   <div class="ui-state-highlight ui-corner-all" style="margin-top: 20px; padding: 0 .7em;">
   <p>Role Menus</p>
   </div>
   
   <p>  </p>
   
   <table id="menuRoles" class="ui-widget ui-widget-content">
    <thead>
      <tr class="ui-widget-header ">
         <th>Menu</th> <th>Add</th><th>Modify</th><th>Delete</th>
      </tr>
    </thead>
    <tbody>
	<% for(RoleMenu rm : roleMenuList) {%>
      <tr>
       <td><a href="javascript:loadDetails('<%=rm.getRolemenuId()%>');"><%=menuMap.get(rm.getMenuId())%></a>
       <td><%=rm.isAddAllowed()%></td><td><%=rm.isModifyAllowed()%></td><td><%=rm.isDeleteAllowed()%></td>
      </tr>
	<%} %>
  
     <tr><td><button id="create-menu-role">Add new</button></td></tr>
    </tbody>
  </table>


<div class="ui-state-highlight ui-corner-all" style="margin-top: 20px; padding: 0 .7em;">
   <p>Users</p>
   </div>
   
   
   <table id="menuRoles" class="ui-widget ui-widget-content">
    <thead>
      <tr class="ui-widget-header ">
         <th>User Name</th><th>Email</th> 
      </tr>
    </thead>
    <tbody>
	<% for(User user: userList) {%>
      <tr>
       <td><a href="javascript:loadDetails('<%=user.getUserId()%>');"><%=user.getUserName()%></a>
       <td><%=user.getEmail()%></td> 
      </tr>
	<%} %>
  
     <tr><td><button id="create-user">Add new</button></td></tr>
    </tbody>
  </table>


  <%} %>
   
 
  </div>
 </div>

	<input type="hidden" name="actionName" value="">
	<input type="hidden" name="roleId" value="<%=role.getRoleId()==null?"":role.getRoleId()%>">
	<input type="hidden" name="module" value="roles">
 

</form>
</body>
</html>