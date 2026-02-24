import { Component, inject, signal } from '@angular/core';
import { CommonModule } from '@angular/common';
import { FormBuilder, Validators, ReactiveFormsModule } from '@angular/forms';
import { Router, RouterModule } from '@angular/router';
import { AuthService } from '../../../../core/services/auth.service';

// Angular Material
import { MatCardModule } from '@angular/material/card';
import { MatFormFieldModule } from '@angular/material/form-field';
import { MatInputModule } from '@angular/material/input';
import { MatButtonModule } from '@angular/material/button';
import { MatIconModule } from '@angular/material/icon';

@Component({
  selector: 'app-register',
  standalone: true,
  imports: [
    CommonModule,
    ReactiveFormsModule,
    RouterModule,
    MatCardModule,
    MatFormFieldModule,
    MatInputModule,
    MatButtonModule,
    MatIconModule
  ],
  templateUrl: './register.component.html',
  styleUrls: ['./register.component.css']
})
export class RegisterComponent {

  private readonly fb = inject(FormBuilder);
  private readonly authService = inject(AuthService);
  private readonly router = inject(Router);

  hidePassword = signal(true);

  form = this.fb.nonNullable.group({
    firstName: this.fb.nonNullable.control('', [
      Validators.required,
      Validators.maxLength(100)
    ]),
    lastName: this.fb.nonNullable.control('', [
      Validators.required,
      Validators.maxLength(100)
    ]),
    username: this.fb.nonNullable.control('', [
      Validators.required,
      Validators.minLength(4)
    ]),
    email: this.fb.nonNullable.control('', [
      Validators.required,
      Validators.email
    ]),
    password: this.fb.nonNullable.control('', [
      Validators.required,
      Validators.minLength(8)
    ]),
  });

  submit(): void {
    if (this.form.invalid) return;

    this.authService.register(this.form.getRawValue())
      .subscribe({
        next: () => {
          alert('Revisa tu correo para activar tu cuenta');
          this.router.navigate(['/login']);
        },
        error: (err) => {
          console.error(err);
          alert('Error al registrar usuario');
        }
      });
  }
}