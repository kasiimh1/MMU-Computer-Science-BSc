<!-- Created by Kasim Hussain - 15078165 -->       
<!DOCTYPE html PUBLIC "-//W3C/DTD HTML 4.01 Transitional//EN " "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
    <meta http-eqiv="Content-Type" content="text/html; charset=ISO-8859-1">
    <%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
    <link rel="stylesheet" href="./css/site.css">
</head>
<body>
<center><h1>Vehicle Inventory Login</h1>
<h5>By Kasim Hussain</h5>
<br><br>
<a id="buttons" class="btn btn-primary button" href="./addnewuser">Sign-up</a>
<br><br>
    <form method="POST" action="./login" id="small">
        Username:
        <input class="form-control text-center" type="text" name="username" placeholder="enter username">
        <br>
        Password:
        <input class="form-control text-center" type="password" name="password" placeholder="enter password"> 
        <br>
        <input type="submit" class="btn btn-primary">
    </form>

    <br><br>
    <a id="buttons" class="btn btn-danger button" href="./reset"> Reset Password</a>
</center>