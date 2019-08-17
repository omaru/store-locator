package com.omaru.storelocator.util.script;

import org.springframework.core.io.Resource;

import java.io.InputStream;

public interface ScriptRunner {
    void run(Resource resource);
    void run(InputStream stream);
}