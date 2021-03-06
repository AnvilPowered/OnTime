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

package org.anvilpowered.ontime.sponge.module;

import com.google.inject.TypeLiteral;
import org.anvilpowered.anvil.api.command.CommandNode;
import org.anvilpowered.ontime.api.task.SyncTaskService;
import org.anvilpowered.ontime.common.module.CommonModule;
import org.anvilpowered.ontime.common.registry.CommonConfigurationService;
import org.anvilpowered.ontime.sponge.command.SpongeOnTimeCommandNode;
import org.anvilpowered.ontime.sponge.data.config.SpongeConfigurationService;
import org.anvilpowered.ontime.sponge.task.SpongeSyncTaskService;
import org.spongepowered.api.command.CommandSource;
import org.spongepowered.api.entity.living.player.Player;
import org.spongepowered.api.entity.living.player.User;
import org.spongepowered.api.text.Text;

public class SpongeModule extends CommonModule<User, Player, Text, CommandSource> {

    @Override
    protected void configure() {
        super.configure();

        bind(new TypeLiteral<CommandNode<CommandSource>>() {
        }).to(SpongeOnTimeCommandNode.class);
        bind(CommonConfigurationService.class).to(SpongeConfigurationService.class);

        bind(SyncTaskService.class).to(SpongeSyncTaskService.class);
    }
}
