package uk.firedev.datapackssuck.command;

import com.mojang.brigadier.builder.LiteralArgumentBuilder;
import com.mojang.brigadier.tree.LiteralCommandNode;
import io.papermc.paper.command.brigadier.CommandSourceStack;
import io.papermc.paper.command.brigadier.Commands;
import uk.firedev.daisylib.libs.messagelib.message.ComponentMessage;
import uk.firedev.datapackssuck.DatapacksSuck;

public class MainCommand {

    public static LiteralCommandNode<CommandSourceStack> get() {
        return Commands.literal("datapackssuck")
            .then(reload())
            .build();
    }

    private static LiteralArgumentBuilder<CommandSourceStack> reload() {
        return Commands.literal("reload")
            .executes(ctx -> {
                DatapacksSuck.getInstance().reload();
                ComponentMessage.componentMessage(
                    "<gray>[DatapacksSuck] <white>Successfully reloaded the plugin."
                ).send(ctx.getSource().getSender());
                return 1;
            });
    }

}
