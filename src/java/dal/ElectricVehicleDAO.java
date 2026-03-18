package dal;

import java.sql.PreparedStatement;
import java.sql.SQLException;
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
}
