package com.example;

import javafx.application.Application;

/**
 * Точка входа для запуска JavaFX.
 * Позволяет запускать приложение напрямую кнопкой Run / F5 без конфликтов модулей.
 */
public class Launcher {
    public static void main(String[] args) {
        Application.launch(App.class, args);
    }
}
