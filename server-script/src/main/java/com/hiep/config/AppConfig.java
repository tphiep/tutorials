package com.hiep.config;

import com.hiep.scripts.ScriptingExecuteEngine;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;

import java.nio.file.Paths;

/**
 * Created by hiep on 8/8/2016.
 */
@Configuration
@ComponentScan("com.hiep.scripts")
@PropertySource("classpath:scripts.properties")
public class AppConfig {

    @Autowired
    private Environment env;

    @Bean
    public ScriptingExecuteEngine scriptEngine(){
        ScriptingExecuteEngine scriptEngine = new ScriptingExecuteEngine();
        scriptEngine.init(Paths.get(env.getProperty("scriptPath")));
        return scriptEngine;
    }
}
