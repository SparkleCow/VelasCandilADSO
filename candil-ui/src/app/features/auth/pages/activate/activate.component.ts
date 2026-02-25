import { Component, inject, signal } from '@angular/core';
import { CommonModule } from '@angular/common';
import { FormBuilder, Validators, ReactiveFormsModule } from '@angular/forms';
import { Router, RouterModule } from '@angular/router';
import { AuthService } from '../../../../core/services/auth.service';

import { MatCardModule } from '@angular/material/card';
import { MatFormFieldModule } from '@angular/material/form-field';
import { MatInputModule } from '@angular/material/input';
import { MatButtonModule } from '@angular/material/button';
import { MatIconModule } from '@angular/material/icon';

@Component({
  selector: 'app-activate',
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
  templateUrl: './activate.component.html',
  styleUrls: ['./activate.component.css']
})
export class ActivateComponent {

  private fb = inject(FormBuilder);
  private authService = inject(AuthService);
  private router = inject(Router);

  loading = signal(false);
  error = signal(false);

  form = this.fb.nonNullable.group({
    token: ['', [
      Validators.required,
      Validators.minLength(6),
      Validators.maxLength(6)
    ]]
  });

  submit(): void {
    if (this.form.invalid) return;

    this.loading.set(true);
    this.error.set(false);

    const { token } = this.form.getRawValue();

    this.authService.activate(token).subscribe({
      next: () => {
        this.loading.set(false);
        alert('Cuenta activada correctamente');
        this.router.navigate(['/login']);
      },
      error: () => {
        this.loading.set(false);
        this.error.set(true);
      }
    });
  }
}