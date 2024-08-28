# Verwendete Systeme

## Desktopsystem:
- Betriebssystem: Windows 11 Version 21H2
- CPU: Ryzen 5700u
- RAM: 16GB

## Androidsystem:
- Betriebssystem: Android 10 (QP1A.190711.020)
- Handy: Xiaomi Redmi Note 9

# Entwicklung nachvollziehen

## Datenbank einrichten
- xampp Paket downloaden [hier](https://www.apachefriends.org/de/index.html) (Version 8.1.1)
- xampp Paket entpacken
- `.../xampp/setup.bat` ausführen
- `.../xampp/xampp-control.exe` ausführen
- Apache Server starten
- MySQL Server starten
- [http://127.0.0.1/phpmyadmin/](http://127.0.0.1/phpmyadmin/) im Browser öffnen
- Auf den Reiter "SQL" klicken
- Das Script aus `.../DB/erstellen.sql` einfügen und ausführen
- Datenbank ist fertig!

## Desktopanwendung einrichten
- Java JDK downloaden [hier](https://www.oracle.com/java/technologies/downloads/) (Version 8, Update 301)
- Java Editor downloaden [hier](https://javaeditor.org/doku.php) (Version 19.30)
- Java JDK installieren
- Java Editor installieren und einrichten
- In `.../Quelltext/` die `.java` Dateien mit dem Java Editor öffnen
- Alle `.java` Dateien kompilieren
- Sicherstellen, dass der DB Server auf dem gleichen System läuft
- `MainFrame.java` starten
- Desktopanwendung läuft!

## Android Applikation einrichten
- Android Studio downloaden [hier](https://developer.android.com/studio) (Android Studio 4.2, Vers. 11.0.8)
- Android Studio installieren 
- `.../Android_App` mit dem Android Studio öffnen
- App auf Handy oder virtuelle Maschine starten
- IP von der Datenbank (vom PC, auf dem xampp läuft) eingeben
- Nutzername: Admin / Passwort: 47114711
- "Verbinden" klicken
- App sollte sich verbinden und ist bereit!
- Android Applikation läuft!

## Starten DB-Server
- xampp Ordner öffnen
- `.../xampp/xampp-control.exe` ausführen
- Apache Server starten
- MySQL Server starten

## Beenden DB-Server
- Apache Server beenden
- MySQL Server beenden
- xampp beenden