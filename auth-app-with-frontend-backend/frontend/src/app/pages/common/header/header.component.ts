import { Component } from '@angular/core';
import { AuthService } from '../../../core/auth.service';
import { RouterLink } from '@angular/router';

@Component({
  selector: 'app-header',
  imports: [RouterLink],
  templateUrl: './header.component.html',
  styleUrl: './header.component.scss'
})
export class HeaderComponent {
  constructor(private auth: AuthService) { }

  logout() {
    this.auth.logout();
  }
}
