package com.mongodb;

import com.mongodb.*;
import org.bson.BsonType;
import org.bson.codecs.BsonTypeClassMap;
import org.bson.codecs.Codec;
import org.bson.codecs.DateCodec;
import org.bson.codecs.configuration.CodecRegistry;
import org.bson.types.BSONTimestamp;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Pattern;

public class CustomDBObjectCodecProvider extends DBObjectCodecProvider {
    private static BsonTypeClassMap createDefaultBsonTypeClassMap() {
        Map<BsonType, Class<?>> replacements = new HashMap();
        replacements.put(BsonType.REGULAR_EXPRESSION, Pattern.class);
        replacements.put(BsonType.SYMBOL, String.class);
        replacements.put(BsonType.TIMESTAMP, BSONTimestamp.class);
        replacements.put(BsonType.JAVASCRIPT_WITH_SCOPE, (Class<?>) null);
        replacements.put(BsonType.DOCUMENT, (Class<?>) null);
        return new BsonTypeClassMap(replacements);
    }

    @Override
    public <T> Codec<T> get(Class<T> clazz, CodecRegistry registry) {
        if (clazz == BSONTimestamp.class) {
            return (Codec<T>) new BSONTimestampCodec();
        } else if (DBObject.class.isAssignableFrom(clazz) && !List.class.isAssignableFrom(clazz)) {
            return (Codec<T>) new CustomDBObjectCodec(registry, createDefaultBsonTypeClassMap());
        } else {
            return Date.class.isAssignableFrom(clazz) ? (Codec<T>) new DateCodec() : null;
        }
    }
}
