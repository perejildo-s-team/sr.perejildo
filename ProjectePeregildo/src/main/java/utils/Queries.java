package utils;

/**
 * The Queries class contains SQL queries related to the 'status' table in the database.
 */
public class Queries {
    /**
     * SQL query to retrieve all status records from the 'status' table.
     */
    public static final String GET_ALL_STATUS = "select id_plant, time_stamp, light, soil_humidity, air_humidity, temperature from status";

    /**
     * SQL query to retrieve a specific status record by ID from the 'status' table.
     */
    public static final String GET_STATUS_BY_ID = "select id_plant, time_stamp, light, soil_humidity, air_humidity, temperature from status";

    /**
     * SQL query to insert a new status record into the 'status' table.
     * Uses NOW() to automatically set the current timestamp.
     */
    public static final String INSERT_STATUS = "insert into status (id_plant, time_stamp, light, soil_humidity, air_humidity, temperature) values (?, NOW(), ?, ?, ?, ?)";

    /**
     * SQL query to delete a specific status record from the 'status' table by ID and timestamp.
     */
    public static final String DELETE_STATUS = "delete from status where id_plant = ? and time_stamp = ?";
}
