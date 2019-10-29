package OldschoolRuneScape.TechnoSheepShearerTasks;

import OldschoolRuneScape.Task;
import OldschoolRuneScape.Walker;
import org.powerbot.script.Condition;
import org.powerbot.script.Random;
import org.powerbot.script.Tile;
import org.powerbot.script.rt4.*;


public class ShearerSpinWool extends Task {

    private final int WOOL_ID = 1737;
    private final int SPINNING_WHEEL_ID = 14889;
    private static final Tile WHEEL_LOCATION[] = {new Tile(3209, 3213, 1)};
    private final int SHEARER_QUEST_VARPBIT = 179;
    private final Walker walker;

    public ShearerSpinWool(ClientContext ctx, Walker walker) {
        super(ctx);
        this.walker = walker;
    }


    @Override
    public boolean activate() {
        return ctx.varpbits.varpbit(SHEARER_QUEST_VARPBIT) != 21 && ctx.inventory.select().id(WOOL_ID).count() > 0 && WHEEL_LOCATION[0].distanceTo(ctx.players.local()) < 3;
    }

    @Override
    public void execute() {
        System.out.println("Looking for wheel");

        ctx.game.tab(Game.Tab.INVENTORY);
        GameObject spinningWheel = ctx.objects.select().id(SPINNING_WHEEL_ID).nearest().poll();
        Item wool = ctx.inventory.select().id(WOOL_ID).poll();
        final int temp = ctx.inventory.select().id(WOOL_ID).count();

        if (WHEEL_LOCATION[0].matrix(ctx).reachable() && spinningWheel.inViewport()) {
            System.out.println("Using wheel");
            wool.interact("Use");
            spinningWheel.interact("Use", "Spinning wheel");
            Condition.sleep(Random.nextInt(1250, 2000));
        } else if (WHEEL_LOCATION[0].matrix(ctx).reachable() && !spinningWheel.inViewport()) {
            System.out.println("Turning to wheel");
            ctx.camera.turnTo(spinningWheel);
        } else if (!WHEEL_LOCATION[0].matrix(ctx).reachable()) {
            System.out.println("Walking to wheel");
            this.walker.walkPath(WHEEL_LOCATION);
        }
        Component spin = ctx.widgets.widget(270).component(14);

        if (spin.visible()) {
            spin.interact("Make");
            Condition.wait(() -> temp != ctx.inventory.select().id(WOOL_ID).count(), 250, 8);

            if (temp != ctx.inventory.select().id(WOOL_ID).count()) {
                Condition.wait(() -> ctx.inventory.select().id(WOOL_ID).isEmpty() ||
                        ctx.chat.canContinue(), 1000, 50);
            }
        }


    }
}
