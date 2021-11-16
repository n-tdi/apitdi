package net.ntdi.api;

import me.kodysimpson.simpapi.command.CommandList;
import me.kodysimpson.simpapi.command.CommandManager;
import me.kodysimpson.simpapi.command.SubCommand;
import net.ntdi.api.commands.insultCommand;
import net.ntdi.api.commands.*;
import org.bukkit.ChatColor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.List;

public final class Api extends JavaPlugin {

    @Override
    public void onEnable() {
        // Plugin startup logic
        try {
            CommandManager.createCoreCommand(this, "apitdi", "a api plugin by Ntdi", "/apitdi", new CommandList() {
                @Override
                public void displayCommandList(CommandSender commandSender, List<SubCommand> list) {
                    Player p = (Player) commandSender;
                    p.sendMessage(ChatColor.BLUE + "-------------------------");
                    p.sendMessage("");
                    list.forEach(subCommand -> {
                        p.sendMessage(ChatColor.AQUA + subCommand.getSyntax() + " : " + ChatColor.AQUA + subCommand.getDescription());
                    });
                    p.sendMessage("");
                    p.sendMessage(ChatColor.GRAY + "Made by Ntdi");
                    p.sendMessage(ChatColor.BLUE + "-------------------------");
                }
            }, kanyeCommand.class, insultCommand.class);
        } catch (NoSuchFieldException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
    }
}
