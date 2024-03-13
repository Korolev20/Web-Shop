Запуск.

git clone https://github.com/Korolev20/Web-Shop.git (open Intellij Idea)
В файле application.properties должно быть:

spring.datasource.url=jdbc:oracle:thin:@localhost:1521/oracle
spring.datasource.username= ‘Your Username’
spring.datasource.password= ‘Your Password’
Maven – MyWebProgr – lifecycle: -maven clean -maven package

Примечание: Есть sql-файл, который лежит в папке sql в корне проекта (имя файла init.sql). Sql-файл содержит скрипты, которые надо использовать в oracle.Файл содержит создание таблиц, добавление строк (по два строчки в дефолтные таблицы, одну строчку для таблицы users и три дефолтных строчки для таблицы sales).

БД
Таблица Users
userId(PK INTEGER NOT NULL)	fistName (TEXT(50))	lastName (TEXT(50))	email (TEXT(50) NOT NULL)

Таблица Exbarc
exbarcId (PK INTEGER NOR NULL)	packId (FK INTEGER NOT NULL)	exbarBody (TEXT(30) NOT NULL)

Таблица Pack
packId (PK INTEGER NOT NULL)	unitId (INTEGER NOT NULL)	packName (TEXT(50) NOT NULL)	packQuant (INTEGER NOT NULL)	packType (INTEGER NOT NULL)

Таблица PackPrc
packId (PK INTEGER NOT NULL)	packPrice (INTEGER NOT NULL)	packBonus(INTEGER NOT NULL)

Таблица UnitId
unitId(PK INTEGER NOT NULL)	unitName (TEXT(50) NOT NULL)
- exbarBody – номер штрихкода;
- packName – название товара;
- packQuant – количество товара (зависит от packType, если packType = 0, то packQuant = 1 (товар штучный), если packType = 1, то packQuant = 1000 (товар весовой измеряется в граммах));
- packType  = 0 или 1 (можно заменить на булево значение);
-packPrice - цена товара (в копейках)
-packBonus – скидка товара (в копейках)
-unitName – название единицы измерения;


Запросы в Postman:

1. Регистрация http://localhost:8080/auth/registration Post-запрос: { "fistname":"…", - необязательное "lastname":"…", - необязательное "email ":"…", "password":"" } Если такой email уже имеется в БД или(и) пароль указан неверно, то – {"message": " There is already a human with the same email or not correct password"}. При успешной регистрации, в БД добавляется email и зашифрованный пароль от BCryptPasswordEncoder и создается JWT токен на основе email и возвращается клиенту. Токен действителен 1 месяц.

2. Аутентификация http://localhost:8080/auth/login Можно залогиниться используя email и password для существующего пользователя: POST – запрос: { "email":"oskar@mail.ru", "password":"1234" } Или зарегистрироваться и потом ввести данные. Если email и password совпадают, то создается новый токен и возвращается клиенту.

3. Удаление пользователя по id Post-запрос http://localhost:8080/auth/deleteUserById/{id} Если ввести id несуществующего пользователя, то вернется not found

4. Работа с запросами Магазина Для выполнения запросов, нужно сначала зарегистрироваться, чтобы получить JWT токен, после ДОБАВЛЯТЬ в header: key – Authorization и value – Bearer {JWT токен} после чего можно выполнять запросы:

4.1 Cписок всех Assortment (GET) - http://localhost:8080/api/findAll

4.2 Получение Product(GET) – http://localhost:8080/api/calculateProductPrice?exbarBody1=123123&quantity1=1&isDiscountProvided=true, Где : exbarBody1= 123123 или 321321 (существующие товары) quantity1 = 1 > 0 isDiscountProvided=true или false Обрабатывается исключение: quantity – измеряется в кг, если данный товар весовой. А если данный товар штучный – то мы не можем ввести дробное значение. И обрабатывается исключение, если exbarBody1 ввод несуществующего штриха. (not found)

4.3 Post запрос Продажа (Sales) - http://localhost:8080/api/sales Пример { "productDTOList": [ { "exbarBody": "32111", "packName": "Бонаква", "quantity1": 1, "totalPrice": 1.0, "bonusPrice": 0.20, "unitName": "шт", "packType": 0 }, { "exbarBody": "32111", "packName": "Бонаква", "quantity1": 1, "totalPrice": 2.0, "bonusPrice": 0.20, "unitName": "шт", "packType": 0 }, { "exbarBody": "32111", "packName": "Бонаква", "quantity1": 1, "totalPrice": 1.0, "bonusPrice": 0.20, "unitName": "шт", "packType": 0 } ], "cashPrice": 20, "cardPrice": 0.0 } Вывод: “Операция оплаты прошла успешно, сдача = 16.0 “(cashPrice + CardPrice(сумма клиента) – totalPrice суммы всех товаров). Если сумма клиента( напр. 3.0) будет меньше суммы товаров то будет сообщение “Операция не выполнена: задолженность = -1.0”. В Таблице, первые три строчки, дефолтные, там totalPrice и BonusPrice изменяются(суммируются) после выполнений запросов. В зависимости чем выполнялась оплата cashPrice cardPrice

SalesTag=3-сумма товаров, когда оплата и cashPrice и cardPrice. пример : cashPrice": 20,"cardPrice": 20.0 SalesTag=2-сумма товаров, когда оплата была только cashPrice (как написано в примере выше) пример : cashPrice": 20,"cardPrice": 0 SalesTag=1-сумма товаров, когда оплата была только cardPrice. пример : cashPrice": 0,"cardPrice": 20 Для товаров SalesTag = 0.

Валидации: на ProductList @NotEmpty(message = "Should not be empty") И на ввод отрицательных cashPrice,cardPrice @Min(value = 0, message = "Should not be less than 0;")

4.4 Удаление по дате и времени(Post) – http://localhost:8080/api/deleteByData/{salestime} Получится проверить, если посмотреть в таблице sales поле salestime. Нужно добавить %20 после даты. Пример: В БД: 2024-03-09 15:40:02.273925 Запроc http://localhost:8080/api/deleteByData/2024-03-09%2015:40:02.273925 Обработано исключение, если такого sales нету в БД, то будет исключение "message": "not found"

Список использованных технологий: Spring Boot Maven Spring Security, JWT Hibernate, Spring Data Jpa PostgreSQL ModelMapper
