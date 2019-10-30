package OldschoolRuneScape.TechnoQuestingTasks.CooksAssistant;

import OldschoolRuneScape.Task;
import OldschoolRuneScape.Walker;
import org.powerbot.script.Condition;
import org.powerbot.script.Random;
import org.powerbot.script.Tile;
import org.powerbot.script.rt4.ClientContext;

import java.util.concurrent.Callable;

public class CooksAssistantLumbToWheat extends Task {
    public static final Tile pathLumbToWheat[] = {new Tile(3221, 3219, 0), new Tile(3224, 3219, 0), new Tile(3227, 3219, 0), new Tile(3230, 3219, 0), new Tile(3232, 3222, 0), new Tile(3232, 3225, 0), new Tile(3232, 3228, 0), new Tile(3231, 3231, 0), new Tile(3228, 3233, 0), new Tile(3225, 3235, 0), new Tile(3222, 3238, 0), new Tile(3222, 3241, 0), new Tile(3221, 3244, 0), new Tile(3221, 3247, 0), new Tile(3219, 3250, 0), new Tile(3219, 3253, 0), new Tile(3217, 3256, 0), new Tile(3217, 3259, 0), new Tile(3217, 3262, 0), new Tile(3217, 3265, 0), new Tile(3216, 3268, 0), new Tile(3215, 3271, 0), new Tile(3215, 3274, 0), new Tile(3215, 3277, 0), new Tile(3212, 3277, 0), new Tile(3209, 3278, 0), new Tile(3206, 3278, 0), new Tile(3203, 3278, 0), new Tile(3200, 3280, 0), new Tile(3197, 3280, 0), new Tile(3194, 3280, 0), new Tile(3191, 3280, 0), new Tile(3188, 3282, 0), new Tile(3185, 3282, 0), new Tile(3182, 3283, 0), new Tile(3179, 3286, 0), new Tile(3176, 3286, 0), new Tile(3173, 3286, 0), new Tile(3170, 3286, 0), new Tile(3167, 3286, 0), new Tile(3164, 3288, 0), new Tile(3162, 3291, 0), new Tile(3161, 3294, 0)};
    private final int WHEAT_ITEM_ID = 1947;
    private final int POT_OF_FLOUR_ID = 1933;
    private final int EMPTY_POT_ID = 1931;
    private final int EMPTY_BUCKET_ID = 1925;
    // private final int COOKS_ASSISTANT_QUEST_VARPBIT = ;
    private final Walker walker;

    public CooksAssistantLumbToWheat(ClientContext ctx, Walker walker) {
        super(ctx);
        this.walker = walker;
    }

    @Override
    public boolean activate() {
       return ctx.inventory.select().id(WHEAT_ITEM_ID).count() == 0 && ctx.inventory.select().id(POT_OF_FLOUR_ID).count() == 0 && ctx.inventory.select().id(EMPTY_POT_ID).count() == 1 && ctx.inventory.select().id(EMPTY_BUCKET_ID).count() == 1 && pathLumbToWheat[pathLumbToWheat.length - 1].distanceTo(ctx.players.local()) > 2;
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
