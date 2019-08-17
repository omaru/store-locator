package com.omaru.storelocator.util.cmd;

import com.omaru.storelocator.util.cmd.exception.CommandLineDataIngesterException;
import org.apache.commons.cli.*;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class DefaultCommandLineMenu implements CommandLineMenu {
    private String applicationName;
    private Options options = new Options();
    private HelpFormatter formatter = new HelpFormatter();
    private CommandLineParser parser = new DefaultParser();
    public DefaultCommandLineMenu(@Value("${spring.application.name}")String applicationName){
        this.applicationName = applicationName;
        setOptions();
    }

    private void setOptions() {
        Option inputFileOption = new Option("i",true,"ingest testing data from path," +
                "if none provided reads from classpath by default");
        inputFileOption.setArgName("/path/to/file.json");
        options.addOption(inputFileOption);
    }

    @Override
    public void print() {
        formatter.printHelp(applicationName, options);
    }

    @Override
    public CommandLine getCommandLine(String... args) {
        try {
            return parser.parse(options, args);
        } catch (ParseException e) {
            throw new CommandLineDataIngesterException("unable to parse options for command line menu",e);
        }
    }
}
