export class ProductCategory {
    id: number;
    name: string;
    description: string;
    image: string;
    parent: {
        parentId:number;
        parentName:string;
    }
}