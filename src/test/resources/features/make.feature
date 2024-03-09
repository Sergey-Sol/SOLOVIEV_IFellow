#language: ru
Функция: Проверка статуса 'СДЕЛАТЬ' в задаче 'TestSelenium'
  Структура сценария: Авторизация пользователя, переход на задачу 'TestSelenium', проверка статуса задачи
    Дано логин, пароль, наименование задачи, статус задачи: <username>, <password>, <taskname>, <taskstatus>
    Когда Проходим авторизацию, переходим на страницу задачи и смотрим статус
    Тогда Статус задачи должен быть <taskstatus>

    Примеры:
      | username | password  | taskname     | taskstatus |
      | AT9      | Qwerty123 | TestSelenium | СДЕЛАТЬ    |