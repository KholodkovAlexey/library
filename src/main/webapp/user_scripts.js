var users = [];

function sortUsers() {
    users.sort(function (a, b) {
        if (a.name > b.name) {
            return 1;
        }
        if (a.name < b.name) {
            return -1;
        }
        return 0;
    });
}

function getUserRequestData(user) {
    return 'name=' + user.name + '&password=' + user.password;
}

function createDeleteButton(user) {
    var button = document.createElement("input");
    button.type = "button";
    button.value = "Удалить";
    button.onclick = function () {
        if (confirm('Удалить пользователя?')) {
            deleteUserRequest(user);
        }
    };
    return button;
}

function createNameButton(user) {
    var button = document.createElement("input");
    button.type = "button";
    button.value = user.name;
    button.onclick = function () {
        showModal(function(new_user) {
            updateUserRequest(new_user, user); 
        });
        document.getElementById('modal_name').value = user.name;
        document.getElementById('modal_password').value = '';
    };

    button.setAttribute('class', 'refbutton');

    return button;
}

function fillUserRow(user, row) {
    var cells = [];
    cells[0] = createNameButton(user);
    cells[1] = createDeleteButton(user);

    for (var i = 0; i < 2; ++i) {
        // Insert a cell in the row at index i
        var newCell = row.insertCell(i);

        // Append element to the cell
        newCell.appendChild(cells[i]);
    }
}

function clearUsersTable() {
    var table = document.getElementById('usersTable');

    while (table.rows.length > 1) {
        table.deleteRow(1);
    }
}

function fillUsersTable() {
    var table = document.getElementById('usersTable');

    for (var i = 0; i < users.length; ++i) {
        var row = table.insertRow(table.rows.length);
        fillUserRow(users[i], row);
    }
}

function updateUsersTable() {
    clearUsersTable();
    fillUsersTable();
}

function selectUsersRequest() {
    $.ajax({
        dataType: 'json',
        url: 'ajax/users/select',
        success: function (jsondata) {
            users = jsondata;
            fillUsersTable();
        },
        error: function (e, xhr) {
            alert('selectUsersRequest: ' + xhr);
        }
    });
}

function showModal(onSave) {
    document.getElementById('modal_name').value = '';
    document.getElementById('modal_password').value = '';

    document.getElementById('modal_save').onclick = function () {
        var user = {};
        user.name = document.getElementById('modal_name').value;
        user.password = document.getElementById('modal_password').value;

        onSave(user);

        closeModal();
    };

    document.getElementById('win').removeAttribute('style');
}

function closeModal() {
    document.getElementById('win').style.display = 'none';
}

function insertUserRequest(user) {
    $.ajax({
        dataType: 'json',
        url: 'ajax/users/insert',
        data: getUserRequestData(user),
        success: function (jsondata) {
            if (jsondata.success) {
                users.push(user);
                sortUsers();
                updateUsersTable();
            } else {
                alert('Не удалось сохранить изменения.');
            }
        },
        error: function (e, xhr) {
            alert('insertUserRequest: ' + xhr);
        }
    });
}

function updateUserRequest(new_user, old_user) {
    $.ajax({
        dataType: 'json',
        url: 'ajax/users/update',
        data: getUserRequestData(new_user) + '&oldname=' + old_user.name,
        success: function (jsondata) {
            if (jsondata.success) {
                var update_index = users.indexOf(old_user);
                users[update_index] = new_user;
                sortUsers();
                updateUsersTable();
            } else {
                alert('Не удалось сохранить изменения.');
            }
        },
        error: function (e, xhr) {
            alert('updateUserRequest: ' + xhr);
        }
    });
}

function deleteUserRequest(user) {
    $.ajax({
        dataType: 'json',
        url: 'ajax/users/delete',
        data: getUserRequestData(user),
        success: function (jsondata) {
            if (jsondata.success) {
                users.splice(users.indexOf(user), 1);
                updateUsersTable();
            } else {
                alert('Не удалось удалить пользователя.');
            }
        },
        error: function (e, xhr) {
            alert('deleteUserRequest: ' + xhr);
        }
    });
}