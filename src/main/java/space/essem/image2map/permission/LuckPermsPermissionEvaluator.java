package space.essem.image2map.permission;

import com.mojang.brigadier.exceptions.CommandSyntaxException;
import net.luckperms.api.*;
import net.luckperms.api.platform.PlayerAdapter;
import net.luckperms.api.util.Tristate;
import net.minecraft.server.command.ServerCommandSource;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.text.LiteralText;
import space.essem.image2map.Image2Map;

public class LuckPermsPermissionEvaluator implements IPermissionsEvaluator {

    private static boolean isApiLoaded = false;

    private static LuckPerms api;
    private static PlayerAdapter<ServerPlayerEntity> playerAdapter;

    @Override
    public boolean hasPermission(ServerCommandSource source)  {

        if (!isApiLoaded) {
            if (!canEnable()) {
                Image2Map.LOGGER.warn("LuckPerms API is not yet loaded!");
                source.sendFeedback(new LiteralText("LuckPerms API is not yet loaded!"), false);
                return false;
            }
        }

        try {
            Tristate result = playerAdapter.getPermissionData(source.getPlayer()).checkPermission("image2map.create");
            return result.asBoolean();
        } catch(CommandSyntaxException ex) {
            return false;
        }
    }

    private static boolean canEnable() {
        try {
            LuckPermsPermissionEvaluator.api = LuckPermsProvider.get();
            LuckPermsPermissionEvaluator.playerAdapter = api.getPlayerAdapter(ServerPlayerEntity.class);
            LuckPermsPermissionEvaluator.isApiLoaded = true;
            return true;
        } catch (IllegalArgumentException ex) {
            return false;
        }
    }
}
