/*
 *   OnTime - AnvilPowered
 *   Copyright (C) 2020
 *
 *     This program is free software: you can redistribute it and/or modify
 *     it under the terms of the GNU Lesser General Public License as published by
 *     the Free Software Foundation, either version 3 of the License, or
 *     (at your option) any later version.
 *
 *     This program is distributed in the hope that it will be useful,
 *     but WITHOUT ANY WARRANTY; without even the implied warranty of
 *     MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 *     GNU Lesser General Public License for more details.
 *
 *     You should have received a copy of the GNU Lesser General Public License
 *     along with this program.  If not, see <https://www.gnu.org/licenses/>.
 */
package org.anvilpowered.ontime.sponge.command

import org.anvilpowered.anvil.api.splitContext
import org.anvilpowered.ontime.api.registry.OnTimeKeys
import org.anvilpowered.ontime.common.command.CommonAddCommand
import org.anvilpowered.ontime.common.command.CommonOnTimeCommandNode
import org.spongepowered.api.command.CommandCallable
import org.spongepowered.api.command.CommandResult
import org.spongepowered.api.command.CommandSource
import org.spongepowered.api.entity.living.player.Player
import org.spongepowered.api.entity.living.player.User
import org.spongepowered.api.text.Text
import org.spongepowered.api.world.Location
import org.spongepowered.api.world.World
import java.util.Optional

class SpongeAddCommand : CommonAddCommand<User, Player, Text, CommandSource>(), CommandCallable {

    companion object {
        val DESCRIPTION: Optional<Text> = Optional.of(Text.of(CommonOnTimeCommandNode.ADD_DESCRIPTION))
        val USAGE: Text = Text.of(CommonOnTimeCommandNode.ADD_USAGE)
    }

    override fun process(source: CommandSource, context: String): CommandResult {
        execute(source, context.splitContext())
        return CommandResult.success()
    }

    override fun getSuggestions(source: CommandSource, context: String, targetPosition: Location<World>?): List<String> {
        return suggest(source, context.splitContext())
    }

    override fun testPermission(source: CommandSource): Boolean {
        return source.hasPermission(registry.getOrDefault(OnTimeKeys.EDIT_PERMISSION))
    }

    override fun getShortDescription(source: CommandSource): Optional<Text> = DESCRIPTION
    override fun getHelp(source: CommandSource): Optional<Text> = DESCRIPTION
    override fun getUsage(source: CommandSource): Text = USAGE
}
