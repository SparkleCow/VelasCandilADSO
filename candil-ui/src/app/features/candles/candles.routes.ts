import { Routes } from '@angular/router';
import { authGuard } from '../../core/guards/auth.guard';

export const CANDLE_ROUTES: Routes = [
  {
    path: 'home',
    canActivate: [authGuard],
    loadComponent: () =>
      import('./pages/home/home.component').then(m => m.HomeComponent)
  },
  {
    path: 'candles',
    canActivate: [authGuard],
    loadComponent: () =>
      import('./pages/candle-list/candle-list.component').then(m => m.CandleListComponent)
  }
];