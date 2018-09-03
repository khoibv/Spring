$(document).ready(function () {
    $('button[name=addUser]').on('click', function () {
        window.location = app.home + app.user.urls.insert;
    });
    $('input[name=backToList]').on('click', function () {
        window.location = app.home + app.user.urls.index;
    });
});