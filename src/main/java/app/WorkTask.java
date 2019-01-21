package app;

public class WorkTask {
    private String name;
    private double chance;
    private int minLength;
    private int maxLength;

    public WorkTask() {
    }

    public WorkTask(double chance, int minLength, int maxLength, String name) {
        this.name = name;
        this.chance = chance;
        this.minLength = minLength;
        this.maxLength = maxLength;
    }

    public String getName() {
        return name;
    }

    public WorkTask setName(String name) {
        this.name = name;
        return this;
    }

    public double getChance() {
        return chance;
    }

    public WorkTask setChance(double chance) {
        this.chance = chance;
        return this;
    }

    public int getMinLength() {
        return minLength;
    }

    public WorkTask setMinLength(int minLength) {
        this.minLength = minLength;
        return this;
    }

    public int getMaxLength() {
        return maxLength;
    }

    public WorkTask setMaxLength(int maxLength) {
        this.maxLength = maxLength;
        return this;
    }
}
