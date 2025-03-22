import { Component, OnInit } from '@angular/core';
import { Product } from '../model/product';
import { ActivatedRoute } from '@angular/router';
import { ProductDetailsService } from '../services/product-details.service';
import { CommonModule } from '@angular/common';
import { apiConfig } from '../api/api-config';
import { ProductDeleteService } from '../services/product-delete.service';

@Component({
  selector: 'app-product-details',
  imports: [CommonModule],
  templateUrl: './product-details.component.html',
  styleUrl: './product-details.component.css'
})
export class ProductDetailsComponent implements OnInit {
  product!: Product;
  apiBaseUrl = apiConfig.apiBaseUrl;
  
  constructor(
    private route: ActivatedRoute,
    private productDetailsService: ProductDetailsService,
    private productDeleteService: ProductDeleteService

  ) {}

  ngOnInit(): void {
    const productId = Number(this.route.snapshot.paramMap.get('productId'));
    if (productId) {
      this.fetchProductDetails(productId);
    }
    
  }

  fetchProductDetails(productId: number) {
    this.productDetailsService.getProductById(productId).subscribe(
      (data) => {
        this.product = data;
        console.log(this.product);
      }
    );
  }

  deleteProduct(productId: number) {
    this.productDeleteService.productDelete(productId).subscribe(
      (message) => {
        console.log("Product deleted successfully", message);
        alert("Product deleted successfully!");
        window.location.href="/";
      },
      (error) => {
        console.log("Failed to delete product", error);
        alert("Failed to delete product!");
      }
    )
  }

  updateProduct(productId: number) {
    window.location.href=`/product/${productId}/edit`;
  }

}
