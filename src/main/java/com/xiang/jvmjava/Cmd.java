package com.xiang.jvmjava;

import lombok.Getter;
import lombok.Setter;
import org.apache.commons.cli.*;

/**
 * @author 项三六
 * @time 2019/3/15 11:00
 * @comment
 */


@Getter
@Setter
public class Cmd {

    private String mainClass;

    private String classpath;

    private boolean help;

    private boolean version;

    private Options options;

    private Cmd() {
        this.options = new Options();
        this.options.addOption("cp", "classpath", true, "Specify classpath");
        this.options.addOption("v", "version", false, "Show version");
        this.options.addOption("h", "help", false, "Help");
    }

    void printHelp() {
        new HelpFormatter().printHelp("jvm-java", this.options);
    }

    void printVersion() {
        System.out.println("version: 0.0.1");
    }

    static Cmd parseCmd(String[] args) {
        Cmd cmd = new Cmd();
        CommandLineParser parser = new DefaultParser();
        CommandLine commandLine;
        try {
            commandLine = parser.parse(cmd.getOptions(), args);
            if (commandLine.hasOption("v")) {
                cmd.setVersion(true);
                return cmd;
            }
            if (0 == commandLine.getArgList().size()) {
                cmd.setHelp(true);
                return cmd;
            }
            cmd.setMainClass(commandLine.getArgList().get(0));
            cmd.setClasspath(commandLine.getOptionValue("classpath"));
        } catch (ParseException e) {
            cmd.setHelp(true);
        }
        return cmd;
    }

}
