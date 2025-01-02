function togglePasswordVisibility() {
    var passwordInput = document.querySelector('input[name="password"]');
    var showPasswordCheckbox = document.getElementById('show-password');

    if (showPasswordCheckbox.checked) {
        passwordInput.type = 'text';
    } else {
        passwordInput.type = 'password';
    }
}