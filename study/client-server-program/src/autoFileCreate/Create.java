package autoFileCreate;

import autoFileCreate.constant.CreateCRUD;
import autoFileCreate.constant.FileType;

import java.lang.annotation.ElementType;
import java.lang.annotation.Target;



@Target({ElementType.ANNOTATION_TYPE})
public @interface Create {
    FileType createType() default FileType.ALL;
    CreateCRUD crud() default CreateCRUD.CRUD;
    // if deleteField don't find it, realDelete is true
    boolean realDelete() default false;
    String deleteField() default "delYn";

}
