Rock-Paper- Scissors Game
=========================
## Overview
This is a simple **Rock, Paper, Scissors** game developed to demonstrate basic game mechanics and data persistence for analysis. The game allows users to compete against a computer opponent. Player data, game history, and round details are stored for later analysis.
You can find a quick demo video here: https://streamable.com/aku35c
## Features
- **Player Management**: Users can register and play multiple games.
- **Game Mechanics**: Standard Rock, Paper, Scissors rules:
  - Rock beats Scissors
  - Scissors beats Paper
  - Paper beats Rock
- **Data Storage**:
  - Player details
  - Game history (who won each round)
  - Round-by-round data for future analysis
- **Basic AI**: The computer opponent randomly selects Rock, Paper, or Scissors.
## Prerequisites
For development purposes, you need to be sure you have some things installed on your machine. Find below the commands to install everything in case you don't have them already. If you just want to run the whole project as quickly as possible , jump to the 'Running with Docker ' section of this file.
We assume you are using Ubuntu ; commands may change based on your distribution .
### Backend Requirements
For the backend , you need Java 21 and Maven so you can build and run the project locally. We also provide steps on how to configure a MySQL instance for data storage.
**Java 21**
You can install any version of Java 21 , but we recommend using Eclipse Temurin. To install temurin-21-jdk , run the following commands:
```bash
sudo apt install -y wget gnupg
wget -qO - https://packages.adoptium.net/artifactory/api/gpg/key/public | sudo gpg --dearmor -o /usr/share/keyrings/adoptium.gpg
sudo apt update
sudo apt install temurin-21-jdk
```
Then , running `java -version` should return something like this:
```bash
openjdk version "21.0.5" 2024-10-15 LTS
OpenJDK Runtime Environment Temurin-21.0.5+11 (build 21.0.5+11-LTS)
OpenJDK 64-Bit Server VM Temurin-21.0.5+11 (build 21.0.5+11-LTS, mixed mode, sharing)
```
**Maven**  
You can install Maven with the following command:
```bash
apt install maven
```
To be sure , run `mvn --version` , and you should see something similar to:
```bash
Apache Maven 3.6.3
Maven home: /usr/share/maven
Java version: 21.0.5, vendor: Eclipse Adoptium, runtime: /usr/lib/jvm/temurin-21-jdk-amd64
Default locale: en, platform encoding: UTF-8
```
**MySQL**  
For the backend to work properly, we need to have a MySQL instance installed and running on the default port.
Run the following commands to install mysql-server :
```bash
sudo apt install mysql-server
sudo systemctl start mysql
sudo systemctl enable mysql
```
Then run the following command and follow the steps to configure some security :
```bash
sudo mysql_secure_installation
```
Finally , log into the MySQL instance :
```bash
sudo mysql -u root -p
```
Sure! Here is the corrected text maintaining the same number of words:

And run the following command to create a user
```sql
CREATE USER 'myuser'@'localhost' IDENTIFIED BY 'mypassword';
GRANT ALL PRIVILEGES ON your_database_name.* TO 'myuser'@'localhost';
FLUSH PRIVILEGES;
```
### Frontend requisites
For the frontend , you will need npm and Angular CLI so you can build and run the project locally .  

**NPM**  
Run the following command to install Node.js 18 and npm from Ubuntu's repositories :
```bash
curl -fsSL https://deb.nodesource.com/setup_18.x | sudo -E bash -
apt install -y nodejs
apt install -g npm
```
As before, you can ensure it's correctly installed with `npm -v`.
**Angular CLI**
Install Angular CLI globally with :
```bash
npm install -g @angular/cli
```
Once done, the command `ng version` will show something similar to :
```bash
Angular CLI: 18.2.11
Node: 18.20.4
Package Manager: npm 10.9.0
OS: linux x64
```
### (Optional) Install Docker
If you want to utilize the containerization capabilities of the project, you will need to have Docker installed.
Run the following commands to install Docker :
```bash
apt install -y ca-certificates curl gnupg lsb-release
sudo mkdir -p /etc/apt/keyrings
curl -fsSL https://download.docker.com/linux/ubuntu/gpg | sudo gpg --dearmor -o /etc/apt/keyrings/docker.gpg
echo \
"deb [arch=$(dpkg --print-architecture) signed-by=/etc/apt/keyrings/docker.gpg] https://download.docker.com/linux/ubuntu \
$(lsb_release -cs) stable" | sudo tee /etc/apt/sources.list.d/docker.list > /dev/null
sudo apt update
apt install -y docker-ce docker-ce-cli containerd.io docker-buildx-plugin docker-compose-plugin
```
Now that we have Docker installed, we must add the current user to the Docker user group.
Run:
```bash
sudo usermod -aG docker $USER
```
Then start and enable Docker :
```bash
sudo systemctl start docker
sudo systemctl enable docker
```
To verify that Docker is correctly installed , you can run the hello-world container.
```bash
docker run hello-world
```
## Running with Docker
If you don't want to install all the prerequisites for development or you simply want to see how the app works without making any changes , we provide a docker-compose . yml file.
**Note**: You still need to have Docker installed.
First , generate a folder on your system where MySQL will store the data or modify the .env file provided to set an already existing directory :
```bash
mkdir /opt/mysql_data
```
Then, from the root folder of the project , run:
```bash
docker compose up
```
The docker-compose . yml file will generate three different containers : one with a MySQL instance, one with the backend app , and the last one with the frontend app.
Just visit localhost:8081 and enjoy your game.
If multiple users run the Docker compose from the same machine, every user must set a range of free ports in the .env file. Otherwise, the containers will not run.