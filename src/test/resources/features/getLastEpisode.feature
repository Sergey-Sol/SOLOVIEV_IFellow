#language: ru
Функция: Получить последний эпизод, в котором появлялся Морти
  Структура сценария: Получение информации по последнему эпизоду, в котором появлялся Морти
    Дано Ссылка на последний эпизод, в котором появлялся Морти, статус-код <statusCode>
    Когда Отправляем GET-запрос на последний эпизод
    Тогда Статус код-ответа для страницы последнего эпизода должен быть <statusCode>
    И Название эпизода должно быть <name>

    Примеры:
      | statusCode | name           |
      | 200        | Rickmurai Jack |