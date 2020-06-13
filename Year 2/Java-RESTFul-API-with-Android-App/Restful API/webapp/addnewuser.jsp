    <!-- Created by Kasim Hussain - 15078165 -->       
    <!DOCTYPE html PUBLIC "-//W3C/DTD HTML 4.01 Transitional//EN " "http://www.w3.org/TR/html4/loose.dtd">
    <html>
    <head>        
        <meta http-eqiv="Content-Type" content="text/html; charset=ISO-8859-1">
        <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
        <link rel="stylesheet" href="./css/site.css">
        <%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
        <meta <%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>>
    </head>
    <body><br><br><br><br>
        <center>
            <h1>Create a User Account for Vehicle Inventory System</h1>
                <form method="POST" action="./addnewuser" id="small">
                    Firstname:
                    <input class="form-control text-center" type="text" name="firstname" placeholder="enter firstname"> 
                    Surname:
                    <input class="form-control text-center" type="text" name="surname" placeholder="enter surname"> 
                    Username:
                    <input class="form-control text-center" type="text" name="username" placeholder="enter username"> 
                    Password:
                    <input class="form-control text-center" type="password" name="password" placeholder="Enter password"> 
                    <br>              
                    <input type="submit" class="btn btn-primary">
                </form>
                <br><br>
                <a id="Buttons" class="btn btn-danger button glyphicon glyphicon-log-out" href=./home> Return Home</a>
        </center>
    </body>
    </html>