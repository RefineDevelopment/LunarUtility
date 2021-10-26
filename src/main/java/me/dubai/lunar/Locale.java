package me.dubai.lunar;

import me.dubai.lunar.utils.Color;
import me.dubai.lunar.utils.ConfigFile;

import java.text.MessageFormat;

public enum Locale {
    LUNAR_STAFF_COMMAND_PLAYER("MESSAGES.LUNAR-STAFF-COMMAND.PLAYER"),
    LUNAR_STAFF_COMMAND_TARGET("MESSAGES.LUNAR-STAFF-COMMAND.TARGET"),
    LUNAR_STAFF_COMMAND_TO_TARGET("MESSAGES.LUNAR-STAFF-COMMAND.TO-TARGET"),
    PLAYER_NOT_FOUND("MESSAGES.MAIN.OFFLINE"),
    LUNAR_COMMAND_PLAYER("MESSAGES.LUNAR-COMMAND.PLAYER"),
    LUNAR_COMMAND_TARGET("MESSAGES.LUNAR-COMMAND.TARGET"),
    LUNAR_TAG_1("NAMETAG.FIRST"),
    LUNAR_TAG_2("NAMETAG.SECOND.TAG"),
    LUNAR_COMMAND_RELOAD("MESSAGES.MAIN.RELOAD");

    private final String path;

    Locale(String path) {
        this.path = path;
    }

    public String messageFormat(Object... objects) {
        return (new MessageFormat(Color.translate(ConfigFile.getConfig().getString(this.path)))).format(objects);
    }
}
