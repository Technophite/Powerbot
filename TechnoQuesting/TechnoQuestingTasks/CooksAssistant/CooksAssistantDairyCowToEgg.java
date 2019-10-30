package OldschoolRuneScape.TechnoQuestingTasks.CooksAssistant;

import OldschoolRuneScape.Task;
import OldschoolRuneScape.Walker;
import org.powerbot.script.Condition;
import org.powerbot.script.Random;
import org.powerbot.script.Tile;
import org.powerbot.script.rt4.ClientContext;
import org.powerbot.script.rt4.GroundItem;

import java.util.concurrent.Callable;

public class CooksAssistantDairyCowToEgg extends Task {

    public static final Tile pathDairyCowToEgg[] = {new Tile(3171, 3317, 0), new Tile(3174, 3316, 0), new Tile(3177, 3313, 0), new Tile(3180, 3310, 0), new Tile(3183, 3308, 0), new Tile(3186, 3308, 0), new Tile(3189, 3305, 0), new Tile(3189, 3302, 0), new Tile(3188, 3299, 0), new Tile(3188, 3296, 0), new Tile(3188, 3293, 0), new Tile(3188, 3290, 0), new Tile(3188, 3287, 0), new Tile(3191, 3284, 0), new Tile(3194, 3281, 0), new Tile(3197, 3280, 0), new Tile(3200, 3279, 0), new Tile(3203, 3279, 0), new Tile(3206, 3279, 0), new Tile(3209, 3280, 0), new Tile(3212, 3280, 0), new Tile(3215, 3277, 0), new Tile(3218, 3275, 0), new Tile(3221, 3272, 0), new Tile(3222, 3269, 0), new Tile(3225, 3267, 0), new Tile(3227, 3264, 0), new Tile(3230, 3262, 0), new Tile(3233, 3262, 0), new Tile(3236, 3262, 0), new Tile(3239, 3262, 0), new Tile(3241, 3265, 0), new Tile(3240, 3268, 0), new Tile(3240, 3271, 0), new Tile(3240, 3274, 0), new Tile(3240, 3277, 0), new Tile(3241, 3280, 0), new Tile(3239, 3283, 0), new Tile(3239, 3286, 0), new Tile(3239, 3289, 0), new Tile(3239, 3292, 0), new Tile(3238, 3295, 0), new Tile(3235, 3295, 0), new Tile(3232, 3297, 0), new Tile(3229, 3299, 0)};

    private final int POT_OF_FLOUR_ID = 1933;
    private final int BUCKET_OF_MILK_ID = 1927;
    private final int EGG_ID = 1944;
    // private final int COOKS_ASSISTANT_QUEST_VARPBIT = ;

    private final Walker walker;

    public CooksAssistantDairyCowToEgg(ClientContext ctx, Walker walker) {
        super(ctx);
        this.walker = walker;
    }

    @Override
    public boolean activate() {
        return ctx.inventory.select().id(EGG_ID).count() == 0 && ctx.inventory.select().id(POT_OF_FLOUR_ID).count() == 1 && ctx.inventory.select().id(BUCKET_OF_MILK_ID).count() == 1;
        //  return ctx.varpbits.varpbit(COOKS_ASSISTANT_QUEST_VARPBIT) != ? && make it not finished, multiple stages
    }

    @Override
    public void execute() {
        System.out.println("Should be looking to get to Egg");
        if ((!ctx.players.local().inMotion() || ctx.movement.destination().equals(Tile.NIL) || ctx.movement.destination().distanceTo(ctx.players.local()) < 6) && pathDairyCowToEgg[pathDairyCowToEgg.length - 1].distanceTo(ctx.players.local()) > 5) {

            this.walker.walkPath(pathDairyCowToEgg);

            Condition.sleep(Random.nextInt(500, 1000));
        }

        if (!ctx.players.local().inMotion() && pathDairyCowToEgg[pathDairyCowToEgg.length - 1].distanceTo(ctx.players.local()) < 6) {
            System.out.println("Picking up egg");
            GroundItem egg = ctx.groundItems.select().id(EGG_ID).nearest().poll();
            egg.interact("Take");
            Condition.wait(() -> ctx.inventory.select().id(EGG_ID).count() > 0, 50, 20);
            Condition.sleep(Random.nextInt(500, 750));
        }
    }
}