package dao;

import model.Status;
import utils.Queries;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

/**
 * The StatusDAO class provides data access methods for interacting with the 'status' table in the database.
 */
public class StatusDAO {
    private final Connection con;

    /**
     * Constructs a StatusDAO instance with a database connection.
     *
     * @param con The database connection.
     */
    public StatusDAO(Connection con) {
        this.con = con;
    }

    /**
     * Retrieves all status records from the 'status' table.
     *
     * @return An ArrayList of Status objects representing the retrieved status records.
     */
    public ArrayList<Status> getAllStatus() {
        ArrayList<Status> statusList = new ArrayList<>();

        try (Statement st = con.createStatement();
             ResultSet rs = st.executeQuery(Queries.GET_ALL_STATUS)) {
            while (rs.next()) {
                Status status = mapResultSetToStatus(rs);
                statusList.add(status);
            }
        } catch (SQLException e) {
            handleSQLException(e);
        }
        return statusList;
    }

    /**
     * Inserts a new status record into the 'status' table.
     *
     * @param status The Status object to be inserted.
     */
    public void insertStatus(Status status) {
        try (PreparedStatement ps = con.prepareStatement(Queries.INSERT_STATUS)) {
            setStatementValues(ps, status);
            ps.execute();
        } catch (SQLException e) {
            handleSQLException(e);
        }
    }

    /**
     * Deletes a status record from the 'status' table.
     *
     * @param status The Status object representing the record to be deleted.
     */
    public void deleteStatus(Status status) {
        try (PreparedStatement ps = con.prepareStatement(Queries.DELETE_STATUS)) {
            ps.setString(1, status.getId());
            ps.setString(2, status.getTimeStamp());
            ps.executeUpdate();
        } catch (SQLException e) {
            handleSQLException(e);
        }
    }

    /**
     * Maps a ResultSet to a Status object.
     *
     * @param rs The ResultSet containing status data.
     * @return A Status object with data from the ResultSet.
     * @throws SQLException If a database access error occurs.
     */
    private Status mapResultSetToStatus(ResultSet rs) throws SQLException {
        Status status = new Status();
        status.setId(rs.getString("id_plant"));
        status.setLight(rs.getInt("light"));
        status.setSoilHumidity(rs.getInt("soil_humidity"));
        status.setAirHumidity(rs.getInt("air_humidity"));
        status.setTemperature(rs.getInt("temperature"));
        status.setTimeStamp(rs.getString("time_stamp"));
        return status;
    }

    /**
     * Sets PreparedStatement values based on the provided Status object.
     *
     * @param ps     The PreparedStatement to set values for.
     * @param status The Status object containing data.
     * @throws SQLException If a database access error occurs.
     */
    private void setStatementValues(PreparedStatement ps, Status status) throws SQLException {
        ps.setString(1, status.getId());
        ps.setInt(2, status.getLight());
        ps.setInt(3, status.getSoilHumidity());
        ps.setInt(4, status.getAirHumidity());
        ps.setInt(5, status.getTemperature());
    }

    /**
     * Handles SQLException by printing the stack trace. In a real application, consider logging or handling
     * the exception more appropriately based on your use case.
     *
     * @param e The SQLException to handle.
     */
    private void handleSQLException(SQLException e) {
        e.printStackTrace();
    }
}
