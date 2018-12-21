import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { FormGroup } from '@angular/forms';
import { FormControl } from '@angular/forms';
import { Validators } from '@angular/forms';
import { PromotionService } from '../../../service/promotion.service';
import { Table } from '../../../model/table';
import { Router } from '@angular/router';
import { ToastrService } from 'ngx-toastr';
import { Observable, Subject, concat, of } from 'rxjs';
import { debounceTime, distinctUntilChanged, tap, switchMap, catchError, map } from 'rxjs/operators';
import { FoodService } from '../../../service/food.service';

@Component({
  selector: 'app-edit-table',
  templateUrl: './table.edit.component.html',
  styleUrls: ['./table.edit.component.scss']
})
export class EditTableComponent implements OnInit {
  products = [];
  sanpham: Observable<any[]>;
  sanphamInput = new Subject<string>();
  sanphamLoading = false;
  isSubmited: boolean = false;
  promotionId: number = Number.NaN;
  editorForm = new FormGroup({
    discount: new FormControl('', Validators.required),
    productId: new FormControl(''),
    discountCode: new FormControl(),
    createDate: new FormControl(),
    exprieDate: new FormControl(),
  });
  listSupplierParent: any[] = [];
  constructor(private route: ActivatedRoute,
    private promotionService: PromotionService,
    private foodService: FoodService,
    private router: Router,
    private toastr: ToastrService) {

  }
  ngOnInit(): void {

    this.promotionId = Number.parseInt(this.route.snapshot.paramMap.get('id'));
    this.promotionService.getPromotion(this.promotionId).subscribe(data => {
      this.editorForm.get('discount').setValue(data.discount);
      this.editorForm.get('productId').setValue(data.productId);
      this.editorForm.get('discountCode').setValue(data.discountCode);
    });

    this.sanpham = concat(
      of([]),
      this.sanphamInput.pipe(
        tap(() => this.sanphamLoading = true),
        switchMap(term => this.foodService.findFood({ name: term }).pipe(
          map(item => {
            return item.content;
          }),
          map((item: any[]) => {
            return item.filter(i => {
              return !this.products.includes(i.productId);
            });
          }),
          map((item: any[]) => {
            return item.filter(i => {
              return !!!i.discount;
            });
          }),
          catchError(() => of([])), // empty list on error
          tap(() => this.sanphamLoading = false)
        )),
        distinctUntilChanged(),
      )
    );
  };

  editorFormSubmit() {
    this.isSubmited = true;
    let data = this.editorForm.getRawValue();
    data.createDate = data.createDate.getTime();
    data.exprieDate = data.exprieDate.getTime();
    data.productIds = this.products;
    data.deleted = false;
    console.log(data);
    if (this.editorForm.valid) {
      if (Number.isNaN(this.promotionId)) {
        this.promotionService.createPromotion(data).subscribe(
          data => {
            this.toastr.success(' tạo thành công.');
            this.router.navigate(['/manage/table/list']);
          },
          error => {
            this.toastr.error(error.message);
          }
        )
      } else {
        this.promotionService.updatePromotion(this.promotionId, data).subscribe(
          data => {
            this.toastr.success(' cập nhật thành công');
            this.router.navigate(['/manage/table/list']);
          },
          error => {
            this.toastr.error(error.error.message);
          }
        )
      }
    }
  }
}