import { CommonModule } from '@angular/common';
import { Component, OnInit, inject, signal } from '@angular/core';
import { MatButtonModule } from '@angular/material/button';
import { MatCardModule } from '@angular/material/card';
import { MatIconModule } from '@angular/material/icon';
import { MatProgressSpinnerModule } from '@angular/material/progress-spinner';
import { finalize, switchMap, tap } from 'rxjs';
import { CartService } from '../../core/services/cart.service';
import { ShoppingCartResponseDto } from '../../shared/models/cart.models';

@Component({
  selector: 'app-cart',
  standalone: true,
  imports: [
    CommonModule,
    MatCardModule,
    MatButtonModule,
    MatIconModule,
    MatProgressSpinnerModule,
  ],
  templateUrl: './cart.component.html',
  styleUrl: './cart.component.scss',
})
export class CartComponent implements OnInit {
  private readonly cartService = inject(CartService);

  readonly cart = signal<ShoppingCartResponseDto | null>(null);
  readonly loading = signal(false);
  readonly actionLoading = signal(false);
  readonly errorMessage = signal('');

  ngOnInit(): void {
    this.loadCart();
  }

  loadCart(): void {
    this.loading.set(true);
    this.errorMessage.set('');

    this.cartService
      .getCart()
      .pipe(finalize(() => this.loading.set(false)))
      .subscribe({
        next: (response) => this.cart.set(response),
        error: () => this.errorMessage.set('No se pudo cargar el carrito.'),
      });
  }

  increaseItem(candleId: number): void {
    this.runCartAction(this.cartService.increaseItem(candleId));
  }

  decreaseItem(candleId: number): void {
    this.runCartAction(this.cartService.decreaseItem(candleId));
  }

  removeItem(candleId: number): void {
    this.runCartAction(this.cartService.removeItem(candleId));
  }

  clearCart(): void {
    this.runCartAction(this.cartService.clearCart());
  }

  hasItems(): boolean {
    return (this.cart()?.items.length ?? 0) > 0;
  }

  private runCartAction(action$: ReturnType<CartService['addItem']>): void {
    this.actionLoading.set(true);
    this.errorMessage.set('');

    action$
      .pipe(
        tap((updatedCart) => this.cart.set(updatedCart)),
        switchMap(() => this.cartService.getCart()),
        finalize(() => this.actionLoading.set(false)),
      )
      .subscribe({
        next: (response) => this.cart.set(response),
        error: () => this.errorMessage.set('No se pudo actualizar el carrito.'),
      });
  }
}
