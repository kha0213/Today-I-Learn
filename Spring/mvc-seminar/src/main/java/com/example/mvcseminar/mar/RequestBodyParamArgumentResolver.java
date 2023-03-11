package com.example.mvcseminar.mar;

import org.springframework.core.MethodParameter;
import org.springframework.web.service.invoker.HttpRequestValues;
import org.springframework.web.service.invoker.HttpServiceArgumentResolver;

public class RequestBodyParamArgumentResolver implements HttpServiceArgumentResolver {

    @Override
    public boolean resolve(Object argument, MethodParameter parameter, HttpRequestValues.Builder requestValues) {


        RequestBodyParam annot = parameter.getParameterAnnotation(RequestBodyParam.class);
        if (annot == null) {
            return false;
        }

        if (argument != null) {
            requestValues.setBodyValue(argument);
        }

        return true;
    }

    private void validateType(Class<?> type) {
        if( type == null ) {
            throw new IllegalArgumentException("type is null");
        }
        if (type.isPrimitive() ||
                type == Character.class ||
                type == String.class ||
                type == Short.class ||
                type == Integer.class ||
                type == Long.class ||
                type == Boolean.class ||
                type == Double.class ||
                type == Float.class) {
            return;
        }
        throw new IllegalArgumentException("type is not supported");
    }
}
