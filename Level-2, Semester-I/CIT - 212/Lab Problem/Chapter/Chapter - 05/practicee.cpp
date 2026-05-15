/*
 * ============================================
 *   TASNIM DEFENSE SYSTEM — Arduino Uno Code
 *   v3.1 — Servo Direction Fix
 * ============================================
 * Connections:
 *   Pin 2  (RX) ← ESP8266 D6 (TX)
 *   Pin 3  (TX) → ESP8266 D5 (RX)
 *   Pin 9  → Ultrasonic TRIG
 *   Pin 10 ← Ultrasonic ECHO
 *   Pin 11 → Radar Servo  (Signal)
 *   Pin 12 → Aim Servo    (Signal)
 *   Pin 13 → Fire Servo   (Signal)
 *   GND ←→ ESP8266 GND
 * ============================================
 */

#include <Servo.h>
#include <SoftwareSerial.h>

// ── SoftwareSerial ────────────────────────────
SoftwareSerial espSerial(2, 3); // RX, TX

// ── Servos ────────────────────────────────────
Servo radarServo;
Servo aimServo;
Servo fireServo;

// ── Ultrasonic ───────────────────────────────
const int TRIG = 9;
const int ECHO = 10;

// ── Radar config ─────────────────────────────
const int ANGLE_MIN   = 15;
const int ANGLE_MAX   = 165;
const int SWEEP_DELAY = 80;   // ms

// ── Direction Fix ────────────────────────────
// যদি এখনও উল্টো থাকে, RADAR_FLIP = false করো
#define RADAR_FLIP true   // radar servo physically উল্টো লাগানো
#define AIM_FLIP   true   // aim servo physically উল্টো লাগানো
#define FIRE_FLIP  true   // fire servo physically উল্টো লাগানো

// Servo angle mirror করার function
int flipAngle(int angle) { return 180 - angle; }

// ── State ────────────────────────────────────
int  currentAngle = ANGLE_MIN;
int  sweepDir     = 1;
int  lastAimPos   = 90;
bool aimAttached  = false;
bool fireAttached = false;

unsigned long lastSweepTime  = 0;
unsigned long lastSendTime   = 0;
const int     SEND_INTERVAL  = 50;

// ─────────────────────────────────────────────
void setup() {
  Serial.begin(9600);
  espSerial.begin(9600);

  pinMode(TRIG, OUTPUT);
  pinMode(ECHO, INPUT);

  radarServo.attach(11);
  aimServo.attach(12);
  fireServo.attach(13);

  // শুরুতে center position এ যাবে
  radarServo.write(RADAR_FLIP ? flipAngle(ANGLE_MIN) : ANGLE_MIN);
  aimServo.write(AIM_FLIP ? flipAngle(90) : 90);
  fireServo.write(FIRE_FLIP ? flipAngle(90) : 90);    // rest position
  delay(600);

  aimServo.detach();  aimAttached  = false;
  fireServo.detach(); fireAttached = false;

  Serial.println("Arduino Ready. v3.1");
}

// ─────────────────────────────────────────────
void loop() {

  unsigned long now = millis();

  // ── ১. Radar Sweep ──
  if (now - lastSweepTime >= SWEEP_DELAY) {
    lastSweepTime = now;

    // RADAR_FLIP true হলে angle mirror করে পাঠাবে
    int writeAngle = RADAR_FLIP ? flipAngle(currentAngle) : currentAngle;
    radarServo.write(writeAngle);

    currentAngle += sweepDir;
    if (currentAngle >= ANGLE_MAX) { currentAngle = ANGLE_MAX; sweepDir = -1; }
    if (currentAngle <= ANGLE_MIN) { currentAngle = ANGLE_MIN; sweepDir =  1; }
  }

  // ── ২. Distance measure & ESP send ──
  // ESP কে সবসময় original angle পাঠাবে (web dashboard এ সঠিক দেখাবে)
  if (now - lastSendTime >= SEND_INTERVAL) {
    lastSendTime = now;

    int dist = getDistance();

    espSerial.print(currentAngle);
    espSerial.print(',');
    espSerial.println(dist);
  }

  // ── ৩. Command check ──
  handleCommands();
}

// ── Distance ─────────────────────────────────
int getDistance() {
  digitalWrite(TRIG, LOW);
  delayMicroseconds(2);
  digitalWrite(TRIG, HIGH);
  delayMicroseconds(10);
  digitalWrite(TRIG, LOW);

  long dur  = pulseIn(ECHO, HIGH, 15000);
  int  dist = dur * 0.034 / 2;

  if (dist <= 0 || dist > 200) return 200;
  return dist;
}

// ── Commands from ESP ─────────────────────────
void handleCommands() {
  if (!espSerial.available()) return;

  String cmd = espSerial.readStringUntil('\n');
  cmd.trim();
  if (cmd.length() == 0) return;

  // ── FIRE ──
  if (cmd == "FIRE") {
    if (!fireAttached) {
      fireServo.attach(13);
      fireAttached = true;
      delay(20);
    }
    fireServo.write(FIRE_FLIP ? flipAngle(150) : 150);
    delay(600);
    fireServo.write(FIRE_FLIP ? flipAngle(90) : 90);
    delay(200);
    fireServo.detach();
    fireAttached = false;
    Serial.println("FIRED!");
  }

  // ── AIM:angle ──
  else if (cmd.startsWith("AIM:")) {
    int pos = constrain(cmd.substring(4).toInt(), 0, 180);
    if (abs(pos - lastAimPos) > 2) {
      if (!aimAttached) {
        aimServo.attach(12);
        aimAttached = true;
        delay(20);
      }
      // AIM_FLIP true হলে aim servo mirror করবে
      int writePos = AIM_FLIP ? flipAngle(pos) : pos;
      aimServo.write(writePos);
      lastAimPos = pos;
      delay(100);
      aimServo.detach();
      aimAttached = false;
    }
  }
}