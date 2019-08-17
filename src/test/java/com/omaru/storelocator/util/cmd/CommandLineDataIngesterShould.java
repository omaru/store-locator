package com.omaru.storelocator.util.cmd;

import com.omaru.storelocator.util.script.ScriptRunner;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
public class CommandLineDataIngesterShould {
    @Mock
    private CommandLineMenu commandLineMenu;
    @Mock
    private ScriptRunner scriptRunner;
    private CommandLineDataIngester commandLineDataIngester;
    @BeforeEach
    public void setUp(){
        commandLineDataIngester = new DefaultCommandLineDataIngester(commandLineMenu,scriptRunner,"test");
    }
    @Test
    void onFileRoutePresentRunFromFile() throws Exception {
        commandLineDataIngester.accept("test");
    }
}
