# ThankYourTeam

## Requirements

- JDK 6 or later
- Activator (https://typesafe.com/activator)

## Installation

1. Clone the repository.
2. `activator "run 9091"`

## Running

Point your browser to http://localhost:8080

## Nginx proxy

    upstream thankyourteam-scala {
        server 127.0.0.1:9091;
    }

    server {
        listen      80;
        server_name .thankyourteam.com;

        error_log    /wwwroot/logs/thankyourteam/thankyourteam.error.log;
        access_log   /wwwroot/logs/thankyourteam/thankyourteam.access.log;

        location / {
            proxy_pass http://thankyourteam-scala;
        }
    }
