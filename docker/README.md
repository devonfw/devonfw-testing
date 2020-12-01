### Docker-compose
The docker-compose file features:
* jenkins (cicd server)
* selenium hub + firefox + chrome + opera (environemnt for executing selenium test)
* gitea (git hosting)


| Service        | Web Port     | License           |
|----------------|--------------|-------------------|
| Gitea          | 3000         | MIT               |
| Jenkins        | 8888         | MIT               |
| Selenium Grid  | 4444         | Apache 2.0        |
| PostgreSQL     | 5432         | PosgreSQL License |

It is best to run this on linux.