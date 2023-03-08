package xyz.zielinus.mhider.utils;

import org.bukkit.ChatColor;

public class Colorize {

    public static String color(String s) {
        return ChatColor.translateAlternateColorCodes('&', s);
    }

    public static String colorConsole(String s, Color c) {
        return c.color + s;
    }

    public enum Color {
        RESET("\u001B[0m"),
        RED("\u001B[31m"),
        GREEN("\u001B[32m"),
        YELLOW("\\u001B[33m"),
        BLUE("\u001B[34m"),
        CYAN("\u001B[36m"),
        PURPLE("\u001B[35m");

        public final String color;
        private Color(String color) {
            this.color = color;
        }
    }

}
