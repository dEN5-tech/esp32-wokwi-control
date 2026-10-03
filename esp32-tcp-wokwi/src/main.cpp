#include <Arduino.h>
#include <WiFi.h>

// Порт для TCP сервера управления
const int SERVER_PORT = 8080;
WiFiServer server(SERVER_PORT);

// Пин светодиода (GPIO 23 находится рядом со светодиодом на схеме)
const int LED_PIN = 23;

// Настройки WiFi для симулятора Wokwi
const char* WIFI_SSID = "Wokwi-GUEST";
const char* WIFI_PASS = "";

void setup() {
  Serial.begin(115200);
  pinMode(LED_PIN, OUTPUT);
  digitalWrite(LED_PIN, LOW);

  // 1. Подключение к виртуальному WiFi Wokwi
  Serial.println("\n--- ESP32 TCP Control Server Starting ---");
  Serial.print("Connecting to WiFi: ");
  Serial.println(WIFI_SSID);
  WiFi.begin(WIFI_SSID, WIFI_PASS);

  while (WiFi.status() != WL_CONNECTED) {
    delay(400);
    Serial.print(".");
  }

  Serial.println("\nWiFi connected successfully!");
  Serial.print("ESP32 IP: ");
  Serial.println(WiFi.localIP());

  // 2. Запуск TCP сервера
  server.begin();
  Serial.print("TCP Server listening on port: ");
  Serial.println(SERVER_PORT);
  Serial.println("Ready for commands: ON, OFF, STATUS");
}

void loop() {
  WiFiClient client = server.available();

  if (client) {
    Serial.println("\n[TCP] Client connected!");

    while (client.connected()) {
      if (client.available()) {
        String command = client.readStringUntil('\n');
        command.trim();

        Serial.print("[TCP] Received: '");
        Serial.print(command);
        Serial.println("'");

        if (command == "ON") {
          digitalWrite(LED_PIN, HIGH);
          client.println("OK: LED turned ON");
          Serial.println("[LED] State -> HIGH (ON)");
        } 
        else if (command == "OFF") {
          digitalWrite(LED_PIN, LOW);
          client.println("OK: LED turned OFF");
          Serial.println("[LED] State -> LOW (OFF)");
        } 
        else if (command == "STATUS") {
          int state = digitalRead(LED_PIN);
          if (state == HIGH) {
            client.println("STATUS: ON");
          } else {
            client.println("STATUS: OFF");
          }
        } 
        else if (command.length() > 0) {
          client.println("ERROR: Unknown command. Supported: ON, OFF, STATUS");
        }
      }
    }

    client.stop();
    Serial.println("[TCP] Client disconnected.");
  }
}
