import { SupplierService } from '../../../service/supplier.service';
import { Component, OnInit, ViewChild, TemplateRef } from '@angular/core';
import { Page } from '../../../model/page';
import { BsModalService, BsModalRef } from 'ngx-bootstrap/modal';
import { FormGroup, FormControl, Validators } from '@angular/forms';
import { ToastrService } from 'ngx-toastr';
import { ActivatedRoute, Router } from '@angular/router';

@Component({
  templateUrl: 'supplier.editor.component.html'
})
export class SupplierEditorComponent implements OnInit {
  isSubmited: boolean = false;
  supplierId: number = Number.NaN;
  editorForm = new FormGroup({
    supplierAddress: new FormControl('', Validators.required),
    supplierName: new FormControl(''),
    supplierPhone: new FormControl()
  });
  listSupplierParent: any[] = [];
  constructor(private route: ActivatedRoute,
    private supplierService: SupplierService,
    private router: Router,
    private toastr: ToastrService) {

  }
  ngOnInit(): void {

    this.supplierId = Number.parseInt(this.route.snapshot.paramMap.get('id'));
    this.supplierService.getSupplier(this.supplierId).subscribe(data => {
      this.editorForm.get('supplierName').setValue(data.supplierName);
      this.editorForm.get('supplierAddress').setValue(data.supplierAddress);
      this.editorForm.get('supplierPhone').setValue(data.supplierPhone);
    });
  };

  editorFormSubmit() {
    this.isSubmited = true;
    let data = this.editorForm.getRawValue();
    data.deleted = false;
    if (this.editorForm.valid) {
      if (Number.isNaN(this.supplierId)) {
        this.supplierService.createSuppliers(data).subscribe(
          data => {
            this.toastr.success('Nhà cung cấp được tạo thành công.');
            this.router.navigate(['/manage/supplier/list']);
          },
          error => {
            this.toastr.error(error.message);
          }
        )
      } else {
        this.supplierService.updateSuppliers(this.supplierId, data).subscribe(
          data => {
            this.toastr.success('Nhà cung cấp được cập nhật thành công');
            this.router.navigate(['/manage/supplier/list']);
          },
          error => {
            this.toastr.error(error.error.message);
          }
        )
      }
    }
  }

}
