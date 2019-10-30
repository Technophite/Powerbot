package OldschoolRuneScape.TechnoQuestingTasks.RomeoAndJuliet;

import OldschoolRuneScape.Task;
import org.powerbot.script.Condition;
import org.powerbot.script.Random;
import org.powerbot.script.Tile;
import org.powerbot.script.rt4.ClientContext;
import org.powerbot.script.rt4.GameObject;

public class RomeoPickCadava extends Task {

    private final int[] CADAVABUSH_IDS = {23625,23626};
    private final Tile nearBushes = new Tile(3270, 3367, 0);
    private final int CADAVA_BERRIES_ID = 753;

    public RomeoPickCadava(ClientContext ctx) {
        super(ctx);
    }

    @Override
    public boolean activate() {
        return ctx.inventory.select().id(CADAVA_BERRIES_ID).count() == 0 && nearBushes.distanceTo(ctx.players.local()) > 5;
    }

    @Override
    public void execute() {
        GameObject cadavaBush = ctx.objects.select().id(CADAVABUSH_IDS).nearest().poll();

        if (ctx.inventory.select().id(CADAVA_BERRIES_ID).count() == 0) {
            cadavaBush.interact("Pick");
            System.out.println("Picking Cadava berries.");
            Condition.wait(() -> ctx.players.local().animation() == 893, 100, 5);
            Condition.wait(() -> ctx.players.local().animation() == -1, 100, 5);
            Condition.sleep(1000 + Random.nextInt(0, 1000));
        }

    }
}
