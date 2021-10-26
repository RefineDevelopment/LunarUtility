package me.dubai.lunar.utils.command;

import me.dubai.lunar.Lunar;

public abstract class BaseCommand {
    public Lunar plugin = Lunar.getInstance();

    public BaseCommand() {
        this.plugin.getCommandFramework().registerCommands(this);
    }
}