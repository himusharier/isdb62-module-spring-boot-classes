import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs/internal/Observable';

@Injectable({
  providedIn: 'root'
})
export class ProductListService {

  apiUrl = 'http://localhost:8081/api/products';
  
  constructor(private http: HttpClient) { }

  getProducts(): Observable<any> {
    return this.http.get<any[]>(this.apiUrl);
  }
  
}
