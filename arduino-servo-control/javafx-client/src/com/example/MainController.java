package com.example;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.Slider;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

/**
 * 💡 Практическая работа: Управление сервоприводом Arduino Uno через Serial.
 * 
 * ЗАДАНИЕ:
 * 1. Сверстайте интерфейс в Scene Builder (MainView.fxml).
 * 2. Привяжите @FXML переменные.
 * 3. Реализуйте методы-обработчики (onApplyAngle, onSet0, onSet90, onSet180, onGetStatus) через sendSerial().
 */
public class MainController {

    // Элементы интерфейса
    @FXML private TextField ipField;
    @FXML private TextField portField;
    @FXML private Slider angleSlider;
    @FXML private Label angleValueLabel;
    @FXML private Label statusLabel;
    @FXML private TextArea logArea;

    @FXML
    public void initialize() {
        // Обновление текстового значения при движении ползунка
        if (angleSlider != null && angleValueLabel != null) {
            angleSlider.valueProperty().addListener((obs, oldVal, newVal) -> {
                int angle = newVal.intValue();
                angleValueLabel.setText(angle + "°");
            });
        }
    }

    /**
     * Кнопка: "Применить" (отправляет текущее значение со слайдера)
     */
    @FXML
    void onApplyAngle() {
        // TODO: Получите угол из angleSlider и вызовите sendSerial("ANGLE " + angle)
        // int angle = (int) angleSlider.getValue();
        // String reply = sendSerial("ANGLE " + angle);
        // if (reply != null) {
        //     statusLabel.setText("Текущий угол: " + angle + "°");
        //     logArea.appendText("Arduino: " + reply + "\n");
        // }
    }

    /**
     * Пресет: 0 градусов
     */
    @FXML
    void onSet0() {
        // TODO: Установите angleSlider в 0 и вызовите onApplyAngle()
    }

    /**
     * Пресет: 90 градусов
     */
    @FXML
    void onSet90() {
        // TODO: Установите angleSlider в 90 и вызовите onApplyAngle()
    }

    /**
     * Пресет: 180 градусов
     */
    @FXML
    void onSet180() {
        // TODO: Установите angleSlider в 180 и вызовите onApplyAngle()
    }

    /**
     * Кнопка: "Статус"
     */
    @FXML
    void onGetStatus() {
        // TODO: Вызовите sendSerial("STATUS") и обновите UI
    }

    /**
     * Готовый метод отправки команды в Serial Arduino.
     */
    public String sendSerial(String command) {
        String host = (ipField != null && !ipField.getText().isEmpty()) ? ipField.getText().trim() : "127.0.0.1";
        int port = (portField != null && !portField.getText().isEmpty()) ? Integer.parseInt(portField.getText().trim()) : 4002;

        try (Socket socket = new Socket(host, port)) {
            PrintWriter writer = new PrintWriter(socket.getOutputStream(), true);
            BufferedReader reader = new BufferedReader(new InputStreamReader(socket.getInputStream()));

            // Отправляем строку команды (например, "ANGLE 90" или "STATUS")
            writer.println(command);

            // Читаем ответ
            return reader.readLine();
        } catch (Exception e) {
            if (logArea != null) {
                logArea.appendText("[Ошибка сети] " + e.getMessage() + "\n");
            }
            return null;
        }
    }
}
