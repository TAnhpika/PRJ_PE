package controller;

import dal.ElectricVehicleDAO;
import java.io.IOException;
import java.util.List;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import model.ElectricVehicle;

/**
 *
 * @author Anhpika 
 */
@WebServlet(name="EVControllerServlet", urlPatterns={"/EVControllerServlet"})
public class EVControllerServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
    throws ServletException, IOException {
        String service = request.getParameter("service");
        if (service == null) {
            service = "list";
        }

        switch (service) {
            case "add":
                request.getRequestDispatcher("AddEV.jsp").forward(request, response);
                break;
            case "remove":
                removeVehicle(request, response);
                break;
            case "edit":
                loadEditPage(request, response);
                break;
            case "list":
            default:
                listVehicles(request, response);
                break;
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
    throws ServletException, IOException {
        String service = request.getParameter("service");
        if ("add".equals(service)) {
            addVehicle(request, response);
        } else if ("update".equals(service)) {
            updateVehicle(request, response);
        }
    }

    private void listVehicles(HttpServletRequest request, HttpServletResponse response)
    throws ServletException, IOException {
        String batteryType = request.getParameter("batteryType");
        ElectricVehicleDAO dao = new ElectricVehicleDAO();
        List<ElectricVehicle> list;
        
        if (batteryType != null && !batteryType.trim().isEmpty()) {
            list = dao.searchByBatteryType(batteryType.trim());
        } else {
            list = dao.listAllVehicles();
        }
        
        request.setAttribute("list", list);
        request.setAttribute("searchedBatteryType", batteryType);
        request.getRequestDispatcher("ListEV.jsp").forward(request, response);
    }

    private void removeVehicle(HttpServletRequest request, HttpServletResponse response)
    throws ServletException, IOException {
        String id = request.getParameter("id");
        ElectricVehicleDAO dao = new ElectricVehicleDAO();
        dao.deleteVehicle(id);
        response.sendRedirect("EVControllerServlet?service=list");
    }

    private void loadEditPage(HttpServletRequest request, HttpServletResponse response)
    throws ServletException, IOException {
        String id = request.getParameter("id");
        ElectricVehicleDAO dao = new ElectricVehicleDAO();
        ElectricVehicle ev = dao.getVehicleById(id);
        request.setAttribute("ev", ev);
        request.getRequestDispatcher("EditEV.jsp").forward(request, response);
    }

    private void addVehicle(HttpServletRequest request, HttpServletResponse response)
    throws ServletException, IOException {
        String vehicleID = request.getParameter("vehicleID");
        String modelName = request.getParameter("modelName");
        String priceStr = request.getParameter("price");
        String batteryType = request.getParameter("batteryType");

        boolean hasError = false;

        if (vehicleID == null || vehicleID.trim().isEmpty()) {
            request.setAttribute("error_vehicleID", "Vehicle ID is required.");
            hasError = true;
        }

        if (modelName == null || modelName.trim().isEmpty()) {
            request.setAttribute("error_modelName", "Model Name is required.");
            hasError = true;
        }

        double price = 0;
        if (priceStr == null || priceStr.trim().isEmpty()) {
            request.setAttribute("error_price", "Price is required.");
            hasError = true;
        } else {
            try {
                price = Double.parseDouble(priceStr);
            } catch (NumberFormatException e) {
                request.setAttribute("error_price", "Price must be a numerical value.");
                hasError = true;
            }
        }

        if (batteryType == null || batteryType.trim().isEmpty()) {
            request.setAttribute("error_batteryType", "Battery Type is required.");
            hasError = true;
        }

        if (hasError) {
            request.getRequestDispatcher("AddEV.jsp").forward(request, response);
        } else {
            ElectricVehicle ev = new ElectricVehicle(vehicleID, modelName, price, batteryType);
            ElectricVehicleDAO dao = new ElectricVehicleDAO();
            dao.addVehicle(ev);
            response.sendRedirect("EVControllerServlet?service=list");
        }
    }

    private void updateVehicle(HttpServletRequest request, HttpServletResponse response)
    throws ServletException, IOException {
        String vehicleID = request.getParameter("vehicleID"); // Readonly in edit
        String modelName = request.getParameter("modelName");
        String priceStr = request.getParameter("price");
        String batteryType = request.getParameter("batteryType");

        boolean hasError = false;

        if (modelName == null || modelName.trim().isEmpty()) {
            request.setAttribute("error_modelName", "Model Name is required.");
            hasError = true;
        }

        double price = 0;
        if (priceStr == null || priceStr.trim().isEmpty()) {
            request.setAttribute("error_price", "Price is required.");
            hasError = true;
        } else {
            try {
                price = Double.parseDouble(priceStr);
            } catch (NumberFormatException e) {
                request.setAttribute("error_price", "Price must be a numerical value.");
                hasError = true;
            }
        }

        if (batteryType == null || batteryType.trim().isEmpty()) {
            request.setAttribute("error_batteryType", "Battery Type is required.");
            hasError = true;
        }

        if (hasError) {
            // keep the vehicleID to reload the page safely
            ElectricVehicle ev = new ElectricVehicle(vehicleID, modelName, priceStr != null && !priceStr.isEmpty() && !hasError ? price : 0, batteryType);
            request.setAttribute("ev", ev);
            request.getRequestDispatcher("EditEV.jsp").forward(request, response);
        } else {
            ElectricVehicle ev = new ElectricVehicle(vehicleID, modelName, price, batteryType);
            ElectricVehicleDAO dao = new ElectricVehicleDAO();
            dao.updateVehicle(ev);
            response.sendRedirect("EVControllerServlet?service=list");
        }
    }
}
