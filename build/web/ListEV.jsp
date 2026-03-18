<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Electric Vehicle List</title>
        <style>
            table { width: 80%; border-collapse: collapse; margin: 20px 0; }
            th, td { border: 1px solid #ddd; padding: 12px; text-align: left; }
            th { background-color: #f4f4f4; }
        </style>
    </head>
    <body>
        <h1>Electric Vehicle List</h1>
        
        <form action="EVControllerServlet" method="GET" style="margin-bottom: 15px;">
            <input type="hidden" name="service" value="list"/>
            Search by Battery Type: 
            <input type="text" name="batteryType" value="${requestScope.searchedBatteryType}"/>
            <input type="submit" value="Search"/>
        </form>

        <a href="EVControllerServlet?service=add">Add New Vehicle</a>
        <table>
            <thead>
                <tr>
                    <th>Vehicle ID</th>
                    <th>Model Name</th>
                    <th>Price</th>
                    <th>Battery Type</th>
                    <th>Actions</th>
                </tr>
            </thead>
            <tbody>
                <c:forEach items="${requestScope.list}" var="ev">
                    <tr>
                        <td>${ev.vehicleID}</td>
                        <td>${ev.modelName}</td>
                        <td>${ev.price}</td>
                        <td>${ev.batteryType}</td>
                        <td>
                            <a href="EVControllerServlet?service=edit&id=${ev.vehicleID}">Edit</a> | 
                            <a href="EVControllerServlet?service=remove&id=${ev.vehicleID}" onclick="return confirm('Are you sure you want to remove this vehicle?');">Remove</a>
                        </td>
                    </tr>
                </c:forEach>
            </tbody>
        </table>
        
        <div>
            <strong>Total Vehicles:</strong> ${requestScope.list.size()}
        </div>
    </body>
</html>
