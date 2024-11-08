import { Component } from '@angular/core';
import { AbstractControl, FormControl, FormGroup, ReactiveFormsModule, ValidationErrors, Validators } from '@angular/forms';
import { Router, RouterModule } from '@angular/router';
import { CardModule } from 'primeng/card';
import { of } from 'rxjs';
import { AuthService } from '../../services/auth.service';
import { BasicPageComponent } from '../../ui/pages/basic-page/basic-page.component';
import { InputTextModule } from 'primeng/inputtext';
import { ButtonModule } from 'primeng/button';
import { MessagesModule } from 'primeng/messages';

function emailIsUnique(control: AbstractControl){
    //TODO: Check backend for unique email
    if(control.value !== 'test@example.com'){
      return of(null);
    }

    return of({notUnique: true})
}

function aliasIsUnique(control: AbstractControl){
  //TODO: Check backend for unique alias
  if(control.value !== 'test_user'){
    return of(null);
  }

  return of({notUnique: true})
}

@Component({
  selector: 'app-register',
  standalone: false,
  templateUrl: './register.component.html',
  styleUrls: ['./register.component.css']
})
export class RegisterComponent {

  registerForm = new FormGroup(
    {
      email : new FormControl('',{
        validators: [Validators.email, Validators.required],
        asyncValidators: [emailIsUnique]
      }),
      alias: new FormControl('',{
        validators: [Validators.required],
        asyncValidators: [aliasIsUnique]
      }),
      passwords : new FormGroup(
        {
          password: new FormControl('', {
            validators: [Validators.required, Validators.minLength(8)],
          }),
          confirmPassword: new FormControl('',{
            validators: [Validators.required, Validators.minLength(8)],
          }) 
        },
        { validators: [this.passwordsMatch] }
      )
    },
  );

  passwordsMatch(control: AbstractControl): ValidationErrors | null {
    const password = control.get('password')?.value;
    const confirmPassword = control.get('confirmPassword')?.value;

    return password === confirmPassword ? null : { passwordMismatch: true };
  }

  get emailIsInvalid(){
    return (
      this.registerForm.controls.email.touched && 
      this.registerForm.controls.email.dirty && 
      this.registerForm.controls.email.invalid
    );
  }

  get aliasIsInvalid(){
    return (
      this.registerForm.controls.alias.touched && 
      this.registerForm.controls.alias.dirty && 
      this.registerForm.controls.alias.invalid
    );
  }

  get passwordIsInvalid(){
    return (
      this.registerForm.controls.passwords.controls.password.touched && 
      this.registerForm.controls.passwords.controls.password.dirty && 
      this.registerForm.controls.passwords.controls.password.invalid
    );
  }

  get passwordConfirmIsInvalid(){
    return (
      this.registerForm.controls.passwords.controls.confirmPassword.touched && 
      this.registerForm.controls.passwords.controls.confirmPassword.dirty && 
      this.registerForm.controls.passwords.controls.confirmPassword.invalid
    );
  }

  constructor(private authService: AuthService, private router: Router) {}

  
  onSubmit(): void {
    if(this.registerForm.valid){
      const enteredEmail = this.registerForm.value.email!;
      const enteredAlias = this.registerForm.value.alias!;
      const enteredPassword = this.registerForm.controls.passwords.value.password!;
      this.authService.signup(enteredEmail, enteredAlias, enteredPassword).subscribe({
        next: () => {
          this.router.navigate(['/login']);
        },
        error: () => {
        }
      });
    }
  }
}
