import { Component, NgModule, OnInit } from '@angular/core';
import { FormBuilder, FormGroup, FormsModule, ReactiveFormsModule, Validators } from '@angular/forms';
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
  productForm!: FormGroup;
  selectedImage: File | null = null;

  constructor(
    private route: ActivatedRoute,
    private router: Router,
    private productService: ProductDetailsService,
    private productUpdateService: ProductUpdateService,
    private formBuilder: FormBuilder
  ) {}

  ngOnInit(): void {
    this.productId = Number(this.route.snapshot.paramMap.get('productId'));
    if (this.productId) {
      this.product = this.fetchProduct(this.productId);
    }

    this.productForm = this.formBuilder.group({
      name: ['', Validators.required],
      brand: ['', Validators.required],
      price: ['', [Validators.required, Validators.min(0)]],
      category: ['', Validators.required],
      description: ['', Validators.required],
      releaseDate: ['', Validators.required],
      available: ['', [Validators.required]],
      quantity: ['', [Validators.required, Validators.min(1)]],
      image: ['']
    });
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

  onFileChange(event: any): void {
    const file = event.target.files[0];
    if (file) {
      this.selectedImage = file;
    }
  }

  onSubmit(): void {

    const formData = new FormData();
    formData.append('name', this.productForm.get('name')?.value);
    formData.append('brand', this.productForm.get('brand')?.value);
    formData.append('price', this.productForm.get('price')?.value);
    formData.append('category', this.productForm.get('category')?.value);
    formData.append('description', this.productForm.get('description')?.value);
    formData.append('releaseDate', this.productForm.get('releaseDate')?.value);
    formData.append('available', this.productForm.get('available')?.value === 'true' ? 'true' : 'false');
    formData.append('quantity', this.productForm.get('quantity')?.value);

    if (this.selectedImage) {
      formData.append('imageFile', this.selectedImage);
    }
    
    if (this.productId) {
      this.productUpdateService.updateProduct(this.productId, formData).subscribe(
        () => {
          alert('Product updated successfully!');
          //this.router.navigate(['/products']);
        },
        (error) => {
          console.error('Error updating product', error);
        }
      );
    }
  }

  
}