package xyz.zielinus.mhider.commands;

import com.destroystokyo.paper.profile.PlayerProfile;
import com.destroystokyo.paper.profile.ProfileProperty;
import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.TextComponent;
import net.md_5.bungee.api.chat.ComponentBuilder;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;
import xyz.zielinus.mhider.Mhider;
import xyz.zielinus.mhider.utils.ApiClient;

public class MorphCommand implements CommandExecutor {

    private final Mhider plugin;
    public MorphCommand(Mhider mhider) {
        this.plugin = mhider;
    }

    @Override
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command command, @NotNull String label, @NotNull String[] args) {
        if (sender instanceof Player) {
            Player player = (Player) sender;

            if (args.length != 1) {
                player.sendMessage("You need to provide a username");
                return true;
            }

            String username = args[0];

            String uuid = ApiClient.getUUID(username);
            ProfileProperty skin = ApiClient.getSkin(uuid);

            PlayerProfile profile = player.getPlayerProfile();

            if (skin != null) {
                profile.setProperty(skin);
                player.setPlayerProfile(profile);

                Component name = Component.text(username);
                player.playerListName(name);
                player.displayName(name);
                player.customName(name);
                player.setCustomNameVisible(true);

                player.sendMessage("Skin changed");
                return true;
            }

            player.sendMessage("Could not load the skin");
        }
        return true;
    }
}
