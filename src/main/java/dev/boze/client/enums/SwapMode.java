package dev.boze.client.enums;

public enum SwapMode {
    Normal(SlotSwapMode.Normal),
    Silent(SlotSwapMode.Normal),
    Alt(SlotSwapMode.Alt);

    private static final SwapMode[] field9 = method4();
    public final SlotSwapMode field8;

    SwapMode(SlotSwapMode var3) {
        this.field8 = var3;
    }

    private static SwapMode[] method4() {
        return new SwapMode[]{Normal, Silent, Alt};
    }
}
