package utils;

public class Queries {
    public static final String GET_ALL_STATUS = "select id_plant, time_stamp, light, soil_humidity, air_humidity, temperature from status";
    public static final String GET_STATUS_BY_ID = "select id_plant, time_stamp, light, soil_humidity, air_humidity, temperature from status";
    public static final String INSERT_STATUS = "insert into status (id_plant, time_stamp, light, soil_humidity, air_humidity, temperature) values (?, NOW(), ?, ?, ?, ?)";
    public static final String DELETE_STATUS = "delete from status where id_plant = ? and time_stamp = ?";

}
