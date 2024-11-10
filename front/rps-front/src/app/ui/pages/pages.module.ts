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
    ModeMenuComponent
],
  exports: [
    HomePageComponent,
    LoginPageComponent,
    PlayPageComponent
  ]
})
export class PagesModule { }