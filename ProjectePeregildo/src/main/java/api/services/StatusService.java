package api.services;

import dao.StatusDAO;
import utils.DBConnection;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@Path("/status")
@Produces(value = MediaType.APPLICATION_JSON)
public class StatusService {
    @GET
    public Response getAllStatus() {
        DBConnection dbConnection = new DBConnection();
        StatusDAO dao = new StatusDAO(dbConnection.connect());
        return Response.ok(dao.getAllStatus()).build();
    }
}
