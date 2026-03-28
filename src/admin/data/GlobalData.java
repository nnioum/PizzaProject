package admin.data;

import admin.model.Dough;

public class GlobalData {

    public static final Dough CLASSIC_DOUGH = new Dough("Классическая", 100);

    public static boolean IS_RUNNING = true;

    public static String WELCOME_MESSAGE = "Добро пожаловать в Конструктор Пиццы!";

    public static String HELP_BASE = """
            ============================================================================================================
            КОНСТРУКТОР ПИЦЦЫ — CLI
            ============================================================================================================
            
            ИСПОЛЬЗОВАНИЕ:
                <command> [options]
            
            ОСНОВНЫЕ КОМАНДЫ:
              admin         Режим администратора: управление справочниками
              order         Режим заказов: создание и управление заказами
              pizza-order   Режим управления пиццами в заказе
              help          Показать справку по команде\s
              exit          Выход из программы
            
            """;

    public static String HELP_ADMIN = """
            РЕЖИМ ADMIN — справка
            ============================================================================================================
            
            ИСПОЛЬЗОВАНИЕ:
              admin <resource> <action> [flags]
            
            ------------------------------------------------------------------------------------------------------------
            РЕСУРСЫ:
              ingredient   Управление ингредиентами
              dough        Управление основами\s
              crust        Управление бортиками
              pizza        Управление пиццами
            
            """;

    public static String HELP_ADMIN_INGREDIENT = """
            ------------------------------------------------------------------------------------------------------------
            INGREDIENT — ИНГРЕДИЕНТЫ:
            
              admin ingredient create --name=<str> --price=<decimal>
                Создать ингредиент
            
              admin ingredient edit --name=<str> [--new-name=<str>] [--price=<decimal>]
                Редактировать ингредиент
            
              admin ingredient delete --name=<str>
                Удалить ингредиент
            
              admin ingredient get --name=<str>
                Получить информацию об ингредиенте
            
              admin ingredient list
                Вывести список ингредиентов\s
            
            """;

    public static String HELP_ADMIN_DOUGH = """
            ------------------------------------------------------------------------------------------------------------
            DOUGH — ОСНОВЫ:
            
              admin dough create --name=<str> --price=<decimal>
                Создать основу
            
              admin dough edit --name=<str> [--new-name=<str>] [--price=<decimal>]
                Редактировать основу
            
              admin dough delete --name=<str>
                Удалить основу
            
              admin dough get --name=<str>
                Получить информацию об основе
            
              admin dough list
                Вывести список основ
            
            """;

    public static String HELP_ADMIN_CRUST = """
            ------------------------------------------------------------------------------------------------------------
            CRUST — БОРТИКИ:
            
              admin crust create --name=<str> --ingredients=<csv> --allowed-pizzas=<csv>
                Создать бортик
              admin crust edit --name=<str> [--new-name=<str>] [--ingredients=<csv>] [--allowed-pizzas=<csv> | --deny-pizzas=<csv>]
                Редактировать бортик и правила совместимости
            
              admin crust delete --name=<str>
                Удалить бортик
            
              admin crust get --name=<str>
                Получить информацию о бортике
            
              admin crust list\s
                Вывести список бортиков\s
            
            """;

    public static String HELP_ADMIN_PIZZA = """
            ------------------------------------------------------------------------------------------------------------
            PIZZA — ПИЦЦЫ:
            
              admin pizza create --name=<str> --dough=<doughName> --ingredients=<csv> [--crust=<crustName>]
                Создать пиццу\s
            
              admin pizza edit --name=<str> [--new-name=<str>] [--dough=<doughName>] [--ingredients=<csv>] [--crust=<crustName>]
                Редактировать пиццу
            
              admin pizza delete --name=<str>
                Удалить пиццу
            
              admin pizza get --name=<str>
                Получить информацию о пицце
            
              admin pizza list\s
                Вывести список пицц\s
            ------------------------------------------------------------------------------------------------------------
            ВАЖНО:
              • Пиццу нельзя создать без основы.
              • Основа (кроме классической) не может превышать +20% её стоимости.
              • Бортик должен быть совместим с пиццей.
              • CSV-формат: --ingredients=cheese,tomato,meat
            
            """;

    public static String HELP_ORDER = """
            ============================================================================================================
            РЕЖИМ ORDER — справка
            ============================================================================================================
            
            ИСПОЛЬЗОВАНИЕ:
              order <command> [flags]
            
            ------------------------------------------------------------------------------------------------------------
            ORDER — ЗАКАЗЫ:
            
              order create [--comment=<"str">] [--scheduled-date=<ГГГГ-ММ-ДДТЧЧ:ММ>]
                Создать новый заказ
            
              order edit --id=<id> [--comment=<"str">] [--scheduled-date=<ГГГГ-ММ-ДДТЧЧ:ММ>]
                Редактировать существующий заказ
            
              order get --id=<id>
                Показать детальную информацию по заказу
            
              order list [--status=<str>] [--created-date-from=<str>] [--created-date-to=<str>] [--sort-by=created-date|status]
                Вывести список заказов с фильтрацией или сортировкой
            
              order submit --id=<id>
                Оформить заказ (перевести в статус "подтверждён")
            
              order split --id=<id> --guests=<int>
                 Разделить стоимость заказа между гостями.
                 Разница добавляется одному гостю в большую сторону.
            
              order closed --id=<id>
                 Оплатить и завершить заказ
            """;

    public static String HELP_PIZZA_ORDER = """
            ============================================================================================================
            РЕЖИМ PIZZA-ORDER — справка
            ============================================================================================================
            
            ИСПОЛЬЗОВАНИЕ:
              pizza-order <command> [flags]
            
            ------------------------------------------------------------------------------------------------------------
            PIZZA-ORDER — ПИЦЦЫ В ЗАКАЗЕ:
            
              pizza-order create --order-id=<id> --type-pizza=<custom|halved|ready|sliced> --size=<small|medium|big> --dough-name=<str>
                Создать пиццу в указанном заказе
            
              pizza-order edit --id=<id> [--dough-name=<str>]
                        ready (готовая пицца из каталога):
                        [--name=<name>] [--doubled-ingredients=<1,2>]
            
                        custom (пользовательская пицца):
                        [--name=<name>] [--ingredients=<1,2>]
            
                        halved (половина на половину):
                        [--left-half-name=<name>] [--left-half-doubled-ingredients=<1,2>] [--right-half-name=<name>] [--right-half-doubled-ingredients=<1,2>]
            
                        sliced (покусочная пицца):
                        [--slice-number=<number>] [--slice-from=<number>] [--slice-to=<number>] [--override-slices=<true|false>] [--ingredients=<1,2>]
            
                Редактировать существующую пиццу в заказе
            
              pizza-order delete --id=<id>
                Удалить пиццу из заказа
            
              pizza-order get --id=<id>
                Показать детальную информацию по пицце
            
              pizza-order list --order-id=<id>
                Показать список всех пицц в указанном заказе
            
            ------------------------------------------------------------------------------------------------------------
            ВАЖНО:
              • Заказ создаётся командой order create, затем в него добавляются пиццы
              • Для каждой пиццы обязательно указывать размер и основу (тесто)
              • Заказ завершается только командой order submit
              • Ингредиенты указываются как CSV-список ID: --ingredients=1,2,3
              • ID заказа и ID пиццы — это UUID
            
            ============================================================================================================
            """;
}
