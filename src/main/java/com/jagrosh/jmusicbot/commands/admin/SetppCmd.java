package com.jagrosh.jmusicbot.commands.admin;

import com.jagrosh.jdautilities.command.CommandEvent;
import com.jagrosh.jdautilities.commons.utils.FinderUtil;
import com.jagrosh.jmusicbot.Bot;
import com.jagrosh.jmusicbot.commands.AdminCommand;
import com.jagrosh.jmusicbot.settings.Settings;
import com.jagrosh.jmusicbot.utils.FormatUtil;
import net.dv8tion.jda.api.entities.TextChannel;

import java.util.List;

/**
 *
 * @author Peter Tamas Czibula <pterczibula@gmail.com>
 */
public class SetppCmd extends AdminCommand
{
    public SetppCmd(Bot bot)
    {
        this.name = "setpp";
        this.help = "sets the maximum YouTube playlist pages (100 songs per page)";
        this.arguments = "<1 - 100>";
        this.aliases = bot.getConfig().getAliases(this.name);
    }

    @Override
    protected void execute(CommandEvent event)
    {
        try
        {
            int val = Integer.parseInt(event.getArgs());
            if( val < 1 || val > 100)
            {
                event.replyError("The provided value must be between 1 and 100!");
                return;
            }
            Settings s = event.getClient().getSettingsFor(event.getGuild());
            s.setPlaylistPages(val);
            event.replySuccess("Maximum YouTube playlist pages has been set to `" + val + "` on *" + event.getGuild().getName() + "*");
        }
        catch(NumberFormatException ex)
        {
            event.replyError("Please include an integer between 1 and 100 (default is 6). This number is the maximum page of the YouTube playlist. ( 100 songs per page )");
        }
    }

}
