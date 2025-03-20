import { Component, OnInit } from '@angular/core';
import { Product } from '../model/product';
import { ProductListService } from '../services/product-list.service';
import { CommonModule } from '@angular/common';
import { ProductComponent } from "./product/product.component";

@Component({
  selector: 'app-product-list',
  imports: [CommonModule, ProductComponent],
  templateUrl: './product-list.component.html',
  styleUrl: './product-list.component.css'
})
export class ProductListComponent implements OnInit {
  products: Product[] = [];

  constructor(
    private productListService: ProductListService

  ) {}

  ngOnInit(): void {
    this.fetchProducts();

  }

  fetchProducts() {
    this.productListService.getProducts().subscribe(
      (data) => {
        this.products = data;
        console.log(this.products)
      }
    )
  }

}
