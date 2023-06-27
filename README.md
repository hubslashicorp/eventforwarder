# Getting Started Event Forward API

### Reference Documentation
For further reference, please consider the following sections:

* [Official Apache Maven documentation](https://maven.apache.org/guides/index.html)
* [Spring Boot Maven Plugin Reference Guide](https://docs.spring.io/spring-boot/docs/3.1.1/maven-plugin/reference/html/)
* [Create an OCI image](https://docs.spring.io/spring-boot/docs/3.1.1/maven-plugin/reference/html/#build-image)


```shell
└── src
    └── main
        ├── java
        │   └── com
        │       └── example
        │           ├── config
        │           │   ├── JwtTokenFilter.java
        │           │   └── SecurityConfig.java
        │           ├── controller
        │           │   └── EventController.java
        │           ├── dto
        │           │   └── EventDTO.java
        │           ├── entity
        │           │   └── Event.java
        │           ├── repository
        │           │   └── EventRepository.java
        │           ├── service
        │           │   └── EventService.java
        │           └── Application.java
        └── resources
            ├── application.properties
            └── db
                └── migration
                    └── V1__Create_Events_Table.sql
```

# MYSQL test local config

Para iniciar um contêiner MySQL e criar as tabelas da última aplicação, você pode seguir os passos a seguir:

Inicie um contêiner MySQL com o seguinte comando:

```shell
docker run -d --name mysql-container -e MYSQL_ROOT_PASSWORD=root -p 3306:3306 mysql:latest
```

> Certifique-se de substituir "senha" pela senha desejada para o usuário root do MySQL.

1. Aguarde alguns segundos para que o contêiner seja iniciado e o servidor MySQL esteja pronto para aceitar conexões.
2. Conecte-se ao contêiner MySQL usando um cliente MySQL, como o MySQL Workbench ou o MySQL CLI, usando as seguintes informações de conexão:

- Host: localhost
- Porta: 3306
- Usuário: root
- Senha: a senha fornecida no comando do passo 1

1. Crie um banco de dados para a aplicação executando o seguinte comando SQL:

```sql
CREATE TABLE IF NOT EXISTS events (
  id INT AUTO_INCREMENT PRIMARY KEY,
  format_version VARCHAR(10) NOT NULL,
  vendor VARCHAR(50) NOT NULL,
  product VARCHAR(50) NOT NULL,
  version VARCHAR(20) NOT NULL,
  agent_id VARCHAR(50) NOT NULL,
  agent_desc VARCHAR(100) NOT NULL,
  agent_ver VARCHAR(20) NOT NULL,
  category VARCHAR(50) NOT NULL,
  severity INT NOT NULL,
  event_id VARCHAR(50) NOT NULL,
  event_name VARCHAR(100) NOT NULL,
  event_desc VARCHAR(255) NOT NULL,
  event_date DATETIME NOT NULL,
  source_host VARCHAR(100) NOT NULL,
  os VARCHAR(100) NOT NULL,
  source_ip VARCHAR(20) NOT NULL,
  event_subject VARCHAR(100) NOT NULL,
  event_type INT NOT NULL,
  user VARCHAR(100) NOT NULL,
  workgroup_id VARCHAR(100) NOT NULL,
  workgroup_desc VARCHAR(100) NOT NULL,
  workgroup_location VARCHAR(100) NOT NULL,
  nvps TEXT NOT NULL
);
```

# Application test

Aqui está um exemplo de uma solicitação POST no Postman para a rota `/api/events` da `EventController`:

1. Abra o Postman e selecione o método HTTP POST.
2. Insira a URL da rota: http://localhost:8080/api/events.
3. Na seção `Body`, selecione "raw" e defina o tipo de conteúdo para `JSON (application/json)`.
4. No corpo da solicitação, insira os dados do evento no formato JSON, por exemplo:

```json
{
  "formatVersion": "1.0",
  "vendor": "BeyondTrust",
  "product": "BeyondInsight",
  "version": "6.0.0",
  "agentId": "attack",
  "agentDesc": "Application Bus 3.0",
  "agentVer": "Unknown",
  "category": "User",
  "severity": "0",
  "eventId": "RET-SCAN-007",
  "eventName": "beyondtrust",
  "eventDesc": "bt admin",
  "eventDate": "Jun 10 2016 03:05:04",
  "sourceHost": "mymachine-ws",
  "os": "Windows,Microsoft,Windows,Unknown",
  "sourceIp": "172.168.101.202",
  "eventSubject": "172.168.101.222",
  "eventType": "0",
  "user": "MYMACHINE-WS$",
  "workgroupId": "BeyondTrust Workgroup",
  "workgroupDesc": "BeyondTrust",
  "workgroupLocation": "Default Location",
  "nvps": {
    "id": "c85dca8c-df30-4a70-98f8-c8a47f7fc2fa",
    "evtdate": "6/10/2016 3:05:04 AM",
    "clienthost": "mymachine-ws",
    "eventseverity": "0",
    "dllversion": "AppBus EMS v3.0 com xml",
    "transactiongroup": "5B3A069BE0D84E7EA56F2A40EFDBE253",
    "subjectdescription": "mymachine-ws",
    "evtsubjbi": "2896693762",
    "evtsrcipbi": "2896693762",
    "referenceid": "7",
    "evtdatatype": "SCAN",
    "evtstatus": "True",
    "badpwcount0101": "0",
    "countrycode0101": "0",
    "expires0101": "never ",
    "fullname0101": "beyondtrust",
    "lastlogoff0101": "unknown ",
    "lastlogon0101": "Tue Jun 02 19:26:42 2015",
    "logonserver0101": "\\\\*",
    "maxstorage0101": "unlimited",
    "memberofgroup0101": "Administrators, Performance Log Users, Users",
    "numberoflogons0101": "7",
    "passwordage0101": "412 days",
    "passwordexpired0101": "no",
    "privilege0101": "Administrator",
    "rid0101": "1006",
    "sid0101": "S-1-5-21-4152543990-75340177-3020034217-1006",
    "source0101": "NetUserEnum"
  }
}
```

1. Clique em `Send` para enviar a solicitação `POST`.
2. Você receberá uma resposta com o evento criado e o status `HTTP 201` Created, indicando que o evento foi criado com sucesso.

> Certifique-se de que a aplicação esteja em execução e que a porta correta esteja sendo usada na URL da solicitação.