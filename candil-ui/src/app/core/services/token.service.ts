import { Injectable } from '@angular/core';

@Injectable({
  providedIn: 'root'
})
export class TokenService {

  private readonly key = 'jwt';

  get(): string | null {
    return localStorage.getItem(this.key);
  }

  set(token: string): void {
    localStorage.setItem(this.key, token);
  }

  remove(): void {
    localStorage.removeItem(this.key);
  }

  isLogged(): boolean {
    return !!this.get();
  }
}