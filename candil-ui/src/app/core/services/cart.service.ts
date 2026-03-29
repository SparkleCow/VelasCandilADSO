import { Injectable, inject } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';
import { ShoppingCartResponse } from '../../shared/models/cart.models';

@Injectable({ providedIn: 'root' })
export class CartService {
  private readonly http = inject(HttpClient);
  private readonly base = 'http://localhost:8080/v1/cart';

  getOrCreateCart(): Observable<ShoppingCartResponse> {
    return this.http.get<ShoppingCartResponse>(this.base);
  }

  addItem(candleId: number): Observable<ShoppingCartResponse> {
    return this.http.post<ShoppingCartResponse>(`${this.base}/items/${candleId}`, {});
  }

  increaseItem(candleId: number): Observable<ShoppingCartResponse> {
    return this.http.post<ShoppingCartResponse>(`${this.base}/items/${candleId}/increase`, {});
  }

  decreaseItem(candleId: number): Observable<ShoppingCartResponse> {
    return this.http.delete<ShoppingCartResponse>(`${this.base}/items/${candleId}/decrease`);
  }

  removeItem(candleId: number): Observable<ShoppingCartResponse> {
    return this.http.delete<ShoppingCartResponse>(`${this.base}/items/${candleId}`);
  }

  clearCart(): Observable<ShoppingCartResponse> {
    return this.http.delete<ShoppingCartResponse>(`${this.base}/clear`);
  }
}