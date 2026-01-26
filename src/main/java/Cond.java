public enum Cond {
    INIT(-1),
    FALSE(0),
    TRUE(1),
    ORR(2),
    ANDD(3),
    NOTUSED(4);

    public int value;

    Cond(int value) {
        this.value = value;
    }
}