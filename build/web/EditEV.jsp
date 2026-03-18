<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Edit Electric Vehicle</title>
        <style>
            body { font-family: 'Segoe UI', Tahoma, Geneva, Verdana, sans-serif; background-color: #f4f7f6; color: #333; margin: 0; padding: 40px 20px; display: flex; justify-content: center; }
            .container { width: 100%; max-width: 500px; background: #fff; padding: 30px 40px; border-radius: 8px; box-shadow: 0 4px 6px rgba(0,0,0,0.1); }
            h1 { color: #2c3e50; text-align: center; margin-bottom: 20px; font-size: 24px; }
            .form-group { margin-bottom: 15px; }
            label { display: block; font-weight: bold; margin-bottom: 5px; color: #555; }
            input[type="text"] { width: 100%; padding: 10px; border: 1px solid #ccc; border-radius: 4px; box-sizing: border-box; font-size: 14px; transition: border 0.3s ease; }
            input[type="text"]:focus { border-color: #3498db; outline: none; box-shadow: 0 0 5px rgba(52, 152, 219, 0.3); }
            input[readonly] { background-color: #f8f9fa; cursor: not-allowed; }
            .error { color: #e74c3c; font-size: 0.85em; margin-top: 5px; }
            .btn-submit { width: 100%; padding: 12px; background: #2ecc71; color: #fff; border: none; border-radius: 4px; font-size: 16px; font-weight: 600; cursor: pointer; transition: background 0.3s; margin-top: 10px; }
            .btn-submit:hover { background: #27ae60; }
            .back-link { display: inline-block; margin-bottom: 20px; color: #7f8c8d; text-decoration: none; font-weight: 500; transition: color 0.3s; }
            .back-link:hover { color: #34495e; text-decoration: underline; }
            .back-link i { margin-right: 5px; }
        </style>
    </head>
    <body>
        <div class="container">
            <a href="EVControllerServlet?service=list" class="back-link">&#8592; Return To EV List</a>
            <h1>Edit Electric Vehicle</h1>
            <form action="EVControllerServlet?service=update" method="POST">
                <div class="form-group">
                    <label for="vehicleID">Vehicle ID (Readonly)</label>
                    <input type="text" id="vehicleID" name="vehicleID" value="${ev.vehicleID}" readonly="readonly"/>
                </div>
                <div class="form-group">
                    <label for="modelName">Model Name</label>
                    <input type="text" id="modelName" name="modelName" value="${ev.modelName}"/>
                    <div class="error">${requestScope.error_modelName}</div>
                </div>
                <div class="form-group">
                    <label for="price">Price ($)</label>
                    <input type="text" id="price" name="price" value="${ev.price}"/>
                    <div class="error">${requestScope.error_price}</div>
                </div>
                <div class="form-group">
                    <label for="batteryType">Battery Type</label>
                    <input type="text" id="batteryType" name="batteryType" value="${ev.batteryType}"/>
                    <div class="error">${requestScope.error_batteryType}</div>
                </div>
                <input type="submit" value="Save Changes" class="btn-submit"/>
            </form>
        </div>
    </body>
</html>
