import { CommonModule } from '@angular/common';
import { Component, OnInit } from '@angular/core';
import { ProductComponent } from '../product-list/product/product.component';
import { Product } from '../model/product';
import { ProductSearchService } from '../services/product-search.service';
import { ActivatedRoute } from '@angular/router';

@Component({
  selector: 'app-product-search',
  imports: [CommonModule, ProductComponent],
  templateUrl: './product-search.component.html',
  styleUrl: './product-search.component.css'
})
export class ProductSearchComponent implements OnInit {
  products: Product[] = [];
  keyword: string = "";
  
    constructor(
      private productSearchService: ProductSearchService,
      private route: ActivatedRoute
  
    ) {}
  
    ngOnInit(): void {
      this.route.queryParams.subscribe(params => {
        this.keyword = params['keyword'];
      });

      this.fetchProducts();
  
    }
  
    fetchProducts() {
      this.productSearchService.searchProduct(this.keyword).subscribe(
        (data) => {
          this.products = data;
          console.log(this.products)
        }
      )
    }
}
