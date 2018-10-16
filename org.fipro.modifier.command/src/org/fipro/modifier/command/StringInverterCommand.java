package org.fipro.modifier.command;

import java.util.List;

import org.apache.felix.service.command.CommandProcessor;
import org.fipro.modifier.api.StringModifier;
import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;

@Component(
    property= {
        CommandProcessor.COMMAND_SCOPE + ":String=fipro",
        CommandProcessor.COMMAND_FUNCTION + ":String=modify"
    },
    service=StringInverterCommand.class
)
public class StringInverterCommand {
 
	@Reference
	private volatile List<StringModifier> modifier;
 
    public void modify(String input) {
    	modifier.forEach(x -> System.out.println(x.modify(input)));
    }
    
}