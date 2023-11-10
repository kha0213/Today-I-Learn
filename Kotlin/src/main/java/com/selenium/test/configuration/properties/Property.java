package com.selenium.test.configuration.properties;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * Created by Sidelnikov Mikhail on 18.09.14.
 * Annotates field of {@link com.selenium.test.configuration.TestsConfig} fields for setting to this field
 * value from properties - system or file(about file you could find information in {@link com.selenium.test.configuration.properties.PropertyFile} class javadoc
 */
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.FIELD)
public @interface Property {
    String value() default "";
}
