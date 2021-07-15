package me.dubai.lunar;

import java.text.MessageFormat;
import me.dubai.lunar.utils.CC;
import me.dubai.lunar.utils.ConfigFile;

public enum Locale {
    LUNAR_STAFF_COMMAND_PLAYER("MESSAGES.LUNAR-STAFF-COMMAND.PLAYER"),
    LUNAR_STAFF_COMMAND_TARGET("MESSAGES.LUNAR-STAFF-COMMAND.TARGET"),
    LUNAR_STAFF_COMMAND_TO_TARGET("MESSAGES.LUNAR-STAFF-COMMAND.TO-TARGET"),
    PLAYER_NOT_FOUND("MESSAGES.MAIN.OFFLINE"),
    LUNAR_COMMAND_PLAYER("MESSAGES.LUNAR-COMMAND.PLAYER"),
    LUNAR_COMMAND_TARGET("MESSAGES.LUNAR-COMMAND.TARGET"),
    LUNAR_TAG_1("NAMETAG.FIRST"),
    LUNAR_TAG_2("NAMETAG.SECOND"),
    LUNAR_COMMAND_RELOAD("MESSAGES.MAIN.RELOAD");

    Locale(String path) {
        this.path = path;
    }

    private final String path;

    public String format(Object... objects) {
        return (new MessageFormat(CC.translate(ConfigFile.getConfig().getString(this.path)))).format(objects);
    }
}
