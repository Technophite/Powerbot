package OldschoolRuneScape.TechnoQuestingTasks.CooksAssistant;

        import OldschoolRuneScape.Task;
        import OldschoolRuneScape.Walker;
        import org.powerbot.script.Condition;
        import org.powerbot.script.Tile;
        import org.powerbot.script.rt4.ClientContext;
        import org.powerbot.script.rt4.GameObject;

public class CooksAssistantMillFlour extends Task {

    // private final int COOKS_ASSISTANT_QUEST_VARPBIT = ;
    private final int POT_OF_FLOUR_ID = 1933;
    private final int WHEAT_ITEM_ID = 1947;
    private final int EMPTY_POT_ID = 1931;
    private final int EMPTY_BUCKET_ID = 1925;
    private final int HOPPER_ID = 24961;
    private final int HOPPER_FILL_ANIMATION_ID = 3572;
    private final int HOPPER_CONTROLS_ID = 24964;
    private static final Tile hopperFloor = new Tile(3166, 3308, 2);
    private final int flourBinVarpbit = 695;


    private final Walker walker;


    public CooksAssistantMillFlour(ClientContext ctx, Walker walker) {
        super(ctx);
        this.walker = walker;
    }

    @Override
    public boolean activate() {
        return ctx.varpbits.varpbit(flourBinVarpbit) != 1 && ctx.inventory.select().id(POT_OF_FLOUR_ID).count() == 0 && ctx.inventory.select().id(EMPTY_POT_ID).count() == 1 && ctx.inventory.select().id(EMPTY_BUCKET_ID).count() == 1 && hopperFloor.distanceTo(ctx.players.local()) < 5;
        //  return ctx.varpbits.varpbit(COOKS_ASSISTANT_QUEST_VARPBIT) != ? && make it not finished, multiple stages
    }

    @Override
    public void execute() {

        System.out.println("Looking for hopper");

        GameObject hopper = ctx.objects.select().id(HOPPER_ID).nearest().poll();
        GameObject hopperControls = ctx.objects.select().id(HOPPER_CONTROLS_ID).nearest().poll();


        if (hopper.inViewport() && ctx.inventory.select().id(WHEAT_ITEM_ID).count() > 0) {
            System.out.println("Filling Hopper");
            hopper.interact("Fill");
            Condition.wait(() -> ctx.players.local().animation() == 3572, 50, 40);
        } else if (!hopper.inViewport() && ctx.inventory.select().id(WHEAT_ITEM_ID).count() > 0) {
            System.out.println("Turning to Hopper");
            ctx.camera.turnTo(hopper);
        }

        if (hopperControls.inViewport() && ctx.inventory.select().id(WHEAT_ITEM_ID).count() == 0) {
            System.out.println("Operating Hopper controls");
            hopperControls.interact("Operate");
            Condition.wait(() -> ctx.varpbits.varpbit(flourBinVarpbit) == 1, 50, 40);
        } else if (!hopperControls.inViewport() && ctx.inventory.select().id(WHEAT_ITEM_ID).count() == 0) {
            System.out.println("Turning to hopperControls");
            ctx.camera.turnTo(hopperControls);
        }
    }
}

