import { Component, OnInit, ViewChild, TemplateRef } from '@angular/core';
import { Page } from '../../../model/page';
import { BsModalService, BsModalRef } from 'ngx-bootstrap/modal';
import { FormGroup, FormControl, Validators } from '@angular/forms';
import { ToastrService } from 'ngx-toastr';
import { ActivatedRoute, Router } from '@angular/router';
import { ProductService } from '../../../service/product.service';
import { ProductCategory } from '../../../model/productCategory';
import { CategoryService } from '../../../service/category.service';
import { from } from 'rxjs';
import { ProductSupplier } from '../../../model/productSupplier';
import { environment } from '../../../../environments/environment';
import { map } from 'rxjs/operators';
var self;

@Component({
  templateUrl: 'product.editor.component.html'
})
export class ProductEditorComponent implements OnInit {
  productsupplier: ProductSupplier[] = []
  productcategory: ProductCategory[] = []
  category: any;
  supplier: any;
  isSubmited: boolean = false;
  proID: number = Number.NaN;
  data: any = null;
  img: any[] = [];
  editorForm = new FormGroup({
    productId: new FormControl('', Validators.required),
    productName: new FormControl('', Validators.required),
    priceIn: new FormControl('', Validators.required),
    priceOut: new FormControl('', Validators.required),
    quantity: new FormControl('', Validators.required),
    description: new FormControl(''),
    image: new FormControl(''),
    category: new FormControl(''),
    supplier: new FormControl('')
  });

  constructor(private route: ActivatedRoute,
    private categoryService: CategoryService,
    private productService: ProductService,
    private router: Router,
    private toastr: ToastrService) {

  }

  ngOnInit(): void {
    self = this;
    this.proID = Number.parseInt(this.route.snapshot.paramMap.get('id'));
    this.productService.getAllSupplier().subscribe(data => {
      this.productsupplier = data;
      console.log(this.productsupplier);
    })
    this.productService.getAllCategory().pipe(
      map(content=>{
        return content.filter(cat=>!cat.deleted);
      })
    ).subscribe(data => {
      this.productcategory = data;
      console.log("aaaaaaaaaaaa", this.productcategory);

    });
    if (!Number.isNaN(this.proID)) {
      this.productService.getOneProduct(this.proID).subscribe(data => {
        console.log(data);
        this.editorForm.get('productId').setValue(data.productId);
        this.editorForm.get('productName').setValue(data.productName);
        this.editorForm.get('priceIn').setValue(data.priceIn);
        this.editorForm.get('description').setValue(data.description);
        this.editorForm.get('priceOut').setValue(data.priceOut);
        this.editorForm.get('quantity').setValue(data.quantity);
        let cat = this.productcategory.filter((cat: any) => cat.categoryId == data.category.categoryId)
        this.category = cat.length > 0 ? cat[0] : null;
        this.editorForm.get('category').setValue(this.category.categoryId);
        let sup = this.productsupplier.filter((sup: any) => sup.supplierId == data.supplier.supplierId)
        this.supplier = sup.length > 0 ? sup[0] : null;
        this.editorForm.get('supplier').setValue(this.supplier.supplierId);
        if (data.images != null)
          this.img = data.images.map(data=>{
            data.imageUrl = environment.url+'api/image/'+data.imageId;
            return data;
          });
        else
          this.img = [];
      });
    }
  }
  removeImage(i){
    this.img = this.img.filter(im=>im!=i);
  }

  editorFormSubmit() {
    this.isSubmited = true;
    this.data = {
      productName: this.editorForm.get('productName').value.trim(),
      priceIn: this.editorForm.get('priceIn').value,
      priceOut: this.editorForm.get('priceOut').value,
      quantity: this.editorForm.get('quantity').value,
      description: this.editorForm.get('description').value,
      //status: this.editorForm.get('status').value,
      images: this.img,
      categoryID: this.editorForm.get('category').value,
      supplierId: this.editorForm.get('supplier').value,


    }
    if (!this.validateByRegex(this.data.name, '[^]+')) {
      this.editorForm.get('productName').setErrors({ message: 'Name is invalid' })
    }
    if (!this.validateByRegex(this.data.priceIn, '^[0-9 ]*$')) {
      this.editorForm.get('priceIn').setErrors({ message: 'Price is invalid' })
    }
    if (!this.validateByRegex(this.data.priceOut, '^[0-9 ]*$')) {
      this.editorForm.get('priceOut').setErrors({ message: 'Price is invalid' })
    }
    // if (this.img.length > 25000) {
    //   console.log(this.img.length);
    //   this.editorForm.get('image').setErrors({ message: 'Image too large' })
    // }
    if (this.img.length < 1) {
      console.log(this.img.length);
      this.editorForm.get('image').setErrors({ message: 'Trường này không được bỏ trống' })
    }
    if (this.editorForm.valid) {
      if (Number.isNaN(this.proID)) {

        this.productService.createNewProduct(this.data).subscribe(
          data => {
            this.toastr.success('thêm mới thành công');
            this.router.navigate(['/manage/product/list']);
          },
          error => {
            this.toastr.error(error.error.message);
          }
        )
      } else {
        this.data.id = this.proID;
        console.log(this.data);
        this.productService.updateProduct(this.proID, this.data).subscribe(
          data => {
            this.toastr.success('cập nhật thành công');
            this.router.navigate(['/manage/product/list']);
          },
          error => {
            this.toastr.error(error.error.message);
          }
        )
      }
    }
  }

  validateByRegex(string: string, patterns: string) {
    let pattern = new RegExp(patterns);
    return pattern.test(string);
  }

  readUrl(event: any) {
    let imageName = "";
    if (event.srcElement) {
      for (var i = 0; i < event.srcElement.files.length; i++) {
        let file = event.srcElement.files[i];
        imageName = file.name.toUpperCase();
        if (!imageName.endsWith('JPG') && !imageName.endsWith('PNG')) {
          this.toastr.error("Chỉ có thể upload file ảnh (jpg hoặc png)");
          return;
        }
        var reader = new FileReader();
        reader.onload = (event: any) => {
          this.img.push({ imageUrl: event.target.result });
        }
        reader.readAsDataURL(file);
      }
    }

  }
}

