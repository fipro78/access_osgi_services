package org.fipro.modifier.remote.api;

import java.util.List;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

@Path("/modify")
public interface ModifierService {

	@GET
	@Produces(MediaType.APPLICATION_JSON)
	@Path("/{value}")
	List<String> getModifications(@PathParam("value") String value);

}
