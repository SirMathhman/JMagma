package com.meti.app.compile;

import com.meti.api.core.Exception;
import com.meti.api.io.Path;
import com.meti.api.log.Logger;
import com.meti.api.log.SimpleLogger;

import static com.meti.api.io.JavaPath.Root;
import static com.meti.api.log.Logger.Level.Severe;
import static com.meti.api.log.Logger.Level.Warning;

public class Main {
    private static final Logger logger = new SimpleLogger();

    public static void main(String[] args) {
        Path path = Root().resolveName(".build");
        if (!path.exists()) {
            try {
                String format = "Build did not exist at '%s' and will be created.";
                logger.log(Warning, format.formatted(path.toString()));
                path.createAsFile();
            } catch (Exception e) {
                logger.logWithException(Severe, "Failed to create build file", e);
            }
        }
    }
}
