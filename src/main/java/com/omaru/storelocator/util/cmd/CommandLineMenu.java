package com.omaru.storelocator.util.cmd;

import org.apache.commons.cli.CommandLine;

public interface CommandLineMenu {
        void print();
        CommandLine getCommandLine(String...args);
}