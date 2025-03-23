import { Injectable } from '@angular/core';
import { Product } from '../model/product';
import { Observable } from 'rxjs';
import { apiConfig } from '../api/api-config';
import { HttpClient } from '@angular/common/http';

@Injectable({
  providedIn: 'root'
})
export class ProductUpdateService {

  apiUrl = `${apiConfig.apiBaseUrl}/product`;
  
  constructor(private http: HttpClient) { }

  updateProduct(productId: number, product: FormData): Observable<any> {
    
    return this.http.put(`${this.apiUrl}/${productId}`, product);
  }

}
