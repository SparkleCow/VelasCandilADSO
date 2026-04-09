import { Component, OnInit, signal } from '@angular/core';
import { Router, RouterModule } from '@angular/router';
import { CommonModule } from '@angular/common';
import { MatToolbarModule } from '@angular/material/toolbar';
import { MatButtonModule } from '@angular/material/button';
import { MatIconModule } from '@angular/material/icon';
import { MatMenuModule } from '@angular/material/menu';
import { MatTooltipModule } from '@angular/material/tooltip';
import { AuthService } from '../../../core/services/auth.service';
import { UserInformation } from '../../models/user-information.models';

@Component({
  selector: 'app-navbar',
  standalone: true,
  imports: [
    CommonModule,
    RouterModule,
    MatToolbarModule,
    MatButtonModule,
    MatIconModule,
    MatMenuModule,
    MatTooltipModule,
  ],
  templateUrl: './navbar.component.html',
  styleUrl: './navbar.component.css',
})
export class NavbarComponent implements OnInit {
  auth: boolean = false;
  username: String = '';
  imageUrl: String = '';

  constructor(
    private authService: AuthService,
    private router: Router,
  ) {}

  ngOnInit(): void {
    this.authService.getUserInformation().subscribe({
      next: (response: UserInformation) => {
        this.auth = true;
        this.username = response.username;
        this.imageUrl = response.imageUrl;
      },
      error: () => {
        this.auth = false;
      },
    });

    const saved = localStorage.getItem('theme');
    const prefersDark = window.matchMedia(
      '(prefers-color-scheme: dark)',
    ).matches;
    const dark = saved ? saved === 'dark' : prefersDark;
    this.isDark.set(dark);
    this.applyTheme(dark);
  }

  login(): void {
    this.router.navigate(['/login']);
  }

  isDark = signal(false);

  toggleTheme(): void {
    const next = !this.isDark();
    this.isDark.set(next);
    localStorage.setItem('theme', next ? 'dark' : 'light');
    this.applyTheme(next);
  }

  private applyTheme(dark: boolean): void {
    const body = document.body;
    if (dark) {
      body.classList.remove('light-theme');
      body.classList.add('dark-theme');
    } else {
      body.classList.remove('dark-theme');
      body.classList.add('light-theme');
    }
  }

  logout(): void {
    this.authService.logout();
    this.auth = false;
    this.router.navigate(['/']);
  }

  register(): void {
    this.router.navigate(['/register']);
  }

  cart(): void {
    this.router.navigate(['/cart']);
  }

  contact(): void {
    this.router.navigate(['/contact']);
  }

  information(): void {
    this.router.navigate(['/information']);
  }

  catalog(): void {
    this.router.navigate(['/catalog']);
  }

  home(): void {
    this.router.navigate(['/']);
  }
}
