package isft.operativeinvestigation.model;

public final class SolverFactory {
    private SolverFactory(){
        
    }
    
    public static Maximizer createMaximizer(){
        return new Maximizer();
    }
    public static Minimizer createMinimizer(){
        return new Minimizer();
    }
}
