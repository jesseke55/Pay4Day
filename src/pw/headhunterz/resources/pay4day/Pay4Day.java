package pw.headhunterz.resources.pay4day;

import net.milkbowl.vault.economy.Economy;
import net.milkbowl.vault.economy.EconomyResponse;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.plugin.Plugin;
import org.bukkit.plugin.RegisteredServiceProvider;
import org.bukkit.plugin.java.JavaPlugin;

import java.io.File;
import java.util.Collection;

public class Pay4Day
        extends JavaPlugin
{
  String prefix = ChatColor.GREEN + "[Pay4Day] ";
  public File configFile = new File(getDataFolder(), "config.yml");
  public static Economy econ = null;
  private static Plugin plugin;

  public void onDisable()
  {
    plugin = null;
    getLogger().info("Pay4Day has been disabled!");
  }

  public void onEnable()
  {
    if (!setupEconomy())
    {
      getLogger().warning(String.format("[%s] - Disabled due to no Vault dependency found!", new Object[] { getDescription().getName() }));
      getServer().getPluginManager().disablePlugin(this);
      return;
    }
plugin = this;


    loadConfig();
    getLogger().info("-------------------------");
    getLogger().info("");
    getLogger().info("Name: " + getDescription().getName() );
    getLogger().info("Version: " + getDescription().getVersion() );
    getLogger().info("Authors: " + getDescription().getAuthors() );
    getLogger().info("Website: " + getDescription().getWebsite() );
    getLogger().info("");
    getLogger().info(ChatColor.GREEN + "Please leave a rate if you like the plugin!");
    getLogger().info("");
    getLogger().info("-------------------------");
  }

  private boolean setupEconomy()
  {
    if (getServer().getPluginManager().getPlugin("Vault") == null) {
      return false;
    }
    RegisteredServiceProvider<Economy> rsp = getServer().getServicesManager().getRegistration(Economy.class);
    if (rsp == null) {
      return false;
    }
    econ = (Economy)rsp.getProvider();
    return econ != null;
  }

  public boolean onCommand(CommandSender sender, Command command, String commandLabel, String[] args)
  {
    if (command.getName().equalsIgnoreCase("pay4day"))
    {
      if (!(sender instanceof Player))
      {
        getLogger().info(getConfig().getString("msg." + getConfig().getString("config.language") + ".cmd_command"));
        return true;
      }
      if (args.length == 0)
      {
        if (sender.hasPermission("pay4day.use.classic")) {
          builWeather(sender, "classic", getConfig().getDouble("config.price.classic"));
        } else {
          sender.sendMessage(prefix + getConfig().getString(new StringBuilder("msg.").append(getConfig().getString("config.language")).append(".permissions").toString()));
        }
      }
      else if (args.length == 1) {
        if (args[0].equalsIgnoreCase("day"))
        {
          if (sender.hasPermission("pay4day.use.day")) {
            builWeather(sender, "day", getConfig().getDouble("config.price.day"));
          } else {
            sender.sendMessage(prefix + getConfig().getString(new StringBuilder("msg.").append(getConfig().getString("config.language")).append(".permissions").toString()));
          }
        }
        else if (args[0].equalsIgnoreCase("night"))
        {
          if (sender.hasPermission("pay4day.use.night")) {
            builWeather(sender, "night", getConfig().getDouble("config.price.night"));
          } else {
            sender.sendMessage(prefix + getConfig().getString(new StringBuilder("msg.").append(getConfig().getString("config.language")).append(".permissions").toString()));
          }
        }
        else if (args[0].equalsIgnoreCase("sun"))
        {
          if (sender.hasPermission("pay4day.use.sun")) {
            builWeather(sender, "sun", getConfig().getDouble("config.price.sun"));
          } else {
            sender.sendMessage(prefix + getConfig().getString(new StringBuilder("msg.").append(getConfig().getString("config.language")).append(".permissions").toString()));
          }
        }
        else if (args[0].equalsIgnoreCase("rain"))
        {
          if (sender.hasPermission("pay4day.use.rain")) {
            builWeather(sender, "rain", getConfig().getDouble("config.price.rain"));
          } else {
            sender.sendMessage(prefix + getConfig().getString(new StringBuilder("msg.").append(getConfig().getString("config.language")).append(".permissions").toString()));
          }
        }
        else if (args[0].equalsIgnoreCase("storm")) {
          if (sender.hasPermission("pay4day.use.storm")) {
            builWeather(sender, "storm", getConfig().getDouble("config.price.storm"));
          } else {
            sender.sendMessage(prefix + getConfig().getString(new StringBuilder("msg.").append(getConfig().getString("config.language")).append(".permissions").toString()));
          }}

        else if (args[0].equalsIgnoreCase("help")) {
          if (sender.hasPermission("pay4day.use.help")) {
            sender.sendMessage("-----------------------------------------------");
            sender.sendMessage(prefix + "Thanks for using this plugin!");
            sender.sendMessage("");
            sender.sendMessage("/pay4day help - Alias: /pay4help");
            sender.sendMessage("/pay4day about - Alias: /pay4about");
            sender.sendMessage("/pay4day version - Alias: /pay4version");
            sender.sendMessage("/pay4day");
            sender.sendMessage("/pay4night - Alias: /pay4day night");
            sender.sendMessage("/pay4day sun - Alias: /pay4sun");
            sender.sendMessage("/pay4day rain - Alias: /pay4rain");
            sender.sendMessage("/pay4day storm - Alias: /pay4storm");
            sender.sendMessage("-----------------------------------------------");
          } else {
            sender.sendMessage(prefix + getConfig().getString(new StringBuilder("msg.").append(getConfig().getString("config.language")).append(".permissions").toString()));
          }}


        else if (args[0].equalsIgnoreCase("version")) {
          if (sender.hasPermission("pay4day.use.version")) {
            sender.sendMessage("Version: " + getDescription().getVersion());
          } else {
            sender.sendMessage(prefix + getConfig().getString(new StringBuilder("msg.").append(getConfig().getString("config.language")).append(".permissions").toString()));
          }}


        else if (args[0].equalsIgnoreCase("about")) {
          if (sender.hasPermission("pay4day.use.about")) {
            sender.sendMessage("------------------------");
            sender.sendMessage("");
            sender.sendMessage("Name: " + getDescription().getName() );
            sender.sendMessage("Authors: " + getDescription().getAuthors() );
            sender.sendMessage("Version: " + getDescription().getVersion() );
            sender.sendMessage("Website: " + getDescription().getWebsite() );
            sender.sendMessage("------------------------");
            sender.sendMessage("");
          } else {
            sender.sendMessage(prefix + getConfig().getString(new StringBuilder("msg.").append(getConfig().getString("config.language")).append(".permissions").toString()));
          }


        }
      }
    }
    else if (command.getName().equalsIgnoreCase("pay4night"))
    {
      getServer().dispatchCommand(sender, "pay4day night");
    }
    else if (command.getName().equalsIgnoreCase("pay4sun"))
    {
      getServer().dispatchCommand(sender, "pay4day sun");
    }
    else if (command.getName().equalsIgnoreCase("pay4rain"))
    {
      getServer().dispatchCommand(sender, "pay4day rain");
    }
    else if (command.getName().equalsIgnoreCase("pay4storm"))
    {
      getServer().dispatchCommand(sender, "pay4day storm");
    }
    else if (command.getName().equalsIgnoreCase("pay4help"))
    {
      getServer().dispatchCommand(sender, "pay4day help");
    }
    else if (command.getName().equalsIgnoreCase("pay4version"))
    {
      getServer().dispatchCommand(sender, "pay4day version");
    }
    else if (command.getName().equalsIgnoreCase("pay4ver"))
    {
      getServer().dispatchCommand(sender, "pay4day version");
    }
    else if (command.getName().equalsIgnoreCase("pay4about"))
    {
      getServer().dispatchCommand(sender, "pay4day about");
    }
    return true;
  }


  @SuppressWarnings("deprecation")
  private void builWeather(CommandSender csender, String typ, double price)
  {
    Player sender = (Player)csender;
    EconomyResponse r = econ.withdrawPlayer(sender.getName(), price);
    if (r.transactionSuccess())
    {
      if (typ.equalsIgnoreCase("classic"))
      {
        if (getConfig().getBoolean("config.classic.setDay"))
        {
          sender.getWorld().setTime(0L);
        }
        else if (getConfig().getBoolean("config.classic.setNight"))
        {
          sender.getWorld().setTime(18000L);
        }
        else if (getConfig().getBoolean("config.classic.setSun"))
        {
          sender.getWorld().setThundering(false);
          sender.getWorld().setStorm(false);
        }
        else if (getConfig().getBoolean("config.classic.setRain"))
        {
          sender.getWorld().setThundering(false);
          sender.getWorld().setStorm(true);
        }
        else if (getConfig().getBoolean("config.classic.setStorm"))
        {
          sender.getWorld().setThundering(true);
          sender.getWorld().setStorm(true);
        }
      }
      else if (typ.equalsIgnoreCase("day"))
      {
        sender.getWorld().setTime(0L);
      }
      else if (typ.equalsIgnoreCase("night"))
      {
        sender.getWorld().setTime(18000L);
      }
      else if (typ.equalsIgnoreCase("sun"))
      {
        sender.getWorld().setThundering(false);
        sender.getWorld().setStorm(false);
      }
      else if (typ.equalsIgnoreCase("rain"))
      {
        sender.getWorld().setThundering(false);
        sender.getWorld().setStorm(true);
      }
      else if (typ.equalsIgnoreCase("storm"))
      {
        sender.getWorld().setThundering(true);
        sender.getWorld().setStorm(true);
      }
      sender.sendMessage(prefix + getConfig().getString(new StringBuilder("msg.").append(getConfig().getString("config.language")).append(".surcces_msg_").append(typ).toString()));
      if (getConfig().getBoolean("config.sendLocalMsg"))
      {
        Collection<Player> arrayOfPlayer = Bukkit.getOnlinePlayers().size();
        for j = Collection<Player> arrayOfPlayer = Bukkit.getOnlinePlayers().size();
        for (int i = 0; i < j; i++)
        {
          Player p = arrayOfPlayer[i];
          String broadcast = getConfig().getString("msg." + getConfig().getString("config.language") + ".broadcast_" + typ).replace("%price%", String.valueOf(price));
          broadcast = broadcast.replace("%currency%", getConfig().getString("config.currency"));
          broadcast = broadcast.replace("%player%", sender.getName());
          p.sendMessage(prefix + broadcast);
        }
      }
      String str = getConfig().getString("msg." + getConfig().getString("config.language") + ".success_money_msg").replace("%price%", String.valueOf(price));
      String success_money_msg = str.replace("%currency%", getConfig().getString("config.currency"));
      sender.sendMessage(prefix + success_money_msg);
    }
    else
    {
      String str = getConfig().getString("msg." + getConfig().getString("config.language") + ".success_money_msg").replace("%price%", String.valueOf(price));
      String success_money_msg = str.replace("%currency%", getConfig().getString("config.currency"));
      sender.sendMessage(prefix + success_money_msg);
    }
  }

  private void loadConfig()
  {
    if (!this.configFile.exists())
    {
      getLogger().info("Config didn't exist. Creating a new one");
      getConfig().options().copyDefaults(true);
    }
    getConfig().addDefault("config.price.classic", Double.valueOf(100.0D));
    getConfig().addDefault("config.price.day", Double.valueOf(100.0D));
    getConfig().addDefault("config.price.night", Double.valueOf(100.0D));
    getConfig().addDefault("config.price.sun", Double.valueOf(100.0D));
    getConfig().addDefault("config.price.rain", Double.valueOf(100.0D));
    getConfig().addDefault("config.price.storm", Double.valueOf(100.0D));

    getConfig().addDefault("config.classic.setDay", Boolean.valueOf(true));
    getConfig().addDefault("config.classic.setNight", Boolean.valueOf(true));
    getConfig().addDefault("config.classic.setSun", Boolean.valueOf(true));
    getConfig().addDefault("config.classic.setRain", Boolean.valueOf(false));
    getConfig().addDefault("config.classic.setStorm", Boolean.valueOf(false));

    getConfig().addDefault("config.language", "en");
    getConfig().addDefault("config.currency", "Dollar");

    getConfig().addDefault("config.sendLocalMsg", Boolean.valueOf(true));

    //--------------- ENGLISH ---------------\\


    getConfig().addDefault("msg.en.succes_msg_classic", "Successfully set day and sun.");
    getConfig().addDefault("msg.en.succes_msg_day", "Successfully set day.");
    getConfig().addDefault("msg.en.succes_msg_night", "Successfully set night.");
    getConfig().addDefault("msg.en.succes_msg_sun", "Successfully set sun.");
    getConfig().addDefault("msg.en.succes_msg_rain", "Successfully set rain.");
    getConfig().addDefault("msg.en.succes_msg_storm", "Successfully set storm.");

    getConfig().addDefault("msg.en.broadcast_classic", "%player% spent %price% %currency% for day and sun.");
    getConfig().addDefault("msg.en.broadcast_day", "%player% spent %price% %currency% for day.");
    getConfig().addDefault("msg.en.broadcast_night", "%player% spent %price% %currency% for night.");
    getConfig().addDefault("msg.en.broadcast_sun", "%player% spent %price% %currency% for sun.");
    getConfig().addDefault("msg.en.broadcast_rain", "%player% spent %price% %currency% for rain.");
    getConfig().addDefault("msg.en.broadcast_storm", "%player% spent %price% %currency% for storm.");

    getConfig().addDefault("msg.en.success_money_msg", "You spent %price% %currency%.");
    getConfig().addDefault("msg.en.not_enough_money", "You do not have enough money, you need %price% %currency%");
    getConfig().addDefault("msg.en.permissions", "You do not have permissions for this command.");
    getConfig().addDefault("msg.en.cmd_command", "Only players can use this command.");


    //--------------- DEUTSCH ---------------\\


    getConfig().addDefault("msg.de.succes_msg_classic", "Du hast dir Tag & Gutes Wetter gekauft.");
    getConfig().addDefault("msg.de.succes_msg_day", "Du hast dir Tag & Gutes Wetter gekauft.");
    getConfig().addDefault("msg.de.succes_msg_night", "Du hast dir die Nacht gekauft.");
    getConfig().addDefault("msg.de.succes_msg_sun", "Du hast dir die Sonne gekauft.");
    getConfig().addDefault("msg.de.succes_msg_rain", "Du hast dir den Regen gekauft.");
    getConfig().addDefault("msg.de.succes_msg_storm", "Du hast dir den Sturm gekauft.");

    getConfig().addDefault("msg.de.broadcast_classic", "%player% hat sich Tag & Sonne gekauft für %price% %currency%.");
    getConfig().addDefault("msg.de.broadcast_day", "%player% hat sich Tag gekauft für %price% %currency%.");
    getConfig().addDefault("msg.de.broadcast_night", "%player% hat sich Nacht gekauft für %price% %currency%.");
    getConfig().addDefault("msg.de.broadcast_sun", "%player% hat sich Sonne gekauft für %price% %currency%.");
    getConfig().addDefault("msg.de.broadcast_rain", "%player% hat sich Regen gekauft für %price% %currency%.");
    getConfig().addDefault("msg.de.broadcast_storm", "%player% hat sich Stumr gekauft für %price% %currency%.");

    getConfig().addDefault("msg.de.success_money_msg", "Dir wurden %price% %currency%s abgezogen");
    getConfig().addDefault("msg.de.not_enough_money", "Du hast zu wenig Geld, du brauchst %price% %currency%.");
    getConfig().addDefault("msg.de.permissions", "Du hast nicht die Permissions für diesen Befehl.");
    getConfig().addDefault("msg.de.cmd_command", "Nur Spieler können diesen Befehl benutzen.");


    //--------------- DUTCH ---------------\\

    getConfig().addDefault("msg.nl.succes_msg_classic", "Je hebt Dag & Zon gekocht.");
    getConfig().addDefault("msg.nl.succes_msg_day", "Je hebt Dag en Zon gekocht.");
    getConfig().addDefault("msg.nl.succes_msg_night", "Je hebt Nacht gekocht.");
    getConfig().addDefault("msg.nl.succes_msg_sun", "Je hebt Zon gekocht.");
    getConfig().addDefault("msg.nl.succes_msg_rain", "Je hebt Rregen gekocht.");
    getConfig().addDefault("msg.nl.succes_msg_storm", "Je hebt Storm gekocht.");

    getConfig().addDefault("msg.nl.broadcast_classic", "%player% Heeft Dag & Zon gekocht voor %price% %currency%.");
    getConfig().addDefault("msg.nl.broadcast_day", "%player% Heeft Dag & Zon gekocht voor %price% %currency%.");
    getConfig().addDefault("msg.nl.broadcast_night", "%player% Heeft Nacht gekocht voor %price% %currency%.");
    getConfig().addDefault("msg.nl.broadcast_sun", "%player% Heeft Zon gekocht voor %price% %currency%.");
    getConfig().addDefault("msg.nl.broadcast_rain", "%player% Heeft Regen gekocht voor %price% %currency%.");
    getConfig().addDefault("msg.nl.broadcast_storm", "%player% Heeft Storm gekocht voor %price% %currency%.");

    getConfig().addDefault("msg.nl.success_money_msg", "Er is %price% %currency%s afgetrokken van je ingame geld.");
    getConfig().addDefault("msg.nl.not_enough_money", "Je hebt te weinig geld, je hebt %price% %currency% nodig.");
    getConfig().addDefault("msg.nl.permissions", "Je hebt hiervoor geen permissie om dit uit te voeren.");
    getConfig().addDefault("msg.nl.cmd_command", "Alleen spelers kunnen deze commando uitvoeren.");


    //--------------- FRENCH ---------------\\


    getConfig().addDefault("msg.fr.succes_msg_classic", "Temps changé, Jour et Soleil choisi.");
    getConfig().addDefault("msg.fr.succes_msg_day", "Temps changé, Jour choisi.");
    getConfig().addDefault("msg.fr.succes_msg_night", "Temps changé, Night choisi.");
    getConfig().addDefault("msg.fr.succes_msg_sun", "Temps changé, Soleil choisi.");
    getConfig().addDefault("msg.fr.succes_msg_rain", "Temps changé, Pluie choisi.");
    getConfig().addDefault("msg.fr.succes_msg_storm", "Temps changé, Neige choisi.");

    getConfig().addDefault("msg.fr.broadcast_classic", "%player% a payé %price% pour mettre le jour et le soleil pendant %currency%.");
    getConfig().addDefault("msg.fr.broadcast_day", "%player% a payé %price% pour le mettre le jour pendant %currency%.");
    getConfig().addDefault("msg.fr.broadcast_night", "%player% a payé %price% pour le mettre la nuit pendant %currency%.");
    getConfig().addDefault("msg.fr.broadcast_sun", "%player% a payé %price% pour le mettre le soleil pendant %currency%.");
    getConfig().addDefault("msg.fr.broadcast_rain", "%player% a payé %price% pour le mettre la pluie pendant %currency%.");
    getConfig().addDefault("msg.fr.broadcast_storm", "%player% a payé %price% pour le mettre la neige pendant %currency%.");

    getConfig().addDefault("msg.fr.success_money_msg", "Vous avez payé %price% pour %currency%.");
    getConfig().addDefault("msg.fr.not_enough_money", "Vous n'avez pas assez d'argent, Il vous faut %price% pour %currency%");
    getConfig().addDefault("msg.fr.permissions", "Vous n'avez pas la permission de faire ça.");
    getConfig().addDefault("msg.fr.cmd_command", "Seul les joueurs peuvent exécuter cette commande.");




    //--------------- SPANISH ---------------\\

    getConfig().options().copyDefaults(true);
    saveConfig();
  }
}
