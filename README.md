Программный комплекс работает на ОС семейства Linux
1. Данный репозиторий является main сервисом, который отвечает за связь фронта и модели.
   Его запуск:
   1.1 Чтобы развернуть базу данных postgres:
    sudo apt update
    sudo apt install postgresql postgresql-contrib
    sudo -i -u postgres
    psql
    \q
    sudo -u postgres psql
    sudo -u postgres createuser --interactive
    man createuser
    sudo -u postgres createdb <dbname>
    sudo adduser <username>
    psql -U <username> -d <dbname>
    \i /mnt/d/.../script.sql
    sudo -u <username> psql
    sudo service postgresql start
    sudo service postgresql status
   1.2 Скачать необходимые пакеты: java и maven
   1.3 Внимательно изучить файл resourse/application.properties - и указать там настройки вашего сервера
   1.4 Запуск
   mvn spring-boot:run
   
2. Данный репозиторий https://github.com/Innosan/ml-edu-platform - представляет собой web-приложение, его запуск осуществляется после установки npm и node.
    Подробнее под Linux систему написано в этой статье: https://dev.to/wimadev/deploy-a-nuxt-3-app-on-a-vps-minimal-setup-3h91
   Запуск осуществляется с помощью команды: https://dev.to/wimadev/deploy-a-nuxt-3-app-on-a-vps-minimal-setup-3h91

3. Данный репозиторий https://github.com/MuwwciWay/MorphoRuEva - представляет собой модуль для AI. Взаимодействие происходит на основе файлов, поэтому дополнительных действий по запуску не требуется.
   
