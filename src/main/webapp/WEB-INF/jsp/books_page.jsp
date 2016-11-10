<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Книги</title>
        <link rel="stylesheet" href="styles.css">
    </head>
    <body>
        <script src="jquery-3.1.1.min.js"></script>
        <script src="user_name.js"></script>
        <script src="book_scripts.js"></script>

        <p>
            <a href="books">Книги</a>
            <a href="users">Пользователи</a>
        </p>

        <p>
            <div id="user_div"></div>
            <script>document.getElementById("user_div").innerHTML = getGreeting();</script>
        </p>

    <button onclick="showModal(insertBookRequest)">Добавить книгу</button> 

    <div id="win" style="display:none;">
        <div class="overlay"></div>
        <div class="visible">
            <table>
                <tr>
                    <th>ISN</th><th><input type="text" id="modal_isn" size="20" value=""></th>
                </tr>
                <tr>
                    <th>Author</th><th><input type="text" id="modal_author" size="20" value=""></th>
                </tr>
                <tr>
                    <th>Title</th><th><input type="text" id="modal_title" size="20" value=""></th>
                </tr>
            </table>
            <button id="modal_save">Сохранить</button>
            <button onClick="closeModal()">Отмена</button>
        </div>
    </div>

    <table border="1" id="booksTable">
        <tr>
            <th>ISN</th>
            <th onclick="sortByAuthorOnClick()" id="table_author" class="order">Автор</th>
            <th onclick="sortByTitleOnClick()" id="table_title">Название</th>
            <th>Кем взята</th>
            <th>Удаление</th>
        </tr>
    </table>

    <button onclick="showMoreBooksOnClick()">Показать еще</button> 
</body>
</html>
