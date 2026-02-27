package data;

import model.Dough;

public class GlobalData {

    public static final Dough CLASSIC_DOUGH = new Dough("Классическая", 100);

    public static boolean IS_RUNNING = true;

    public static String WELCOME_MESSAGE = "Добро пожаловать в Конструктор Пиццы!";

    public static String HELP_BASE = "ИСПОЛЬЗОВАНИЕ:\n" +
            "    <command> [options]\n" +
            "\n" +
            "ОСНОВНЫЕ КОМАНДЫ:\n" +
            "  admin      Режим администратора: управление справочниками\n" +
            "  order      Режим заказов: создание и управление заказами\n" +
            "  help       Показать справку по команде (например: help admin)\n" +
            "  exit       Выход из программы\n";

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

    public static String HELP_ADMIN_INGREDIENT = "INGREDIENT — ИНГРЕДИЕНТЫ:\n" +
            "  admin ingredient create --name=<str> --price=<decimal>\n" +
            "    Создать ингредиент\n" +
            "\n" +
            "  admin ingredient edit --name=<str> [--new-name=<str>] [--price=<decimal>]\n" +
            "    Редактировать ингредиент\n" +
            "\n" +
            "  admin ingredient delete --name=<str>\n" +
            "    Удалить ингредиент\n" +
            "\n" +
            "  admin ingredient list [--sort=name|price] [--desc] [--filter-name=<substr>] [--price-min=<decimal>] [--price-max=<decimal>]\n" +
            "    Вывести список ингредиентов (с фильтрацией и сортировкой)\n";

    public static String HELP_ADMIN_BASE = "BASE — ОСНОВЫ:\n" +
            "  admin base create --name=<str> --price=<decimal> [--classic]\n" +
            "    Создать основу. Флаг --classic задаёт классическую основу (эталон для правила +20%)\n" +
            "\n" +
            "  admin base edit --name=<str> [--new-name=<str>] [--price=<decimal>] [--classic]\n" +
            "    Редактировать основу\n" +
            "\n" +
            "  admin base delete --name=<str>\n" +
            "    Удалить основу\n" +
            "\n" +
            "  admin base list [--sort=name|price] [--desc] [--filter-name=<substr>]\n" +
            "    Вывести список основ\n";

    public static String HELP_ADMIN_CRUST = "CRUST — БОРТИКИ:\n" +
            "  admin crust create --name=<str> --ingredients=<csv> --price=<decimal> [--allow-pizzas=<csv> | --deny-pizzas=<csv>]\n" +
            "    Создать бортик. Нужно указать либо список разрешённых пицц, либо запрещённых (не одновременно)\n" +
            "\n" +
            "  admin crust edit --name=<str> [--new-name=<str>] [--ingredients=<csv>] [--price=<decimal>] [--allow-pizzas=<csv> | --deny-pizzas=<csv>]\n" +
            "    Редактировать бортик и правила совместимости\n" +
            "\n" +
            "  admin crust delete --name=<str>\n" +
            "    Удалить бортик\n" +
            "\n" +
            "  admin crust list [--sort=name|price] [--desc] [--filter-name=<substr>] [--pizza=<pizzaName>]\n" +
            "    Вывести список бортиков (можно проверить совместимость с пиццей)\n";

    public static String HELP_ADMIN_PIZZA = "PIZZA — ПИЦЦЫ:\n" +
            "  admin pizza create --name=<str> --base=<baseName> --ingredients=<csv> [--crust=<crustName>]\n" +
            "    Создать пиццу (основа обязательна). Цена = основа + ингредиенты + бортик\n" +
            "\n" +
            "  admin pizza edit --name=<str> [--new-name=<str>] [--base=<baseName>] [--ingredients=<csv>] [--add-ingredients=<csv>] [--remove-ingredients=<csv>] [--crust=<crustName>|--no-crust]\n" +
            "    Редактировать пиццу\n" +
            "\n" +
            "  admin pizza delete --name=<str>\n" +
            "    Удалить пиццу\n" +
            "\n" +
            "  admin pizza list [--filter-ingredient=<name>] [--filter-base=<name>] [--sort=name|price] [--desc]\n" +
            "    Вывести список пицц (например, содержащих помидоры)\n" +
            "\n" +
            "ВАЖНО:\n" +
            "  - Пиццу нельзя создать без основы.\n" +
            "  - Основа (кроме классической) не может превышать +20% её стоимости.\n" +
            "  - Бортик должен быть совместим с пиццей.\n" +
            "  - CSV-формат: --ingredients=cheese,tomato,meat\n";

    public static String HELP_ORDER = "============================================================================================================\n" +
            "\n" +
            "РЕЖИМ ORDER — справка\n" +
            "\n" +
            "ИСПОЛЬЗОВАНИЕ:\n" +
            "  order <command> [flags]\n" +
            "\n" +
            "ЖИЗНЕННЫЙ ЦИКЛ ЗАКАЗА:\n" +
            "  order start [--comment=<text>] [--postpone=\"YYYY-MM-DD HH:MM\"]\n" +
            "    Создать черновик заказа и сделать его активным\n" +
            "\n" +
            "  order show\n" +
            "    Показать текущий активный заказ\n" +
            "\n" +
            "  order comment --text=<text>\n" +
            "    Добавить или изменить комментарий\n" +
            "\n" +
            "  order postpone --at=\"YYYY-MM-DD HH:MM\"\n" +
            "    Сделать заказ отложенным\n" +
            "\n" +
            "  order submit\n" +
            "    Оформить заказ (генерируется UUID, фиксируется дата и время)\n" +
            "\n" +
            "  order cancel\n" +
            "    Отменить текущий черновик заказа\n" +
            "    \n" +
            "ИНФОРМАЦИЯ:\n" +
            "    order info <resource>\n" +
            "        Показать список доступных опций определенного типа ресурса. Пример: \n" +
            "        order info pizza -- список доступных пицц\n" +
            "        order info ingredient -- список доступных ингредиентов\n" +
            "        order info base -- список доступных основ\n" +
            "        order info crust -- список доступных корочек\n" +
            "\n" +
            "ДОБАВЛЕНИЕ ПИЦЦ В АКТИВНЫЙ ЗАКАЗ:\n" +
            "order pizza add-ready --size=<small|medium|large> --pizza=<pizzaName> [--double=<csv>] [--base=<baseName>] [--crust=<crustName>]\n" +
            "    Добавить готовую пиццу. Можно удвоить существующие ингредиенты.\n" +
            "\n" +
            "  order pizza begin-custom --size=<small|medium|large> --name=<str> --base=<baseName> [--crust=<crustName>]\n" +
            "    Начать сборку кастомной пиццы (не сохраняется в каталоге)\n" +
            "\n" +
            "  order pizza add-ingredient --name=<ingredientName> [--qty=1|2]\n" +
            "    Добавить ингредиент к текущей собираемой пицце\n" +
            "\n" +
            "  order pizza remove-ingredient --name=<ingredientName>\n" +
            "    Удалить ингредиент из текущей пиццы\n" +
            "\n" +
            "  order pizza done\n" +
            "    Завершить текущую пиццу и добавить её в заказ (заказ не отправляется)\n" +
            "\n" +
            "ПОЛОВИНА + ПОЛОВИНА:\n" +
            "\n" +
            "  order pizza add-half --size=<small|medium|large> --left=<pizzaA> --right=<pizzaB> --base=<baseName> [--crust-left=<crustName>] [--crust-right=<crustName>] [--crust=<crustName>]\n" +
            "    Добавить комбинированную пиццу из двух половин\n" +
            "\n" +
            "ПОКУСОЧНАЯ ПИЦЦА:\n" +
            "\n" +
            "  order pizza begin-slices --size=<small|medium|large> --name=<str> --base=<baseName> [--crust=<crustName>]\n" +
            "    Начать сборку покусочной пиццы\n" +
            "\n" +
            "  order slice add --slice=<n> --ingredient=<name> [--qty=1|2]\n" +
            "    Добавить ингредиент на конкретный кусок (номер в пределах размера)\n" +
            "\n" +
            "  order slice range-add --from=<n> --to=<n> --ingredient=<name> [--qty=1|2]\n" +
            "    Добавить ингредиент на диапазон кусков\n" +
            "\n" +
            "  order pizza done\n" +
            "    Завершить покусочную пиццу и добавить её в заказ\n" +
            "\n" +
            "СПИСОК ЗАКАЗОВ:\n" +
            "\n" +
            "  order list [--date=YYYY-MM-DD] [--ingredient=<name>] [--size=small|medium|large] [--sort=date|total] [--desc]\n" +
            "    Вывести список оформленных заказов с фильтрацией\n" +
            "\n" +
            "РАЗДЕЛЕНИЕ СТОИМОСТИ:\n" +
            "\n" +
            "  order split --guests=<int>\n" +
            "    Разделить стоимость заказа между гостями.\n" +
            "    Деление равномерное, округление до 2 знаков.\n" +
            "    Разница добавляется одному гостю в большую сторону. \n" +
            "\n" +
            "ВАЖНО:\n" +
            "  - Можно добавить несколько пицц в один заказ.\n" +
            "  - Каждая пицца должна быть завершена командой order pizza done.\n" +
            "  - Заказ завершается только командой order submit.\n" +
            "  - Номер куска должен соответствовать размеру.\n" +
            "  - Основа обязательна для любой пиццы.\n";
}
