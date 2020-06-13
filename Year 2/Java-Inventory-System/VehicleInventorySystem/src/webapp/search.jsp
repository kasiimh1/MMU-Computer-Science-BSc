<!-- Created by Kasim Hussain - 15078165 -->       
<html>
   <head>
      <meta charset="UTF-8">
      <title>Vehicles</title>
      <meta <%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>>
      <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
      <link rel="stylesheet" href="./css/site.css">
</head>
   <body>
      <center>
         <h1>Vehicle Inventory System</h1>
         <h5>By Kasim Hussain</h5>
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
         <a class="btn btn-primary button" href="./home">Return Home</a>
         <br>
         <br>
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
            </tr>
            </c:forEach>
         </table>
      </center>
   </body>
</html>