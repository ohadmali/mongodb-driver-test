package com.mongodb.quickstart;

import org.bson.codecs.Codec;
import org.bson.json.JsonMode;
import org.bson.json.JsonWriterSettings;

import java.util.Date;

public abstract class CodecBase<T> implements Codec<T> {
    protected JsonWriterSettings settings;

    public CodecBase(){
        this.settings = JsonWriterSettings.builder()
                .outputMode(JsonMode.STRICT)
                .int64Converter((v,w) -> w.writeNumber(v.toString()))
                .dateTimeConverter(new DBObjectDateConverter())
                .build();
    }
}
