package org.fipro.modifier.remote.client;

import org.apache.felix.service.command.CommandProcessor;
import org.fipro.modifier.remote.api.ModifierService;
import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;

@Component(
    property= {
        CommandProcessor.COMMAND_SCOPE + ":String=fipro",
        CommandProcessor.COMMAND_FUNCTION + ":String=mod"
    },
    service=ModifierCommand.class
)
public class ModifierCommand {
 
	@Reference
    private ModifierService modifier;
 
    public void mod(String input) {
    	modifier.getModifications(input).forEach(mod -> System.out.println(mod));
    }
}