package bean;

public class Price {
    public static final Price INSTANCE = new Price(12);
    private static int staticPrice=5;
    public int todayPrice = 20;

    public Price(int price) {
        System.out.println(price);
        System.out.println(staticPrice);
        this.todayPrice = price - staticPrice;
    }

    public static void main(String[] args) {
        System.out.println(Price.INSTANCE.todayPrice);
    }
}
