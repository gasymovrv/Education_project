# Основные команды GIT

### Устанавливаем git.
+ После установки пакета git
в контекстном меню появляются пункты "Git Bash Here", "Git Gui Here".

+ Задаем настройки git в Git Bash:
```
git config --global user.name "ваше имя"
git config --global user.email "ваша почта"
git config --global core.autocrlf true //если будут возникать ошибки при добавлении файлов,
git config --global core.safecrlf true //то надо заменить эти параметры на false
```
+ Чтобы подключить необходимую директорию к git:
    + нажимаем в ней правой кнопкой
    + выбираем 'Git Bash Here' в контекстном меню
    + прописываем следующую команду в появившемся окне: ```git init```


### Вариант добавления на GitHub #1:
+ в нужной папке нажимаем на 'Git Bash Here' в контекстном меню
+ прописываем следующие команды в появившемся окне:
```
git add . //добавить все файлы директории
git commit -a -m 'first commit' //закоммитить их с комментарием
git remote add origin https://github.com/gasymovrv/имя_репозитория.git //указываем адрес репозитория
git push -u origin master //загрузить на сервер
```


### Вариант добавления на GitHub #2:
+ в нужной папке нажимаем на 'Git Gui Here' в контекстном меню
+ работаем в появившемся окне:
    + перемещаем необходимые файлы из Unstaged Changes в Staged Changes
    + пишем комментарий коммита в окне Initial Commit Message, жмем Commit
    + жмем Push и указываем url-адрес репозитория (```https://github.com/gasymovrv/имя_репозитория.git```)



### Получение репозитория с GitHub #1:
+ в нужной папке нажимаем на 'Git Bash Here' в контекстном меню
+ прописываем следующую команду в появившемся окне:
```
git clone https://github.com/gasymovrv/имя_репозитория.git
```


### Получение репозитория с GitHub #2:
```
git init
git remote add repo https://github.com/gasymovrv/GitTestRepo.git
git pull repo
git checkout master
```
+ repo - это имя репозитория, может быть любым, но обычно называют origin
+ например при команде clone гит автоматически называет origin


### Получение изменений из GitHub #1:
```
git pull origin
```

### Получение изменений из GitHub #1:
```
git fetch origin
git checkout master //переключаем на локальную master если еще не была переключена
git merge origin/master //делаем merge из удаленной
```
+ git pull — это, по сути, команда git fetch, после которой сразу же следует git merge. 
+ git fetch получает изменения с сервера и сохраняет их в каталог refs/remotes/. Это никак не влияет на локальные ветки и текущие изменения. 
+ А git merge уже вливает все эти изменения в локальную копию.



