package com.lemberski.cachedemo.config;

import java.util.Arrays;
import java.util.List;
import java.util.UUID;

import org.springframework.context.annotation.Configuration;
import org.springframework.core.convert.converter.Converter;
import org.springframework.data.convert.ReadingConverter;
import org.springframework.data.convert.WritingConverter;
import org.springframework.data.jdbc.repository.config.AbstractJdbcConfiguration;

@Configuration
public class ConverterConfig extends AbstractJdbcConfiguration {

    @Override
    protected List<?> userConverters() {
        return Arrays.asList(new UuidToStringConverter(), new StringToUuidConverter());
    }

    @WritingConverter
    public class UuidToStringConverter implements Converter<UUID, String> {
        @Override
        public String convert(UUID source) {
            return source != null ? source.toString() : null;
        }
    }

    @ReadingConverter
    public class StringToUuidConverter implements Converter<String, UUID> {
        @Override
        public UUID convert(String source) {
            return source != null ? UUID.fromString(source) : null;
        }
    }

}
