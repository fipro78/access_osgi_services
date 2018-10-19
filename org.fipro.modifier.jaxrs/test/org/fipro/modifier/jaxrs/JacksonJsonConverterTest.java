package org.fipro.modifier.jaxrs;

import static org.junit.Assert.assertEquals;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.util.Arrays;
import java.util.List;

import org.junit.Test;

public class JacksonJsonConverterTest {

	@Test
	public void shouldConvertToJson() throws Exception {
		List<String> values = Arrays.asList("Homer", "Marge", "Bart", "Lisa", "Maggie");

		JacksonJsonConverter<List<String>> converter = new JacksonJsonConverter<>();
		ByteArrayOutputStream out = new ByteArrayOutputStream();
		converter.writeTo(values, List.class, null, null, null, null, out);
		
		assertEquals("[\"Homer\",\"Marge\",\"Bart\",\"Lisa\",\"Maggie\"]", new String(out.toByteArray()));
	}

	@Test
	public void shouldConvertToObject() throws Exception {
		List<String> values = Arrays.asList("Homer", "Marge", "Bart", "Lisa", "Maggie");

		JacksonJsonConverter<List<String>> converter = new JacksonJsonConverter<>();
		InputStream in = new ByteArrayInputStream("[\"Homer\",\"Marge\",\"Bart\",\"Lisa\",\"Maggie\"]".getBytes());
		List<String> result = converter.readFrom(null, List.class, null, null, null, in);
		
		assertEquals(values, result);
	}
}
