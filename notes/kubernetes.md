# Minikube - kubernetes в локальном окружении

## Как поднять на linux
### Установить Minikube
```bash
curl -LO https://storage.googleapis.com/minikube/releases/latest/minikube-linux-amd64
sudo install minikube-linux-amd64 /usr/local/bin/minikube && rm minikube-linux-amd64
```
Подробнее в документации https://minikube.sigs.k8s.io/docs/start/?arch=%2Flinux%2Fx86-64%2Fstable%2Fbinary+download

### Установить драйвер виртуализации (например Docker)
Если докера нет в системе, то можно установить несколькими способами. Мы выберем ["apt repository"](https://docs.docker.com/engine/install/ubuntu/#install-using-the-repository)

+ Настраиваем apt repository:
```bash
# Add Docker's official GPG key:
sudo apt-get update
sudo apt-get install ca-certificates curl
sudo install -m 0755 -d /etc/apt/keyrings
sudo curl -fsSL https://download.docker.com/linux/ubuntu/gpg -o /etc/apt/keyrings/docker.asc
sudo chmod a+r /etc/apt/keyrings/docker.asc

# Add the repository to Apt sources:
echo \
  "deb [arch=$(dpkg --print-architecture) signed-by=/etc/apt/keyrings/docker.asc] https://download.docker.com/linux/ubuntu \
  $(. /etc/os-release && echo "$VERSION_CODENAME") stable" | \
  sudo tee /etc/apt/sources.list.d/docker.list > /dev/null
sudo apt-get update
```
+ Устанавливаем пакеты докера:
```bash
sudo apt-get install docker-ce docker-ce-cli containerd.io docker-buildx-plugin docker-compose-plugin
```
+ Create the docker group: `sudo groupadd docker`
+ Add your user to the docker group: `sudo usermod -aG docker $USER`
+ Activate the changes to groups: `newgrp docker`
+ Сделать docker драйвером по умолчанию для minikube: `minikube config set driver docker`

Дока minikube по драйверам: https://minikube.sigs.k8s.io/docs/drivers/docker/

### Установить kubectl
+ Download the latest release with the command:
```bash
curl -LO "https://dl.k8s.io/release/$(curl -L -s https://dl.k8s.io/release/stable.txt)/bin/linux/amd64/kubectl
```
+ Validate the binary (optional):
  + Download the kubectl checksum file
    ```bash
    curl -LO "https://dl.k8s.io/release/$(curl -L -s https://dl.k8s.io/release/stable.txt)/bin/linux/amd64/kubectl.sha256"
    ```
  + Validate the kubectl binary against the checksum file:
    ```bash
    echo "$(cat kubectl.sha256)  kubectl" | sha256sum --check
    ```
  + If valid, the output is: `kubectl: OK`
+ Install kubectl:
```bash
sudo install -o root -g root -m 0755 kubectl /usr/local/bin/kubectl
```
+ Test to ensure the version you installed is up-to-date: `kubectl version --client`

Подробнее в документации https://kubernetes.io/docs/tasks/tools/install-kubectl-linux/

### Запустить minikube
+ Запуск:
`minikube start`
+ Проверить состояние minikube:
`kubectl get po -A`

Подробнее в документации https://minikube.sigs.k8s.io/docs/start/?arch=%2Flinux%2Fx86-64%2Fstable%2Fbinary+download
