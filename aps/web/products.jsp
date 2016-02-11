<%@ page language="java" contentType="text/html; charset=ISO-8859-1"  pageEncoding="ISO-8859-1"%>
<%@ page import="java.util.List"%>
<%@ page import="java.util.ArrayList"%>
<%@ page import="java.util.Map"%>
<%@ page import="java.util.Map.Entry"%>
<%@ page import="com.apceps.domain.Product"%>
<%@ page import="com.apceps.util.StringUtil"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%
 
 String message = (String)request.getAttribute("message");

 List<Product> productList = (List<Product>)request.getAttribute("productList");

 if(productList == null) productList = new ArrayList<Product>();
 
 String tabName = request.getParameter("tabName");

 Product product = (Product)request.getAttribute("product");

 if(product == null ) product = new Product();

 Map<Long,String> userMap = (Map<Long,String>)request.getAttribute("userMap");
 
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
  </style>
  

<script type='text/javascript'>

var tabName  = "<%=tabName%>";

var productListSize = "<%=productList.size()%>";

$(function() {
	$( "#tabs" ).tabs();
	
	$( "#create-product" )
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
			if(productListSize>0) return ;
			document.forms[0].actionName.value = "listAll";
			document.forms[0].submit();	
		});

		
});


function loadDetails(productId){
	 document.forms[0].productId.value = productId;
	 document.forms[0].actionName.value = "details";
 	 document.forms[0].submit();
} 
  
</script>

</head>
<body>
<form method="post" action="productController">
<h3></h3>




<div id="tabs">
  <ul>
    <li id="listTabLi"><a href="#listTab">Product List</a></li>
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

  <table id="products" class="ui-widget ui-widget-content">
    <thead>
      <tr class="ui-widget-header ">
        <th>Product</th>
        <th>Description</th>
        <th>Rate</th>
        <th>Mfd By</th>
      </tr>
    </thead>
    <tbody>
	<% for(Product prod : productList) {%>
      <tr>
       <td><a href="javascript:loadDetails('<%=prod.getProductId()%>');"><%=prod.getProductName()%></a>
       <td><%=prod.getProductDesc()%></td>
       <td><%=prod.getRate()%></td>
       <td><%=userMap.get(prod.getMfdBy())%></td>
      </tr>
	<%} %>
  
  <tr><td><button id="create-product">Create new product</button></td></tr>
 
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
    <input type="text" name="productName" id="productName" value='<%=product.getProductName()==null?"":product.getProductName()%>' class="text ui-widget-content ui-corner-all" maxlength=40 size=40>
    <label for="text">Description</label>
    <input type="text" name="productDesc" id="productDesc" value='<%=product.getProductDesc()==null?"":product.getProductDesc()%>' class="text ui-widget-content ui-corner-all" maxlength=40 size=40>
    <label for="rate">Rate</label>
    <input type="text" name="rate" id="rate" value='<%=product.getRate()==null?"":product.getRate()%>' class="text ui-widget-content ui-corner-all" maxlength=100 size=100>
    <label for="rate">Mfd By</label>
    <input type="text" name="rate" id="rate" value='<%=product.getMfdBy()==null?"":userMap.get(product.getMfdBy())%>' class="text ui-widget-content ui-corner-all" maxlength=100 size=100>
    
  </fieldset>
 
   <button id="save">Save</button>
   <button id="delete">Delete</button>
 
  </div>
 </div>

	<input type="hidden" name="actionName" value="">
	<input type="hidden" name="productId" value="<%=product.getProductId()==null?"":product.getProductId()%>">
	<input type="hidden" name="module" value="products">
 

</form>
</body>
</html>