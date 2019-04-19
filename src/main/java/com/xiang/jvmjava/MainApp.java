package com.xiang.jvmjava;

/**
 * @author 项三六
 * @time 2019/3/15 10:49
 * @comment MainApp
 */

public class MainApp {

    public static void main(String[] args) {
        Cmd cmd = Cmd.parseCmd(args);
        if (cmd.isHelp()) {
            cmd.printHelp();
        } else if (cmd.isVersion()) {
            cmd.printVersion();
        } else {
            new JVM(cmd).start();
        }
    }

}
