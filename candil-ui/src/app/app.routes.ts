import { Routes } from '@angular/router';
import { AUTH_ROUTES } from './features/auth/auth.routes';
import { CANDLE_ROUTES } from './features/candles/candles.routes';

export const routes: Routes = [
  ...AUTH_ROUTES,
  ...CANDLE_ROUTES,
  { path: '', redirectTo: 'home', pathMatch: 'full' },
  { path: '**', redirectTo: 'home' }
];