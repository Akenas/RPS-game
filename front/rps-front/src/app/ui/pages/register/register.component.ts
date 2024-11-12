import { Component } from '@angular/core';

@Component({
  selector: 'app-register-page',
  standalone: false,
  templateUrl: './register.component.html',
  styleUrl: './register.component.css'
})
export class RegisterPageComponent {
  pageId: string = 'register-page';
}
