#Java
Проект представляет из себя портал с тестами. Есть панель администратора и панель пользователя. Администратор может добавлять категории тестов, добавлять сами тесты и вопросы к ним. Может ограничивать доступ к тестам, может редактировать все, начиная от своего профиля, заканчивая вопросами к тестам. Может удалять вопросы, категории, сами тесты, результаты тестов пользователей. Пользователь может просмотреть список доступных тестов, зайти и решить какой-то из них, результат будет доступен сразу после отправки теста на проверку. Список всех результатов он может посмотреть на отдельной странице. Он может удалить свой профиль, отредактировать информацию в нем. На портале есть регистрация и авторизация, реализованная с помощью Jwt токена. Фронт на Angular(Репозитория - ExamFront). Запросы на Back могут приходить только с указанного в CrossOrigin адреса. Также реализована защита на стороне фронта с помощью guards. Но, даже, если каким-то образом будет получен доступ к страницам администратора, на стороне BackEnd реализована защита через Permission, которая не даст обычному пользователю ничего сделать, то есть, даже, при переводе CrossOrigin к "*" ничего не изменится, так как прямые Http запросы не смогут ничего разрушить. Проект написан в учебных целях и соответственно, в каких-то местах могут встретиться недоработки, так как опыта у меня особого нет, но я старался) Можно безособых проблем расширить какие-то возможности, но, я решил, что этого результата достаточно, чтобы показать, что я что-то умею.

![alt text](screenshots/Пользователь/Тест.png "Список всех заявок")

