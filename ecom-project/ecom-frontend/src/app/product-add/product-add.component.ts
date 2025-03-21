import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormGroup, ReactiveFormsModule, Validators } from '@angular/forms';
import { ProductAddService } from '../services/product-add.service';

@Component({
  selector: 'app-product-add',
  imports: [ReactiveFormsModule],
  templateUrl: './product-add.component.html',
  styleUrl: './product-add.component.css'
})
export class ProductAddComponent implements OnInit {
  productForm!: FormGroup;
  selectedImage: File | null = null;

  constructor(
    private formBuilder: FormBuilder,
    private productAddService: ProductAddService

  ) {}

  ngOnInit(): void {
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

  onFileChange(event: any): void {
    const file = event.target.files[0];
    if (file) {
      this.selectedImage = file;
    }
  }

  onSubmit(): void {
    if (this.productForm.invalid || !this.selectedImage) {
      return;
    }

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

    console.log(formData.values);
    this.productAddService.addProduct(formData).subscribe(
      (response) => {
        console.log('Product uploaded successfully:', response);
        alert("Product uploaded successfully!")
      }, 
      (error) => {
        console.error('Error uploading product:', error);
        alert("Error uploading product!")
      }
    );
  }

}
