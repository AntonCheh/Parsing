FilesAndNetwork/DataCollector

Описание проекта

Данный проект представляет собой программу, которая собирает данные из различных источников и записывает их в два JSON-файла. Программа выполняет парсинг данных из веб-страницы и файлов разных форматов (JSON и CSV), которые находятся в дереве папок. Каждый тип данных обрабатывается в отдельных классах.

Структура проекта

Проект включает следующие классы:

1. WebParsingHtmlLines - Класс для парсинга веб-страницы.
2. FindFolder - Класс для поиска файлов в папках.
3. JsonParsing - Класс для парсинга файлов формата JSON.
4. CsvParsing - Класс для парсинга файлов формата CSV.
5. JsonWriter - Класс для объединения данных и создания JSON-файлов.

Функциональные возможности

PACKAGE - firstJsonFile
WebParsingHtmlLines
Получение HTML-кода страницы.
Парсинг HTML-кода для получения данных о линиях метро (имя и номер линии).
Парсинг HTML-кода для получения данных о станциях метро (имя станции и номер линии).

FindFolder
Обход всех вложенных папок и поиск файлов форматов JSON и CSV.

JsonParsing
Парсинг данных из JSON-файла и возврат списка соответствующих объектов.

CsvParsing
Парсинг данных из CSV-файла и возврат списка соответствующих объектов.

JsonWriter
Добавление данных, полученных из других классов.
Создание и запись JSON-файла:
[merged_info.json]

PACKAGE - SecondJsonFile
WebParsingHtmlStations
Получение HTML-кода страницы непосредственно с сайта.
Парсинг HTML-кода для получения данных.

Остальные классы работают по примеру классов из PACKAGE - firstJsonFile
с задачей парсинга информации и записи её в дальнейшем в JSON файл

TryJsonWrite
Добавление данных, полученных из других классов.
Создание и запись JSON-файла:
[stations.json]

Установка:

Требования
Java 8+
Maven
Jsoup

В результате работы программа
получит HTML-код страницы и распарсит данные о линиях и станциях метро.
Обойдет указанные папки и найдет все JSON и CSV файлы.
Распарсит найденные файлы и извлечет из них данные.
Объединит все данные и создаст два JSON-файла.

Примеры
Пример структуры JSON-файлов 

{
"stations": [
{
"name": "Название станции",
"line": "Название линии",
"date": "Дата открытия в формате 19.01.2005",
"depth": "Глубина станции в виде числа",
"hasConnection": "Есть ли на станции переход"
},
…
]
}


{
"stations": [
{
"name": "Ленинский проспект",
"line": "Калужско-Рижская",
"date": "13.10.1962",
"depth": -16,
"hasConnection": true
},
…
]
}



Содействие
Создайте issue для предложений или багов.
Отправляйте pull requests для улучшений.



