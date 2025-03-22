import { Component, NgModule, OnInit } from '@angular/core';
import { FormsModule, ReactiveFormsModule } from '@angular/forms';
import { ActivatedRoute, Router } from '@angular/router';
import { ProductDetailsService } from '../services/product-details.service';
import { ProductUpdateService } from '../services/product-update.service';
import { CommonModule } from '@angular/common';
import { Product } from '../model/product';
import { apiConfig } from '../api/api-config';

@Component({
  selector: 'app-product-update',
  imports: [ReactiveFormsModule, CommonModule, FormsModule],
  templateUrl: './product-update.component.html',
  styleUrl: './product-update.component.css'
})
export class ProductUpdateComponent implements OnInit {
  productId: number | null = null;
  product: Product = {
    id: 0,
    name: '',
    brand: '',
    price: 0,
    category: '',
    description: '',
    releaseDate: new Date(),
    available: false,
    quantity: 0,
    image: ''
  };
  apiBaseUrl = apiConfig.apiBaseUrl;

  constructor(
    private route: ActivatedRoute,
    private router: Router,
    private productService: ProductDetailsService,
    private productUpdateService: ProductUpdateService
  ) {}

  ngOnInit(): void {
    this.productId = Number(this.route.snapshot.paramMap.get('productId'));
    if (this.productId) {
      this.product = this.fetchProduct(this.productId);
    }
  }

  fetchProduct(id: number): Product {
    this.productService.getProductById(id).subscribe(
      (product) => {
        this.product = {
          ...product        
        };
      },
      (error) => {
        console.error('Error fetching product', error);
      }
    );
    return this.product;
  }

  onSubmit(): void {
    if (this.productId) {
      this.productUpdateService.updateProduct(this.productId, this.product).subscribe(
        () => {
          alert('Product updated successfully!');
          this.router.navigate(['/products']);
        },
        (error) => {
          console.error('Error updating product', error);
        }
      );
    }
  }

  onFileChange(event: any): void {
    const file = event.target.files[0];
    if (file) {
      this.product.image = file;
    }
  }
}