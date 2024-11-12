import { Component } from '@angular/core';

@Component({
  selector: 'app-login-page',
  standalone: false,
  templateUrl: './login.component.html',
  styleUrl: './login.component.css'
})
export class LoginPageComponent {

  pageId: string = 'login-page';
}
