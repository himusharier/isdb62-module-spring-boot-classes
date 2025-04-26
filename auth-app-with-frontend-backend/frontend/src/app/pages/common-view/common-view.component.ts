import { NgIf } from '@angular/common';
import { Component } from '@angular/core';
import { RouterOutlet } from '@angular/router';
import { AuthService } from '../../core/auth.service';

@Component({
  selector: 'app-common-view',
  imports: [
    RouterOutlet,
    NgIf
  ],
  templateUrl: './common-view.component.html',
  styleUrl: './common-view.component.scss'
})
export class CommonViewComponent {
  role = '';

  constructor(private auth: AuthService) {
    this.role = auth.getUserRole();
    console.log(`Role in common view: ${this.role}`);
  }
}
