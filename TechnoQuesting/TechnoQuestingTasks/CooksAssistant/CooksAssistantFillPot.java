package OldschoolRuneScape.TechnoQuestingTasks.CooksAssistant;

import OldschoolRuneScape.Task;
import OldschoolRuneScape.Walker;
import org.powerbot.script.Condition;
import org.powerbot.script.Random;
import org.powerbot.script.Tile;
import org.powerbot.script.rt4.ClientContext;
import org.powerbot.script.rt4.GameObject;

public class CooksAssistantFillPot extends Task {

    // private final int COOKS_ASSISTANT_QUEST_VARPBIT = ;
    private final int POT_OF_FLOUR_ID = 1933;
    private final int WHEAT_ITEM_ID = 1947;
    private final int EMPTY_POT_ID = 1931;
    private final int EMPTY_BUCKET_ID = 1925;
    private final int FLOUR_BIN_ID = 1782;
    private static final Tile windMillTiles[] = {new Tile(3164, 3306, 2), new Tile(3164, 3306, 1), new Tile(3164, 3306, 0)};
    private final int flourBinVarpbit = 695;


    private final Walker walker;


    public CooksAssistantFillPot(ClientContext ctx, Walker walker) {
        super(ctx);
        this.walker = walker;
    }

    @Override
    public boolean activate() {
        return ctx.varpbits.varpbit(flourBinVarpbit) == 1 && ctx.inventory.select().id(POT_OF_FLOUR_ID).count() == 0 && ctx.inventory.select().id(EMPTY_POT_ID).count() == 1 && ctx.inventory.select().id(EMPTY_BUCKET_ID).count() == 1;
        //  return ctx.varpbits.varpbit(COOKS_ASSISTANT_QUEST_VARPBIT) != ? && make it not finished, multiple stages
    }

    @Override
    public void execute() {

        System.out.println("Looking for Flour bin");

        GameObject flourBin = ctx.objects.select().id(FLOUR_BIN_ID).nearest().poll();

        if (!windMillTiles[2].matrix(ctx).reachable()) {
            this.walker.walkPath(windMillTiles);
            Condition.sleep(Random.nextInt(500, 1000));
        }


       if (flourBin.inViewport()) {
            System.out.println("Emptying flour bin");
            flourBin.interact("Empty");
            Condition.wait(() -> ctx.varpbits.varpbit(flourBinVarpbit) == 0, 250, 8);
        } else if (!flourBin.inViewport()) {
            System.out.println("Turning to flourBin");
            ctx.camera.turnTo(flourBin);
        }
    }
}

