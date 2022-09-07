package com.example.batch.config;

import lombok.extern.slf4j.Slf4j;
import org.springframework.batch.core.JobParameters;
import org.springframework.batch.core.JobParametersInvalidException;
import org.springframework.batch.core.JobParametersValidator;
import org.springframework.util.StringUtils;

@Slf4j
public class ParameterValidator implements JobParametersValidator {
    @Override
    public void validate(JobParameters parameters) throws JobParametersInvalidException {
        log.info("==== ParameterValidator [{}]", parameters.getParameters());
        String fileName = parameters.getString("fileName");

        if (!StringUtils.hasText(fileName)) {

            throw new JobParametersInvalidException("=== fileName 파라미터가 없습니다!!!");
        }
        if (!StringUtils.endsWithIgnoreCase(fileName, "csv")) {
            throw new JobParametersInvalidException("=== 파일 이름의 끝이 csv가 아닙니다.");
        }
    }
}
