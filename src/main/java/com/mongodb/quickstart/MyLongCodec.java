package com.mongodb.quickstart;

import org.bson.codecs.Codec;
import org.bson.BsonReader;
import org.bson.BsonWriter;
import org.bson.codecs.DecoderContext;
import org.bson.codecs.EncoderContext;
import org.bson.json.JsonMode;
import org.bson.json.JsonWriter;
import org.bson.json.JsonWriterSettings;

import java.io.StringWriter;
import java.util.Date;

public class MyLongCodec extends CodecBase<Long>  {
    @Override
    public void encode(final BsonWriter writer, final Long value, final EncoderContext encoderContext) {
        writer.writeInt64(value);
    }


    @Override
    public Class<Long> getEncoderClass() {
        return Long.class;
    }

    @Override
    public Long decode(BsonReader bsonReader, DecoderContext decoderContext) {
//        StringWriter stringWriter = new StringWriter();
//        new JsonWriter(stringWriter, this.settings).pipe(bsonReader);
//        return Long.parseLong(stringWriter.toString());
//        return Long.parseLong(stringWriter.toString());
        long x = bsonReader.readInt64();
        return new Long(5);
    }
}
