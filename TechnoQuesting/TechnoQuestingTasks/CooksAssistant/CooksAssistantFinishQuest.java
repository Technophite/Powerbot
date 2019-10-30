package OldschoolRuneScape.TechnoQuestingTasks.CooksAssistant;

import OldschoolRuneScape.Task;
import OldschoolRuneScape.Walker;
import org.powerbot.script.Condition;
import org.powerbot.script.Random;
import org.powerbot.script.Tile;
import org.powerbot.script.rt4.ClientContext;
import org.powerbot.script.rt4.Component;
import org.powerbot.script.rt4.Components;
import org.powerbot.script.rt4.Npc;

public class CooksAssistantFinishQuest extends Task {

    private final int POT_OF_FLOUR_ID = 1933;
    private final int BUCKET_OF_MILK_ID = 1927;
    private final int EGG_ID = 1944;
    private final int COOK_ID = 4626;
    private final Tile cooksKitchen = new Tile(3208, 3216, 0);
    private final int COOKS_ASSISTANT_QUEST_VARPBIT = 29;

    private final Walker walker;

    public CooksAssistantFinishQuest(ClientContext ctx, Walker walker) {
        super(ctx);
        this.walker = walker;
    }

    @Override
    public boolean activate() {
        return ctx.varpbits.varpbit(COOKS_ASSISTANT_QUEST_VARPBIT) != 2 && ctx.inventory.select().id(EGG_ID).count() == 1 && ctx.inventory.select().id(POT_OF_FLOUR_ID).count() == 1 && ctx.inventory.select().id(BUCKET_OF_MILK_ID).count() == 1 && cooksKitchen.distanceTo(ctx.players.local()) < 6 || ctx.varpbits.varpbit(COOKS_ASSISTANT_QUEST_VARPBIT) == 1 && cooksKitchen.distanceTo(ctx.players.local()) < 6 ;
        //  return ctx.varpbits.varpbit(COOKS_ASSISTANT_QUEST_VARPBIT) != 2 && make it not finished, multiple stages
    }

    @Override
    public void execute() {

        System.out.println("Looking for Cook");
//
//Component assignments were here before
        Npc cook = ctx.npcs.select().id(COOK_ID).nearest().poll();


        if (cook.inViewport() && cook.tile().matrix(ctx).reachable() && !ctx.chat.chatting()) {
            System.out.println("Talking to Cook");
            cook.interact("Talk-to");
            Condition.wait(() -> ctx.chat.chatting(), 250, 8);

        } else if (!cook.inViewport() && cook.tile().matrix(ctx).reachable() && !ctx.chat.chatting()) {
            System.out.println("Turning to Cook");
            ctx.camera.turnTo(cook);
        } else if (!ctx.players.local().inMotion() && ctx.movement.destination().equals(Tile.NIL) && !cook.tile().matrix(ctx).reachable()) {
            System.out.println("Walking to Cook");
            Tile fredTile[] = {cook.tile()};
            this.walker.walkPath(fredTile);
        }

        if (ctx.chat.chatting()) {
            System.out.println("Chatting already");

            Component chatContinue = ctx.components.select().text("Click here to continue").poll();
            Component chatOptions = ctx.components.select().text("What's wrong?", "I'm always happy to help a cook in distress.").poll();

            if (chatContinue.visible()) {
                System.out.println("Sending space");
                ctx.input.send("{VK_SPACE}");
                Condition.wait(() -> chatOptions.visible(), 50, 20);
            } else if (chatOptions.visible()) {
                System.out.println("Sending option 1");
                ctx.input.send("1");
                Condition.wait(() -> chatContinue.visible(), 50, 20);
            }
        }
    }
}

