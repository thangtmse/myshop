import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { AppService } from '../../app.service';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { MatSnackBar } from '@angular/material';
import { emailValidator, matchingPasswords } from '../../theme/utils/app-validators';
import { MenuComponent } from '../../theme/components/menu/menu.component';

@Component({
  selector: 'app-sign-in',
  templateUrl: './sign-in.component.html',
  styleUrls: ['./sign-in.component.scss']
})
export class SignInComponent implements OnInit {
  loginForm: FormGroup;
  registerForm: FormGroup;

  constructor( public formBuilder: FormBuilder, public router:Router, public snackBar: MatSnackBar,public appService:AppService) { }

  ngOnInit() {
    this.loginForm = this.formBuilder.group({
      'username': ['', Validators.compose([Validators.required])],
      'password': ['', Validators.compose([Validators.required, Validators.minLength(6)])] 
    });

    this.registerForm = this.formBuilder.group({
      'username': ['', Validators.compose([Validators.required, Validators.minLength(3)])],
      'lastName': ['', Validators.compose([Validators.required, Validators.minLength(3)])],
      'firstName': ['', Validators.compose([Validators.required, Validators.minLength(3)])],
      'email': ['', Validators.compose([Validators.required, emailValidator])],
      'password': ['', Validators.required],
      'phone': ['', Validators.required],
      'confirmPassword': ['', Validators.required]
    },{validator: matchingPasswords('password', 'confirmPassword')});

  }

  public onLoginFormSubmit(values:Object):void {
    console.log('start login');
    if (this.loginForm.valid) {
      let user : any = {};
      user.username = this.loginForm.get(['username']).value;
      user.password = this.loginForm.get(['password']).value; 
      console.log(user);
      this.appService.login(user).subscribe(data => {
        if(data.message!= null){
          this.snackBar.open(data.message, '×', { panelClass: 'error', verticalPosition: 'top', duration: 3000 });
        } else {
          location.reload();
          this.router.navigate(['/']);
        }
      });
     // this.router.navigate(['/']);
    }
  }

  public onRegisterFormSubmit(values:Object):void {
    if (this.registerForm.valid) {
      let user : any = {};
      let check = true;
      user.username = this.registerForm.get(['username']).value;
      user.password = this.registerForm.get(['password']).value;
      user.firstName = this.registerForm.get(['firstName']).value; 
      user.lastName = this.registerForm.get(['lastName']).value;
      user.phone = this.registerForm.get(['phone']).value;
      user.email = this.registerForm.get(['email']).value;
      this.appService.register(user).subscribe(data => {
        check = false;
        if(data.error!= null){
          this.snackBar.open('Dang ky thanh con1g', '×', { panelClass: 'success', verticalPosition: 'top', duration: 3000 });
        } else {
          this.snackBar.open('Dang ky thanh cong', '×', { panelClass: 'success', verticalPosition: 'top', duration: 3000 });
        }
      });
      if(check){
        this.snackBar.open("tai khoan da ton tai", '×', { panelClass: 'error', verticalPosition: 'top', duration: 3000 });
      }
    }
  }

}
