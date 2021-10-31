package space.essem.image2map.permission;

import net.minecraft.server.command.ServerCommandSource;
import space.essem.image2map.Image2Map;

public class DefaultPermissionEvaluator implements IPermissionsEvaluator {

    @Override
    public boolean hasPermission(ServerCommandSource source) {
        return source.hasPermissionLevel(Image2Map.CONFIG.minPermLevel);
    }
}
