import { CategoryService } from './../../../service/category.service';
import { Component, OnInit, ViewChild, TemplateRef } from '@angular/core';
import { Page } from '../../../model/page';
import { BsModalService, BsModalRef } from 'ngx-bootstrap/modal';
import { FormGroup, FormControl, Validators } from '@angular/forms';
import { ToastrService } from 'ngx-toastr';
import { ActivatedRoute, Router } from '@angular/router';
import { environment } from '../../../../environments/environment';

@Component({
  templateUrl: 'category.editor.component.html'
})
export class CategoryEditorComponent implements OnInit {
  isSubmited: boolean = false;
  categoryId: number = Number.NaN;
  editorForm = new FormGroup({
    categoryname: new FormControl('', Validators.required),
    description: new FormControl(''),
    image: new FormControl(),
    parent: new FormControl("0"),
  });
  img=[];
  listCategoryParent: any[] = [];
  constructor(private route: ActivatedRoute,
    private categoryService: CategoryService,
    private router: Router,
    private toastr: ToastrService) {

  }
  ngOnInit(): void {

    this.categoryId = Number.parseInt(this.route.snapshot.paramMap.get('id'));
    if (!Number.isNaN(this.categoryId)) {
      this.categoryService.getCategory(this.categoryId).subscribe(data => {
        // this.editorForm.get('categoryname').disable();
        console.log(data);
        this.editorForm.get('categoryname').setValue(data.categoryName);
        this.editorForm.get('description').setValue(data.description);
        this.editorForm.get('parent').setValue(data.categoryParentId);
        this.img.push({ imageUrl: environment.url+'api/category/'+data.categoryId+'/image' });
        this.getListCategoryParent(this.categoryId);
      });
    } else {
      this.getListCategoryParent(-1);
    }
  };

  getListCategoryParent(categoryId) {
    this.categoryService.getListCategoryParent(categoryId).subscribe(
      res => {
      this.listCategoryParent = res;
      }
    )
  }

  editorFormSubmit() {
    console.log("start editorFormSubmit");
    this.isSubmited = true;
    let data = {
      categoryName: this.editorForm.get('categoryname').value.trim(),
      description: this.editorForm.get('description').value,
      categoryParentId: this.editorForm.get('parent').value,
      imageurl: this.img.length>0?this.img[0].imageUrl:'',
      deleted: 0
    }
    console.log("catagory: ", data);
    console.log("validate value");
    if (this.img.length < 1) {
      console.log(this.img.length);
      this.editorForm.get('image').setErrors({ message: 'Trường này không được bỏ trống' })
    }
    if (this.editorForm.valid) {
      if (Number.isNaN(this.categoryId)) {
        console.log("start call api");
        this.categoryService.createCategorys(data).subscribe(
          data => {
            this.toastr.success('Danh mục được tạo thành công.');
            this.router.navigate(['/manage/category/list']);
          },
          error => {
            this.toastr.error(error.message);
          }
        )
      } else {
        this.categoryService.updateCategorys(this.categoryId, data).subscribe(
          data => {
            this.toastr.success('Danh mục được cập nhật thành công');
            this.router.navigate(['/manage/category/list']);
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
          this.img = [];
          this.img.push({ imageUrl: event.target.result });
        }
        reader.readAsDataURL(file);
      }
    }
  }
}
