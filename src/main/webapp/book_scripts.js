//array for storing books displayed on page
var books = [];

//order by title if false
var order_by_author = true;

//descendin order if false
var ascending_order = true;

function getBookIndexByIsn(book) {
    var index = 0;
    while(book.isn !== books[index].isn && index < books.length) {
        ++index;
    }
    
    return index;
}

function getSelectRequestData() {
    var from_index = books.length;

    var data = 'from_index=' + from_index + '&order_by_author=' +
            order_by_author + '&ascending_order=' + ascending_order;

    return data;
}

function getBookRequestData(book) {
    return 'isn=' + book.isn + '&author=' + book.author + '&title=' +
            book.title + '&borrower_name=' + book.borrower_name;
}

function createButton(title, func) {
    var button = document.createElement("input");
    button.type = "button";
    button.value = title;
    button.onclick = func;
    return button;
}

function createIsnButton(book) {
    var button = document.createElement("input");
    button.type = "button";
    button.value = book.isn;
    button.onclick = function () {
        isnOnClick(book);
    };

    button.setAttribute('class', 'refbutton');

    return button;
}

function fillBookRow(book, row) {
    var cells = [];
    cells[0] = createIsnButton(book);

    cells[1] = document.createTextNode(book.author);
    cells[2] = document.createTextNode(book.title);

    if (book.borrower_name !== 'null') {
        if (book.borrower_name === getUserName()) {
            cells[3] = createButton("Вернуть", function () {
                returnBookRequest(book);
            });
        } else {
            cells[3] = document.createTextNode(book.borrower_name);
        }
    } else {
        cells[3] = createButton("Взять", function () {
            borrowBookRequest(book);
        });
    }

    cells[4] = createButton("Удалить", function () {
        deleteBookRequest(book);
    });

    for (var i = 0; i < 5; ++i) {
        // Insert a cell in the row at index i
        var newCell = row.insertCell(i);

        // Append element to the cell
        newCell.appendChild(cells[i]);
    }
}

function addBooksRowOnClient(table, book) {
    books.push(book);

    // Insert a row in the table at the last row
    var row = table.insertRow(table.rows.length);

    fillBookRow(book, row);
}

function updateBookOnClient(book) {
    var table = document.getElementById('booksTable');
    var book_index = getBookIndexByIsn(book);
    var row_index = book_index + 1;
    
    books[book_index] = book;
    
    table.deleteRow(row_index);
     
    var row = table.insertRow(row_index);
    
    fillBookRow(book, row);
}

function clearBooksOnClient() {
    var table = document.getElementById('booksTable');

    for (var i = 0; i < books.length; ++i) {
        table.deleteRow(1);
    }

    books = [];
}

function showModal(onSave) {
    document.getElementById('modal_isn').value = '';
    document.getElementById('modal_author').value = '';
    document.getElementById('modal_title').value = '';

    document.getElementById('modal_isn').disabled = false;

    document.getElementById('modal_save').onclick = function () {
        var book = {};
        book.isn = document.getElementById('modal_isn').value;
        book.author = document.getElementById('modal_author').value;
        book.title = document.getElementById('modal_title').value;
        book.borrower_name = 'null';

        onSave(book);

        closeModal();
    };

    document.getElementById('win').removeAttribute('style');
}

function closeModal() {
    document.getElementById('win').style.display = 'none';
}

function insertBookRequest(book) {
    $.ajax({
        dataType: 'json',
        url: 'ajax/books/insert',
        data: getBookRequestData(book),
        success: function (jsondata) {
            if (jsondata.success === false) {
                alert('Не удалось сохранить книгу');
            }
        },
        error: function (e, xhr) {
            alert('insertBook: ' + xhr);
        }
    });
}

function updateBookRequest(book) {
    $.ajax({
        dataType: 'json',
        url: 'ajax/books/update',
        data: getBookRequestData(book),
        success: function (jsondata) {
            if (jsondata.success === false) {
                alert('Не удалось сохранить изменения');
            } else {
                updateBookOnClient(book);
            }
        },
        error: function (e, xhr) {
            alert('updateBook: ' + xhr);
        }
    });
}

function deleteBookRequest(book) {

    if (confirm('Удалить книгу?')) {

        //gettin book index to delete it from array and from table
        var book_index = getBookIndexByIsn(book);

        $.ajax({
            url: 'ajax/books/delete',
            data: getBookRequestData(books[book_index]),
            success: function () {
                books.splice(book_index, 1);

                //deleting row with index +1 because first row is table header
                document.getElementById('booksTable').deleteRow(book_index + 1);
            },
            error: function (e, xhr) {
                alert('deleteBook: ' + xhr);
            }
        });
    }
}

function borrowBookRequest(book) {
    book.borrower_name = getUserName();
    updateBookRequest(book);
}

function returnBookRequest(book) {
    book.borrower_name = 'null';
    updateBookRequest(book);
}

function isnOnClick(book) {
    showModal(updateBookRequest);
    
    var isn_field = document.getElementById('modal_isn');
    isn_field.value = book.isn;
    isn_field.disabled = true;
    
    document.getElementById('modal_author').value = book.author;
    document.getElementById('modal_title').value = book.title;
}

function showMoreBooksOnClick() {
    $.ajax({
        dataType: 'json',
        url: 'ajax/books/select',
        data: getSelectRequestData(),
        success: function (jsondata) {

            var table = document.getElementById('booksTable');

            for (var i = 0; i < jsondata.length; ++i) {
                addBooksRowOnClient(table, jsondata[i]);
            }

        },
        error: function (e, xhr) {
            alert('showMoreBooks: ' + xhr);
        }
    });
}

function sortByAuthorOnClick() {
    if (order_by_author) {
        ascending_order = !ascending_order;
    } else {
        order_by_author = true;
        ascending_order = true;
        document.getElementById('table_author').setAttribute('class', 'order');
        document.getElementById('table_title').setAttribute('class', '');
    }

    clearBooksOnClient();
    showMoreBooksOnClick();
}

function sortByTitleOnClick()  {
    if (!order_by_author) {
        ascending_order = !ascending_order;
    } else {
        order_by_author = false;
        ascending_order = true;
        document.getElementById('table_author').setAttribute('class', '');
        document.getElementById('table_title').setAttribute('class', 'order');
    }

    clearBooksOnClient();
    showMoreBooksOnClick()();
}