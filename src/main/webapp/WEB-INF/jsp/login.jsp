<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <title>Авторизация</title>
    </head>
    <body onload='document.f.username.focus();'>

        <script src="user_name.js"></script>

        <h3>Авторизация</h3>

        <% if (request.getParameter("error") != null) {%>
        <div>Неправильные имя пользователя или пароль.</div>
        <%}%>

        <form class="login-form" action="/j_spring_security_check" method="post" onsubmit="setUserName(document.getElementById('j_username').value);return true">
            <table>
                <tr>
                    <th><label for="j_username">Имя: </label></th>
                    <th><input id="j_username" name="j_username" type="text" /></th>
                </tr>

                <tr>
                    <th><label for="j_password">Пароль: </label></th>
                    <th><input id="j_password" name="j_password" type="password" /></th>
                </tr>

                <tr>
                    <th><input type="submit" value="Войти" /></th>
                </tr>
            </table>

            <input type="hidden" name="${_csrf.parameterName}"   value="${_csrf.token}" />

        </form>
</html>
