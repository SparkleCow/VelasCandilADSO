import { Injectable, inject } from '@angular/core';
import { TokenService } from './token.service';

@Injectable({ providedIn: 'root' })
export class UserService {
  private readonly tokenService = inject(TokenService);

  private decodePayload(): Record<string, unknown> | null {
    const token = this.tokenService.get();
    if (!token) return null;
    try {
      const base64 = token.split('.')[1]
        .replace(/-/g, '+')
        .replace(/_/g, '/');
      return JSON.parse(atob(base64));
    } catch {
      return null;
    }
  }

  getUsername(): string {
    return (this.decodePayload()?.['sub'] as string) ?? '';
  }

  getRoles(): string[] {
    const payload = this.decodePayload();
    if (!payload) return [];

    const raw = payload['authorities'] ?? payload['roles'] ?? [];
    if (!Array.isArray(raw)) return [];

    return (raw as unknown[]).map(a => {
      if (typeof a === 'string') return a;
      if (typeof a === 'object' && a && 'authority' in a) {
        return (a as { authority: string }).authority;
      }
      return '';
    }).filter(Boolean);
  }

  isAdmin(): boolean {
    return this.getRoles().some(r => r === 'ADMIN' || r === 'ROLE_ADMIN');
  }

  isLogged(): boolean {
    return this.tokenService.isLogged();
  }
}