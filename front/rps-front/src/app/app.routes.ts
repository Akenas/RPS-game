import { Routes } from '@angular/router';
import { AuthGuard } from './auth.guard';
import { HomePageComponent } from './ui/pages/home/home.component';
import { LoginPageComponent } from './ui/pages/login/login.component';
import { RegisterPageComponent } from './ui/pages/register/register.component';

export const routes: Routes = [
    {
        path: '',
        component: HomePageComponent,
        canActivate: [AuthGuard]
    },
    {
        path: 'login',
        component: LoginPageComponent
        
    },
    {
        path: 'signup',
        component: RegisterPageComponent
    },
];
