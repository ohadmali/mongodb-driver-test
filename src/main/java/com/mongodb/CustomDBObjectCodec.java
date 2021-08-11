package com.mongodb;

import com.mongodb.DBObjectCodec;
import org.bson.codecs.BsonTypeClassMap;
import org.bson.codecs.configuration.CodecRegistry;

public class CustomDBObjectCodec extends DBObjectCodec {
    public CustomDBObjectCodec(CodecRegistry registry, BsonTypeClassMap defaultBsonTypeClassMap) {
        super(registry, defaultBsonTypeClassMap, new customBasicDBObjectFactory());
    }
}
