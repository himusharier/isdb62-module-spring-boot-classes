import { Component, OnInit } from '@angular/core';
import { Product } from '../model/product';
import { ActivatedRoute } from '@angular/router';
import { ProductDetailsService } from '../services/product-details.service';
import { CommonModule } from '@angular/common';

@Component({
  selector: 'app-product-details',
  imports: [CommonModule],
  templateUrl: './product-details.component.html',
  styleUrl: './product-details.component.css'
})
export class ProductDetailsComponent implements OnInit {
  product!: Product;
  
  constructor(
    private route: ActivatedRoute,
    private productDetailsService: ProductDetailsService

  ) {}

  ngOnInit(): void {
    const productId = this.route.snapshot.paramMap.get('productId');
    if (productId) {
      this.fetchProductDetails(productId);
    }
    
  }

  fetchProductDetails(productId: string) {
    this.productDetailsService.getProductById(productId).subscribe(
      (data) => {
        this.product = data;
        console.log(this.product);
      }
    );
  }

}
