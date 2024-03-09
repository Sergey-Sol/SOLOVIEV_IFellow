#language: ru
Функция: Переход на задачу 'TestSelenium'
  Структура сценария: Авторизация пользователя, переход на задачу 'TestSelenium'
    Дано логин, пароль, наименование задачи: <username>, <password>, <taskname>
    Когда Проходим авторизацию и переходим на страницу задачи
    Тогда заголовок страницы должен быть <taskname>

    Примеры:
      | username | password  | taskname     |
      | AT9      | Qwerty123 | TestSelenium |