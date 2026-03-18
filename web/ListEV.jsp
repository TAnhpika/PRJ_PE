<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Electric Vehicle List</title>
        <style>
            body { font-family: 'Segoe UI', Tahoma, Geneva, Verdana, sans-serif; background-color: #f4f7f6; color: #333; margin: 0; padding: 40px 20px; }
            .container { max-width: 1000px; margin: 0 auto; background: #fff; padding: 30px; border-radius: 8px; box-shadow: 0 4px 6px rgba(0,0,0,0.1); }
            h1 { color: #2c3e50; text-align: center; margin-bottom: 25px; font-size: 28px; }
            .btn { display: inline-block; padding: 10px 18px; background: #2ecc71; color: #fff; text-decoration: none; border-radius: 4px; border: none; font-weight: 500; cursor: pointer; transition: background 0.3s; }
            .btn:hover { background: #27ae60; }
            .header-actions { display: flex; justify-content: space-between; align-items: center; margin-bottom: 20px; }
            .search-form { display: flex; gap: 10px; align-items: center; background: #f8f9fa; padding: 10px 15px; border-radius: 6px; border: 1px solid #e9ecef; }
            .search-form input[type="text"] { padding: 8px 12px; border: 1px solid #ccc; border-radius: 4px; width: 250px; outline: none; transition: border-color 0.3s; }
            .search-form input[type="text"]:focus { border-color: #3498db; }
            .search-form input[type="submit"] { padding: 8px 15px; background: #3498db; color: white; border: none; border-radius: 4px; cursor: pointer; transition: background 0.3s; }
            .search-form input[type="submit"]:hover { background: #2980b9; }
            table { width: 100%; border-collapse: collapse; margin-top: 10px; }
            th, td { border: 1px solid #e0e0e0; padding: 12px 15px; text-align: left; }
            th { background-color: #34495e; color: #fff; font-weight: 500; text-transform: uppercase; font-size: 14px; letter-spacing: 0.5px; }
            tr:nth-child(even) { background-color: #f8f9fa; }
            tr:hover { background-color: #f1f3f5; }
            .action-links a { margin-right: 12px; text-decoration: none; font-weight: 600; transition: opacity 0.3s; }
            .action-links a.edit-btn { color: #f39c12; }
            .action-links a.remove-btn { color: #e74c3c; }
            .action-links a:hover { opacity: 0.7; }
            .total-summary { margin-top: 20px; font-weight: 600; text-align: right; font-size: 1.1em; color: #2c3e50; padding-top: 15px; border-top: 2px solid #eee; }
        </style>
    </head>
    <body>
        <div class="container">
            <h1>Electric Vehicle Inventory</h1>
            
            <div class="header-actions">
                <form action="EVControllerServlet" method="GET" class="search-form">
                    <input type="hidden" name="service" value="list"/>
                    <label style="font-weight: 500; color: #555;">Battery Type:</label>
                    <input type="text" name="batteryType" value="${requestScope.searchedBatteryType}" placeholder="Filter..."/>
                    <input type="submit" value="Search"/>
                </form>
                <a href="EVControllerServlet?service=add" class="btn">+ Add New Vehicle</a>
            </div>

            <table>
                <thead>
                    <tr>
                        <th>Vehicle ID</th>
                        <th>Model Name</th>
                        <th>Price ($)</th>
                        <th>Battery Type</th>
                        <th style="width: 130px; text-align: center;">Actions</th>
                    </tr>
                </thead>
                <tbody>
                    <c:forEach items="${requestScope.list}" var="ev">
                        <tr>
                            <td><strong>${ev.vehicleID}</strong></td>
                            <td>${ev.modelName}</td>
                            <td>${ev.price}</td>
                            <td><span style="background:#e8f4fd; color:#2980b9; padding:4px 8px; border-radius:12px; font-size:12px; font-weight:bold;">${ev.batteryType}</span></td>
                            <td class="action-links" style="text-align: center;">
                                <a href="EVControllerServlet?service=edit&id=${ev.vehicleID}" class="edit-btn">Edit</a>
                                <a href="EVControllerServlet?service=remove&id=${ev.vehicleID}" class="remove-btn" onclick="return confirm('Confirm deletion of ${ev.vehicleID}?');">Remove</a>
                            </td>
                        </tr>
                    </c:forEach>
                    <c:if test="${empty requestScope.list}">
                        <tr>
                            <td colspan="5" style="text-align: center; color: #7f8c8d; padding: 20px;">No vehicles found.</td>
                        </tr>
                    </c:if>
                </tbody>
            </table>
            
            <div class="total-summary">
                Total Vehicles: <span style="color: #3498db;">${requestScope.list.size()}</span>
            </div>
        </div>
    </body>
</html>
