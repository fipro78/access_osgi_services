package org.fipro.modifier.jaxrs;

import java.util.List;
import java.util.stream.Collectors;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import org.fipro.modifier.api.StringModifier;
import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;
import org.osgi.service.jaxrs.whiteboard.propertytypes.JSONRequired;
import org.osgi.service.jaxrs.whiteboard.propertytypes.JaxrsResource;

// The jax-rs path annotation for this service.
@Path("/modify")
// The JAX-RS annotation to specify that JSON is produced.
@Produces(MediaType.APPLICATION_JSON)
// Mark this class as a OSGi DS component. 
@Component(service=ModifierServiceImpl.class)
// Mark this class as a JAX-RS resource type that should be processed by the JAX-RS whiteboard.
@JaxrsResource
// Mark this class to requiring a serializer capable of supporting JSON.
@JSONRequired
public class ModifierServiceImpl {
	 
    @Reference
    private volatile List<StringModifier> modifier;

	@GET
	@Path("/{value}")
	public List<String> getModifications(@PathParam("value") String value) {
		return modifier.stream().map(x -> x.modify(value)).collect(Collectors.toList());
	}

}
