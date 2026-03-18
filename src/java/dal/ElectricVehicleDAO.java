package dal;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import model.ElectricVehicle;

/**
 *
 * @author ADMIN
 */
public class ElectricVehicleDAO extends DBContext {

    public void addVehicle(ElectricVehicle ev) {
        String sql = "INSERT INTO [dbo].[ElectricVehicle] ([VehicleID], [ModelName], [Price], [BatteryType]) VALUES (?, ?, ?, ?)";
        try {
            PreparedStatement st = c.prepareStatement(sql);
            st.setString(1, ev.getVehicleID());
            st.setString(2, ev.getModelName());
            st.setDouble(3, ev.getPrice());
            st.setString(4, ev.getBatteryType());
            st.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(ElectricVehicleDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public List<ElectricVehicle> listAllVehicles() {
        List<ElectricVehicle> list = new ArrayList<>();
        String sql = "SELECT * FROM ElectricVehicle";
        try {
            PreparedStatement st = c.prepareStatement(sql);
            ResultSet rs = st.executeQuery();
            while (rs.next()) {
                list.add(new ElectricVehicle(
                        rs.getString("VehicleID"),
                        rs.getString("ModelName"),
                        rs.getDouble("Price"),
                        rs.getString("BatteryType")
                ));
            }
        } catch (SQLException ex) {
            Logger.getLogger(ElectricVehicleDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return list;
    }

    public List<ElectricVehicle> searchByBatteryType(String batteryType) {
        List<ElectricVehicle> list = new ArrayList<>();
        String sql = "SELECT * FROM ElectricVehicle WHERE BatteryType LIKE ?";
        try {
            PreparedStatement st = c.prepareStatement(sql);
            st.setString(1, "%" + batteryType + "%");
            ResultSet rs = st.executeQuery();
            while (rs.next()) {
                list.add(new ElectricVehicle(
                        rs.getString("VehicleID"),
                        rs.getString("ModelName"),
                        rs.getDouble("Price"),
                        rs.getString("BatteryType")
                ));
            }
        } catch (SQLException ex) {
            Logger.getLogger(ElectricVehicleDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return list;
    }

    public void deleteVehicle(String vehicleID) {
        String sql = "DELETE FROM [dbo].[ElectricVehicle] WHERE [VehicleID] = ?";
        try {
            PreparedStatement st = c.prepareStatement(sql);
            st.setString(1, vehicleID);
            st.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(ElectricVehicleDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public ElectricVehicle getVehicleById(String vehicleID) {
        String sql = "SELECT * FROM [dbo].[ElectricVehicle] WHERE [VehicleID] = ?";
        try {
            PreparedStatement st = c.prepareStatement(sql);
            st.setString(1, vehicleID);
            ResultSet rs = st.executeQuery();
            if (rs.next()) {
                return new ElectricVehicle(
                        rs.getString("VehicleID"),
                        rs.getString("ModelName"),
                        rs.getDouble("Price"),
                        rs.getString("BatteryType")
                );
            }
        } catch (SQLException ex) {
            Logger.getLogger(ElectricVehicleDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

    public void updateVehicle(ElectricVehicle ev) {
        String sql = "UPDATE [dbo].[ElectricVehicle] SET [ModelName] = ?, [Price] = ?, [BatteryType] = ? WHERE [VehicleID] = ?";
        try {
            PreparedStatement st = c.prepareStatement(sql);
            st.setString(1, ev.getModelName());
            st.setDouble(2, ev.getPrice());
            st.setString(3, ev.getBatteryType());
            st.setString(4, ev.getVehicleID());
            st.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(ElectricVehicleDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
