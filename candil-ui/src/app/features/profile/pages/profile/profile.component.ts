import { CommonModule } from '@angular/common';
import { Component, computed, inject, signal } from '@angular/core';
import { FormBuilder, ReactiveFormsModule, Validators } from '@angular/forms';
import { MatButtonModule } from '@angular/material/button';
import { MatCardModule } from '@angular/material/card';
import { MatFormFieldModule } from '@angular/material/form-field';
import { MatIconModule } from '@angular/material/icon';
import { MatInputModule } from '@angular/material/input';

interface PurchaseHistoryItem {
  id: number;
  candleName: string;
  price: number;
  quantity: number;
  purchasedAt: string;
}

@Component({
  selector: 'app-profile',
  standalone: true,
  imports: [
    CommonModule,
    ReactiveFormsModule,
    MatCardModule,
    MatFormFieldModule,
    MatInputModule,
    MatButtonModule,
    MatIconModule
  ],
  templateUrl: './profile.component.html',
  styleUrl: './profile.component.css'
})
export class ProfileComponent {
  private readonly fb = inject(FormBuilder);

  readonly isEditing = signal(false);

  readonly profileForm = this.fb.nonNullable.group({
    firstName: this.fb.nonNullable.control('Mariana', [Validators.required, Validators.maxLength(80)]),
    lastName: this.fb.nonNullable.control('Gonzalez', [Validators.required, Validators.maxLength(80)]),
    username: this.fb.nonNullable.control('mariana.velas', [Validators.required, Validators.maxLength(80)]),
    email: this.fb.nonNullable.control('mariana@candil.com', [Validators.required, Validators.email]),
    imageUrl: this.fb.nonNullable.control('https://images.unsplash.com/photo-1494790108377-be9c29b29330?auto=format&fit=crop&w=500&q=80', [Validators.required])
  });

  private initialProfileSnapshot = this.profileForm.getRawValue();

  readonly purchaseHistory = signal<PurchaseHistoryItem[]>([
    { id: 1, candleName: 'Vela Aromatica Lavanda', price: 42000, quantity: 2, purchasedAt: '2026-04-20' },
    { id: 2, candleName: 'Vela Clasica Vainilla', price: 31000, quantity: 1, purchasedAt: '2026-04-14' },
    { id: 3, candleName: 'Vela Decorativa Canela', price: 39000, quantity: 3, purchasedAt: '2026-04-02' },
    { id: 4, candleName: 'Vela Romantic Rose', price: 47000, quantity: 1, purchasedAt: '2026-03-27' },
    { id: 5, candleName: 'Vela Essential Balance', price: 51000, quantity: 2, purchasedAt: '2026-03-15' }
  ]);

  readonly fullName = computed(() => {
    const { firstName, lastName } = this.profileForm.getRawValue();
    return `${firstName} ${lastName}`.trim();
  });

  readonly totalSpent = computed(() =>
    this.purchaseHistory().reduce((acc, item) => acc + item.price * item.quantity, 0)
  );

  startEdit(): void {
    this.isEditing.set(true);
  }

  cancelEdit(): void {
    this.profileForm.reset(this.initialProfileSnapshot);
    this.isEditing.set(false);
  }

  saveChanges(): void {
    if (this.profileForm.invalid) {
      this.profileForm.markAllAsTouched();
      return;
    }

    this.initialProfileSnapshot = this.profileForm.getRawValue();
    this.isEditing.set(false);
  }

  applyImageUrl(): void {
    this.profileForm.controls.imageUrl.markAsTouched();
  }
}
