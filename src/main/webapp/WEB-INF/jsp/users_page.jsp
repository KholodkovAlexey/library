<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Пользователи</title>
        <link rel="stylesheet" href="styles.css">
    </head>
    <body>
        <script src="jquery-3.1.1.min.js"></script>
        <script src="user_name.js"></script>
        <script src="user_scripts.js"></script>

        <p>
            <a href="books">Книги</a>
            <a href="users">Пользователи</a>
        </p>

        <p>
        <div id="user_div"></div>
        <script>document.getElementById("user_div").innerHTML = getGreeting();</script>
    </p>

    <button onclick="showModal(insertUserRequest)">Добавить пользователя</button> 

    <div id="win" style="display:none;">
        <div class="overlay"></div>
        <div class="visible">
            <table>
                <tr>
                    <th>Имя</th><th><input type="text" id="modal_name" size="20" value=""></th>
                </tr>
                <tr>
                    <th>Пароль</th><th><input type="text" id="modal_password" size="20" value=""></th>
                </tr>
            </table>
            <button id="modal_save">Сохранить</button>
            <button onClick="closeModal()">Отмена</button>
        </div>
    </div>

    <table border="1" id="usersTable">
        <tr>
            <th>Имя</th>
            <th>Удаление</th>
        </tr>
    </table>

    <script>selectUsersRequest();</script>

</body>
</html>
