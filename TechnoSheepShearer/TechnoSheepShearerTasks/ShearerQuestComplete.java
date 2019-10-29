package OldschoolRuneScape.TechnoSheepShearerTasks;

import OldschoolRuneScape.Task;
import org.powerbot.script.rt4.ClientContext;

public class ShearerQuestComplete extends Task {

    private final int SHEARER_QUEST_VARPBIT = 179;

    public ShearerQuestComplete(ClientContext ctx) {
        super(ctx);
    }

    @Override
    public boolean activate() {
        return ctx.varpbits.varpbit(SHEARER_QUEST_VARPBIT) == 21;
    }

    @Override
    public void execute() {
        ctx.controller.stop();
    }
}
