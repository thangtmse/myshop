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
    if (this.loginForm.valid) {
      let user : any = {};
      user.username = this.loginForm.get(['username']).value;
      user.password = this.loginForm.get(['password']).value; 
      this.appService.login(user).subscribe(data => {
        if(!data || data.message!= null){
          this.snackBar.open('Sai mật khẩu hoặc tài khoản', '×', { panelClass: 'error', verticalPosition: 'top', duration: 3000 });
        } else {
          location.reload();
          this.router.navigate(['/']);
        }
      },error=> {
        this.snackBar.open('Sai mật khẩu hoặc tài khoản', '×', { panelClass: 'error', verticalPosition: 'top', duration: 3000 });

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
      user.fullName = this.registerForm.get(['firstName']).value; 
      user.fullName +=' '+ this.registerForm.get(['lastName']).value;
      user.phone = this.registerForm.get(['phone']).value;
      user.email = this.registerForm.get(['email']).value;
      user.address = '';
      user.role = 'CUSTOMER';
      this.appService.register(user).subscribe(data => {
        check = false;
        if(data.error!= null){
          this.snackBar.open('Đăng ký thành công', '×', { panelClass: 'success', verticalPosition: 'top', duration: 3000 });
        } else {
          this.snackBar.open('Đăng ký thành công', '×', { panelClass: 'success', verticalPosition: 'top', duration: 3000 });
        }
        this.loginForm.get('username').setValue(user.username);
        this.loginForm.get('password').setValue(user.password);
        this.onLoginFormSubmit({});
      });
      if(check){
        this.snackBar.open("Tài khoản đã tồn tại", '×', { panelClass: 'error', verticalPosition: 'top', duration: 3000 });
      }
    }
  }

}
