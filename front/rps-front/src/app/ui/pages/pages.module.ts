import { NgModule } from '@angular/core';
import { CardMenuComponent } from '../menu/button-menu/button-menu.component';
import { HomePageComponent } from './home/home.component';
import { LoginPageComponent } from './login/login.component';
import { BasicPageComponent } from './basic-page/basic-page.component';
import { AuthModule } from "../../auth/auth.module";
import { CardModule } from 'primeng/card';
import { RegisterPageComponent } from './register/register.component';

@NgModule({
  declarations: [
    HomePageComponent,
    BasicPageComponent,
    LoginPageComponent,
    RegisterPageComponent
  ],
  imports: [
    CardMenuComponent,
    AuthModule,
    CardModule
],
  exports: [
    HomePageComponent,
    LoginPageComponent
  ]
})
export class PagesModule { }