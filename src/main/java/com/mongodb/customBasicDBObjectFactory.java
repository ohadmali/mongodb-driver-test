package com.mongodb;

import com.mongodb.DBObject;
import com.mongodb.DBObjectFactory;

import java.util.List;

public class customBasicDBObjectFactory implements DBObjectFactory {
    @Override
    public DBObject getInstance() {
        return new CustomBasicDBObject();
    }

    @Override
    public DBObject getInstance(List<String> list) {
        return new CustomBasicDBObject();
    }
}
