import { Routes } from '@angular/router';
import { authGuard } from '../../core/guards/auth.guard';

export const CANDLE_ROUTES: Routes = [
  {
    path: 'home',
    canActivate: [authGuard],
    loadComponent: () =>
      import('./pages/home/home.component').then(m => m.HomeComponent)
  }
  // candle-list y candle-detail se agregan cuando se creen sus componentes
];