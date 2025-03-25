import { Component } from '@angular/core';
import { FormsModule } from '@angular/forms';
import { Router } from '@angular/router';

@Component({
  selector: 'app-header',
  imports: [FormsModule],
  templateUrl: './header.component.html',
  styleUrl: './header.component.css'
})
export class HeaderComponent {
  keyword: string = "";

  constructor(
    private router: Router
    
  ) {}

  onSearch() {
    if (this.keyword) {
      this.router.navigate(['/products/search'], { queryParams: { keyword: this.keyword } });
    }
  }

}
