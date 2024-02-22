!!!!!!!!!!!!Wenn du das abgibst das Löschen!!!!!!!!!!!!!!!!!!
Ich würde auch entfehlen, Nochmal in Intelij überprüfen, das min name da nicht als Author steht.
Wenn mein name da steht musst du leider ein neues Prfojekt machen un dden Code von diesem zu dem neuen Projekt kopieren (Den Code und nicht die .Java Dateien!)

Datei "README.md" öffnen und oben in der leiste von Preview auf Code umstellen zur besseren Ansicht :)


In dieses Repo findest du das Programm. Wenn du es ausprobieren möchtest kannst du auf "Code" drücken und dir als ZIP runterladen. 

Stand 22.02. 20:45
Die bisherigen Klassen und Packages:

Exceptions                      -Hier findest du die angelegten Exceptions
  ISINFormatException           -Diese Exception wird geworfen, wenn das Format der ISIN nicht richtig ist
  WertpapierkaufEmptyException  -Wird geworfen, wenn man ein null Objekt zum depot hinzufügen will
  ISINNichtGefundenException    -Wird geworfen, wenn man nach der ISIN sucht und kein ergebnis gefunden wird.

Objekte                         -Hier sind deine verschiedenen Objekte drin, die du brauchst
  Wertpapier                    -Dieses Objekt ist für ein Wertpapier besteht aus ISIN und Name
                                -Hier wird auch geprüft, ob die ISIN ein richtiges Format hat  
  Wertpapierkauf                -Dieses Objekt ist für einen Kauf es besteht aus einem Wertpapier Objekt und den anderen nötigen Daten (Kaufdatum usw.)
                                -Des weiteren wird hier auch der Wert un die Rendite berechnet

Services                        -Hier ist die eigentliche Programmierlogik
  DepotService                  -Hier kann man alle aktivitäten, die für das Depot wichtig sind programmiert
  KonsolenService               -Hier wird alles geregelt, was mit der Konsole zusammenhängt

Tools
  Tools                         -Das ist eine Klasse, die ich von KOP5 geklaut habe.

Main                            -Startet die Konsole.




