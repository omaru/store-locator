package com.omaru.storelocator.util.cmd;

import com.omaru.storelocator.util.cmd.exception.CommandLineDataIngesterException;
import org.apache.commons.cli.CommandLine;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;

class CommandLineMenuShould {
    @Test
    void beAbleToPrintMenu(){
        CommandLineMenu menu = new DefaultCommandLineMenu("appName");
        menu.print();
    }
    @Test
    void beAbleToRetrieveCommandLine(){
        CommandLineMenu menu = new DefaultCommandLineMenu("appName");
        CommandLine cmd = menu.getCommandLine();
        assertThat(cmd).isNotNull();
    }
}
