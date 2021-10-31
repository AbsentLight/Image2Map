package space.essem.image2map.permission;

import net.minecraft.server.command.ServerCommandSource;

public interface IPermissionsEvaluator {

    boolean hasPermission(ServerCommandSource source);

}
