import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { FormGroup } from '@angular/forms';
import { FormControl } from '@angular/forms';
import { Validators } from '@angular/forms';
import { PromotionService } from '../../../service/promotion.service';
import { Table } from '../../../model/table';
import { Router } from '@angular/router';
import { ToastrService } from 'ngx-toastr';

@Component({
  selector: 'app-edit-table',
  templateUrl: './table.edit.component.html',
  styleUrls: ['./table.edit.component.scss']
})
export class EditTableComponent implements OnInit {

  isSubmited: boolean = false;
  promotionId: number = Number.NaN;
  editorForm = new FormGroup({
    discount: new FormControl('', Validators.required),
    productId: new FormControl(''),
    discountCode: new FormControl()
  });
  listSupplierParent: any[] = [];
  constructor(private route: ActivatedRoute,
    private promotionService: PromotionService,
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
  };

  editorFormSubmit() {
    this.isSubmited = true;
    let data = this.editorForm.getRawValue();
    data.deleted = false;
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