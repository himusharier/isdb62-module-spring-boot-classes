import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs/internal/Observable';
import { apiConfig } from '../api/api-config';

@Injectable({
  providedIn: 'root'
})
export class ProductListService {

  apiUrl = `${apiConfig.apiBaseUrl}/products`;
  
  constructor(private http: HttpClient) { }

  getProducts(): Observable<any> {
    return this.http.get<any[]>(this.apiUrl);
  }
  
}
