package OldschoolRuneScape.TechnoQuestingTasks.CooksAssistant;

import OldschoolRuneScape.Task;
import OldschoolRuneScape.Walker;
import org.powerbot.script.Condition;
import org.powerbot.script.Random;
import org.powerbot.script.Tile;
import org.powerbot.script.rt4.ClientContext;
import org.powerbot.script.rt4.GameObject;

public class CooksAssistantPickWheat extends Task {

    // private final int COOKS_ASSISTANT_QUEST_VARPBIT = ;
    private final int POT_OF_FLOUR_ID = 1933;
    private final int EMPTY_POT_ID = 1931;
    private final int EMPTY_BUCKET_ID = 1925;
    private final int WHEAT_ITEM_ID = 1947;
    private final int WHEAT_OBJECT_ID[] = {15507, 15506};
    private final int WHEAT_PICK_ANIMATION = 872;
    private static final Tile wheatField = new Tile(3162, 3293, 0);


    private final Walker walker;


    public CooksAssistantPickWheat(ClientContext ctx, Walker walker) {
        super(ctx);
        this.walker = walker;
    }

    @Override
    public boolean activate() {
        return ctx.inventory.select().id(WHEAT_ITEM_ID).count() == 0 && ctx.inventory.select().id(POT_OF_FLOUR_ID).count() == 0 && ctx.inventory.select().id(EMPTY_POT_ID).count() == 1 && ctx.inventory.select().id(EMPTY_BUCKET_ID).count() == 1 && wheatField.distanceTo(ctx.players.local()) < 5;
        //  return ctx.varpbits.varpbit(COOKS_ASSISTANT_QUEST_VARPBIT) != ? && make it not finished, multiple stages
    }

    @Override
    public void execute() {

        System.out.println("Looking for Wheat");

        GameObject groundWheat = ctx.objects.select().id(WHEAT_OBJECT_ID).nearest().poll();


        if (groundWheat.inViewport() && groundWheat.tile().matrix(ctx).reachable()) {
            System.out.println("Picking wheat");
            groundWheat.interact("Pick");
            Condition.wait(() -> ctx.players.local().animation() == WHEAT_PICK_ANIMATION, 50, 40);
            Condition.sleep(Random.nextInt(1500, 2250));
        } else if (!groundWheat.inViewport() && groundWheat.tile().matrix(ctx).reachable() && !ctx.chat.chatting()) {
            System.out.println("Turning to Wheat");
            ctx.camera.turnTo(groundWheat);
        } else if (!ctx.players.local().inMotion() && ctx.movement.destination().equals(Tile.NIL) && !groundWheat.tile().matrix(ctx).reachable()) {
            System.out.println("Walking to Wheat");
            Tile wheatTile[] = {groundWheat.tile()};
            this.walker.walkPath(wheatTile);
        }
    }
}

