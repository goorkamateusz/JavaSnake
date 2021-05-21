public class Random {

    public static int Range(int min, int max) {
        return (int) Math.floor(Math.random()*(max-min+1)+min);
    }
}
