package com.example.a23b_11345a_l10.Models;

public class Vehicle {
    public enum eType {
        NA,
        GASOLINE,
        DIESEL,
        HYBRID,
        ELECTRIC,
        HYDROGEN
    }

    private int manufactureYear = 0;
    private String licensePlate = "";
    private int wheelCount = 0;
    private String brand = "";
    private double price = 0.0;
    private eType type = eType.NA;

    public Vehicle() {    }

    public int getManufactureYear() {
        return manufactureYear;
    }

    public Vehicle setManufactureYear(int manufactureYear) {
        this.manufactureYear = manufactureYear;
        return this;
    }

    public String getLicensePlate() {
        return licensePlate;
    }

    public Vehicle setLicensePlate(String licensePlate) {
        this.licensePlate = licensePlate;
        return this;
    }

    public int getWheelCount() {
        return wheelCount;
    }

    public Vehicle setWheelCount(int wheelCount) {
        this.wheelCount = wheelCount;
        return this;
    }

    public String getBrand() {
        return brand;
    }

    public Vehicle setBrand(String brand) {
        this.brand = brand;
        return this;
    }

    public double getPrice() {
        return price;
    }

    public Vehicle setPrice(double price) {
        this.price = price;
        return this;
    }

    public eType getType() {
        return type;
    }

    public Vehicle setType(eType type) {
        this.type = type;
        return this;
    }

    @Override
    public String toString() {
        return "Vehicle{" +
                "manufactureYear=" + manufactureYear +
                ", licensePlate='" + licensePlate + '\'' +
                ", wheelCount=" + wheelCount +
                ", brand='" + brand + '\'' +
                ", price=" + price +
                ", type=" + type +
                '}';
    }
}
