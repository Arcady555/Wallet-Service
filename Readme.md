Cервис, который управляет кредитными/дебетовыми транзакциями от имени игроков.

Описание

Денежный счет содержит текущий баланс игрока. Баланс можно изменить, зарегистрировав транзакции на счете, либо дебетовые транзакции (удаление средств), либо кредитные транзакции (добавление средств).

Данные хранятся в памяти приложения. Приложение консольное
Есть регистрация игрока, авторизация игрока, текущий баланс игрока
При дебете или кредите вызывающая должна сторона предоставить идентификатор транзакции, который должен быть уникальным для всех транзакций. Если идентификатор транзакции не уникален, операция завершится ошибкой.
Есть просмотр истории пополнения/снятия средств игроком