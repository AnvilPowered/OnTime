/*
 *   OnTime - AnvilPowered
 *   Copyright (C) 2020 Cableguy20
 *
 *     This program is free software: you can redistribute it and/or modify
 *     it under the terms of the GNU General Public License as published by
 *     the Free Software Foundation, either version 3 of the License, or
 *     (at your option) any later version.
 *
 *     This program is distributed in the hope that it will be useful,
 *     but WITHOUT ANY WARRANTY; without even the implied warranty of
 *     MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 *     GNU General Public License for more details.
 *
 *     You should have received a copy of the GNU General Public License
 *     along with this program.  If not, see <https://www.gnu.org/licenses/>.
 */

package org.anvilpowered.ontime.sponge.commands;

import com.google.inject.Inject;
import org.anvilpowered.ontime.api.data.key.MSOnTimeKeys;
import org.anvilpowered.ontime.api.member.MemberManager;
import org.spongepowered.api.command.CommandException;
import org.spongepowered.api.command.CommandResult;
import org.spongepowered.api.command.CommandSource;
import org.spongepowered.api.command.args.CommandContext;
import org.spongepowered.api.command.spec.CommandExecutor;
import org.spongepowered.api.entity.living.player.Player;
import org.spongepowered.api.entity.living.player.User;
import org.spongepowered.api.text.Text;

import java.util.Optional;

public class OnTimeCheckCommand implements CommandExecutor {

    @Inject
    private MemberManager<Text> memberManager;

    @Override
    public CommandResult execute(CommandSource source, CommandContext context) throws CommandException {
        Optional<User> optionalUser = context.getOne(Text.of("user"));
        if (optionalUser.isPresent()) {
            memberManager.infoExtended(optionalUser.get().getUniqueId()).thenAcceptAsync(source::sendMessage);
        } else if (source instanceof Player) {
            if (source.hasPermission(MSOnTimeKeys.CHECK_EXTENDED_PERMISSION.getFallbackValue())) {
                memberManager.infoExtended(((Player) source).getUniqueId()).thenAcceptAsync(source::sendMessage);
            } else {
                memberManager.info(((Player) source).getUniqueId()).thenAcceptAsync(source::sendMessage);
            }
        } else {
            throw new CommandException(Text.of("Specify user or run as player!"));
        }
        return CommandResult.success();
    }
}
