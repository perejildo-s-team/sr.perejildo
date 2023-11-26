package utils;

/**
 * The DBConstants class contains constants related to the database connection.
 */
public class DBConstants {
    /**
     * The schema name for the database.
     */
    public static final String SCHEMA = "perejildo";

    /**
     * The IP address and port number of the database server.
     */
    public static final String ADDRESS = "34.18.19.52:3306";

    /**
     * The complete JDBC connection URL for the database.
     * Includes the schema, useUnicode, timezone settings, SSL configuration, and other parameters.
     */
    public static final String CONNECTION =
            "jdbc:mysql://" + ADDRESS + "/" +
                    SCHEMA +
                    "?useUnicode=true&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC&useSSL=false&allowPublicKeyRetrieval=True";
}
