    <!-- Created by Kasim Hussain - 15078165 -->       
    <!DOCTYPE html PUBLIC "-//W3C/DTD HTML 4.01 Transitional//EN " "http://www.w3.org/TR/html4/loose.dtd">
    <html>
    <head>
        <meta http-eqiv="Content-Type" content="text/html; charset=ISO-8859-1">
        <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
        <link rel="stylesheet" href="./css/site.css">
        <meta <%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
        <%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
    </head>
    <body>
        <center>
            <%-- check session status, hide if not logged in --%>
            <c:if test="${loggedin ne true}">
                <br><br>
                <h1>You must login before you can complete this action!<br><br>
                <a class="btn btn-primary button glyphicon glyphicon-log-in" href="./login"> Login</a>
            </c:if>
            <%-- check session status, show if logged in, the values are pulled from the GET request that we made --%>
            <c:if test="${loggedin eq true}"> 
                <form method="POST" action="./update" id="small">
                    <input type="hidden" class="form-control text-center" readonly type="text" name="current_id" value='<%=request.getParameter("vehicle_id")%>'/>
                    Vehicle ID:
                    <input class="form-control text-center" type="text" name="vehicle_id" value='<%=request.getParameter("vehicle_id")%>'/>
                    Make:
                    <input class="form-control text-center" type="text" name="make" value='<%=request.getParameter("make")%>'/>
                    Model:
                    <input class="form-control text-center" type="text" name="model" value='<%=request.getParameter("model")%>'/>
                    Year:
                    <input class="form-control text-center" type="text" name="year" value='<%=request.getParameter("year")%>'/> 
                    Price:
                    <input class="form-control text-center" type="text" name="price" value='<%=request.getParameter("price")%>'/> 
                    License:
                    <input pattern="[A-Z]{2}[0-9]{2}[A-Z]{3}" title="License in format: XXNNXXX" class="form-control text-center" type="text" name="license_number" value='<%=request.getParameter("license_number")%>'/>
                    Colour:
                    <input class="form-control text-center" type="text" name="colour" value='<%=request.getParameter("colour")%>'/>
                    Number of Doors:
                    <input class="form-control text-center" type="text" name="number_doors" value='<%=request.getParameter("number_doors")%>'/> 
                    Transmission:
                    <input class="form-control text-center" type="text" name="transmission" value='<%=request.getParameter("transmission")%>'/>
                    Mileage:
                    <input class="form-control text-center" type="text" name="mileage" value='<%=request.getParameter("mileage")%>'/> 
                    Fuel Type:
                    <input class="form-control text-center" type="text" name="fuel_type" value='<%=request.getParameter("fuel_type")%>'/>
                    Engine Size:
                    <input class="form-control text-center" type="text" name="engine_size" value='<%=request.getParameter("engine_size")%>'/>
                    Body Style
                    <input class="form-control text-center" type="text" name="body_style" value='<%=request.getParameter("body_style")%>'/> 
                    Condition:
                    <input class="form-control text-center" type="text" name="condition" value='<%=request.getParameter("condition")%>'/>
                    Notes:
                    <input class="form-control text-center" type="text" name="notes" value='<%=request.getParameter("notes")%>'/>
                    Update Sold Status: 
                    <input class="form-control text-center" type="text" name="sold" value='<%=request.getParameter("sold")%>'/>
                    <br>
                    <input type="submit" class="btn btn-primary">
                </form> 
                <br><br>
                <a id="Buttons" class="btn btn-danger button glyphicon glyphicon-log-out" href="./logout"> Logout</a>
            </c:if>
        </center>
    </body>

    </html>