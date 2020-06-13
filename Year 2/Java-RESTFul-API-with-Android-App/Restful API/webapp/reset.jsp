<!-- Created by Kasim Hussain - 15078165 -->       
<!DOCTYPE html PUBLIC "-//W3C/DTD HTML 4.01 Transitional//EN " "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
    <meta http-eqiv="Content-Type" content="text/html; charset=ISO-8859-1">
    <%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
    <meta <%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>>
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
    <link rel="stylesheet" href="./css/site.css">
</head>

<body>
<center><h1>Vehicle Inventory Password Reset</h1>
<h5>By Kasim Hussain</h5>
<a id="buttons" class="btn btn-primary button" href="./addnewuser">Sign-up</a>
    <form method="POST" action="./reset" id="small">    
        Username:
        <input class="form-control text-center" type="text" name="username" placeholder="enter username">    
        <br>        
        <input class="form-control text-center" type="password" name="password" placeholder="enter new password"> 
        <br>
        <input type="submit" class="btn btn-primary">
    </form>
    <br>    
    <%-- display the message that is set within the class file --%>
    <c:out value="Message: ${message}"/>
    <br><br>
    <a class="btn btn-primary button" href="./home">Return Home</a>
</center>