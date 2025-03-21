import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs/internal/Observable';
import { apiConfig } from '../api/api-config';

@Injectable({
  providedIn: 'root'
})
export class ProductDeleteService {
  apiUrl: string = "";
  
  constructor(private http: HttpClient) { }

  productDelete(productId: number): Observable<any> {
    this.apiUrl = `${apiConfig.apiBaseUrl}/product/${productId}`;
    return this.http.delete(this.apiUrl);
  }
  
}
