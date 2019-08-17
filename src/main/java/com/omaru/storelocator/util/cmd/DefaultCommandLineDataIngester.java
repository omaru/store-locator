package com.omaru.storelocator.util.cmd;

import com.omaru.storelocator.util.script.ScriptRunner;
import org.apache.commons.cli.*;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Component;

import javax.inject.Inject;
import java.io.FileInputStream;
import java.util.Optional;

import static org.apache.commons.lang3.StringUtils.isNotBlank;

@Component
public class DefaultCommandLineDataIngester implements CommandLineDataIngester{
    private final ScriptRunner scriptRunner;
    private final String defaultScriptName;
    private final CommandLineMenu commandLineMenu;
    @Inject
    public DefaultCommandLineDataIngester(CommandLineMenu commandLineMenu,
                                          ScriptRunner scriptRunner,
                                          @Value("${command.line.data.ingester.default-script}")String defaultScriptName){
        this.commandLineMenu = commandLineMenu;
        this.scriptRunner = scriptRunner;
        this.defaultScriptName = defaultScriptName;
    }
    @Override
    public void accept(String... args) throws Exception {
        commandLineMenu.print();
        CommandLine cmd = commandLineMenu.getCommandLine(args);
        Optional<String> fileRoute = getFileRoute(cmd);
        if(fileRoute.isPresent()){
            runFromFile(fileRoute.get());
        }else{
            runFromDefaultFile();
        }

    }
    private void runFromFile(String fileRoute) throws Exception {
        scriptRunner.run(new FileInputStream(fileRoute));
    }
    private void runFromDefaultFile() throws Exception {
        Resource resource = new ClassPathResource(defaultScriptName);
        scriptRunner.run(resource);
    }

    private Optional<String> getFileRoute(CommandLine cmd) {
        Optional<String> script = Optional.empty();
        if (cmd.hasOption("i")) {
            String value = cmd.getOptionValue("i");
            if(isNotBlank(value)){
                script = Optional.of(value);
            }
        }
        return script;
    }

}
