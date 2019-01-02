import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { emailValidator } from '../../theme/utils/app-validators';
import { AppService } from '../../app.service';

@Component({
  selector: 'app-contact',
  templateUrl: './contact.component.html',
  styleUrls: ['./contact.component.scss']
})
export class ContactComponent implements OnInit {
  contactForm: FormGroup;
  userInfo: any;

  constructor(public formBuilder: FormBuilder, private appservice: AppService) { }

  ngOnInit() {
    this.userInfo = JSON.parse(localStorage.getItem('userInfo'));
    this.contactForm = this.formBuilder.group({
      userId: this.userInfo.userId,
      name: [this.userInfo.fullName, Validators.required],
      email: [this.userInfo.email, Validators.compose([Validators.required, emailValidator])],
      phone: [this.userInfo.phone, Validators.required],
      content: ['', Validators.required]
    });
  }

  public onContactFormSubmit(values: Object): void {
    if (this.contactForm.valid) {
      this.appservice.createReport(this.contactForm.getRawValue()).subscribe(data=>{
      })
    }
  }

}
