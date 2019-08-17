package com.omaru.storelocator.util.cmd;

import org.apache.commons.cli.CommandLine;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

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
