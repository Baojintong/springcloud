package com.eureka.ribbon.command;

//具体命令实现
public class ConcreteCommand implements Command{

    private Receiver receiverl;

    public ConcreteCommand(Receiver receiverl) {
        this.receiverl = receiverl;
    }

    @Override
    public void execute() {
        this.receiverl.action();
    }
}
