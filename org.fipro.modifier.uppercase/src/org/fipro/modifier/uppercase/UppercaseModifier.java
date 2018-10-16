package org.fipro.modifier.uppercase;

import org.fipro.modifier.api.StringModifier;
import org.osgi.service.component.annotations.Component;

@Component
public class UppercaseModifier implements StringModifier {

	@Override
	public String modify(String input) {
		return input.toUpperCase();
	}

}
