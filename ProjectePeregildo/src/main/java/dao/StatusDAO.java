package dao;

import model.Status;
import utils.Queries;

import java.sql.*;
import java.util.ArrayList;

public class StatusDAO {
    private Connection con;

    public StatusDAO(Connection con) {
        this.con = con;
    }

    public ArrayList<Status> getAllStatus() {
        ArrayList<Status> statusList = new ArrayList<>();

        try (Statement st = con.createStatement()) {
            try (ResultSet rs = st.executeQuery(Queries.GET_ALL_STATUS)) {
                while(rs.next()) {
                    Status status = new Status();
                    status.setId(rs.getString("id_plant"));
                    status.setLight(rs.getInt("light"));
                    status.setSoilHumidity(rs.getInt("soil_humidity"));
                    status.setAirHumidity(rs.getInt("air_humidity"));
                    status.setTemperature(rs.getInt("temperature"));
                    status.setTimeStamp(rs.getString("time_stamp"));
                    statusList.add(status);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return statusList;
    }

    public void insertStatus(Status status) {
        try (PreparedStatement ps =  con.prepareStatement(Queries.INSERT_STATUS)) {
            ps.setString(1, status.getId());
            ps.setInt(2, status.getLight());
            ps.setInt(3, status.getSoilHumidity());
            ps.setInt(4, status.getAirHumidity());
            ps.setInt(5, status.getTemperature());
            ps.execute();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void deleteStatus(Status status) {
        String deleteQuery = Queries.DELETE_STATUS;

        try (PreparedStatement ps = con.prepareStatement(Queries.DELETE_STATUS)) {
            ps.setString(1, status.getId());
            ps.setString(2, status.getTimeStamp());
            ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
