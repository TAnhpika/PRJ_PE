-- 1. Tạo Database
CREATE DATABASE EV_Workshop_Se1501;
GO

-- 2. Sử dụng Database vừa tạo
USE EV_Workshop_Se1501;
GO

-- 3. Tạo bảng ElectricVehicle
CREATE TABLE ElectricVehicle (
    VehicleID VARCHAR(20) PRIMARY KEY,
    ModelName NVARCHAR(100) NOT NULL,
    Price FLOAT NOT NULL,
    BatteryType NVARCHAR(50) NOT NULL
);
GO

-- 4. Chèn dữ liệu mẫu (Initial Data)
INSERT INTO ElectricVehicle (VehicleID, ModelName, Price, BatteryType)
VALUES ('EV01', 'VinFast VF8', 45000, 'Lithium-ion');

INSERT INTO ElectricVehicle (VehicleID, ModelName, Price, BatteryType)
VALUES ('EV02', 'Tesla Model 3', 35000, 'LFP');
GO
