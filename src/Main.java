import model.PricedItem;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        PricedItem pricedItem = new PricedItem("fghdsj", 12);
        System.out.println(pricedItem);
    }
}