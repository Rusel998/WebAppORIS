function togglePasswordVisibility() {
    // Находим поле для ввода пароля
    var passwordInput = document.querySelector('input[name="password"]');
    // Находим чекбокс
    var showPasswordCheckbox = document.getElementById('show-password');

    // Проверяем состояние чекбокса
    if (showPasswordCheckbox.checked) {
        passwordInput.type = 'text'; // Показываем пароль
    } else {
        passwordInput.type = 'password'; // Скрываем пароль
    }
}