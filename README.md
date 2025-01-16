## Installare WSL

Per installare WSL (Windows Subsystem for Linux) seguire la guida ufficiale di Microsoft:

   [Installare WSL](https://docs.microsoft.com/it-it/windows/wsl/install)

Di default installare la distro Ubuntu.

## Installazione Docker su WSL

Per installare Docker su WSL seguire la guida ufficiale di Docker:

   [Installare Docker su WSL](https://docs.docker.com/engine/install/ubuntu/)

## Docker Compose

Avviare il servizio Docker da terminale WSL:

   ```
   sudo service docker start
   ```

Aprire un terminale WSL nella cartella root del progetto (dove c'è il file `docker-compose.yml`) e lanciare il comando:

   ```
   docker compose up -d
   ```

## Aggiungere variabile d'ambiente per la connessione al database

Da WSL ottenere l'indirizzo IP con il comando:

   ```
   ip addr show eth0
   ```

L'indirizzo IP è nella riga inet.

Esempio:

    ip addr show eth0
        2: eth0: <BROADCAST,MULTICAST,UP,LOWER_UP> mtu 1500 qdisc mq state UP group default qlen 1000
        link/ether 00:15:5d:35:86:15 brd ff:ff:ff:ff:ff:ff
        inet 172.18.104.83/20 brd 172.18.111.255 scope global eth0
            valid_lft forever preferred_lft forever
        inet6 fe80::215:5dff:fe35:8615/64 scope link
            valid_lft forever preferred_lft forever

L'IP in questo esempio è 172.18.104.83

Cercare "variabili d'ambiente" nel menu di ricerca di Windows e aprire la finestra di dialogo.
Selezionare il pulsante "Variabili d'ambiente".
Nella sezione "Variabili di sistema" selezionare "Nuova".

Inserire come nome variabile WSL_DB_HOST e come valore l'indirizzo IP di WSL.
Questa variabile è utilizzata nel file application.properties come ${WSL_DB_HOST}

## Avvio iniziale

Avviare il progetto Spring Boot.
A questo punto il database è pronto per essere utilizzato.
Non è più necessario commentare spring.sql.init.mode=always.


## Test REST API

Gli endpoint REST sono disponibili nel file Test REST API - Full CRUD 1.postman_collection.json da aprire con Postman.

Il riferimento è descritto nel file FormRichiestaController.java in src/main/java/com.codecraft.test_rest_api/controller/

Disponibile anche Swagger per poter testare i servizi REST: http://localhost:8080/swagger-ui/index.html
I body di Swagger UI ora funzionano correttamente con valori validi di default.


## Test e-mail

Credenziali email testing: testcascinacaccia@gmail.com,
PASS: provacaccia 
Per il testing della mail utilizzare le chiamate di create delle richieste, e non inviare troppe email consecutivamente perchè gmail può dare problemi

Se serve fare test consecutivi di create senza email commentare le righe con sendEmailService dentro i due metodi di createForm nel file FormRichiestaService

## Per chiudere WSL

Per chiudere WSL, aprire un terminale PowerShell e lanciare il comando:

   ```
   wsl --shutdown
   ```

Verrà chiusa la sessione WSL corrente, compreso Docker e il database.