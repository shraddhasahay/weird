<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ page import="BankerAlgorithm.BankerAlgo"%>
<%@ page import="java.util.*"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<link rel="stylesheet" href="style.css">
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Result</title>
</head>
 <style>
 body {
    text-align: center;
}
h1 {
display:inline; 
   width:400px;
text-align: right;}

 </style>
<body>

      <h1 id="awesome" class="awesome">SYSTEM IN DEADLOCK STATE</h1>  
     
      
         
        <%-- Fetching the attributes of the request object
             which was previously set by the servlet 
              "BankerAlgo.java"
        --%> 
        <%int[] safestate = 
            (int[])request.getAttribute("data");%>
            
                       
            
            <table style="height:100px;width:100px;margin-left:20%;" class="center">
            <tr>
            <%if(safestate[0]!=-1) {%>
            <td><div id="div1" class="div1"><section><h1><%="P"+safestate[0] %></h1></section></div></td> <%} %>
             <%if(safestate[1]!=-1) {%>
            <td><div id="div2" class="div2"><section><h1><%="P"+safestate[1] %></h1></section></div></td> <%} %>
             <%if(safestate[2]!=-1) {%>
            <td><div id="div3" class="div3"><section><h1><%="P"+safestate[2] %></h1></section></div></td><%} %>
  			 <%if(safestate[3]!=-1) {%>
            <td><div id="div4" class="div4"><section><h1><%="P"+safestate[3] %></h1></section></div></td>  <%} %>
             <%if(safestate[4]!=-1) {%>
            <td><div id="div5" class="div5"><section><h1><%="P"+safestate[4] %></h1></section></div></td> <%} %>       
           
            </tr>
            </table> 
                        
           <div id="container">
            <div id="inner">
            </div></div>
            
                       
</body>
</html>