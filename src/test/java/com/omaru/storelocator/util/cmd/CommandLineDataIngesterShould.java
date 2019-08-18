package com.omaru.storelocator.util.cmd;

import com.omaru.storelocator.util.script.ScriptRunner;
import org.apache.commons.cli.CommandLine;
import org.apache.commons.cli.Option;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class CommandLineDataIngesterShould {
    @Mock
    private CommandLineMenu commandLineMenu;
    @Mock
    private ScriptRunner scriptRunner;
    private CommandLineDataIngester commandLineDataIngester;
    @BeforeEach
    void setUp(){
        commandLineDataIngester = new DefaultCommandLineDataIngester(commandLineMenu,scriptRunner,"test");
    }
    @Test
    void onFileRoutePresentRunFromFile() throws Exception {
        CommandLine.Builder builder = new CommandLine.Builder();
        builder.addOption(new Option("i","")).addArg("/myFile");
        CommandLine cmd =builder.build();
        when(commandLineMenu.getCommandLine(any())).thenReturn(cmd);
        commandLineDataIngester.accept("test");
    }
}