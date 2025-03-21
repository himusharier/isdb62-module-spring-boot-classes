import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs/internal/Observable';
import { Product } from '../model/product';
import { apiConfig } from '../api/api-config';

@Injectable({
  providedIn: 'root'
})
export class ProductAddService {

  apiUrl = `${apiConfig.apiBaseUrl}/product`;
  
  constructor(private http: HttpClient) { }

  addProduct(formData: FormData): Observable<any> {
    // const headers = new HttpHeaders({
    //   'Content-Type': 'multipart/form-data;'
    // });
    return this.http.post(this.apiUrl, formData); //, { headers }
  }
  
}
