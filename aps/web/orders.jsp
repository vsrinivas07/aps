<%@ page language="java" contentType="text/html; charset=ISO-8859-1"  pageEncoding="ISO-8859-1"%>
<%@ page import="java.util.List"%>
<%@ page import="java.util.ArrayList"%>
<%@ page import="java.util.Map"%>
<%@ page import="java.util.Map.Entry"%>
<%@ page import="com.apceps.domain.Order"%>
<%@ page import="com.apceps.util.StringUtil"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%
 
 String message = (String)request.getAttribute("message");

 List<Order> orderList = (List<Order>)request.getAttribute("orderList");

 if(orderList == null) orderList = new ArrayList<Order>();
 
 String tabName = request.getParameter("tabName");

 Order order = (Order)request.getAttribute("order");

 if(order == null ) order = new Order();

 Map<Long,String> productMap = (Map<Long,String>)request.getAttribute("productMap");

 Map<Long,String> userMap = (Map<Long,String>)request.getAttribute("userMap");
  
 %>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Orders</title>
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
var productListSize = "<%=orderList.size()%>";
$(function() {
	
	$( "#orderDate" ).datepicker();

	
	$( "#tabs" ).tabs();
	
	$( "#create-order" )
	.button()
	.click(function() {
		 document.forms[0].actionName.value = "details";
		 document.forms[0].submit();	
	});
	
	$( "#save" )
	.button()
	.click(function() {
		document.forms[0].productId.value = document.forms[0].productIdSelect.value;
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
		document.forms[0].actionName.value = "list";
		document.forms[0].submit();	
	});

	$( "#productIdSelect" ).combobox(); 
	
	$('#actionsDiv').append('<button id="confirm-order" type="button" >Confirm order</button>');
	$('#actionsDiv').append('<button id="dispatch-order" type="button"  >Dispatch Order</button>') ;
	$('#actionsDiv').append('<button id="whs-reach" type="button" >WH Reach</button>') ;
	$('#actionsDiv').append('<button id="delivery-schedule" type="button">Delivery Schedule</button>') ;
	$('#actionsDiv').append('<button id="delivery-confirm" type="button" >Delivery Confirmaion</button>');
	  

 	$('#confirm-order').click(function(){$("#confirmOrderForm").dialog("open");});
	$('#dispatch-order').click(function(){ alert('Dispatch Order!');});
	$('#whs-reach').click(function(){ alert('WHS Reach!');});
	$('#delivery-schedule').click(function(){ alert('Delivery Schedule!');});
	$('#delivery-confirm').click(function(){ alert('Confirm Delivery!');});

	
	
	$( "#confirmOrderForm" ).dialog({
	      autoOpen: false,
	      height: 300,
	      width: 350,
	      modal: true,
	      buttons: {
	        "Create an account": function() {
	         
	        },
	        Cancel: function() {
	          $( this ).dialog( "close" );
	        }
	      },
	      close: function() {
	        allFields.val( "" ).removeClass( "ui-state-error" );
	      }
	    });
	 

		
 });


function loadDetails(orderId){
	 document.forms[0].orderId.value = orderId;
	 document.forms[0].actionName.value = "details";
 	 document.forms[0].submit();
} 
  
</script>

</head>
<body>
<form method="post" action="orderController">
<div id="tabs">
  <ul>
    <li id="listTabLi"><a href="#listTab">Order List</a></li>
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

  <table id="order" class="ui-widget ui-widget-content">
    <thead>
      <tr class="ui-widget-header ">
        <th></th>
        <th>Order</th>
        <th>Product</th>
        <th>Quantity</th>
        <th>Ordered By</th>
        <th>Order Date</th>
        <th>Delivery Location</th>
        <th>Production Pickup Date</th>
        <th>WHS Reach Date</th>
        <th>WHS Pickup Date</th>
        <th>Delivery Date</th>
        <th>Received By</th>
        <th>Order Status</th>
      </tr>
    </thead>
    <tbody>
	<% for(Order ord : orderList) {%>
      <tr>
       <td><input type="checkBox"></td>
       <td><a href="javascript:loadDetails('<%=ord.getOrderId()%>');"><%=ord.getOrderId()%></a>
       <td><a href="javascript:loadDetails('<%=ord.getOrderId()%>');"><%=productMap.get(ord.getProductId())%></a>
       <td><%=ord.getQuantity()%></td>
       <td><%=userMap.get(ord.getOrderedBy())%></td>
       <td><%=ord.getOrderDate()%></td>
       <td><%=ord.getDeliveryLocation()%></td>
       <td><%=ord.getProdPickupDate()%></td>
       <td><%=ord.getWhsReachDate()%></td>
       <td><%=ord.getWhsPickupDate()%></td>
       <td><%=ord.getDeliveryDate()%></td>
       <td><%=ord.getReceivedBy()%></td>
       <td><%=ord.getOrderStatus()%></td>
       </tr>
	<%} %>
      </tbody>
  </table>
   
  <div id="actionsDiv"></div>
      
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
   
   <table id="order" class="ui-widget ui-widget-content">
   
   <tr>
   <td>
	  <label for="productId">Product</label> 

	<% if(order.getOrderId()==null) { %>
      <select id="productIdSelect">
      <option value="">Select one...</option>
   <%
   for(Entry<Long,String> entry : productMap.entrySet()){
    %>
      <option value='<%=entry.getKey()%>' ><%=entry.getValue()%></option>
    <%} %>
  </select>
      <input type="hidden" id="productId" name="productId" value="">
	<%}else{ %>
	  <input type="text" name="productId" id="productId" value='<%=order.getProductId()==null?"":productMap.get(order.getProductId())%>' class="text ui-widget-content ui-corner-all" >
	<%} %>  
	  
     
	  
	  
	  
	  
	  
	  
	  </td>
	   </tr>
	<tr>
	  <td>
	  <label for="orderDate">Order Date</label> <input type="text" name="orderDate" id="orderDate" value='<%=order.getOrderDate()==null?"":order.getOrderDate()%>' class="text ui-widget-content ui-corner-all" size=12 maxlength=12>
	  </td>
   </tr>
   
     <tr>
	  <td>
	  <label for="quantity">Quantity</label> <input type="text" name="quantity" id="quantity" value='<%=order.getQuantity()==null?"":order.getQuantity()%>' class="text ui-widget-content ui-corner-all" >
	</td>
    </tr>
  </table>
  
  
  
 
 
   <button id="save">Save</button>
   <button id="delete">Delete</button>
 
  </div>
 </div>

	<input type="hidden" name="actionName" value="">
	<input type="hidden" name="orderId" value="<%=order.getOrderId()==null?"":order.getOrderId()%>">
	<input type="hidden" name="module" value="orders">
 
 
 <div id="confirmOrderForm" title="ConfirmOrder">
  <p class="validateTips">All form fields are required.</p>
 <fieldset>
    <label for="name">Name</label>
    <input type="text" name="name" id="name" class="text ui-widget-content ui-corner-all">
    <label for="email">Email</label>
    <input type="text" name="email" id="email" value="" class="text ui-widget-content ui-corner-all">
    <label for="password">Password</label>
    <input type="password" name="password" id="password" value="" class="text ui-widget-content ui-corner-all">
  </fieldset>
 
</div>

</form>
</body>
</html>