import { Component, OnInit, OnChanges, ViewChild, TemplateRef } from '@angular/core';
import { ProductService } from '../../../service/product.service';
import { BrowserModule } from '@angular/platform-browser';
import { NgxDatatableModule } from '@swimlane/ngx-datatable';
import { Router, ActivatedRouteSnapshot, ActivatedRoute } from '@angular/router';
import { BsModalService, BsModalRef } from 'ngx-bootstrap/modal';
import { FormGroup, FormControl } from '@angular/forms';
import { ToastrService } from 'ngx-toastr';
import { Page } from '../../../model/page';
import { environment } from '../../../../environments/environment';
import { ProductCategory } from '../../../model/productCategory';
import { map } from 'rxjs/operators';

@Component({
  templateUrl: './manage.product.component.html'
}) export class ManageProductComponent implements OnInit, OnChanges {

  page = new Page();
  @ViewChild('productTable') table: any;
  @ViewChild('deleteModal') deleteModal: TemplateRef<any>;
  @ViewChild('restoreModal') restoreModal: TemplateRef<any>;
  modalRef: BsModalRef;
  rows = [];
  imagePath = environment.url + 'api/image/'
  productcategory: ProductCategory[] = []
  filterForm = new FormGroup({
    search: new FormControl(''),
    category: new FormControl('')
  });
  deleteRow: any = {};
  request: any = {}
  isDeleted = false;
  constructor(
    private route: ActivatedRoute,
    private productService: ProductService,
    private modalService: BsModalService,
    private toastr: ToastrService) { }

  ngOnInit(): void {
    if (this.route.snapshot.data.isDelete) {
      this.isDeleted = true;
    }
    this.setPage({ offset: 0 });

    this.productService.getAllCategory().pipe(
      map(content => {
        return content.filter(cat => !cat.deleted);
      })
    ).subscribe(data => {
      this.productcategory = data;
    });
  }

  setPage(pageInfo) {
    let name = this.filterForm.get('search').value;
    this.request.page = pageInfo.offset;
    this.productService.findProduct({
      name: name.replace(/ +(?= )/g, ''),
      categoryid: this.filterForm.get('category').value,
      page: this.request.page,
      deleted: this.isDeleted,
      size: 9
    }).subscribe(pageData => {
      this.page.totalElements = pageData.totalElements;
      this.page.pageNumber = pageData.number;
      this.page.size = pageData.size;
      this.rows = pageData.content;
    });

  }

  ngOnChanges() {
  }

  toggleExpandRow(row) {
    this.table.rowDetail.toggleExpandRow(row);
  }

  openModal(row: any) {
    this.deleteRow = row;
    this.modalRef = this.modalService.show(this.deleteModal, { class: 'modal-sm' });
  }
  openRestoreModal(row: any){
    this.deleteRow = row;
    this.modalRef = this.modalService.show(this.restoreModal, { class: 'modal-sm' });
  }
  decline() {
    this.modalRef.hide();
  }

  confirmRestore() {
    this.deleteRow.categoryID = this.deleteRow.category.categoryId;
    this.deleteRow.supplierId = this.deleteRow.supplier.supplierId;
    this.productService.updateProduct(this.deleteRow.productId, this.deleteRow)
      .subscribe(data => {
        this.toastr.success('Phục hồi thành công');
        this.modalRef.hide();
        this.setPage({ offset: 0 });
      },
        error => {
          this.toastr.error(error.error.message);
          this.modalRef.hide();
          this.setPage({ offset: 0 });
        }
      );
  }

  confirm() {
    this.productService.deleteProduct(this.deleteRow.productId)
      .subscribe(data => {
        this.toastr.success('xóa thành công');
        this.modalRef.hide();
        this.setPage({ offset: 0 });
      },
        error => {
          this.toastr.error(error.error.message);
          this.modalRef.hide();
          this.setPage({ offset: 0 });
        }
      );
  }

  filterFormSubmit() {
    this.setPage({ offset: 0 });
  }

}
