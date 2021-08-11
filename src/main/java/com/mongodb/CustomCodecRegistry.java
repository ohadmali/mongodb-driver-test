package com.mongodb;

import com.mongodb.client.gridfs.codecs.GridFSFileCodecProvider;
import com.mongodb.client.model.geojson.codecs.GeoJsonCodecProvider;
import org.bson.codecs.*;
import org.bson.codecs.configuration.CodecRegistries;
import org.bson.codecs.configuration.CodecRegistry;
import org.bson.codecs.jsr310.Jsr310CodecProvider;

import java.util.Arrays;

public class CustomCodecRegistry {
    private static final CodecRegistry CUSTOM_CODEC_REGISTRY = CodecRegistries.fromProviders(
            Arrays.asList(
                    new ValueCodecProvider(),
                    new BsonValueCodecProvider(),
                    new DBRefCodecProvider(),
                    //new DBObjectCodecProvider(),
                    new CustomDBObjectCodecProvider(),
                    new DocumentCodecProvider(new DocumentToDBRefTransformer()),
                    new IterableCodecProvider(new DocumentToDBRefTransformer()),
                    new MapCodecProvider(new DocumentToDBRefTransformer()),
                    new GeoJsonCodecProvider(),
                    new GridFSFileCodecProvider(),
                    new Jsr310CodecProvider(),
                    new BsonCodecProvider()
            )
    );

    public static CodecRegistry getCustomCodecRegistry(){
        return CUSTOM_CODEC_REGISTRY;
    }

}
