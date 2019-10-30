package OldschoolRuneScape.TechnoQuestingTasks.CooksAssistant;

import OldschoolRuneScape.Task;
import org.powerbot.script.rt4.ClientContext;

public class CooksAssistantComplete extends Task {

    private final int COOKS_ASSISTANT_QUEST_VARPBIT = 29;

    public CooksAssistantComplete(ClientContext ctx) {
        super(ctx);
    }

    @Override
    public boolean activate() {
        return ctx.varpbits.varpbit(COOKS_ASSISTANT_QUEST_VARPBIT) == 2;
    }

    @Override
    public void execute() {
        System.out.println("Quest is already complete.");
        ctx.controller.stop();
    }
}