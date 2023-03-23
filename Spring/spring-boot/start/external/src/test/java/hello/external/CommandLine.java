package hello.external;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.DefaultApplicationArguments;

import java.util.List;
import java.util.Set;

@Slf4j
public class CommandLine {
    public static void main(String[] args) {
        for (String arg : args) {
            log.info("arg: {}", arg);
        }

        ApplicationArguments appArgs = new DefaultApplicationArguments(args);

        log.info("SourceArgs = {}", List.of(appArgs.getSourceArgs()));
        log.info("NonOptionArgs = {}", List.of(appArgs.getNonOptionArgs()));
        log.info("OptionNames = {}", List.of(appArgs.getOptionNames()));

        Set<String> optionNames = appArgs.getOptionNames();
        for (String optionName : optionNames) {
            log.info("optionName: {}, optionValues: {}", optionName, List.of(appArgs.getOptionValues(optionName)));
        }
    }
}
