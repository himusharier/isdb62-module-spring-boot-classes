import { Injectable } from '@angular/core';
import { apiConfig } from '../api/api-config';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs/internal/Observable';

@Injectable({
  providedIn: 'root'
})
export class ProductSearchService {

  apiUrl = `${apiConfig.apiBaseUrl}/product/search`;
    
    constructor(private http: HttpClient) { }
  
    searchProduct(keyword: string): Observable<any> {
      return this.http.get<any[]>(`${this.apiUrl}?keyword=${keyword}`);
    }
    
}
