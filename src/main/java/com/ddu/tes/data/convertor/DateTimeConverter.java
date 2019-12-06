package com.ddu.tes.data.convertor;

import javax.persistence.AttributeConverter;
import javax.persistence.Converter;
import java.sql.Timestamp;
import java.util.Date;

/**
 * @Author abraham 12/02/2018
 */
@Converter(autoApply = true)
public class DateTimeConverter implements AttributeConverter<Date, Timestamp> {

    @Override
    public Timestamp convertToDatabaseColumn(Date attribute) {

        return null != attribute ? new Timestamp(attribute.getTime()) : null;
    }

    @Override
    public Date convertToEntityAttribute(Timestamp dbData) {

        return null != dbData ? dbData : null;
    }
}
