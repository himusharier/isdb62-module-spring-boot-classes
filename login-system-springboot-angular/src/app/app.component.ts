import { Component } from '@angular/core';
import {HttpClient} from '@angular/common/http';
import {BrowserModule} from "@angular/platform-browser";

@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.css'],
  imports: [BrowserModule],
  standalone: true
})
export class AppComponent {
  title = 'Demo';
  data = {}  as any;
  constructor(private http: HttpClient) {
    http.get('resource').subscribe(data => this.data = data);
  }
}