/**
 * 
 */
package com.foodex.user.security;

import java.io.IOException;

import org.codehaus.jackson.JsonParser;
import org.codehaus.jackson.JsonProcessingException;
import org.codehaus.jackson.map.DeserializationContext;
import org.codehaus.jackson.map.JsonDeserializer;

/**
 * @author Cognizant
 * @version : 1.0
 */
public class DecryptDeSerializer extends JsonDeserializer<String> {

  /*  private static final Logger LOGGER = Logger
	    .getLogger(DecryptDeSerializer.class);*/

    @Override
    public String deserialize(JsonParser jsonParser, DeserializationContext ctxt)
	    throws IOException, JsonProcessingException {
	try {
	    return CipherHandler.decrypt(jsonParser.getText());
	} catch (Exception exception) {
	    throw new IllegalStateException(exception);
	}
    }

}
