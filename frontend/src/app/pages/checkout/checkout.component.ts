import { Component, OnInit, ViewChild } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { MatStepper } from '@angular/material';
import { Data, AppService } from '../../app.service';
import { Router } from '@angular/router';

@Component({
  selector: 'app-checkout',
  templateUrl: './checkout.component.html',
  styleUrls: ['./checkout.component.scss']
})
export class CheckoutComponent implements OnInit {
  @ViewChild('horizontalStepper') horizontalStepper: MatStepper;
  @ViewChild('verticalStepper') verticalStepper: MatStepper;
  billingForm: FormGroup;
  deliveryForm: FormGroup;
  //paymentForm: FormGroup;
  countries = [];
  months = [];
  years = [];
  deliveryMethods = [];
  grandTotal = 0;

  constructor(public appService:AppService, public formBuilder: FormBuilder,public router:Router) { }

  ngOnInit() {

    this.appService.Data.cartList.forEach(product=>{
      this.grandTotal += product.newPrice * product.quantity;
    });
    this.countries = this.appService.getCountries();
    this.months = this.appService.getMonths();
    this.years = this.appService.getYears();
    this.deliveryMethods = this.appService.getDeliveryMethods();
    this.billingForm = this.formBuilder.group({
      fullName: ['', Validators.required],
    
      company: '',
      email: ['', Validators.required],
      phone: ['', Validators.required],
      // country: ['', Validators.required],
      city: ['', Validators.required],
      state: '',
      // zip: ['', Validators.required],
      address: ['', Validators.required]
    });
    this.deliveryForm = this.formBuilder.group({
      deliveryMethod: [this.deliveryMethods[0], Validators.required]
    });
    let user: any;
    user = JSON.parse(localStorage.getItem('userInfo'));
    console.log(user);
    if(user == null) {
      this.router.navigate(['/sign-in']);
    }
    this.billingForm.controls['fullName'].setValue(user.fullName);
    this.billingForm.controls['email'].setValue(user.email);
    this.billingForm.controls['phone'].setValue(user.phone);
    this.billingForm.controls['address'].setValue(user.address);
    // this.paymentForm = this.formBuilder.group({
    //   cardHolderName: ['', Validators.required],
    //   cardNumber: ['', Validators.required],
    //   expiredMonth: ['', Validators.required],
    //   expiredYear: ['', Validators.required],
    //   cvv: ['', Validators.required]
    // });
  }

  public placeOrder(){
    console.log('start build order');
    let order :any =  this.buildOrder();
    console.log('start checkout');
    this.appService.checkout(order).subscribe(data=>{
      console.log(data);
    });
    this.horizontalStepper._steps.forEach(step => step.editable = false);
    this.verticalStepper._steps.forEach(step => step.editable = false);
    this.appService.Data.cartList.length = 0;
  }

  public buildOrder(){
    let user: any;
    user = JSON.parse(localStorage.getItem('userInfo'));
    
    let order: any={
        address:  this.billingForm.get(['address']).value,
        city :  this.billingForm.get(['city']).value,
        //district:  this.billingForm.get(['district']).value,
        orderDetails: [],
        phone: this.billingForm.get(['phone']).value,
        userId: user.userId,
        deliveryMethod: JSON.stringify(this.deliveryForm.get(['deliveryMethod']).value)
    };
    console.log(order);
    this.appService.Data.cartList.forEach(product=>{
      let orderDetail : any ={
          productId: product.id,
          quantity: product.quantity
        }
        console.log(product);
        order.orderDetails.push(orderDetail);
    })
    
    return order;
  }

}
