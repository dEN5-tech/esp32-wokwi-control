# 💡 Arduino Uno Wokwi + JavaFX Serial Controller

Учебный воркспейс для лабораторной работы по дисциплине разработки клиент-серверных IoT приложений на связке **Arduino Uno (Serial UART / Wokwi)** и **JavaFX (Scene Builder)**.

---

## 📁 Структура проекта

```text
├── arduino-wokwi-control.code-workspace   # Файл воркспейса для VSCodium / VS Code
├── МЕТОДИЧКА.md                          # Пошаговая инструкция для студента
│
├── 1. arduino-uno-wokwi/                 # Папка микроконтроллера (Arduino Uno)
│   ├── build/
│   │   ├── firmware.hex                  # Скомпилированная HEX прошивка
│   │   └── firmware.elf
│   ├── diagram.json                      # Схема симуляции (Arduino Uno + LED Пин 13)
│   ├── wokwi.toml                        # Мост Serial-to-TCP (RFC2217 порт 4001)
│   └── src/main.cpp                      # Исходный код C++ (Serial)
│
└── 2. javafx-client/                     # Папка клиентского приложения
    ├── lib/javafx-sdk/                   # Полный локальный JavaFX SDK (JAR + DLL)
    ├── build.bat                         # Сборка проекта
    ├── run.bat                           # Запуск проекта
    └── src/com/example/
        ├── MainView.fxml                 # Макет интерфейса для Scene Builder
        ├── MainController.java           # Контроллер логики с методом sendSerial()
        ├── App.java                      # Загрузчик JavaFX
        └── Launcher.java                 # Точка входа
```

---

## 🚀 Быстрый старт

1. Откройте файл воркспейса `arduino-wokwi-control.code-workspace` в **VSCodium** (`File -> Open Workspace from File...`).
2. Запустите симулятор: откройте `diagram.json` и нажмите **Play ▶️**.
3. Запустите интерфейс: нажмите **F5** в редакторе (или запустите `javafx-client/run.bat`).
4. Следуйте инструкциям в **[`МЕТОДИЧКА.md`](МЕТОДИЧКА.md)** для выполнения заданий.
