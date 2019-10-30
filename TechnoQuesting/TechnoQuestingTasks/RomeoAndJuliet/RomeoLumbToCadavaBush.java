package OldschoolRuneScape.TechnoQuestingTasks.RomeoAndJuliet;

import OldschoolRuneScape.Task;
import OldschoolRuneScape.Walker;

import org.powerbot.script.Condition;
import org.powerbot.script.Random;
import org.powerbot.script.Tile;
import org.powerbot.script.rt4.ClientContext;
import org.powerbot.script.rt4.Item;

import java.util.concurrent.Callable;

public class RomeoLumbToCadavaBush extends Task {
    public static final Tile pathToCadava[] = {new Tile(3223, 3218, 0), new Tile(3226, 3218, 0), new Tile(3229, 3218, 0), new Tile(3232, 3218, 0), new Tile(3235, 3220, 0), new Tile(3238, 3223, 0), new Tile(3239, 3226, 0), new Tile(3242, 3226, 0), new Tile(3245, 3226, 0), new Tile(3248, 3226, 0), new Tile(3251, 3226, 0), new Tile(3254, 3226, 0), new Tile(3257, 3227, 0), new Tile(3259, 3230, 0), new Tile(3259, 3233, 0), new Tile(3259, 3236, 0), new Tile(3259, 3239, 0), new Tile(3259, 3242, 0), new Tile(3259, 3245, 0), new Tile(3259, 3248, 0), new Tile(3256, 3250, 0), new Tile(3253, 3253, 0), new Tile(3252, 3256, 0), new Tile(3250, 3259, 0), new Tile(3250, 3262, 0), new Tile(3250, 3265, 0), new Tile(3248, 3268, 0), new Tile(3247, 3271, 0), new Tile(3245, 3274, 0), new Tile(3242, 3275, 0), new Tile(3241, 3278, 0), new Tile(3239, 3281, 0), new Tile(3238, 3284, 0), new Tile(3238, 3287, 0), new Tile(3238, 3290, 0), new Tile(3238, 3293, 0), new Tile(3238, 3296, 0), new Tile(3238, 3299, 0), new Tile(3238, 3302, 0), new Tile(3239, 3305, 0), new Tile(3242, 3308, 0), new Tile(3244, 3311, 0), new Tile(3247, 3314, 0), new Tile(3249, 3317, 0), new Tile(3251, 3320, 0), new Tile(3254, 3322, 0), new Tile(3257, 3324, 0), new Tile(3258, 3327, 0), new Tile(3261, 3328, 0), new Tile(3264, 3328, 0), new Tile(3267, 3328, 0), new Tile(3267, 3331, 0), new Tile(3267, 3334, 0), new Tile(3269, 3337, 0), new Tile(3269, 3340, 0), new Tile(3269, 3343, 0), new Tile(3271, 3346, 0), new Tile(3271, 3349, 0), new Tile(3271, 3352, 0), new Tile(3271, 3355, 0), new Tile(3271, 3358, 0), new Tile(3271, 3361, 0), new Tile(3270, 3364, 0), new Tile(3270, 3367, 0)};
    private final Walker walker;

    public RomeoLumbToCadavaBush(ClientContext ctx, Walker walker) {
        super(ctx);
        this.walker = walker;
    }

    @Override
    public boolean activate() {
        return ctx.inventory.select().count() < 27 && pathToCadava[0].distanceTo(ctx.players.local()) > 5;
    }

    @Override
    public void execute() {
        if (!ctx.players.local().inMotion() || ctx.movement.destination().equals(Tile.NIL) || ctx.movement.destination().distanceTo(ctx.players.local()) < 5) {

            this.walker.walkPath(pathToCadava);

        }
    }
}