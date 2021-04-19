package autoFileCreate;

import autoFileCreate.constant.FileType;

import java.util.logging.Logger;

/**
 * 2021-03-10
 * Created by tatujjang@gmail.com
 * Github : https://github.com/kha0213/
 */
public class CreateFileSystem {
    private final static Logger LOGGER = Logger.getGlobal();
    /**
     *
     */
    
    private ConfigForCreateImpl config;


    void createFileAll(){

    }

    void createFile(FileType type){
        LOGGER.info("[CREATE " + type + "]");

    }
}
