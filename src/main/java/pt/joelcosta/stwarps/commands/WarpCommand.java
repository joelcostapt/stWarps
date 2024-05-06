package pt.joelcosta.stwarps.commands;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import pt.joelcosta.stwarps.Main;
import pt.joelcosta.stwarps.managers.WarpManager;
import pt.joelcosta.stwarps.objects.Warp;

public class WarpCommand implements CommandExecutor {

    private static final Main instance = Main.getInstance();

    @Override
    public boolean onCommand(CommandSender commandSender, Command command, String s, String[] strings) {
        if (!(commandSender instanceof Player)) commandSender.sendMessage("§cEste comando só pode ser executado por jogadores.");

        assert commandSender instanceof Player;
        Player player = (Player) commandSender;


        WarpManager warpManager = instance.getWarpManager();

        if (command.getName().equalsIgnoreCase("warp")) {
            if (strings.length == 0){
                if (player.hasPermission("stwarps.admin")){
                    player.sendMessage("§aExecute os comandos corretamente:");
                    player.sendMessage("§f• §e/warp list");
                    player.sendMessage("§f• §e/warp ir <nome>");
                    player.sendMessage("§f• §e/warp remove <nome>");
                    player.sendMessage("§f• §e/warp set <nome>");
                } else {
                    player.sendMessage("§aExecute os comandos corretamente:");
                    player.sendMessage("§f• §e/warp list");
                    player.sendMessage("§f• §e/warp ir <nome>");
                }
            }

            if (strings.length == 1) {
                if (strings[0].equalsIgnoreCase("list")){
                    player.sendMessage(warpManager.list());
                }
            }

            if (strings.length == 2) {
                String warpName = strings[1];
                if (strings[0].equalsIgnoreCase("ir")){
                    if (warpManager.get(warpName) == null) {
                        player.sendMessage("§cEssa warp não existe");
                        return false;
                    }
                    warpManager.go(player, warpManager.get(warpName));
                } else if (strings[0].equalsIgnoreCase("remove")) {
                    if (warpManager.delete(warpName)){
                        player.sendMessage("§aSucesso! A warp §f" + warpName + " §afoi deletada com sucesso.");
                    } else {
                        player.sendMessage("§cNão foi possível deletar essa warp.");
                    }

                } else if (strings[0].equalsIgnoreCase("set")) {
                    if (warpManager.get(warpName) != null) player.sendMessage("§eEssa warp já existe!");
                    warpManager.insert(new Warp(warpName, player.getLocation()));
                    player.sendMessage("§aSucesso! A warp §f" + warpName + " §afoi criada!");
                } else {
                    player.sendMessage("§cComando desconhecido.");
                }
            }
        }

        return false;
    }
}
