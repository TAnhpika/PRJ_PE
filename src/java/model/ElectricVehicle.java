package model;

/**
 *
 * @author ADMIN
 */
public class ElectricVehicle {
    private String vehicleID;
    private String modelName;
    private double price;
    private String batteryType;

    public ElectricVehicle() {
    }

    public ElectricVehicle(String vehicleID, String modelName, double price, String batteryType) {
        this.vehicleID = vehicleID;
        this.modelName = modelName;
        this.price = price;
        this.batteryType = batteryType;
    }

    public String getVehicleID() {
        return vehicleID;
    }

    public void setVehicleID(String vehicleID) {
        this.vehicleID = vehicleID;
    }

    public String getModelName() {
        return modelName;
    }

    public void setModelName(String modelName) {
        this.modelName = modelName;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public String getBatteryType() {
        return batteryType;
    }

    public void setBatteryType(String batteryType) {
        this.batteryType = batteryType;
    }
}
