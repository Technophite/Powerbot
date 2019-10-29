package OldschoolRuneScape.TechnoSheepShearerTasks;

import OldschoolRuneScape.Task;
import OldschoolRuneScape.Walker;
import org.powerbot.script.Condition;
import org.powerbot.script.Random;
import org.powerbot.script.Tile;
import org.powerbot.script.rt4.ClientContext;

import java.util.concurrent.Callable;

public class ShearerSheepPenToWheel extends Task {
    public static final Tile pathToWheel[] = {new Tile(3201, 3266, 0), new Tile(3204, 3263, 0), new Tile(3207, 3262, 0), new Tile(3210, 3262, 0), new Tile(3213, 3261, 0), new Tile(3213, 3258, 0), new Tile(3214, 3255, 0), new Tile(3216, 3252, 0), new Tile(3216, 3249, 0), new Tile(3217, 3246, 0), new Tile(3219, 3243, 0), new Tile(3221, 3240, 0), new Tile(3224, 3237, 0), new Tile(3227, 3234, 0), new Tile(3227, 3231, 0), new Tile(3229, 3228, 0), new Tile(3231, 3225, 0), new Tile(3231, 3222, 0), new Tile(3232, 3219, 0), new Tile(3229, 3219, 0), new Tile(3226, 3219, 0), new Tile(3223, 3219, 0), new Tile(3220, 3219, 0), new Tile(3217, 3219, 0), new Tile(3215, 3216, 0), new Tile(3215, 3213, 0), new Tile(3212, 3211, 0), new Tile(3209, 3211, 0), new Tile(3206, 3210, 0), new Tile(3205, 3209, 1), new Tile(3206, 3212, 1), new Tile(3209, 3213, 1)};
    private final int WOOL_ID = 1737;
    private final int SHEARER_QUEST_VARPBIT = 179;
    private final Walker walker;

    public ShearerSheepPenToWheel(ClientContext ctx, Walker walker) {
        super(ctx);
        this.walker = walker;
    }

    @Override
    public boolean activate() {
        return ctx.varpbits.varpbit(SHEARER_QUEST_VARPBIT) != 21 && ctx.inventory.select().id(WOOL_ID).count() >= 20 && pathToWheel[pathToWheel.length - 1].distanceTo(ctx.players.local()) > 2;
    }

    @Override
    public void execute() {
        System.out.println("Should be looking to get to wheel");
        if (!ctx.players.local().inMotion() || ctx.movement.destination().equals(Tile.NIL) || ctx.movement.destination().distanceTo(ctx.players.local()) < 6) {
            if (!ctx.movement.running()) {
                ctx.movement.running(true);
                Condition.wait(new Callable<Boolean>() {
                    @Override
                    public Boolean call() throws Exception {
                        return ctx.movement.running();
                    }
                }, 25 + Random.nextInt(0, 25), 5);

            }
            this.walker.walkPath(pathToWheel);

            Condition.sleep(Random.nextInt(500, 1000));

        }
    }
}