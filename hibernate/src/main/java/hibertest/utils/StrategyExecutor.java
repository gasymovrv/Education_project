package hibertest.utils;

public class StrategyExecutor {
    private InheritanceStrategyExecutable strategy;

    public void setStrategy(InheritanceStrategyExecutable strategy) {
        this.strategy = strategy;
    }

    public void executeStrategy() {
        strategy.execute();
    }
}