package com.mongodb.quickstart;

import org.bson.BsonReader;
import org.bson.BsonWriter;
import org.bson.codecs.DecoderContext;
import org.bson.codecs.EncoderContext;
import org.bson.json.JsonWriter;

import java.io.StringWriter;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.Date;

public class MyDateCodec extends CodecBase<Date>  {
    static final DateTimeFormatter DATE_TIME_FORMATTER = DateTimeFormatter.ISO_INSTANT
            .withZone(ZoneId.of("UTC"));

    @Override
    public Date decode(BsonReader bsonReader, DecoderContext decoderContext) {
        long dateRead = bsonReader.readDateTime();
//        StringWriter stringWriter = new StringWriter();
//        new JsonWriter(stringWriter, this.settings).pipe(bsonReader);
//        return Date.parse(stringWriter.toString());
//        return Long.parseLong(stringWriter.toString());
        return new Date(dateRead);
    }

    @Override
    public void encode(BsonWriter bsonWriter, Date date, EncoderContext encoderContext) {
        bsonWriter.writeDateTime(date.getTime());
    }

    @Override
    public Class<Date> getEncoderClass() {
        return Date.class;
    }
}
