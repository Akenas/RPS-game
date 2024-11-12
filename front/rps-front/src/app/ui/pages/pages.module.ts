import { NgModule } from '@angular/core';
import { CardModule } from 'primeng/card';
import { AuthModule } from "../../auth/auth.module";
import { CardMenuComponent } from '../menu/button-menu/button-menu.component';
import { BasicPageComponent } from './basic-page/basic-page.component';
import { HomePageComponent } from './home/home.component';
import { LoginPageComponent } from './login/login.component';
import { RegisterPageComponent } from './register/register.component';
import { PlayPageComponent } from './play/play.component';
import { ModeMenuComponent } from '../menu/mode-menu/mode-menu.component';
import { QueueControllerComponent } from "../game/queue-controller/queue-controller.component";
import { GameComponent } from "../game/game.component";
import { OverlayMenuComponent } from "../menu/overlay-menu/overlay-menu.component";

@NgModule({
  declarations: [
    HomePageComponent,
    BasicPageComponent,
    LoginPageComponent,
    RegisterPageComponent,
    PlayPageComponent
  ],
  imports: [
    CardMenuComponent,
    AuthModule,
    CardModule,
    ModeMenuComponent,
    QueueControllerComponent,
    GameComponent,
    OverlayMenuComponent
],
  exports: [
    HomePageComponent,
    LoginPageComponent,
    PlayPageComponent
  ]
})
export class PagesModule { }