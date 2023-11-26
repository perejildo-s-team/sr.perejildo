package api.services;

import com.fasterxml.jackson.databind.ObjectMapper;
import dao.StatusDAO;
import model.Status;
import utils.DBConnection;

import javax.servlet.http.HttpServletResponse;
import javax.ws.rs.core.Response;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;

public class StatusService {

    public void getAllStatus(HttpServletResponse resp) {
        resp.setContentType("application/json");
        DBConnection dbConnection = new DBConnection();
        try {
            PrintWriter writer = resp.getWriter();
            StatusDAO dao = new StatusDAO(dbConnection.connect());
            ObjectMapper mapper = new ObjectMapper();
            try {
                writer.print(mapper.writeValueAsString(dao.getAllStatus()));
            } catch (NullPointerException ex) {
                writer.print(mapper.writeValueAsString(""));
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        dbConnection.disconnect();
    }
}
