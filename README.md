# 💡 ESP32 Wokwi + JavaFX TCP Controller

Учебный воркспейс для лабораторной работы по дисциплине разработки клиент-серверных IoT приложений на связке **ESP32 (Wokwi)** и **JavaFX (Scene Builder)**.

---

## 📁 Структура проекта

```text
├── esp32-wokwi-control.code-workspace   # Файл воркспейса для VSCodium / VS Code
├── МЕТОДИЧКА.md                        # Пошаговая инструкция для студента
│
├── 1. esp32-tcp-wokwi/                 # Папка микроконтроллера (ESP32)
│   ├── build/
│   │   ├── firmware.bin                # Скомпилированная прошивка
│   │   └── firmware.elf
│   ├── diagram.json                    # Схема симуляции (ESP32 + LED GPIO 23)
│   ├── wokwi.toml                      # Конфиг проброса портов (8080)
│   └── src/main.cpp                    # Исходный код C++
│
└── 2. javafx-client/                   # Папка клиентского приложения
    ├── lib/javafx-sdk/                 # Полный локальный JavaFX SDK (JAR + DLL)
    ├── build.bat                       # Сборка проекта
    ├── run.bat                         # Запуск проекта
    └── src/com/example/
        ├── MainView.fxml               # Макет интерфейса для Scene Builder
        ├── MainController.java         # Контроллер логики с методом sendTcp()
        ├── App.java                    # Загрузчик JavaFX
        └── Launcher.java               # Точка входа
```

---

## 🚀 Быстрый старт

1. Откройте файл воркспейса `esp32-wokwi-control.code-workspace` в **VSCodium** (`File -> Open Workspace from File...`).
2. Запустите симулятор: откройте `diagram.json` и нажмите **Play ▶️**.
3. Запустите интерфейс: нажмите **F5** в редакторе (или запустите `javafx-client/run.bat`).
4. Следуйте инструкциям в **[`МЕТОДИЧКА.md`](МЕТОДИЧКА.md)** для выполнения заданий.
