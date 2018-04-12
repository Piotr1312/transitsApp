package transitsApp.logic;

public interface TransitInfoProvider {
    public int getDistance(String source, String destination);
    public int getTime(String source, String destination);
}
