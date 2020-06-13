<!-- Created by Kasim Hussain - 15078165 -->       
<html>
   <head>
      <meta charset="UTF-8">
      <title>Vehicles</title>
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
         <form method="GET" action="./search" class="form form-inline">
         <input type="text" class="form-control" name="search" placeholder="Search Vehicle ID...">
         <select name="option" class="form-control">
         <option value="make">Make</option>
         <option value="model">Model</option>
         <option value="price">Price</option>
         </select>
         <input type="submit" class="btn btn-primary glyphicon glyphicon-search" value="&#xe003;">
         </select>
         </form>
         <br>
         <%-- check session status, hide if not logged in --%>
         <c:if test="${loggedin ne true}">
            <a id="buttons" class="btn btn-primary button glyphicon glyphicon-log-in" href="./login"> Login</a>
         </c:if>
         <%-- check session status, show if logged in --%>
         <c:if test="${loggedin eq true}">
            <a id="buttons" class="btn btn-primary button glyphicon glyphicon-plus" href="./addnew"> Vehicle</a>
            <a id="Buttons" class="btn btn-danger button glyphicon glyphicon-log-out" href="./logout"> Logout</a>
         </c:if>
         <table class="table table-hover">
            <tr>
               <th>Vehicle ID</th>
               <th>Make</th>
               <th>Model</th>
               <th>Year</th>
               <th>Price</th>
               <th>License Number</th>
               <th>Colour</th>
               <th>Number Doors</th>
               <th>Transmission</th>
               <th>Mileage</th>
               <th>Fuel Type</th>
               <th>Engine Size</th>
               <th>Body Style</th>
               <th>Condition</th>
               <th>Notes</th>
               <th>Sold</th>
            </tr>
            <%-- print every item in the ArrayList, iterate through the list and use the getters to retrieve the values --%>
            <c:forEach items="${allCons}" var="c">
                  <tr>
                     <td>${c.getVehicle_id()}</td>
                     <td>${c.getMake()}</td>
                     <td>${c.getModel()}</td>
                     <td>${c.getYear()}</td>
                     <td>${c.getPrice()}</td>
                     <td>${c.getLicense_number()}</td>
                     <td>${c.getColour()}</td>
                     <td>${c.getNumber_doors()}</td>
                     <td>${c.getTransmission()}</td>
                     <td>${c.getMileage()}</td>
                     <td>${c.getFuel_type()}</td>
                     <td>${c.getEngine_size()}</td>
                     <td>${c.getBody_style()}</td>
                     <td>${c.getCondition()}</td>
                     <td>${c.getNotes()}</td>
                     <td>${c.getSold()}</td>
                     <td>
                        <%-- check session status, show if logged in --%>
                     	<c:if test="${loggedin eq true}">    
                        <form method="POST" action="./delete">  
                        <input type="hidden" value="${c.getVehicle_id()}" name="vehicle_id">
                        <input class="btn btn-danger button glyphicon glyphicon-trash" value="&#xe020;" type="submit">
                        </form>
                        <%-- get the values for these items to be sent via GET for displaying on the edit .jsp page, these inputs are hidden --%>
                        <form method="GET" action="./update">  
                        <input type="hidden" value="${c.getVehicle_id()}" name="vehicle_id">
                        <input type="hidden" value="${c.getMake()}" name="make">
                        <input type="hidden" value="${c.getModel()}" name="model">
                        <input type="hidden" value="${c.getYear()}" name="year">
                        <input type="hidden" value="${c.getPrice()}" name="price">
                        <input type="hidden" value="${c.getLicense_number()}" name="license_number">
                        <input type="hidden" value="${c.getColour()}" name="colour">
                        <input type="hidden" value="${c.getNumber_doors()}" name="number_doors">
                        <input type="hidden" value="${c.getTransmission()}" name="transmission">
                        <input type="hidden" value="${c.getMileage()}" name="mileage">
                        <input type="hidden" value="${c.getFuel_type()}" name="fuel_type">
                        <input type="hidden" value="${c.getEngine_size()}" name="engine_size">
                        <input type="hidden" value="${c.getBody_style()}" name="body_style">
                        <input type="hidden" value="${c.getCondition()}" name="condition">
                        <input type="hidden" value="${c.getNotes()}" name="notes">
                        <input type="hidden" value="${c.getSold()}" name="sold">                        
                        <input class="btn btn-warning button glyphicon glyphicon-pencil" value="&#x270f;" type="submit">
                        </form>
                        </c:if>                        
                     </td>
                  </tr>
            </c:forEach>
            </tr>
         </table>
      </center>
   </body>
</html>