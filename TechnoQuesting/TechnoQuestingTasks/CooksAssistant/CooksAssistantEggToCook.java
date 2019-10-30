package OldschoolRuneScape.TechnoQuestingTasks.CooksAssistant;

import OldschoolRuneScape.Task;
import OldschoolRuneScape.Walker;
import org.powerbot.script.Condition;
import org.powerbot.script.Random;
import org.powerbot.script.Tile;
import org.powerbot.script.rt4.ClientContext;

import java.util.concurrent.Callable;

public class CooksAssistantEggToCook extends Task {

    public static final Tile pathEggToCook[] = {new Tile(3229, 3299, 0), new Tile(3232, 3298, 0), new Tile(3235, 3297, 0), new Tile(3238, 3295, 0), new Tile(3238, 3292, 0), new Tile(3238, 3289, 0), new Tile(3238, 3286, 0), new Tile(3238, 3283, 0), new Tile(3238, 3280, 0), new Tile(3238, 3277, 0), new Tile(3240, 3274, 0), new Tile(3240, 3271, 0), new Tile(3240, 3268, 0), new Tile(3240, 3265, 0), new Tile(3241, 3262, 0), new Tile(3238, 3262, 0), new Tile(3235, 3262, 0), new Tile(3232, 3262, 0), new Tile(3229, 3261, 0), new Tile(3231, 3258, 0), new Tile(3231, 3255, 0), new Tile(3232, 3252, 0), new Tile(3232, 3249, 0), new Tile(3232, 3246, 0), new Tile(3232, 3243, 0), new Tile(3234, 3240, 0), new Tile(3234, 3237, 0), new Tile(3234, 3234, 0), new Tile(3233, 3231, 0), new Tile(3233, 3228, 0), new Tile(3233, 3225, 0), new Tile(3233, 3222, 0), new Tile(3232, 3219, 0), new Tile(3229, 3219, 0), new Tile(3226, 3219, 0), new Tile(3223, 3219, 0), new Tile(3220, 3218, 0), new Tile(3217, 3218, 0), new Tile(3215, 3215, 0), new Tile(3215, 3212, 0), new Tile(3212, 3211, 0), new Tile(3209, 3211, 0), new Tile(3207, 3214, 0)};

    private final int POT_OF_FLOUR_ID = 1933;
    private final int BUCKET_OF_MILK_ID = 1927;
    private final int EGG_ID = 1944;
    private final int COOKS_ASSISTANT_QUEST_VARPBIT = 29;

    private final Walker walker;

    public CooksAssistantEggToCook(ClientContext ctx, Walker walker) {
        super(ctx);
        this.walker = walker;
    }

    @Override
    public boolean activate() {
        return ctx.varpbits.varpbit(COOKS_ASSISTANT_QUEST_VARPBIT) != 2 && ctx.inventory.select().id(EGG_ID).count() == 1 && ctx.inventory.select().id(POT_OF_FLOUR_ID).count() == 1 && ctx.inventory.select().id(BUCKET_OF_MILK_ID).count() == 1 &&  pathEggToCook[pathEggToCook.length - 1].distanceTo(ctx.players.local()) > 2;
        //   && make it not finished, multiple stages
    }

    @Override
    public void execute() {
        System.out.println("Should be looking to get to Cook");
        if ((!ctx.players.local().inMotion() || ctx.movement.destination().equals(Tile.NIL) || ctx.movement.destination().distanceTo(ctx.players.local()) < 6) && pathEggToCook[pathEggToCook.length - 1].distanceTo(ctx.players.local()) > 5) {

            this.walker.walkPath(pathEggToCook);

            Condition.sleep(Random.nextInt(500, 1000));
        }
    }
}