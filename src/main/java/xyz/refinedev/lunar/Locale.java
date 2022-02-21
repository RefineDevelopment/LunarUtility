package xyz.refinedev.lunar;

import xyz.refinedev.lunar.utils.Utils;

public enum Locale {
    LUNAR_TAG_1("NAMETAG.FIRST"),
    LUNAR_TAG_TICKS("NAMETAG.TICKS"),
    LUNAR_TAG_2("NAMETAG.SECOND.TAG"),
    LUNAR_KICK_MESSAGE("REQUIRE-LUNAR.MESSAGE"),
    LUNAR_COMMAND_RELOAD("MESSAGES.MAIN.RELOAD"),
    LUNAR_COMMAND_PLAYER("MESSAGES.LUNAR-COMMAND.PLAYER"),
    LUNAR_COMMAND_TARGET("MESSAGES.LUNAR-COMMAND.TARGET"),
    LUNAR_USERS_SEPERATOR("MESSAGES.LUNAR-USERS-COMMAND.SEPERATOR"),
    LUNAR_STAFF_COMMAND_PLAYER("MESSAGES.LUNAR-STAFF-COMMAND.PLAYER"),
    LUNAR_STAFF_COMMAND_TARGET("MESSAGES.LUNAR-STAFF-COMMAND.TARGET"),
    LUNAR_STAFF_COMMAND_TO_TARGET("MESSAGES.LUNAR-STAFF-COMMAND.TO-TARGET");
    private final String path;

    Locale(String path) {
        this.path = path;
    }

    public String messageFormat() {
        return Utils.translate(LunarUtility.getInstance().getConfig().getString(this.path));
    }
}