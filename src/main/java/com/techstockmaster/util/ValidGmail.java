package com.techstockmaster.util;

public class ValidGmail {
    /***
     * Verifica se o e-mail está no formato correto.

     * A expressão regular utilizada aqui valida o seguinte:
     * 1. O nome do usuário pode conter letras, números, pontos, sublinhados, e sinais de mais.
     * 2. O domínio principal deve conter pelo menos um ponto.
     * 3. O TLD (Top Level Domain) deve ter pelo menos duas letras.
     * 4. Não pode haver dois pontos consecutivos no domínio.
     */
    public static boolean isValidEmail(String email) {
        String regex = "^[A-Za-z0-9+_.-]+@([A-Za-z0-9-]+\\.)+[A-Za-z]{2,}$";
        return email.matches(regex);
    }
}