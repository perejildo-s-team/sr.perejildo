package utils;

public class DBConstants {
    public static final String SCHEMA = "perejildo";
    public static final String ADRESS = "34.18.19.52:3306";
    public static final String CONNECTION =
            "jdbc:mysql://" + ADRESS + "/" +
                    SCHEMA +
                    "?useUnicode=true&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC&useSSL=false&allowPublicKeyRetrieval=True";

}
