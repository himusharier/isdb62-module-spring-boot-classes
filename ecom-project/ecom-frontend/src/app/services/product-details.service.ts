import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs/internal/Observable';
import { apiConfig } from '../api/api-config';

@Injectable({
  providedIn: 'root'
})
export class ProductDetailsService {

  apiUrl = `${apiConfig.apiBaseUrl}/product`;
  
  constructor(private http: HttpClient) { }

  getProductById(productId: number): Observable<any> {
    return this.http.get<any[]>(`${this.apiUrl}/${productId}`);
  }
  
}
