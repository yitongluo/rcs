# MySQL Dev Database Image

This repo build MySQL database image, for development and testing purpose.

- To build Docker image `mysql_dev`:

```cmd
.\build.bat
```

- To start Docker container `mysql_dev`:

```cmd
.\start.bat
```

After docker container `mysql_dev` started, client can connect to MySQL server localhost:3306, using user name `root` and password '123456'.

- To stop Docker container `mysql_dev`:

```cmd
.\stop.bat
```
