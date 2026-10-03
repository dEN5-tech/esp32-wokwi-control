package com.example;

import javafx.fxml.FXML;
import javafx.event.ActionEvent;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

/**
* 💡 Полностью реализованный контроллер по МЕТОДИЧКЕ.
*/

public class MainController {

    // Элементы интерфейса из Scene Builder
    @FXML private TextField ipField;
    @FXML private TextField portField;
    @FXML private Label statusLabel;
    @FXML private TextArea logArea;

    @FXML
    public void initialize() {
        if (logArea != null) {
            logArea.appendText("Приложение готово к работе.\nНажмите 'Статус' или 'ВКЛ (ON)' для проверки связи.\n");
        }
    }

    /**
     * Кнопка: "Включить LED"
     */
    @FXML
    void onTurnOn() {
        String reply = sendSerial("ON");
        if (reply != null) {
            statusLabel.setText("Состояние: ВКЛ (ON)");
            statusLabel.setStyle("-fx-text-fill: #2e7d32; -fx-font-weight: bold;");
            logArea.appendText("Arduino: " + reply + "\n");
        }
    }

    /**
     * Кнопка: "Выключить LED"
     */
    @FXML
    void onTurnOff() {
        String reply = sendSerial("OFF");
        if (reply != null) {
            statusLabel.setText("Состояние: ВЫКЛ (OFF)");
            statusLabel.setStyle("-fx-text-fill: #c62828; -fx-font-weight: bold;");
            logArea.appendText("Arduino: " + reply + "\n");
        }
    }

    /**
     * Кнопка: "Проверить статус"
     */
    @FXML
    void onCheckStatus() {
        String reply = sendSerial("STATUS");
        if (reply != null) {
            if (reply.contains("ON")) {
                statusLabel.setText("Состояние: ВКЛ (ON)");
                statusLabel.setStyle("-fx-text-fill: #2e7d32; -fx-font-weight: bold;");
            } else {
                statusLabel.setText("Состояние: ВЫКЛ (OFF)");
                statusLabel.setStyle("-fx-text-fill: #c62828; -fx-font-weight: bold;");
            }
            logArea.appendText("Текущий статус: " + reply + "\n");
        }
    }

    /**
     * Прямая отправка строковой команды в Serial UART Arduino через Wokwi RFC2217 мост.
     */
    public String sendSerial(String command) {
        String host = (ipField != null && !ipField.getText().trim().isEmpty()) ? ipField.getText().trim() : "127.0.0.1";
        int port = (portField != null && !portField.getText().trim().isEmpty()) ? Integer.parseInt(portField.getText().trim()) : 4001;

        try (Socket socket = new Socket(host, port)) {
            // Устанавливаем потоки записи и чтения
            PrintWriter writer = new PrintWriter(socket.getOutputStream(), true);
            BufferedReader reader = new BufferedReader(new InputStreamReader(socket.getInputStream()));

            // Отправляем команду в UART платы
            writer.println(command);

            // Читаем ответ из Serial.println()
            return reader.readLine();
        } catch (Exception e) {
            if (logArea != null) {
                logArea.appendText("[Ошибка сети] " + e.getMessage() + "\n");
            }
            return null;
        }
    }
}
