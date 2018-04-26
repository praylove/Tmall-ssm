package com.sherl.tmall.util;

import java.io.IOException;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.sherl.tmall.entity.Status;

public class Enum2Json extends JsonSerializer<Status> {

	@Override
	public void serialize(Status s, JsonGenerator arg1, SerializerProvider arg2) throws IOException {
		arg1.writeString(s.getName());
	}

}
