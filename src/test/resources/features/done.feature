#language: ru
Функция: Перевод задачи в статус 'ГОТОВО'
  Структура сценария: Авторизация пользователя, переход на новую задачу, выполнить
    Дано данные для авторизации, стат задачи: <username>, <password>
    Когда проходим авторизацию, переходим на страницу задачи и меняем статус на 'Готово'
    Тогда статус задачи должен быть 'Готово' <taskstatus>

    Примеры:
      | username | password  | taskstatus |
      | AT9      | Qwerty123 | ГОТОВО     |