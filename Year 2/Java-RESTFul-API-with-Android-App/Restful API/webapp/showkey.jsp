<!-- Created by Kasim Hussain - 15078165 -->       
<html>
   <head>
      <meta charset="UTF-8">
      <title>API Key</title>
      <meta <%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>>
      <%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
      <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
      <link rel="stylesheet" href="./css/site.css">
   </head>
   <body>
      <center>
         <h1>Vehicle Inventory System</h1>
         <h5>By Kasim Hussain</h5>
         <br>
         <br>
          <c:if test="${loggedin ne true}">
            <a id="buttons" class="btn btn-primary button glyphicon glyphicon-log-in" href="./login"> Login</a>
            <a id="buttons" class="btn btn-primary button" href="./addnewuser">Sign-up</a>
            <br><br>
            <h1>Please Log-in or create an account before continuing</h1>
            <h2>API Sercret Key Portal</h2>
         </c:if>
          <c:if test="${loggedin eq true}">
          <h1>API Sercret Key that is used when sending a request to all endpoints</h1>
         <h2 id="key">${{user}}</h2>
         <br>
         <a id="Buttons" class="btn btn-danger button glyphicon glyphicon-log-out" href="./logout"> Logout</a>
     </c:if>
     <br>
     <a class="btn btn-primary button" href="./home">Return Home</a>
      </center>
   </body>
</html>