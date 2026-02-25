import model.Dough;
import service.DoughService;

import java.util.Scanner;

public class Main {
    final DoughService doughService = new DoughService();

    public static void main(String[] args) throws Exception {
        Scanner scanner = new Scanner(System.in);
        while (true) {
            String[] commands = scanner.nextLine().split(" ");
            switch (commands[0]) {
                case "admin":
                    switch (commands[1]) {
                        case "ingredient":
                            adminIngredient(commands);
                        case "base":
                    }
                case "order":
            }
        }
    }

    public static void adminIngredient(String[] commands) throws Exception {
        final DoughService doughService = new DoughService();
        switch (commands[2]) {
            case "create":
                try {
                    if (!commands[3].equals("--name=") || !commands[5].equals("--price="))
                        throw new Exception();
                } catch (Exception e) {
                    System.out.println("Введите корректные данные");
                } finally {
                    String name = commands[4];
                    int price = Integer.valueOf(commands[6]);
                    Dough dough = new Dough(name, price);
                    doughService.create(dough);
            }
            case "edit":
                String name, newName;
                int price;
                try{
                    if(!commands[3].equals("--name=")){
                        throw new Exception();
                    }else {
                        name = commands[3];
                    }
                    if(commands.length==7){
                        if(commands[5].equals("--new-name=")){
                            Dough dough = doughService.getByName(name);
                            newName = commands[6];
                            price = dough.getPrice();
                            Dough newDough = new Dough(newName, price);
                            doughService.update(name, newDough);
                        }else{
                            throw new Exception();
                        }
                    }
                }catch (Exception e){
                    System.out.println("Введите корректные данные");
        }
    }
}