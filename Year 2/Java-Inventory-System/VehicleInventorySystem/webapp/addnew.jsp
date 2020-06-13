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
    <body>
        <center>
            <%-- check session status, hide if not logged in --%>
            <c:if test="${loggedin ne true}">
                <br><br>
                <h1>You must login before you can complete this action!<br><br>
                <a class="btn btn-primary button glyphicon glyphicon-log-in" href="./login"> Login</a>
            </c:if>
            <c:if test="${loggedin eq true}">
            <%-- check session status, show if logged in --%>
                <form method="POST" action="./addnew" id="small">
                    Vehicle ID:
                    <input class="form-control text-center" type="text" name="vehicle_id" placeholder="enter vehicle id"> Make:
                    <input class="form-control text-center" type="text" name="make" placeholder="enter make"> Model:
                    <input class="form-control text-center" type="text" name="model" placeholder="enter model"> Year:
                    <input class="form-control text-center" type="text" name="year" placeholder="enter year"> Price:
                    <input class="form-control text-center" type="text" name="price" placeholder="Enter price"> License:
                    <input class="form-control text-center" type="text" name="license_number" placeholder="Enter license" pattern="[A-Z]{2}[0-9]{2}[A-Z]{3}" title="License in format: XXNNXXX"> Colour:
                    <input class="form-control text-center" type="text" name="colour" placeholder="Enter colour"> Number of Doors:
                    <input class="form-control text-center" type="text" name="number_doors" placeholder="Enter number of doors"> Transmission:
                    <input class="form-control text-center" type="text" name="transmission" placeholder="Enter transmission type"> Mileage:
                    <input class="form-control text-center" type="text" name="mileage" placeholder="Enter mileage"> Fuel Type:
                    <input class="form-control text-center" type="text" name="fuel_type" placeholder="Enter fuel type"> Engine Size:
                    <input class="form-control text-center" type="text" name="engine_size" placeholder="Enter engine size"> Body Style
                    <input class="form-control text-center" type="text" name="body_style" placeholder="Enter body style"> Condition:
                    <input class="form-control text-center" type="text" name="condition" placeholder="Enter condition"> Notes:
                    <input class="form-control text-center" type="text" name="notes" placeholder="Enter note">Sold:
                    <input class="form-control text-center" type="text" name="sold" placeholder="Enter sold status">
                    <input type="submit" class="btn btn-primary">
                </form>
                <br><br>
                <a id="Buttons" class="btn btn-danger button glyphicon glyphicon-log-out" href="./logout"> Logout</a>
            </c:if>
        </center>
    </body>
    </html>