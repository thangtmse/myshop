export class Category {
  constructor(public id: number, 
              public name:string, 
              public hasSubCategory: boolean,
              public parentId: number){ }
}
export class User {
  constructor(public userId: number, 
    public username:string, 
    public fullName: string,
    public email: string,
    public address: string,
    public phone:string,
    public password:string,
    public role:string){ }
}

export class Product {
  constructor(public id: number,
              public name: string,
              public images: Array<any>,
              public oldPrice: number,
              public newPrice: number,
              public discount: number,
              public ratingsCount: number,
              public ratingsValue: number,
              public description: string,
              public availibilityCount: number,
              public color: Array<string>,
              public size: Array<string>,
              public weight: number,
              public quantity: number,
              public categoryId: number){ }
}
