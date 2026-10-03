# 💡 Arduino Uno Wokwi + JavaFX Servo Controller

Учебный воркспейс для лабораторной работы по управлению сервоприводом на **Arduino Uno (Wokwi)** через интерфейс **JavaFX (Scene Builder)**.

---

## 📁 Структура проекта

```text
├── arduino-servo-control.code-workspace   # Файл воркспейса для VSCodium / VS Code
├── МЕТОДИЧКА.md                          # Пошаговая инструкция для студента
│
├── 1. arduino-uno-wokwi/                 # Папка микроконтроллера (Arduino Uno)
│   ├── build/
│   │   ├── firmware.hex                  # Скомпилированная HEX прошивка
│   │   └── firmware.elf
│   ├── diagram.json                      # Схема Wokwi (Arduino Uno + Servo Пин 9)
│   ├── wokwi.toml                        # Мост Serial-to-TCP (порт 4002)
│   └── src/main.cpp                      # Исходный код C++ (Servo.h)
│
└── 2. javafx-client/                     # Папка JavaFX клиента (Scene Builder First)
    ├── lib/javafx-sdk/                   # Полный JavaFX SDK (JAR + DLL)
    ├── build.bat                         # Сборка проекта
    ├── run.bat                           # Запуск проекта
    └── src/com/example/
        ├── MainView.fxml                 # Макет интерфейса (Scene Builder)
        ├── MainController.java           # Контроллер с методом sendSerial()
        ├── App.java                      # Загрузчик JavaFX
        └── Launcher.java                 # Точка входа
```
