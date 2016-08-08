package com.hiep.scripts;

import javax.script.Invocable;
import javax.script.ScriptEngine;
import javax.script.ScriptEngineManager;
import javax.script.ScriptException;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

/**
 * Created by hiep on 12/27/2015.
 */
public class ScriptingExecuteEngine {
    private ScriptEngine engine;
    private Invocable invocable;
    private Path scriptPath;

    public void init(Path path) {
        ScriptEngineManager engineManager = new ScriptEngineManager();
        engine = engineManager.getEngineByName("nashorn");
        if (engine instanceof Invocable) {
            invocable = (Invocable) engine;
        }
        this.scriptPath = path;
        this.loadScript();
    }

    private void loadScript() {
        // Make sure the script file exists. If not, print the full path of
        // the script file and terminate the program.
        if (! Files.exists(scriptPath) ) {
            System.out.println(scriptPath.toAbsolutePath() +
                    " does not exist.");
            return;
        }
        try {
            engine.eval(Files.newBufferedReader(scriptPath));
        } catch (ScriptException | IOException e) {
            e.printStackTrace();
        }
    }

    public Object invoke(String func, Object... params) {
        Object result = null;
        try {
            if (invocable == null) {
                System.out.println("Invoking procedure is not supported.");
            } else {
                result = invocable.invokeFunction(func, params);
            }
        } catch (ScriptException | NoSuchMethodException e) {
            e.printStackTrace();
        }

        return result;
    }
}
