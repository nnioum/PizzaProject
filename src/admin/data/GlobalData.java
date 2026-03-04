package admin.data;

import admin.model.Dough;

public class GlobalData {

    public static final Dough CLASSIC_DOUGH = new Dough("Классическая", 100);

    public static boolean IS_RUNNING = true;

    public static String WELCOME_MESSAGE = "Добро пожаловать в Конструктор Пиццы!";

    public static String HELP_BASE = """
            ИСПОЛЬЗОВАНИЕ:
                <command> [options]
            
            ОСНОВНЫЕ КОМАНДЫ:
              admin      Режим администратора: управление справочниками
              order      Режим заказов: создание и управление заказами
              help       Показать справку по команде (например: help admin)
              exit       Выход из программы
            """;

    public static String HELP_ADMIN = "============================================================================================================\n" +
            "РЕЖИМ ADMIN — справка\n" +
            "\n" +
            "ИСПОЛЬЗОВАНИЕ:\n" +
            "  admin <resource> <action> [flags]\n" +
            "\n" +
            "РЕСУРСЫ:\n" +
            "  ingredient   Управление ингредиентами\n" +
            "  base         Управление основами (тестом)\n" +
            "  crust        Управление бортиками\n" +
            "  pizza        Управление пиццами\n";

    public static String HELP_ADMIN_INGREDIENT = """
            INGREDIENT — ИНГРЕДИЕНТЫ:
            
              admin ingredient create --name=<str> --price=<decimal>
                Создать ингредиент
            
              admin ingredient edit --name=<str> [--new-name=<str>] [--price=<decimal>]
                Редактировать ингредиент
            
              admin ingredient delete --name=<str>
                Удалить ингредиент
            
              admin ingredient get --name=<str>
            
              admin ingredient list [--sort-by=name|price] [--sort-type=asc|desc] [--name=<substr>] [--price=<integer>]
                Вывести список ингредиентов (с фильтрацией и сортировкой)
            """;

    public static String HELP_ADMIN_BASE = """
            BASE — ОСНОВЫ:
              admin base create --name=<str> --price=<decimal>
                Создать основу. Флаг --classic задаёт классическую основу (эталон для правила +20%)
            
              admin base edit --name=<str> [--new-name=<str>] [--price=<decimal>]
                Редактировать основу
            
              admin base delete --name=<str>
                Удалить основу
            
              admin base get --name=<str>
            
              admin base list [--sort-by=name|price] [--sort-type=asc|desc] [--name=<substr>] [--price=<integer>]
                Вывести список основ
            """;

    public static String HELP_ADMIN_CRUST = """
            CRUST — БОРТИКИ:
              admin crust create --name=<str> --ingredients=<csv> --price=<decimal> [--allow-pizzas=<csv> | --deny-pizzas=<csv>]
                Создать бортик. Нужно указать либо список разрешённых пицц, либо запрещённых (не одновременно)
            
              admin crust edit --name=<str> [--new-name=<str>] [--ingredients=<csv>] [--price=<decimal>] [--allow-pizzas=<csv> | --deny-pizzas=<csv>]
                Редактировать бортик и правила совместимости
            
              admin crust delete --name=<str>
                Удалить бортик
            
              admin crust get --name=<str>
            
              admin crust list [--sort-by=name|price] [--sort-type=asc|desc] [--name=<substr>] [--price=<integer>]
                Вывести список бортиков (можно проверить совместимость с пиццей)
            """;

    public static String HELP_ADMIN_PIZZA = """
            PIZZA — ПИЦЦЫ:
              admin pizza create --name=<str> --base=<baseName> --ingredients=<csv> [--crust=<crustName>]
                Создать пиццу (основа обязательна). Цена = основа + ингредиенты + бортик
            
              admin pizza edit --name=<str> [--new-name=<str>] [--base=<baseName>] [--ingredients=<csv>][--crust=<crustName>]
                Редактировать пиццу
            
              admin pizza delete --name=<str>
                Удалить пиццу
            
              admin pizza get --name=<str>
            
              admin pizza list [--sort-by=name|price] [--sort-type=asc|desc] [--name=<substr>] [--price=<integer>]
                Вывести список пицц (например, содержащих помидоры)
            
            ВАЖНО:
              - Пиццу нельзя создать без основы.
              - Основа (кроме классической) не может превышать +20% её стоимости.
              - Бортик должен быть совместим с пиццей.
              - CSV-формат: --ingredients=cheese,tomato,meat
            """;

    public static String HELP_ORDER = """
            ============================================================================================================
            
            РЕЖИМ ORDER — справка
            
            ИСПОЛЬЗОВАНИЕ:
              order <command> [flags]
            
            ЖИЗНЕННЫЙ ЦИКЛ ЗАКАЗА:
              order start [--comment=<text>] [--postpone="YYYY-MM-DD HH:MM"]
                Создать черновик заказа и сделать его активным
            
              order show
                Показать текущий активный заказ
            
              order comment --text=<text>
                Добавить или изменить комментарий
            
              order postpone --at="YYYY-MM-DD HH:MM"
                Сделать заказ отложенным
            
              order submit
                Оформить заказ (генерируется UUID, фиксируется дата и время)
            
              order cancel
                Отменить текущий черновик заказа
               \s
            ИНФОРМАЦИЯ:
                order info <resource>
                    Показать список доступных опций определенного типа ресурса. Пример:\s
                    order info pizza -- список доступных пицц
                    order info ingredient -- список доступных ингредиентов
                    order info base -- список доступных основ
                    order info crust -- список доступных корочек
            
            ДОБАВЛЕНИЕ ПИЦЦ В АКТИВНЫЙ ЗАКАЗ:
            order pizza add-ready --size=<small|medium|large> --pizza=<pizzaName> [--double=<csv>] [--base=<baseName>] [--crust=<crustName>]
                Добавить готовую пиццу. Можно удвоить существующие ингредиенты.
            
              order pizza begin-custom --size=<small|medium|large> --name=<str> --base=<baseName> [--crust=<crustName>]
                Начать сборку кастомной пиццы (не сохраняется в каталоге)
            
              order pizza add-ingredient --name=<ingredientName> [--qty=1|2]
                Добавить ингредиент к текущей собираемой пицце
            
              order pizza remove-ingredient --name=<ingredientName>
                Удалить ингредиент из текущей пиццы
            
              order pizza done
                Завершить текущую пиццу и добавить её в заказ (заказ не отправляется)
            
            ПОЛОВИНА + ПОЛОВИНА:
            
              order pizza add-half --size=<small|medium|large> --left=<pizzaA> --right=<pizzaB> --base=<baseName> [--crust-left=<crustName>] [--crust-right=<crustName>] [--crust=<crustName>]
                Добавить комбинированную пиццу из двух половин
            
            ПОКУСОЧНАЯ ПИЦЦА:
            
              order pizza begin-slices --size=<small|medium|large> --name=<str> --base=<baseName> [--crust=<crustName>]
                Начать сборку покусочной пиццы
            
              order slice add --slice=<n> --ingredient=<name> [--qty=1|2]
                Добавить ингредиент на конкретный кусок (номер в пределах размера)
            
              order slice range-add --from=<n> --to=<n> --ingredient=<name> [--qty=1|2]
                Добавить ингредиент на диапазон кусков
            
              order pizza done
                Завершить покусочную пиццу и добавить её в заказ
            
            СПИСОК ЗАКАЗОВ:
            
              order list [--date=YYYY-MM-DD] [--ingredient=<name>] [--size=small|medium|large] [--sort=date|total] [--desc]
                Вывести список оформленных заказов с фильтрацией
            
            РАЗДЕЛЕНИЕ СТОИМОСТИ:
            
              order split --guests=<int>
                Разделить стоимость заказа между гостями.
                Деление равномерное, округление до 2 знаков.
                Разница добавляется одному гостю в большую сторону.\s
            
            ВАЖНО:
              - Можно добавить несколько пицц в один заказ.
              - Каждая пицца должна быть завершена командой order pizza done.
              - Заказ завершается только командой order submit.
              - Номер куска должен соответствовать размеру.
              - Основа обязательна для любой пиццы.
            """;
}
