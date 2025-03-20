import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs/internal/Observable';

@Injectable({
  providedIn: 'root'
})
export class ProductDetailsService {

  apiUrl = 'http://localhost:8081/api/product';
  
  constructor(private http: HttpClient) { }

  getProductById(productId: string): Observable<any> {
    return this.http.get<any[]>(`${this.apiUrl}/${productId}`);
  }
  
}
