package OldschoolRuneScape.TechnoQuestingTasks.SheepShearer;

import OldschoolRuneScape.Task;
import org.powerbot.script.rt4.ClientContext;

public class SheepShearerQuestComplete extends Task {

    private final int SHEARER_QUEST_VARPBIT = 179;

    public SheepShearerQuestComplete(ClientContext ctx) {
        super(ctx);
    }

    @Override
    public boolean activate() {
        return ctx.varpbits.varpbit(SHEARER_QUEST_VARPBIT) == 21;
    }

    @Override
    public void execute() {
        System.out.println("Quest is already complete.");
        ctx.controller.stop();
    }
}