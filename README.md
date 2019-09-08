# jflexcup

## Build e test
```mvn clean install``` per lanciare la generazione del parser, la build e i test.

## Struttura del progetto
Il progetto utilizza Maven per la gestione delle dipendenze, della build e della generazione del parser (tramite i plugin per maven di jflex e cup).
In ```/src/main/jflex``` si trova il file ```scanner.jflex``` e in ```/src/main/cup``` si trova il file ```parser.cup```. Compilando con il comando sopra riportato, vengono generati i file Java e messi nel package ```it.uniupo.disit.linguaggi2.jflexcup.generated```. Tutti gli altri file presenti, sia in main che in test, sono stati scritti a mano.
