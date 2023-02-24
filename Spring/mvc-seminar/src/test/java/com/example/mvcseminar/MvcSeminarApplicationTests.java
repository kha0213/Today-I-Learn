package com.example.mvcseminar;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import javax.script.*;
import java.util.List;
import java.util.function.Predicate;

@SpringBootTest
class MvcSeminarApplicationTests {

    @Test
    void contextLoads() {

        ScriptEngineManager mgr = new ScriptEngineManager();
        List<ScriptEngineFactory> engineFactories = mgr.getEngineFactories();

        for (ScriptEngineFactory engine : engineFactories) {
            System.out.println("engine.getEngineName() = " + engine.getEngineName());
        }

    }

}
