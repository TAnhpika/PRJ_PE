<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Add Electric Vehicle</title>
        <style>
            .error { color: red; font-size: 0.9em; }
        </style>
    </head>
    <body>
        <h1>Add New Electric Vehicle</h1>
        <form action="EVControllerServlet?service=add" method="POST">
            <div>
                Vehicle ID: <input type="text" name="vehicleID" value="${param.vehicleID}"/>
                <div class="error">${requestScope.error_vehicleID}</div>
            </div>
            <br/>
            <div>
                Model Name: <input type="text" name="modelName" value="${param.modelName}"/>
                <div class="error">${requestScope.error_modelName}</div>
            </div>
            <br/>
            <div>
                Price: <input type="text" name="price" value="${param.price}"/>
                <div class="error">${requestScope.error_price}</div>
            </div>
            <br/>
            <div>
                Battery Type: <input type="text" name="batteryType" value="${param.batteryType}"/>
                <div class="error">${requestScope.error_batteryType}</div>
            </div>
            <br/>
            <input type="submit" value="Save"/>
        </form>
    </body>
</html>
