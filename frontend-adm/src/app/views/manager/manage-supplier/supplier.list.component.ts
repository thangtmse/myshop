import { Component, OnInit, ViewChild, TemplateRef } from '@angular/core';
import { Page } from '../../../model/page';
import { BsModalService, BsModalRef } from 'ngx-bootstrap/modal';
import { SupplierService } from '../../../service/supplier.service';
import { FormGroup, FormControl } from '@angular/forms';
import { ToastrService } from 'ngx-toastr';

@Component({
  templateUrl: 'supplier.list.component.html'
})
export class SupplierListComponent implements OnInit {
  page = new Page();
  @ViewChild('supplierTable') table: any;
  @ViewChild('deleteModal') deleteModal: TemplateRef<any>;
  modalRef: BsModalRef;
  rows = [];
  filterForm = new FormGroup({
    search: new FormControl(''),
  });
  deleteRow: any = {};
  request: any = {};
  constructor(private modalService: BsModalService, private supplierService: SupplierService, private toastr: ToastrService) { 
  }
  ngOnInit(): void {
    console.log(1278612789361298763)
    this.setPage({ offset: 0 });
  }

  setPage(pageInfo) {
    this.request.page = pageInfo.offset;
    this.supplierService.getSuppliers(this.request).subscribe(pagedData => {
      this.page.totalElements = pagedData.totalElements;
      this.page.pageNumber = pagedData.number;
      this.page.size = pagedData.size;
      this.rows = pagedData.content;
      if(pagedData.number >= pagedData.totalPages) {
        this.setPage({ offset: 0 });
      }
    });
  }

  toggleExpandRow(row) {
    this.table.rowDetail.toggleExpandRow(row);
  }
  openModal(row: any) {
    this.deleteRow = row;
    this.modalRef = this.modalService.show(this.deleteModal, { class: 'modal-sm' });
  }

  filterFormSubmit() {
    this.request.name = this.filterForm.get('search').value;
    this.setPage({ offset: 0 });
  }

  decline() {
    this.modalRef.hide();
  }

  confirm() {
    this.supplierService.deleteSuppliers(this.deleteRow.supplierId)
      .subscribe(data => {
        this.toastr.success('Danh mục được xóa thành công.');
        this.modalRef.hide();
        this.setPage({ offset: this.request.page });
      },
        error => {
          this.toastr.error(error.error.message);
          this.modalRef.hide();
          this.setPage({ offset: this.request.page });
        }
      );
  }

  getHeight(row: any, index: number): number {
    return 150;
  }
}
