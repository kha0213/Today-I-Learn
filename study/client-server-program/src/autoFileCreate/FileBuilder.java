package autoFileCreate;

import autoFileCreate.constant.BaseConstant;
import autoFileCreate.constant.FileType;

import java.util.*;
import java.util.stream.Collector;
import java.util.stream.Collectors;

import static autoFileCreate.constant.BaseConstant.SEMI_COLON;
import static autoFileCreate.constant.FileType.SERVICE;
import static java.util.stream.Collectors.*;

/**
 * 2021-03-19
 * Created by tatujjang@gmail.com
 * Github : https://github.com/kha0213/
 */

public class FileBuilder {
    // (SERVICE, SERVICEIMPL, CONTROLLER, XML)
    private final FileType fileType;
    private final String packageName;
    private final Set<String> imports;
    private final String className;
    private final List<MethodInClass> methods;

    private FileBuilder(Builder builder) {
        this.fileType = builder.fileType;
        this.packageName = builder.packageName;
        this.imports = builder.imports;
        this.className = builder.className;
        this.methods = builder.methods;
    }

    public static class Builder {

        private FileType fileType = SERVICE;
        private String packageName;
        private Set<String> imports = new HashSet<>();
        private String className;
        private List<MethodInClass> methods = new ArrayList<>();

        public FileBuilder build() {
            return new FileBuilder(this);
        }

        public Builder fileType(FileType input) {
            fileType = input;
            return this;
        }
        public Builder packageName(String input) {
            packageName = input;
            return this;
        }
        public Builder imports(Collection<String> input) {
            imports.addAll(input);
            return this;
        }

        public Builder importOne(String input) {
            imports.add(input);
            return this;
        }

        /**
         * <pre>
         * Likewise in java import
         * packageName + "." + ClassName + semi colon
         * </pre>
         * @param input
         * @return Builder
         */
        @SuppressWarnings("rawtypes")
        public Builder importsByClass(Collection<Class> input) {
            imports.addAll( input.stream()
                              .map(cla -> cla.getTypeName() + SEMI_COLON)
                              .collect(toSet()));
            return this;
        }

        @SuppressWarnings("rawtypes")
        public Builder importsByClass(Set<Class> input) {
            imports = input.stream()
                    .map(cla -> cla.getTypeName() + SEMI_COLON)
                    .collect(toSet());
            return this;
        }

        public Builder className(String input) {
            className = input;
            return this;
        }
    }
}
