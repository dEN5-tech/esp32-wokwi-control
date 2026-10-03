#include <Arduino.h>
#include <Servo.h>

Servo myServo;
int currentAngle = 90;

void setup() {
  Serial.begin(9600);
  myServo.attach(9);
  myServo.write(currentAngle);
}

void loop() {
  if (Serial.available()) {
    String cmd = Serial.readStringUntil('\n');
    cmd.trim();

    if (cmd.startsWith("ANGLE ")) {
      int angle = cmd.substring(6).toInt();
      if (angle >= 0 && angle <= 180) {
        currentAngle = angle;
        myServo.write(currentAngle);
        Serial.print("OK: ");
        Serial.println(currentAngle);
      } else {
        Serial.println("ERROR: Angle 0-180");
      }
    } 
    else if (cmd == "STATUS") {
      Serial.print("ANGLE: ");
      Serial.println(currentAngle);
    }
  }
}
