package OldschoolRuneScape.TechnoQuestingTasks.CooksAssistant;

import OldschoolRuneScape.Task;
import OldschoolRuneScape.Walker;
import org.powerbot.script.Condition;
import org.powerbot.script.Random;
import org.powerbot.script.Tile;
import org.powerbot.script.rt4.ClientContext;
import org.powerbot.script.rt4.GameObject;

import java.util.concurrent.Callable;

public class CooksAssistantMilkDairyCow extends Task {
    private final int POT_OF_FLOUR_ID = 1933;
    private final int EMPTY_BUCKET_ID = 1925;
    private final int BUCKET_OF_MILK_ID = 1927;
    // private final int COOKS_ASSISTANT_QUEST_VARPBIT = ;
    private final int DAIRY_COW_ID = 8689;
    private final int DAIRY_COW_MILK_ANIMATION = 2305;
    private static final Tile dairyCowTile[] = {new Tile(3171, 3318, 0)};

    private final Walker walker;

    public CooksAssistantMilkDairyCow(ClientContext ctx, Walker walker) {
        super(ctx);
        this.walker = walker;
    }

    @Override
    public boolean activate() {
        return ctx.inventory.select().id(POT_OF_FLOUR_ID).count() == 1 && ctx.inventory.select().id(EMPTY_BUCKET_ID).count() == 1 && dairyCowTile[0].distanceTo(ctx.players.local()) < 6;
        //  return ctx.varpbits.varpbit(COOKS_ASSISTANT_QUEST_VARPBIT) != ? && make it not finished, multiple stages
    }

    @Override
    public void execute() {
        System.out.println("Looking for Dairy Cow");
        GameObject dairyCow = ctx.objects.select().id(DAIRY_COW_ID).nearest().poll();

        if (dairyCow.inViewport() && dairyCowTile[0].matrix(ctx).reachable()) {
            System.out.println("Milking cow");
            dairyCow.interact("Milk");
            Condition.wait(() -> ctx.players.local().animation() == DAIRY_COW_MILK_ANIMATION, 50, 40);
            Condition.sleep(Random.nextInt(1000, 1750));
        } else if (!dairyCow.inViewport() && dairyCowTile[0].matrix(ctx).reachable() && !ctx.chat.chatting()) {
            System.out.println("Turning to cow");
            ctx.camera.turnTo(dairyCow);
        } else if (!ctx.players.local().inMotion() && ctx.movement.destination().equals(Tile.NIL) && !dairyCowTile[0].matrix(ctx).reachable()) {
            System.out.println("Walking to cow");
            this.walker.walkPath(dairyCowTile);
        }
    }
}
