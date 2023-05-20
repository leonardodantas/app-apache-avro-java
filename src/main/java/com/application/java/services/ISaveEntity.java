package com.application.java.services;

import org.apache.avro.generic.GenericRecord;

public interface ISaveEntity {

    void execute(final GenericRecord record);
}
