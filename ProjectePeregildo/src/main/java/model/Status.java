package model;

import com.fasterxml.jackson.annotation.JsonProperty;

public class Status {
    @JsonProperty("id")
    private String id;
    @JsonProperty("light")
    private int light;
    @JsonProperty("soil_humidity")
    private int soilHumidity;
    @JsonProperty("air_humidity")
    private int airHumidity;
    @JsonProperty("temperature")
    private int temperature;

    public Status() {
    }

    public Status(String id, int light, int soilHumidity, int temperature) {
        this.id = id;
        this.light = light;
        this.soilHumidity = soilHumidity;
        this.temperature = temperature;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public int getLight() {
        return light;
    }

    public void setLight(int light) {
        this.light = light;
    }

    public int getSoilHumidity() {
        return soilHumidity;
    }

    public void setSoilHumidity(int soilHumidity) {
        this.soilHumidity = soilHumidity;
    }

    public int getTemperature() {
        return temperature;
    }

    public void setTemperature(int temperature) {
        this.temperature = temperature;
    }

    public int getAirHumidity() {
        return airHumidity;
    }

    public void setAirHumidity(int airHumidity) {
        this.airHumidity = airHumidity;
    }

    @Override
    public String toString() {
        return "Status{" +
                "id='" + id + '\'' +
                ", light=" + light +
                ", soilHumidity=" + soilHumidity +
                ", airHumidity=" + airHumidity +
                ", temperature=" + temperature +
                '}';
    }
}
