# Настройки БД и VM VirtualBox

### Настройка сети виртуалки
1. Настраиваем сеть со статическим ip для доступа с машины-хоста.
    + Создаем host-only adapter (айпишник виртуалки будет доступен только на нашей локальной машине). 
        + В настройках виртуалки указываем ip 192.168.56.1, dhcp отключаем.  - см. [vm_net_settings.jpg](vm_net_settings.jpg)
        + В ubuntu настраиваем айпишник 192.168.56.217 (или другой, но больше 192.168.56.1) - см. [ubuntu_interfaces.png](ubuntu_interfaces.png)
            + Добавляем в файл ```/etc/network/interfaces```:
                ```
                # My host-only adapter
                auto enp0s3
                iface enp0s3 inet static
                address 192.168.56.217
                netmask 255.255.255.0
                ```
            + enp0s3 - можно узнать предварительно выполнив команду ```ifconfig```  - см. [ubuntu_ifconfig.jpg](ubuntu_ifconfig.jpg) - так должно выглядеть после сохранения файла ```/etc/network/interfaces```
    + Примечание. 
    В убунту названия сетевых портов (enp0s3, enp0s8 …) привязаны к адаптерам виртуалки, т.е. Адаптер1- enp0s3, Адаптер2- enp0s8 и т.д. - см. [ubuntu_ifconfig.jpg](ubuntu_ifconfig.jpg)
1. Настраиваем сеть с динамическим ip для доступа в интернет из гостевой ОС.
    + Добавляем адаптер типа NAT в настройках виртуалки
        + Добавляем его настройку в файл ```/etc/network/interfaces``` (у меня в ubuntu он появился под именем enp0s8) - см. [ubuntu_interfaces.png](ubuntu_interfaces.png):
            ```
            # My internet adapter
            allow-hotplug enp0s8
            iface enp0s8 inet dhcp
            ```
1. Проверяем файл ```/etc/network/interfaces``` - должно быть примерно как на [ubuntu_interfaces.png](ubuntu_interfaces.png):
<img width="600" alt="ubuntu_interfaces.png" src="ubuntu_interfaces.png">
1. Применяем изменения 1го адаптера командой ```ifup enp0s3``` и затем 2го ```ifup enp0s8```

### Настройка буфера обмена
1. Включить в настройках виртуальной машины
2. Чтобы заработало, необходимо подключить диск VBoxGuestAdditions.iso (обычно лежит в папке установки VirtualBox, например E:\Programs\VirtualBox)
<img width="600px" alt="vbox_guest_additions.png" src="vbox_guest_additions.png"/>
3. Затем запустить ВМ и выполнить установку с диска командой: `sudo rcvboxadd setup`. [Подробная дока от Oracle](https://www.virtualbox.org/manual/ch04.html#additions-linux)


### Подключение к БД на виртуалке (с помощью туннеля ssh - не использую этот способ)
1. Настраиваем сеть виртуалки: добавляем адаптер №2, прописываем ему ip
1. Этот же ip прописываем в файле настроек ssh-сервера в убунте, которая на виртуалке
1. В сети виртуалке(адаптер №1) делаем проброс порта 22 (или другого если изменили дефолтный)
1. Скачиваем putty, добавляем ip который указан в виртуалке и добавляем ssh-туннель для БД
1. Теперь можно подключаться к БД из кода, как будто она локальная

+ Примечание: можно и не создавать туннель для БД, тогда необходимо его создавать в коде или подключаться напрямую по TCP/IP.
+ Скриншоты процесса настроек в папке «настройка virtualbox как удаленного сервера»

### Настройка mysql на Ubuntu для подлкючения с удаленной машины (или с локальной к виртуалке)
+ Изменяем адреса которые будет слушать mysql в файле в секции [mysqlid]:
```
sudo nano /etc/mysql/my.cnf
```
если там нет такой секции, то в файле:
```
/etc/mysql/mysql.conf.d/mysqld.cnf
```
Вместо: 
```
bind-address=127.0.0.1
```
Вписываем: 
```
bind-address=0.0.0.0
```

И в файле ```/etc/mysql/mysql.conf.d/mysqld.cnf``` добавляем строку:
```
skip-grant-tables
```
Перезагружаем: ```service mysql restart```
+ Чтобы подключиться из вне нам необходим ip виртаулки (можно посмотреть командой ifconfig), порт mysql (по умолчанию 3306) и созданные имя пользователя+пароль


### Настройка postgres на Ubuntu для подлкючения с удаленной машины (или с локальной к виртуалке)
Раскоментить и заменить в файле (9.5 – это версия постгреса):
```
sudo nano /etc/postgresql/9.5/main/postgresql.conf
```
строку:
```listen_addresses = '*'```

Изменяем права для пользователей в файле:
```
sudo nano /etc/postgresql/9.5/main/pg_hba.conf
```
Вместо: 
```
# IPv4 local connections:
host    all             all             127.0.0.1         md5
```
Вписываем: 
```
# IPv4 local connections:
host    all             all             0.0.0.0/0         md5
```

В процессе установки был создан аккаунт пользователя Ubuntu с именем postgres.
Создаем пользователя БД:
```
sudo -i -u postgres
createuser -s -r -d -P admin
4 # это пароль
Exit
```
Перезагружаем: ```service postgresql restart```
+ Чтобы подключиться из вне нам необходим ip виртаулки (можно посмотреть командой ifconfig), порт постгреса (по умолчанию 5432) и созданные имя пользователя+пароль (в примере это – admin, 4)

Теперь можно подключаться удаленно к БД  - см. [postgres_connect.jpg](postgres_connect.jpg)
+ Имя/адрес сервера - ip настроенный в ubuntu
+ Порт - порт postgres (по умолчанию 5432, можно изменить в файле ```/etc/postgresql/9.5/main/postgresql.conf```)

### Проверка в какой кодировке таблицы в mysql
```mysql
SELECT
  `tables`.`TABLE_NAME`,
  `collations`.`character_set_name`
FROM
  `information_schema`.`TABLES` AS `tables`,
  `information_schema`.`COLLATION_CHARACTER_SET_APPLICABILITY` AS `collations`
WHERE
  `tables`.`table_schema` = DATABASE()
  AND `collations`.`collation_name` = `tables`.`table_collation`;
```
