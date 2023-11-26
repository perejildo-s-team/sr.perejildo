package model;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * The Status class represents the status information of a plant, including various environmental parameters.
 */
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
    private String timeStamp;

    /**
     * Default constructor for the Status class.
     */
    public Status() {
    }

    /**
     * Parameterized constructor for the Status class.
     *
     * @param id           The ID of the plant.
     * @param light        The light level.
     * @param soilHumidity The soil humidity level.
     * @param airHumidity  The air humidity level.
     * @param temperature  The temperature level.
     * @param timeStamp    The timestamp of the status.
     */
    public Status(String id, int light, int soilHumidity, int airHumidity, int temperature, String timeStamp) {
        this.id = id;
        this.light = light;
        this.soilHumidity = soilHumidity;
        this.airHumidity = airHumidity;
        this.temperature = temperature;
        this.timeStamp = timeStamp;
    }

    /**
     * Gets the ID of the plant.
     *
     * @return The ID of the plant.
     */
    public String getId() {
        return id;
    }

    /**
     * Sets the ID of the plant.
     *
     * @param id The ID of the plant.
     */
    public void setId(String id) {
        this.id = id;
    }

    /**
     * Gets the light level.
     *
     * @return The light level.
     */
    public int getLight() {
        return light;
    }

    /**
     * Sets the light level.
     *
     * @param light The light level.
     */
    public void setLight(int light) {
        this.light = light;
    }

    /**
     * Gets the soil humidity level.
     *
     * @return The soil humidity level.
     */
    public int getSoilHumidity() {
        return soilHumidity;
    }

    /**
     * Sets the soil humidity level.
     *
     * @param soilHumidity The soil humidity level.
     */
    public void setSoilHumidity(int soilHumidity) {
        this.soilHumidity = soilHumidity;
    }

    /**
     * Gets the temperature level.
     *
     * @return The temperature level.
     */
    public int getTemperature() {
        return temperature;
    }

    /**
     * Sets the temperature level.
     *
     * @param temperature The temperature level.
     */
    public void setTemperature(int temperature) {
        this.temperature = temperature;
    }

    /**
     * Gets the air humidity level.
     *
     * @return The air humidity level.
     */
    public int getAirHumidity() {
        return airHumidity;
    }

    /**
     * Sets the air humidity level.
     *
     * @param airHumidity The air humidity level.
     */
    public void setAirHumidity(int airHumidity) {
        this.airHumidity = airHumidity;
    }

    /**
     * Gets the timestamp of the status.
     *
     * @return The timestamp of the status.
     */
    public String getTimeStamp() {
        return timeStamp;
    }

    /**
     * Sets the timestamp of the status.
     *
     * @param timeStamp The timestamp of the status.
     */
    public void setTimeStamp(String timeStamp) {
        this.timeStamp = timeStamp;
    }

    /**
     * Returns a string representation of the Status object.
     *
     * @return A string representation of the Status object.
     */
    @Override
    public String toString() {
        return "Status{" +
                "id='" + id + '\'' +
                ", light=" + light +
                ", soilHumidity=" + soilHumidity +
                ", airHumidity=" + airHumidity +
                ", temperature=" + temperature +
                ", timeStamp='" + timeStamp + '\'' +
                '}';
    }
}
