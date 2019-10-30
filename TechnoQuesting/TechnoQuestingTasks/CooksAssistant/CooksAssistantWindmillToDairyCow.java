package OldschoolRuneScape.TechnoQuestingTasks.CooksAssistant;

import OldschoolRuneScape.Task;
import OldschoolRuneScape.Walker;
import org.powerbot.script.Condition;
import org.powerbot.script.Random;
import org.powerbot.script.Tile;
import org.powerbot.script.rt4.ClientContext;

import java.util.concurrent.Callable;

public class CooksAssistantWindmillToDairyCow extends Task {

    public static final Tile pathWindmillToDairyCow[] = {new Tile(3165, 3306, 0), new Tile(3166, 3303, 0), new Tile(3169, 3302, 0), new Tile(3171, 3305, 0), new Tile(3171, 3308, 0), new Tile(3172, 3311, 0), new Tile(3175, 3314, 0), new Tile(3176, 3317, 0), new Tile(3173, 3316, 0), new Tile(3171, 3318, 0)};

    private final int POT_OF_FLOUR_ID = 1933;
    private final int EMPTY_BUCKET_ID = 1925;
    private final int BUCKET_OF_MILK_ID = 1927;
    // private final int COOKS_ASSISTANT_QUEST_VARPBIT = ;

    private final Walker walker;

    public CooksAssistantWindmillToDairyCow(ClientContext ctx, Walker walker) {
        super(ctx);
        this.walker = walker;
    }

    @Override
    public boolean activate() {
        return ctx.inventory.select().id(POT_OF_FLOUR_ID).count() == 1 && ctx.inventory.select().id(EMPTY_BUCKET_ID).count() == 1 && pathWindmillToDairyCow[pathWindmillToDairyCow.length - 1].distanceTo(ctx.players.local()) > 2;
        //  return ctx.varpbits.varpbit(COOKS_ASSISTANT_QUEST_VARPBIT) != ? && make it not finished, multiple stages
    }

    @Override
    public void execute() {
        System.out.println("Should be looking to get to Dairy Cow");
        if (!ctx.players.local().inMotion() || ctx.movement.destination().equals(Tile.NIL) || ctx.movement.destination().distanceTo(ctx.players.local()) < 6) {

            this.walker.walkPath(pathWindmillToDairyCow);

            Condition.sleep(Random.nextInt(500, 1000));

        }
    }
}
