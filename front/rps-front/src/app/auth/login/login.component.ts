import { Component, DestroyRef, inject, OnInit } from '@angular/core';
import { FormControl, FormGroup, ReactiveFormsModule, Validators } from '@angular/forms';
import { Router, RouterModule } from '@angular/router';
import { debounceTime } from 'rxjs';
import { AuthService } from '../../services/auth/auth.service';
import { InputTextModule } from 'primeng/inputtext';
import { ButtonModule } from 'primeng/button';
import { BasicPageComponent } from '../../ui/pages/basic-page/basic-page.component';
import { MessagesModule } from 'primeng/messages';
import { CardModule } from 'primeng/card';

let initialEmailValue = '';
const savedForm = window.localStorage.getItem('saved-login-form');
if(savedForm){
  const loadedForm = JSON.parse(savedForm);
  initialEmailValue = loadedForm.email;
}

@Component({
  selector: 'app-login',
  standalone: false,
  templateUrl: './login.component.html',
  styleUrl: './login.component.css'
})
export class LoginComponent implements OnInit{
 
  private destroyRef = inject(DestroyRef);
  private invalidCredentials = false;
  loginForm = new FormGroup(
    {
      email : new FormControl(initialEmailValue,{
        validators: [Validators.email, Validators.required],
      }),
      password: new FormControl('', {
        validators: [Validators.required, Validators.minLength(8)],
      }),
    }
  );

  get emailIsInvalid(){
    return (
      this.loginForm.controls.email.touched && 
      this.loginForm.controls.email.dirty && 
      this.loginForm.controls.email.invalid
    );
  }

  get passwordIsInvalid(){
    return (
      this.loginForm.controls.password.touched && 
      this.loginForm.controls.password.dirty && 
      this.loginForm.controls.password.invalid
    );
  }

  get invalidLogin(){
    return this.invalidCredentials;
  }

  constructor(private authService: AuthService, private router: Router) {}

  ngOnInit(): void {
    
    const subscription = this.loginForm.valueChanges.pipe(debounceTime(500)).subscribe({
      next: (value) => {
        window.localStorage.setItem(
          'saved-login-form',
          JSON.stringify({email : value.email})
        )
      }
    });
    this.destroyRef.onDestroy(() => subscription.unsubscribe());
  }

  onSubmit(): void {
    if(this.loginForm.valid){
      const enteredEmail = this.loginForm.value.email!;
      const enteredPassword = this.loginForm.value.password!;

      this.authService.login(enteredEmail, enteredPassword).subscribe({
        next: () => {
          this.router.navigate(['/']);
        },
        error: () => {
          this.invalidCredentials = true;
        }
      });
    }
  }
}
