function setUserName(name) {
    if (name !== null && name !== '') {
        document.cookie = 'username=' + name + ';';
    }
}

function getUserName() {
    var name = 'username';
    var matches = document.cookie.match(new RegExp(
            "(?:^|; )" + name.replace(/([\.$?*|{}\(\)\[\]\\\/\+^])/g, '\\$1') + "=([^;]*)"
            ));
    return matches ? decodeURIComponent(matches[1]) : undefined;
}

function getGreeting() {
    return 'Здравствуйте, ' + getUserName();
}