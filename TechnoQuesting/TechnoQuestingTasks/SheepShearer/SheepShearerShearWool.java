package OldschoolRuneScape.TechnoQuestingTasks.SheepShearer;

import OldschoolRuneScape.Task;
import OldschoolRuneScape.Walker;
import org.powerbot.script.Condition;
import org.powerbot.script.Tile;
import org.powerbot.script.rt4.ClientContext;
import org.powerbot.script.rt4.Npc;
import org.powerbot.script.Random;

public class SheepShearerShearWool extends Task {

    private final int[] SHEEP_IDS = {2786, 2787, 2693, 2695, 2699};
    private final int WOOL_ID = 1737;
    private final int SHEARS_ID = 1735;
    private final int SHEARER_QUEST_VARPBIT = 179;
    private final Tile SHEEP_PEN = new Tile(3202, 3267, 0);
    private final Walker walker;

    public SheepShearerShearWool(ClientContext ctx, Walker walker) {
        super(ctx);
        this.walker = walker;
    }

    @Override
    public boolean activate() {
        return ctx.varpbits.varpbit(SHEARER_QUEST_VARPBIT) != 21 && !ctx.players.local().inMotion() && ctx.players.local().animation() == -1 && ctx.inventory.select().count() < 28 && ctx.inventory.select().id(WOOL_ID).count() < 20 && ctx.inventory.select().id(SHEARS_ID).count() == 1 && SHEEP_PEN.distanceTo(ctx.players.local()) < 15;
    }

    @Override
    public void execute() {
        System.out.println("Looking for sheep");
        Npc sheep = ctx.npcs.select().id(SHEEP_IDS).nearest().poll();

        if (sheep.inViewport() && sheep.tile().matrix(ctx).reachable() && ctx.inventory.select().id(WOOL_ID).count() < 20) {
            Condition.sleep(Random.nextInt(250, 1000));
            System.out.println("Shearing sheep.");
            sheep.interact("Shear");
            Condition.wait(() -> ctx.players.local().animation() == 893, 50, 20);
        } else if (!sheep.inViewport() && sheep.tile().matrix(ctx).reachable() && ctx.inventory.select().id(WOOL_ID).count() < 20) {
            System.out.println("Turning to Sheep");
            ctx.camera.turnTo(sheep);
        } else if (!ctx.players.local().inMotion() && ctx.movement.destination().equals(Tile.NIL) && !sheep.tile().matrix(ctx).reachable() && ctx.inventory.select().id(WOOL_ID).count() < 20) {
            System.out.println("Walking to Sheep");
            Tile sheepTile[] = {sheep.tile()};
            this.walker.walkPath(sheepTile);
        }

    }
}
