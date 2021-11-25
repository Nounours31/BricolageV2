package sfa.bricolage.v2.server.api;

import javax.ws.rs.ApplicationPath;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.MultivaluedMap;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;
import javax.ws.rs.core.UriInfo;

import com.google.gson.Gson;

import sfa.bricolage.v2.db.object.User;
import sfa.bricolage.v2.global.E4ALogger;

//@ApplicationPath("V1.0.0")
//public class BricolageRestService extends javax.ws.rs.core.Application {
@Path("V1.0.0")
public class BricolageRestService  {
	E4ALogger _logger = E4ALogger.getLogger("BricolageRestService");
	
	
/*	@GET
	@Path("/items/{itemId}")
    @Produces(MediaType.APPLICATION_JSON)
    public int test() {
        return 0;
    }

	@GET
	@Path("/{orderId}/items/{itemId}")
    @Produces(MediaType.APPLICATION_JSON)
    public int test2(@PathParam("orderId") String orderId, @PathParam("itemId") String itemId) {
        return 0;
    }
	
	@GET
    public String getOrders(@DefaultValue("1") @QueryParam("stateCode") String stateCode) {
        return stateCode != null ? "1"  :  "0";
    }
	
	 @GET
	 public String getOrders(@Context UriInfo uriInfo) {
	        MultivaluedMap<String, String> params =
	                uriInfo.getQueryParameters();
	        return "";
	 }

	 @GET
	 public Response get() {
		 Status s = Status.NOT_IMPLEMENTED;
	        Response r =  null;
	        r = Response.status(s).entity("x").build();
	        r =  Response.serverError().entity("x").build();
	        Json json;
	        r =  Response.ok (json, MediaType.APPLICATION_JSON).build();
	 }
*/
	 @GET
	@Path("/user/{id}")
    @Produces(MediaType.APPLICATION_JSON)
	 public Response getUserInfo(@PathParam("id") int id) {
		 _logger.debug(String.format("/user/%d", id));
		 User u = User.buildFromDB(id);
		 
		 Gson gson = new Gson();
		 Response r =  Response.ok (gson.toJson(u), MediaType.APPLICATION_JSON).build();
		 return r;
	 }
}
