# LunarUtility

[![Codacy Badge](https://api.codacy.com/project/badge/Grade/9194a83417de4c939f617d3f4d47b56f)](https://app.codacy.com/gh/GamerRealm/LunarUtility?utm_source=github.com&utm_medium=referral&utm_content=GamerRealm/LunarUtility&utm_campaign=Badge_Grade_Settings)

This plugin was my first ever open source project. The main purpose of this project is for people who would like to use LunarClient's API features and implement them into their server or have an idea of how it works. This project will be updated often until I consider it complete or I am no longer able to continue updating it.

## Dependancies
This plugin depends on LunarClient's Official API [BukkitAPI](https://github.com/LunarClient/BukkitAPI/releases/tag/v1.0.1) or else it wont work!
Insert the jar into your plugins folder aswell as the LunarUtility jar.

The plugin works on 1.7-1.16 although the cooldowns are disabled on versions newer than 1.8 because of the API having an issue with it.
If there is any version that doesn't work with LunarUtility please DM me on discord Dubai#1454.

## Screenshots

### Commands
![2021-10-26_11 25 54](https://user-images.githubusercontent.com/42650369/138830339-36b85f2c-5044-4953-b6da-4e67ee30fe84.png)
![2021-10-26_11 27 38](https://user-images.githubusercontent.com/42650369/138829630-3c2fe296-c3b9-4aae-97f6-0f4b70db5f79.png)

### Staff Mods
![2021-10-26_11 26 09](https://user-images.githubusercontent.com/42650369/138829302-7aeaad61-6cf4-426f-954a-43ace12a972f.png)
![2021-10-26_11 26 23](https://user-images.githubusercontent.com/42650369/138829317-7a8c6015-e692-4a84-b1d4-395a286454bb.png)


### Waypoints
![2021-10-26_11 24 51](https://user-images.githubusercontent.com/42650369/138829478-606493b4-072c-429b-897a-c54948407b4b.png)

### Cooldowns
![2021-10-26_11 26 59](https://user-images.githubusercontent.com/42650369/138830407-ce602bdd-9281-4744-9d3d-105af5223f3f.png)

## Configuration
```yaml
########################
#  LunarUtility V1.5   #
#     By Dubai#1454    #
########################

NAMETAG:
  ENABLE: true #Needs Restart
  FIRST: "&7[<status> &bLC&7]"
  SECOND:
    ENABLE: false
    TAG: "<player_name>" # use <player_displayname> for disguise support

WAYPOINTS:
  ENABLE: true
  NAME: "Spawn"
  WORLD: "world"

OTHER:
  ENABLED: "&aON"
  DISABLED: "&cOFF"

#Disabled cooldowns for 1.9+ because of LC API Issue (Already reported to devs)
COOLDOWN:
  ENDERPEARL:
    ENABLE: true
    DELAY: 16
  GAPPLE:
    ENABLE: true
    DELAY: 5
#Don't set the cooldown to 0 or else the plugin won't work!
#You have to restart the server whenever you change the delay.

MESSAGES:
  MAIN:
    RELOAD: "&6LunarUtility &ehas been reloaded."
    OFFLINE: "&cThat player doesn't exist."
  LUNAR-COMMAND:
    PLAYER: "&6You are currently <status> &eLunarClient."
    TARGET: "&e<target> &6is currently <status> &eLunarClient."
  LUNAR-STAFF-COMMAND:
    PLAYER: "&aYou have enabled all LunarClient staff mods."
    TARGET: "&aYou have enabled all LunarClient staff mods for <target>."
    TO-TARGET: "&aAll LunarClient mods have been enabled for you by <player>."
  LUNAR-USERS-COMMAND:
    LIST:
      - "&7&m-----------------------"
      - "&b&lLunarClient Users"
      - "<list>"
      - "&7&m-----------------------"
```
