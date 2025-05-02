import { Component } from '@angular/core';
import { FormsModule } from '@angular/forms';
import { AuthService } from '../../core/auth.service';
import { Router } from '@angular/router';

@Component({
  selector: 'app-register',
  imports: [FormsModule],
  templateUrl: './register.component.html',
  styleUrl: './register.component.scss'
})
export class RegisterComponent {
  firstName = '';
  lastName = '';
  phoneNumber = '';
  email = '';
  password = '';
  role = '';

  constructor(private auth: AuthService, private router: Router) { }

  onRegister() {
    this.auth.register({firstName: this.firstName, lastName: this.lastName, phoneNumber: this.phoneNumber, email: this.email, password: this.password, role: this.role})
      .subscribe({
        next: (res) => {
          // this.auth.setToken(res.access_token);
          console.log(res);
          alert("Registration Successful!");
          this.router.navigate(['/login']);
        },
        error: (error) => {
          alert('Can not register!')
          console.log(error)
        }
      });
  }
}
