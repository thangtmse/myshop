import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { MatSnackBar } from '@angular/material';
import { AppService } from '../../../app.service';
import { emailValidator, matchingPasswords } from '../../../theme/utils/app-validators';

@Component({
  selector: 'app-information',
  templateUrl: './information.component.html',
  styleUrls: ['./information.component.scss']
})
export class InformationComponent implements OnInit {
  infoForm: FormGroup;
  passwordForm: FormGroup;
  constructor(public formBuilder: FormBuilder, public snackBar: MatSnackBar, public appService:AppService) { }

 
  ngOnInit() {
    this.infoForm = this.formBuilder.group({
      'fullName': ['', Validators.compose([Validators.required, Validators.minLength(3)])],
      'phone': ['', Validators.compose([Validators.required, Validators.minLength(3)])],
      'email': ['', Validators.compose([Validators.required, emailValidator])]
    });
    let user : any = JSON.parse(localStorage.getItem('userInfo'));
    this.infoForm.controls['fullName'].setValue(user.fullName);
    this.infoForm.controls['email'].setValue(user.email);
    this.infoForm.controls['phone'].setValue(user.phone);

    this.passwordForm = this.formBuilder.group({
      'currentPassword': ['', Validators.required],
      'newPassword': ['', Validators.required],
      'confirmNewPassword': ['', Validators.required]
    },{validator: matchingPasswords('newPassword', 'confirmNewPassword')});

  }

  public onInfoFormSubmit(values:Object):void {
    if (this.infoForm.valid) {
      let user : any = {};
      
      user = JSON.parse(localStorage.getItem('userInfo'));
      user.fullName = this.infoForm.get(['fullName']).value; 
      user.phone = this.infoForm.get(['phone']).value;
      user.email = this.infoForm.get(['email']).value;
      this.appService.register(user).subscribe(data => {
        if(data.error!= null){
          this.snackBar.open('Cập nhật không thành công', '×', { panelClass: 'error', verticalPosition: 'top', duration: 3000 });
        } else {
          this.snackBar.open('Cập nhật thành công', '×', { panelClass: 'success', verticalPosition: 'top', duration: 3000 });
          localStorage.setItem('userInfo', JSON.stringify(data));
        }
      });
     
     }
  }

  public onPasswordFormSubmit(values:Object):void {
    if (this.passwordForm.valid) {
      let user : any = JSON.parse(localStorage.getItem('userInfo'));
      let changePassword:any = {
        username: user.username,
        curPassword:this.passwordForm.get(['currentPassword']).value,
        newPassword:this.passwordForm.get(['newPassword']).value
      }
      this.appService.changePassword(changePassword).subscribe(data => {
        if(data.isSuccess){
          this.snackBar.open(data.message, '×', { panelClass: 'success', verticalPosition: 'top', duration: 3000 });
        } else {
          this.snackBar.open(data.message, '×', { panelClass: 'error', verticalPosition: 'top', duration: 3000 });
        }
      });
     
    }
  }

}
