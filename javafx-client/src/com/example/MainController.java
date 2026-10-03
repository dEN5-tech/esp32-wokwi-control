package com.example;

import javafx.fxml.FXML;
import javafx.scene.control.*;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

/**
 * 💡 Практическая работа: Управление ESP32 по протоколу TCP.
 * 
 * ЗАДАНИЕ ДЛЯ СТУДЕНТА:
 * 1. Сверстайте интерфейс в Scene Builder (MainView.fxml).
 * 2. Объявите ниже поля с аннотацией @FXML, соответствующие fx:id из Scene Builder.
 * 3. Напишите методы-обработчики кнопок (@FXML void handle...()), используя готовый метод sendTcp().
 * 
 * Подробная инструкция приведена в файле МЕТОДИЧКА.md.
 */
public class MainController {

    // =========================================================================
    // 1. ПОЛЯ ИНТЕРФЕЙСА (@FXML)
    // Объявите переменные для элементов UI (см. Раздел 4 в МЕТОДИЧКА.md)
    // =========================================================================
    
    // TODO: Объявите TextField для ввода IP-адреса и порта
    // @FXML private TextField hostField;
    // @FXML private TextField portField;

    // TODO: Объявите метки для статуса и логов
    // @FXML private Label statusLabel;
    // @FXML private Label ledStatusLabel;
    // @FXML private TextArea logArea;



    // =========================================================================
    // 2. МЕТОДЫ-ОБРАБОТЧИКИ НАЖАТИЙ КНОПОК (@FXML)
    // Напишите логику для кнопок из Scene Builder (см. Раздел 5 в МЕТОДИЧКА.md)
    // =========================================================================

    // TODO: Напишите метод для проверки связи (команда "STATUS")
    // @FXML
    // void handleConnect() {
    //     // 1. Получите IP и порт из текстовых полей
    //     // 2. Вызовите sendTcp(ip, port, "STATUS")
    //     // 3. Обновите текст на экране в зависимости от ответа
    // }

    // TODO: Напишите метод для включения светодиода (команда "ON")
    // @FXML
    // void handleTurnOn() {
    //     ...
    // }

    // TODO: Напишите метод для выключения светодиода (команда "OFF")
    // @FXML
    // void handleTurnOff() {
    //     ...
    // }



    // =========================================================================
    // 3. ГОТОВЫЙ МЕТОД ОТПРАВКИ КОМАНД ПО TCP
    // (Этот метод готов к использованию, изменять его не требуется)
    // =========================================================================
    
    /**
     * Отправляет строковую команду на ESP32 и возвращает ответ сервера.
     * 
     * @param ip      IP-адрес сервера (например, "127.0.0.1")
     * @param port    Порт сервера (например, 8080)
     * @param command Команда для отправки ("ON", "OFF", "STATUS")
     * @return Текст ответа от ESP32 или null в случае ошибки сети
     */
    public String sendTcp(String ip, int port, String command) {
        try {
            // 1. Создаем TCP-соединение с микроконтроллером
            Socket socket = new Socket(ip, port);

            // 2. Настраиваем потоки для отправки и приёма строк
            PrintWriter writer = new PrintWriter(socket.getOutputStream(), true);
            BufferedReader reader = new BufferedReader(new InputStreamReader(socket.getInputStream()));

            // 3. Отправляем команду
            writer.println(command);

            // 4. Считываем строку ответа от ESP32
            String response = reader.readLine();

            // 5. Закрываем соединение
            socket.close();

            return response;
        } catch (Exception e) {
            System.err.println("[TCP ОШИБКА] " + e.getMessage());
            return null;
        }
    }
}
