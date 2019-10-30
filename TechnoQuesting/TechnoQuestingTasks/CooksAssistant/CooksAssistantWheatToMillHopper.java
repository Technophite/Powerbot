package OldschoolRuneScape.TechnoQuestingTasks.CooksAssistant;

import OldschoolRuneScape.Task;
import OldschoolRuneScape.Walker;
import org.powerbot.script.Condition;
import org.powerbot.script.Random;
import org.powerbot.script.Tile;
import org.powerbot.script.rt4.ClientContext;

import java.util.concurrent.Callable;

public class CooksAssistantWheatToMillHopper extends Task {
    public static final Tile pathLumbToWheat[] = {new Tile(3161, 3292, 0), new Tile(3162, 3289, 0), new Tile(3165, 3289, 0), new Tile(3165, 3292, 0), new Tile(3165, 3295, 0), new Tile(3165, 3298, 0), new Tile(3166, 3301, 0), new Tile(3167, 3304, 0), new Tile(3164, 3306, 0), new Tile(3164, 3306, 1), new Tile(3164, 3306, 2)};
    private final int WHEAT_ITEM_ID = 1947;
    private final int POT_OF_FLOUR_ID = 1933;
    private final int EMPTY_POT_ID = 1931;
    private final int EMPTY_BUCKET_ID = 1925;
    // private final int COOKS_ASSISTANT_QUEST_VARPBIT = ;
    private final Walker walker;

    public CooksAssistantWheatToMillHopper(ClientContext ctx, Walker walker) {
        super(ctx);
        this.walker = walker;
    }

    @Override
    public boolean activate() {
        return ctx.inventory.select().id(WHEAT_ITEM_ID).count() == 1 && ctx.inventory.select().id(POT_OF_FLOUR_ID).count() == 0 && ctx.inventory.select().id(EMPTY_POT_ID).count() == 1 && ctx.inventory.select().id(EMPTY_BUCKET_ID).count() == 1 && pathLumbToWheat[pathLumbToWheat.length - 1].distanceTo(ctx.players.local()) > 2;
        //  return ctx.varpbits.varpbit(COOKS_ASSISTANT_QUEST_VARPBIT) != ? && make it not finished, multiple stages
    }

    @Override
    public void execute() {
        System.out.println("Should be looking to get to wheat");
        if (!ctx.players.local().inMotion() || ctx.movement.destination().equals(Tile.NIL) || ctx.movement.destination().distanceTo(ctx.players.local()) < 6) {

            this.walker.walkPath(pathLumbToWheat);

            Condition.sleep(Random.nextInt(500, 1000));

        }
    }
}
