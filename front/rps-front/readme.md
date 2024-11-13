
# Frontend Application
The frontend application provides the UI layer and enables user interaction with the application. It has been developed with Angular 18 as the main framework.

### Project Structure
```
app
├── auth
│   ├── login
│   └── register
├── model
├── services
│   ├── auth
│   └── game
└── ui
    ├── game
    │   ├── option-picker
    │   ├── pick-visor
    │   ├── queue-controller
    │   └── rounds-visor
    ├── menu
    │   ├── button-menu
    │   ├── mode-menu
    │   └── overlay-menu
    └── pages
        ├── basic-page
        ├── home
        ├── login
        ├── play
        └── register
```
- **auth**: Contains components for signing up and logging in to the application.
    - **login**: Login form .
    - **register**: Signup form .
- **model**: Contains interfaces used by the application to manage data , mirroring the backend application 's data model.
- **services**:
    - **auth**: Contains the service which provides authentication methods that communicate with the backend application .
    - **game**: Contains the service which manages player queueing, WebSocket communication, and everything related to game management performed on the backend.
- **ui**: Contains the components that the user interacts with.
    - **game**: Components for the game match itself. We are able to play the game with these .
    - **menu**: Different menu components .
    - **pages**: The different pages of the application are stored here.

### Features
- **Authentication**: Users can sign up , log in , and log out.
- **Game mode selection**: When the user wants to play, the application prompts them to select a game mode before queueing the player.
- **Game visualization**: While playing, the user can see the number of rounds for the match, each round 's pick , and the name of the player they are competing against.
- **LocalStorage persistence**: Afraid of reloading and losing progress? Fear no more. Ongoing matches are stored in local storage until finished.
- **Rematch or requeue**: Once the match is finished , you can rematch if playing against the AI or requeue for the same mode if playing against a player.
- **Forfeit Match**: You can forfeit a match at any time. Obviously , you will lose , but hey! It saves time sometimes.

### Building and Running the Project
As before , we provide two different options to build the project. If you want to develop and see changes , you can run `ng serve`.

#### Building the Project with NPM
Just run `npm build`. Copy the contents of the `/dist` folder to any static web server of your preference , such as Apache or Nginx . You can also deploy it to GitHub Pages:

```bash
npm install -g angular-cli-ghpages
ng build --base-href "https://.github.io//"
npx angular-cli-ghpages --dir=dist/
```

Go to `https://.github.io//` to see the frontend application.

### Building the Project with Docker
We have packed a Docker file in the root folder of the backend service (/back). If you have Docker installed, you can run:

```bash
cd front/rps-front
docker build ./ -t rps-front
docker run -p 8080:80 rps-front
```

The Dockerfile looks like this :

```docker
# Stage 1: Build the Angular application
FROM node:18 AS build
WORKDIR /app
COPY package*.json ./
RUN npm install
COPY . .
RUN npm run build --prod

# Stage 2: Serve with Nginx
FROM nginx:alpine
COPY --from=build /app/dist/rps-front/browser /usr/share/nginx/html
COPY ./config/nginx.conf /etc/nginx/conf.d/default.conf
```

This will create a production build with npm and create an Nginx instance serving the application.

### Known Bugs as of November 13, 2024
- We are not detecting correctly when the JWT token has expired due to a bug in the backend.
- Sometimes the WebSocket connection is not correctly opened after returning home from a game , and the user is stuck in the queue. Reloading the page usually solves that.


### TODOs
- Redo login and signup forms to better adapt to the UI style (remove Cards) .
- Add a profile section to review match data .
- Add an options section to change some configuration settings such as language.
- Translations.
- Loading component in case of bad network or throttling
- Test online play. The system is able to queue two human players and create a match for them to play. However, it has been poorly tested  on the front end, so it is not available yet.