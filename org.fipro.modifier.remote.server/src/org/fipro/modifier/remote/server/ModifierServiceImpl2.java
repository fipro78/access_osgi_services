package org.fipro.modifier.remote.server;

import java.util.List;
import java.util.stream.Collectors;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import org.fipro.modifier.api.StringModifier;
import org.fipro.modifier.remote.api.ModifierService;
import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;

//The jax-rs path annotation for this service
@Path("/test")
//The OSGi DS component annotation. 
@Component(
		immediate = true, 
		property = { 
				"service.exported.interfaces=*", 
				"service.intents=osgi.async",
				"service.intents=jaxrs",
				"osgi.basic.timeout=50000" })
public class ModifierServiceImpl2 implements ModifierService {
	 
    @Reference
    private volatile List<StringModifier> modifier;

	@GET
	@Produces(MediaType.APPLICATION_JSON)
	@Path("/{value}")
	@Override
	public List<String> getModifications(@PathParam("value") String value) {
		return modifier.stream().map(x -> x.modify(value)).collect(Collectors.toList());
	}

}
