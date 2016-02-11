<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
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
  </style>
  <script>
  $(function() {
    var name = $( "#userName" ),
       password = $( "#userPassword" ),
       allFields = $( [] ).add( name ).add( password ),
    
      tips = $( ".validateTips" );
 
    function updateTips( t ) {
      tips
        .text( t )
        .addClass( "ui-state-highlight" );
      setTimeout(function() {
        tips.removeClass( "ui-state-highlight", 1500 );
      }, 500 );
    }
 
    function checkLength( o, n, min, max ) {
      if ( o.val().length < min ) {
        o.addClass( "ui-state-error" );
        updateTips( "Length of " + n + " must be between " +
          min + " and " + max + "." );
        return false;
      } else {
        return true;
      }
    }
 
    
    $( "#dialog-form" ).dialog({
      autoOpen: true,
      height: 250,
      width: 400,
      modal: false,
      buttons: {
        "Login": function() {
            
        	document.forms[0].actionName.value = "login";
        	document.forms[0].submit();
          },
        "Help": function() {
         // $( this ).dialog( "close" );
        }
      },
       
    });
   });
  </script>
</head>
<body>
 
<div id="dialog-form" title="Login">
  <form method="post" action="loginController?actionName=login">
  <fieldset>
    <label for="name">Name</label>
    <input type="text" name="userName" id="userName" class="text ui-widget-content ui-corner-all">
    <label for="password">Password</label>
    <input type="password" name="userPassword" id="userPassword" value="" class="text ui-widget-content ui-corner-all">
    <input type="hidden" name="actionName" value="">
  </fieldset>
  </form>
</div>
  
</body>
</html>