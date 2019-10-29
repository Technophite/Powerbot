package OldschoolRuneScape.TechnoSheepShearerTasks;

import OldschoolRuneScape.Task;
import OldschoolRuneScape.Walker;
import org.powerbot.script.Condition;
import org.powerbot.script.Random;
import org.powerbot.script.Tile;
import org.powerbot.script.rt4.ClientContext;
import org.powerbot.script.rt4.Component;
import org.powerbot.script.rt4.Npc;

public class ShearerSpeakToFredWithWool extends Task {

    private final int BALL_WOOL_ID = 1759;
    private final int SHEARER_QUEST_VARPBIT = 179;
    private final int FRED_FARMER_ID = 732;
    private static final Tile farmhouse = new Tile(3190, 3272, 0);


    private final Walker walker;


    public ShearerSpeakToFredWithWool(ClientContext ctx, Walker walker) {
        super(ctx);
        this.walker = walker;
    }

    @Override
    public boolean activate() {
        return ctx.varpbits.varpbit(SHEARER_QUEST_VARPBIT) != 21 && ctx.inventory.select().id(BALL_WOOL_ID).count() >= 20 && farmhouse.distanceTo(ctx.players.local()) < 8;
    }

    @Override
    public void execute() {

        System.out.println("Looking for Fred");

        Component chatContinue = ctx.widgets.widget(231).component(3);
        Component chatContinue2 = ctx.widgets.widget(217).component(3);
        Component chatContinue3 = ctx.widgets.widget(229).component(2);

        Component chatOptions0 = ctx.widgets.widget(219).component(1).component(1);


        Npc farmerFred = ctx.npcs.select().id(FRED_FARMER_ID).nearest().poll();


        if (farmerFred.inViewport() && farmerFred.tile().matrix(ctx).reachable() && !ctx.chat.chatting()) {
            System.out.println("Talking to Fred");
            farmerFred.interact("Talk-to");
            Condition.wait(() -> ctx.chat.chatting(), 250, 8);

        } else if (!farmerFred.inViewport() && farmerFred.tile().matrix(ctx).reachable() && !ctx.chat.chatting()) {
            System.out.println("Turning to Fred");
            ctx.camera.turnTo(farmerFred);
        } else if (!ctx.players.local().inMotion() && ctx.movement.destination().equals(Tile.NIL) && !farmerFred.tile().matrix(ctx).reachable()) {
            System.out.println("Walking to fred");
            Tile fredTile[] = {farmerFred.tile()};
            this.walker.walkPath(fredTile);
        }

        if (ctx.chat.chatting()) {
            System.out.println("Chatting already");
            if (chatContinue.text().equals("Click here to continue")) {
                System.out.println("Sending space");
                ctx.input.send("{VK_SPACE}");
                Condition.sleep(Random.nextInt(500, 1500));
            } else if (chatContinue2.text().equals("Click here to continue")) {
                System.out.println("Sending space");
                ctx.input.send("{VK_SPACE}");
                Condition.sleep(Random.nextInt(500, 1500));
            } else if (chatContinue3.text().equals("Click here to continue")) {
                System.out.println("Sending space");
                ctx.input.send("{VK_SPACE}");
                Condition.sleep(Random.nextInt(500, 1500));
            } else if (chatOptions0.text().equals("I'm looking for a quest.")) {
                System.out.println("Sending option 1");
                ctx.input.send("1");
                Condition.sleep(Random.nextInt(500, 1500));
            } else if (chatOptions0.text().equals("Yes okay. I can do that.")) {
                System.out.println("Sending option 1");
                ctx.input.send("1");
                Condition.sleep(Random.nextInt(500, 1500));
            }

        }
    }
}

