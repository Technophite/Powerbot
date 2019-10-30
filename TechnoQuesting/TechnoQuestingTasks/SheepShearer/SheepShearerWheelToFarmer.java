package OldschoolRuneScape.TechnoQuestingTasks.SheepShearer;

import OldschoolRuneScape.Task;
import OldschoolRuneScape.Walker;
import org.powerbot.script.Condition;
import org.powerbot.script.Random;
import org.powerbot.script.Tile;
import org.powerbot.script.rt4.ClientContext;

public class SheepShearerWheelToFarmer extends Task {
    public static final Tile pathToFarmer[] = {new Tile(3209, 3213, 1), new Tile(3206, 3210, 1), new Tile(3206, 3208, 0), new Tile(3209, 3209, 0), new Tile(3212, 3209, 0), new Tile(3215, 3211, 0), new Tile(3215, 3214, 0), new Tile(3215, 3217, 0), new Tile(3218, 3218, 0), new Tile(3221, 3218, 0), new Tile(3224, 3218, 0), new Tile(3227, 3218, 0), new Tile(3230, 3218, 0), new Tile(3232, 3221, 0), new Tile(3232, 3224, 0), new Tile(3232, 3227, 0), new Tile(3232, 3230, 0), new Tile(3229, 3232, 0), new Tile(3226, 3235, 0), new Tile(3224, 3238, 0), new Tile(3222, 3241, 0), new Tile(3221, 3244, 0), new Tile(3221, 3247, 0), new Tile(3218, 3249, 0), new Tile(3218, 3252, 0), new Tile(3218, 3255, 0), new Tile(3218, 3258, 0), new Tile(3218, 3261, 0), new Tile(3218, 3264, 0), new Tile(3217, 3267, 0), new Tile(3216, 3270, 0), new Tile(3216, 3273, 0), new Tile(3215, 3276, 0), new Tile(3212, 3277, 0), new Tile(3209, 3278, 0), new Tile(3206, 3278, 0), new Tile(3203, 3278, 0), new Tile(3200, 3278, 0), new Tile(3197, 3279, 0), new Tile(3194, 3280, 0), new Tile(3191, 3280, 0), new Tile(3189, 3277, 0), new Tile(3189, 3274, 0), new Tile(3191, 3272, 0)};

    private final int BALL_WOOL_ID = 1759;
    private final int SHEARER_QUEST_VARPBIT = 179;
    private final Walker walker;

    public SheepShearerWheelToFarmer(ClientContext ctx, Walker walker) {
        super(ctx);
        this.walker = walker;
    }

    @Override
    public boolean activate() {
        return ctx.varpbits.varpbit(SHEARER_QUEST_VARPBIT) != 21 && ctx.inventory.select().id(BALL_WOOL_ID).count() >= 20 && pathToFarmer[pathToFarmer.length - 1].distanceTo(ctx.players.local()) > 2;
    }

    @Override
    public void execute() {
        System.out.println("Should be looking to get to Farmer");
        if (!ctx.players.local().inMotion() || ctx.movement.destination().equals(Tile.NIL) || ctx.movement.destination().distanceTo(ctx.players.local()) < 6) {

            this.walker.walkPath(pathToFarmer);

            Condition.sleep(Random.nextInt(500, 1000));

        }
    }
}