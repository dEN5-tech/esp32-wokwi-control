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
* 💡 Простой студенческий контроллер для управления Arduino Uno.
*
* ЗАДАНИЕ:
* 1. Сверстайте в Scene Builder форму с кнопками ON, OFF, STATUS.
* 2. Привяжите поля @FXML к элементам интерфейса.
* 3. Напишите код в методах-обработчиках, вызывая sendSerial().
*/

public class MainController {

    // Элементы интерфейса из Scene Builder
    @FXML private TextField ipField;
    @FXML private TextField portField;
    @FXML private Label statusLabel;
    @FXML private TextArea logArea;

    /**
     * Кнопка: "Включить LED"
     */
    @FXML
    void onTurnOn() {
        // TODO: Вызовите sendSerial() с командой "ON" и выведите ответ в logArea
        // String reply = sendSerial("ON");
        // if (reply != null) {
        //     statusLabel.setText("Светодиод: ВКЛ");
        //     logArea.appendText("Arduino: " + reply + "\n");
        // }
    }

    /**
     * Кнопка: "Выключить LED"
     */
    @FXML
    void onTurnOff() {
        // TODO: Вызовите sendSerial() с командой "OFF" и выведите ответ в logArea
        // String reply = sendSerial("OFF");
        // if (reply != null) {
        //     statusLabel.setText("Светодиод: ВЫКЛ");
        //     logArea.appendText("Arduino: " + reply + "\n");
        // }
    }

    /**
     * Кнопка: "Проверить статус"
     */
    @FXML
    void onCheckStatus() {
        // TODO: Вызовите sendSerial() с командой "STATUS" и обновите statusLabel
        // String reply = sendSerial("STATUS");
        // if (reply != null) {
        //     logArea.appendText("Статус: " + reply + "\n");
        // }
    }

    /**
     * Готовый метод отправки команды в Arduino через Socket.
     */
    public String sendSerial(String command) {
        String host = (ipField != null && !ipField.getText().isEmpty()) ? ipField.getText().trim() : "127.0.0.1";
        int port = (portField != null && !portField.getText().isEmpty()) ? Integer.parseInt(portField.getText().trim()) : 4001;

        try (Socket socket = new Socket(host, port)) {
            PrintWriter writer = new PrintWriter(socket.getOutputStream(), true);
            BufferedReader reader = new BufferedReader(new InputStreamReader(socket.getInputStream()));

            // Отправляем строку команды
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
